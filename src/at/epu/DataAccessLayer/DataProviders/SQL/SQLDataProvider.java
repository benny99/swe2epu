package at.epu.DataAccessLayer.DataProviders.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import at.epu.DataAccessLayer.DataObjects.DataObject;
import at.epu.DataAccessLayer.DataObjects.DataObject.DataObjectState;
import at.epu.DataAccessLayer.DataObjects.DataObjectCollection;
import at.epu.DataAccessLayer.DataObjects.DataObjectFactory;
import at.epu.DataAccessLayer.DataProviders.DataProvider;

public class SQLDataProvider implements DataProvider {
	Connection databaseHandle = null;
	
	public SQLDataProvider(String databaseName) {
		String db_url = "jdbc:hsqldb:file:" + databaseName;
		
		try {
			databaseHandle = DriverManager.getConnection(db_url, "SA", "");	
			
			SQLForeignKeyResolveFactory.setDatabaseHandle(databaseHandle);
		} catch (SQLException e) {
			System.err.println("Could not open connection to database at " + db_url);
			e.printStackTrace();
			System.exit(0);
		} 
	}
	
	@Override
	public int getNextIdForTable(String tableName) {
		String sql = "SELECT MAX(id) FROM " + tableName;
		int retVal = 0;
		
		try {
			Statement stm = databaseHandle.createStatement();
			
			ResultSet rs = stm.executeQuery(sql);
			
			rs.next();
			retVal = rs.getInt(1) + 1;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return retVal;
	}
	
	@Override
	public DataObjectCollection selectAll(String tableName)
			throws DataProviderException {
		String selectString = "SELECT * FROM " + tableName;
		
		PreparedStatement statement = null;
		
		try {
			statement = databaseHandle.prepareStatement(selectString);

			if( statement.execute() ) {
				ResultSet result = statement.getResultSet();
				
				DataObjectCollection retVal = new DataObjectCollection();

				while( result.next() ) {
					retVal.add( DataObjectFactory.createObject(tableName, result) );
				}
				
				return retVal;
			} else {
				return new DataObjectCollection();
			}
		} catch (SQLException e) {
			System.err.println("Failed to get all dataObjects from table: " + tableName);
			throw new DataProviderException(e.getMessage());
		}
	}
	
	@Override
	public void syncData(String tableName, DataObjectCollection collection) throws DataProviderException {
		Iterator<DataObject> iterator = collection.iterator();
		
		while(iterator.hasNext()) {
			DataObject obj = iterator.next();
			try {
				if( obj.getState() == DataObjectState.DataObjectStateNew ) {
					insert(tableName, obj);
				} else if( obj.getState() == DataObjectState.DataObjectStateDeleted ) {
					delete(tableName, obj);
					iterator.remove();
				} else if( obj.getState() == DataObjectState.DataObjectStateModified ) {
					update(tableName, obj);
				}
			} catch(SQLException e) {
				System.err.println("Failed to synchronize with database.");
				throw new DataProviderException(e.getMessage());
			}
			
			obj.setState(DataObjectState.DataObjectStateSynchronized);
		}
	}
	
	public DataObject insertForeignMapping(String tableName, DataObject object) 
		throws DataProviderException, SQLException {
		String insertString = new String();
		String selectString = new String();
		/*
		 * todo: make sure no spaces are in the input !!!
		 */
		if(tableName.equals("Kunden")) {
			int nextID = getNextIdForTable("Angebote_Mapping");
			String[] tmp;
			String delimiter = " ";
			
			tmp = object.getFieldValues().get(7).toString().split(delimiter);
			
			for(int i=0;i<tmp.length;i++) {
				selectString = "SELECT id FROM Angebote WHERE Titel = ?";
				PreparedStatement statement1 = databaseHandle.prepareStatement(selectString);
				statement1.setString(1, tmp[i]);
				if( statement1.execute() ) {
					ResultSet rs = statement1.getResultSet();
					rs.next();
					insertString = "INSERT INTO Angebote_Mapping VALUES(?, ?)";
					PreparedStatement statement2 = databaseHandle.prepareStatement(insertString);
					statement2.setInt(1, nextID);
					statement2.setInt(2, rs.getInt(1));
					statement2.execute();
				} 
			}
			
			/*insertString = "UPDATE Kunden SET angebot_mapping_id = ?";
			PreparedStatement statement3 = databaseHandle.prepareStatement(insertString);
			statement3.setInt(1, nextID);
			statement3.execute();
			*/
			
		} else if(tableName.equals("Angebote")) {
			//NOI
		}
		
		return object;
	}
	
	void insert(String tableName, DataObject object) throws SQLException, DataProviderException {
		StringBuilder builder = new StringBuilder();
		
		object = insertForeignMapping(tableName, object);
		
		builder.append("INSERT INTO " + tableName + " (");
		
		for(String fieldName : object.getFieldNames()) {
			builder.append(fieldName + ",");
		}
		
		builder.replace(builder.length()-1, builder.length(), ")");
		builder.append(" VALUES (");
		
		for(Object obj : object.getFieldValues()) {
			if(obj.getClass() == String.class) {
				obj = new String("'" + obj + "'");
			}
			
			
			builder.append(obj + ",");
		}
		
		builder.replace(builder.length()-1, builder.length(), ")");
		
		String sql = builder.toString();
		
		PreparedStatement statement = databaseHandle.prepareStatement(sql);
		
		statement.execute();
	}
	
	void delete(String tableName, DataObject object) throws SQLException {
		String deleteString = "DELETE FROM " + tableName + " WHERE id = ?";
		
		PreparedStatement statement = databaseHandle.prepareStatement(deleteString);
		
		statement.setInt(1, object.getId());
		
		statement.execute();
	}
	
	void update(String tableName, DataObject object) throws SQLException {		
		StringBuilder builder = new StringBuilder();
		
		builder.append("UPDATE " + tableName + " SET ");
		
		ArrayList<String> fieldNames = object.getFieldNames();
		ArrayList<Object> fieldValues = object.getFieldValues();
		
		for(int i = 0; i < object.getFieldNames().size(); i++) {
			String fieldName = fieldNames.get(i);
			Object value = fieldValues.get(i);
			builder.append(fieldName + "=" + value + ",");
		}
		
		builder.append(" WHERE id = ?");
		
		PreparedStatement statement = databaseHandle.prepareStatement(builder.toString());
		
		statement.setInt(1, object.getId());
		
		int numUpdates = statement.executeUpdate();
		
		if( numUpdates != 1) {
			throw new SQLException("[ERROR] Your update for one object updated more than one value.");
		}
	}

	@Override
	public ArrayList<String> resolveForeignKey(String tableName,
			DataObject object, int fieldIndex) throws DataProviderException {
		return SQLForeignKeyResolveFactory.getForeignKeyResults(tableName, object, fieldIndex);
	}

	@Override
	public int getForeignKeyForName(String tableName, String fieldName,
			String name) throws DataProviderException {
		return SQLForeignKeyResolveFactory.getForeignNameResult(tableName, fieldName, name);
	}
}
