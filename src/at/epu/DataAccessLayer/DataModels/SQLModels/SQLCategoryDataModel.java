package at.epu.DataAccessLayer.DataModels.SQLModels;

import java.sql.Connection;
import java.sql.SQLException;

import at.epu.DataAccessLayer.SQLQueryProvider;
import at.epu.DataAccessLayer.DataModels.BackofficeTableModel;
import at.epu.DataAccessLayer.DataModels.CategoryDataModel;

public class SQLCategoryDataModel extends CategoryDataModel{
	private static final long serialVersionUID = -2567049208593892316L;
	
	public SQLCategoryDataModel(Connection databaseHandle) {
		sqlProvider = new SQLQueryProvider(databaseHandle);
		Object[][] data_ = sqlProvider.selectAll(this);
		
		setData(data_);
	}
	
	@Override
	public void saveData(BackofficeTableModel model, Object[] data_){
		try {
			sqlProvider.saveData(this, data_);
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		setData(sqlProvider.selectAll(this));
		updateTableData();
		resetChoosenData();
		deleteChooseIndex();
	}
}
