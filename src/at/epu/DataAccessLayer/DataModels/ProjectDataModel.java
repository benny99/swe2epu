package at.epu.DataAccessLayer.DataModels;

public abstract class ProjectDataModel extends BackofficeTableModel {
	private static final long serialVersionUID = -4590807310742861522L;
	
	public ProjectDataModel() {
		String [] columnNames_ = {"ID",
				"Titel",
				"Angebot",
				"AusgRechnung"};
		
		setColumnNames(columnNames_);
	}
}
