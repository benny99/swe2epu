package at.epu.DataAccessLayer.DataModels.MockModels;

import at.epu.DataAccessLayer.MockDataProvider;
import at.epu.DataAccessLayer.DataModels.BackofficeTableModel;
import at.epu.DataAccessLayer.DataModels.OutBillDataModel;
/*
 * ID(PK) | Kunde | Rechnungszeilen(Anz) | Buchungszeilen(Anz) | Status
 */
public class MockOutBillDataModel extends OutBillDataModel {
	private static final long serialVersionUID = -8018265297745567701L;
	
	public MockOutBillDataModel() {
		Object [][] data_ = {
				{new Integer(1), "MrX", new Integer(2), new Integer(1), "bezahlt"},
				{new Integer(2), "Hur", new Integer(2), new Integer(1), "bezahlt"}
		};
		
		setData(data_);
	}
	
	@Override
	public void saveData(BackofficeTableModel model, Object[] data_){
		mockProvider = new MockDataProvider();
		mockProvider.saveData(model, data_);
	}
}
