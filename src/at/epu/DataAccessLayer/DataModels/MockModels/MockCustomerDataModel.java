package at.epu.DataAccessLayer.DataModels.MockModels;

import at.epu.DataAccessLayer.MockDataProvider;
import at.epu.DataAccessLayer.DataModels.BackofficeTableModel;
import at.epu.DataAccessLayer.DataModels.CustomerDataModel;
/*
 * ID | Vorname | Nachname | Unternehmen | Adresse | Email | Telefon | Angebote(FK/PK) 
 */
public class MockCustomerDataModel extends CustomerDataModel {
	private static final long serialVersionUID = 8742379763002040092L;
	
	public MockCustomerDataModel() {
		Object[][] data_ = {
			    {new Integer(1), "Ben", "Hur", "Mobil Gmbh",
			     "Petergasse 345/7", "sdas.t@gmx.at", "06603412402", "Admin billig A."},
			    {new Integer(2), "Fritz", "DD", "Privat", 
			     "Zeilengasse 143/22", "f.DD@gmail.com", "06761252042", null},
			    {new Integer(3), "Heinz", "MrX", "Haus Gmbh",
			     "Franzgasse 9/12", "x.x@chello.at", "066465352325", "A003"},
			};
		
		setData(data_);
	}
	
	@Override
	public void saveData(BackofficeTableModel model, Object[] data_){
		mockProvider = new MockDataProvider();
		mockProvider.saveData(model, data_);
	}
	
	@Override
	public void updateData(BackofficeTableModel model, Object[] data_, int rowindex) {	
		mockProvider = new MockDataProvider();
		mockProvider.updateData(model, data_, rowindex);
	}
	
	@Override
	public void deleteData(BackofficeTableModel model, int rowindex) {
		mockProvider = new MockDataProvider();
		mockProvider.deleteData(model, rowindex);
	}
}
