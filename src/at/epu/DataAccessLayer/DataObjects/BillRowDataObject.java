package at.epu.DataAccessLayer.DataObjects;

public class BillRowDataObject {
	int id_;
	OutBillDataObject ausgrechnung_;
	OfferDataObject angebot_;
	String kommentar_;
	double steuern_;
	double betrag_;
	
	public int getID() {
		return id_;
	}
	
	public void setID(int id) {
		id_ = id;
	}
	
	public OutBillDataObject getAusgRechnung() {
		return ausgrechnung_;
	}
	
	public void setAusgRechnung(OutBillDataObject ausgrechnung) {
		ausgrechnung_ = ausgrechnung;
	}
	
	public OfferDataObject getAngebot() {
		return angebot_;
	}
	
	public void setAngebot(OfferDataObject angebot) {
		angebot_ = angebot;
	}
	
	public String getKommentar() {
		return kommentar_;
	}
	
	public void setKommentar(String kommentar) {
		kommentar_ = kommentar;
	}
	
	public double getSteuern() {
		return steuern_;
	}
	
	public void setSteuern(double steuern) {
		steuern_ = steuern;
	}
	
	public double getBetrag() {
		return betrag_;
	}
	
	public void setBetrag(double betrag) {
		betrag_ = betrag;
	}
}
 