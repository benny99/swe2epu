package at.epu.DataAccessLayer.DataProviders.Mock;

import java.util.Date;

import org.apache.log4j.Logger;

import at.epu.DataAccessLayer.DataObjects.BankAccountDataObject;
import at.epu.DataAccessLayer.DataObjects.CategoryDataObject;
import at.epu.DataAccessLayer.DataObjects.ContactDataObject;
import at.epu.DataAccessLayer.DataObjects.CustomerDataObject;
import at.epu.DataAccessLayer.DataObjects.DataObject;
import at.epu.DataAccessLayer.DataObjects.OfferDataObject;
import at.epu.DataAccessLayer.DataObjects.OutBillDataObject;
import at.epu.DataAccessLayer.DataObjects.DataObject.DataObjectState;
import at.epu.DataAccessLayer.DataObjects.DataObjectCollection;
import at.epu.DataAccessLayer.DataObjects.InBillDataObject;

public class MockDataFactory {	
	DataObjectCollection createObjects(String tableName) {
		DataObjectCollection collection = new DataObjectCollection();
		
		if( tableName.equals("Buchungszeilen") ) {
			BankAccountDataObject obj1 = new BankAccountDataObject();
			
			obj1.setAusgangsrechnung_id(1);
			obj1.setEingangsrechnung_id(1);
			obj1.setId( new Integer(2) );
			obj1.setBetrag( new Double(45110.00) );
			obj1.setBuchungsdatum( new Date() );
			obj1.setUmsatzsteuer( new Double(1252.00+1003.00) );
			obj1.setKat_mapping_id(1);
			
			collection.add(obj1);
			
			BankAccountDataObject obj2 = new BankAccountDataObject();
			
			obj2.setAusgangsrechnung_id(1);
			obj2.setEingangsrechnung_id(1);
			obj2.setId( new Integer(2) );
			obj2.setBetrag( new Double(45110.00) );
			obj2.setBuchungsdatum( new Date() );
			obj2.setUmsatzsteuer( new Double(1252.00+1003.00) );
			obj2.setKat_mapping_id(1);
			
			collection.add(obj2);
		} else if( tableName.equals("Kontakte") ) {
			ContactDataObject obj1 = new ContactDataObject();
			
			obj1.setId(new Integer(1));
			obj1.setVorname("Kathy");
			obj1.setNachname("Smith");
			obj1.setEmail("sd.sd@gmx.at");
			obj1.setTelefon("066034212502");
			obj1.setAdresse("Landstr 33/7");
			
			collection.add(obj1);
			
			ContactDataObject obj2 = new ContactDataObject();
			
			obj2.setId(new Integer(2));
			obj2.setVorname("John");
			obj2.setNachname("Doe");
			obj2.setEmail("asd.asd@gmail.com");
			obj2.setTelefon("066034212522");
			obj2.setAdresse("Hansgasse 43/2");
			
			collection.add(obj2);
		} else if( tableName.equals("Kategorien") ) {
			
			CategoryDataObject obj1 = new CategoryDataObject();
			obj1.setName("Einnahme");
			collection.add(obj1);
			
			CategoryDataObject obj2 = new CategoryDataObject();
			obj2.setName("Ausgabe");
			collection.add(obj2);
			
			CategoryDataObject obj3 = new CategoryDataObject();
			obj3.setName("Steuer");
			collection.add(obj3);
			

		} else if( tableName.equals("Kunden") ) {
			CustomerDataObject obj1 = new CustomerDataObject();
			
			obj1.setId(1);
			obj1.setVorname("Ben");
			obj1.setNachname("Hur");
			obj1.setUnternehmen("Mobil Gmbh");
			obj1.setAdresse("Petergasse 354/7");
			obj1.setEmail("sdas.t@gmx.at");
			obj1.setTelefon("06602348192");
			obj1.setAngebot_mapping_id(1);
			
			collection.add(obj1);
			
			/**Object[][] data_ = {
				    {new Integer(2), "Fritz", "DD", "Privat", 
				     "Zeilengasse 143/22", "f.DD@gmail.com", "06761252042", null},
				    {new Integer(3), "Heinz", "MrX", "Haus Gmbh",
				     "Franzgasse 9/12", "x.x@chello.at", "066465352325", "A003"},
				};*/
		} else if( tableName.equals("Eingangsrechnungen") ) {
			InBillDataObject obj1 = new InBillDataObject();
			
			obj1.setId(1);
			obj1.setRechnungskürzel("InBill123");
			obj1.setKontakt_id(1);
			obj1.setBzeile_mapping_id(1);
			obj1.setStatus("bezahlt");
			
			collection.add(obj1);
			
			InBillDataObject obj2 = new InBillDataObject();
			
			obj2.setId(2);
			obj2.setRechnungskürzel("InBill932");
			obj2.setKontakt_id(2);
			obj2.setBzeile_mapping_id(0);
			obj2.setStatus("offen");
			
			collection.add(obj2);
			
		} else if( tableName.equals("Angebote") ) {
			/**Object[][] data_ = {
				    {new Integer(1), "Admin billig A.", "Hur", new Double(20000.00), new Integer(365),
				     new SimpleDateFormat("dd.MM.yyyy").format(new Date()), new Double(0.55)},
				    {new Integer(2), "Gutes Projekt teuer A.", "Hur", new Double(150000.00), new Integer(180),
				     new SimpleDateFormat("dd.MM.yyyy").format(new Date()), new Double(0.45)},
				    {new Integer(3), "A003", "MrX", new Double(50000.00), new Integer(85), 
				     new SimpleDateFormat("dd.MM.yyyy").format(new Date()), new Double(0.80)},
				};*/
		    OfferDataObject obj1 = new OfferDataObject();
		    obj1.setId(1);
		    obj1.setChance(0.25);
		    obj1.setDatum(new java.util.Date());
		    obj1.setDauer(250);
		    obj1.setKunde_id(1);
		    obj1.setSumme(25000);
		    obj1.setTitel("A001-Mega Angebot");
		    
		    collection.add(obj1);
		} else if( tableName.equals("Ausgangsrechnungen") ) {
		    OutBillDataObject obj1 = new OutBillDataObject();
		    
		    obj1.setId(1);
		    obj1.setBzeile_mapping_id(1);
		    obj1.setRzeile_mapping_id(1);
		    obj1.setKunde_id(1);
		    obj1.setRechnungskürzel("R01231");
		    obj1.setStatus("offen");
		    
		    collection.add(obj1);
			
		} else if( tableName.equals("Projekte") ) {
			/**Object [][] data_ = {
					{new Integer(1), "Admin Tool", "Admin billig A.", new Integer(1)},
					{new Integer(2), "Gutes Projekt", "Gutes Projekt teuer A.", null},
					{new Integer(3), "Nicht so gutes Projekt", "A003", new Integer(3)}
			};*/
		} else {
			Logger.getLogger(MockDataFactory.class.getName()).error("[ERROR][MockDataFactory] You requested mock data that is not defined. (tableName = " + tableName + " )");
			
			return null;
		}
		
		for(DataObject obj : collection) {
			obj.setState(DataObjectState.DataObjectStateSynchronized);
		}
		
		return collection;
	}
}
