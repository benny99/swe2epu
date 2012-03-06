package at.epu.DataAccessLayer.DataModels.MockModels;

import at.epu.DataAccessLayer.DataModels.ProjectDataModel;

/*
 * ID(PK) | Angebot_ID(FK) | AusgRechnung_ID(FK/PK)
 */
public class MockProjectDataModel extends ProjectDataModel {
	private static final long serialVersionUID = -4733926290403533192L;
	private String[] columnNames;
	private Object[][] data;
	
	public MockProjectDataModel() {
		
		String [] columnNames_ = {"ID",
				"Angebot_ID",
				"AusgRechnung_ID"};

		Object [][] data_ = {
				{new Integer(1), new Integer(2), new Integer(1)},
				{new Integer(1), new Integer(2), new Integer(2)},
				{new Integer(2), new Integer(1), null},
				{new Integer(3), new Integer(3), new Integer(3)}
		};
		columnNames = columnNames_;
		data = data_;
	}
	public void filterDataModel(String filterString) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public String getColumnName(int column)
	{
		return columnNames[column];
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}
}
