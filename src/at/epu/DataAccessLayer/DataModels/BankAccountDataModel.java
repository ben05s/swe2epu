package at.epu.DataAccessLayer.DataModels;

public abstract class BankAccountDataModel extends BackofficeTableModel {
	private static final long serialVersionUID = -7180241673211578694L;
	
	public BankAccountDataModel() {
		tableName = "Buchungszeilen";
		String[] mappingTableName_ = {"-",					"-",					"kat_mapping"};
		String[] foreignTableName_ = {"Eingangsrechnungen",	"Ausgangsrechnungen",	"Kategorien"};
		String[] foreignTableColumns_={"Eingangsrechnung",	"Ausgangsrechnung",		"Kategorie"};
		String[] foreignKeyColumns_={"eingangsrechnung_id",	"ausgangsrechnung_id",	"kat_mapping_id"};
		String[] foreignKeyFromMappingCol_ = {"-",			"-",					"kategorie_id"};
		String[] desiredColFromForeignKey_ = {"rechnungskürzel","rechnungskürzel",	"name"};
		
		mappingTableName = mappingTableName_;
		foreignTableName = foreignTableName_;
		foreignKeyColumns = foreignKeyColumns_;
		foreignKeyFromMappingCol = foreignKeyFromMappingCol_;
		desiredColFromForeignKey = desiredColFromForeignKey_;
		foreignTableColumns = foreignTableColumns_;
		
		String [] columnNames_ = {"ID",
				"Eingangsrechnung",
				"Ausgangsrechnung",
				"Betrag",
				"Umsatzsteuer",
				"Buchungsdatum",
				"Kategorie"};

		setColumnNames(columnNames_);
		
		String [] addEditColNames_ = {"Eingangsrechnung",
				"Ausgangsrechnung",
				"Betrag",
				"Umsatzsteuer",
				"Buchungsdatum",
				"Kategorie"};
		
		setAddEditColNames(addEditColNames_);
	}
}
