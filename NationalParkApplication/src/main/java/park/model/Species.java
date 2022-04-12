package park.model;

public class Species {
	protected String speciesId;
	protected NationalParks park;
	protected String commonName;
	protected String category;
	protected String family;
	protected String occurence;
	protected String nativeness;


	public Species(String speciesId, NationalParks park, String commonName, String category, String family,
			String occurence, String nativeness) {
		super();
		this.speciesId = speciesId;
		this.park = park;
		this.commonName = commonName;
		this.category = category;
		this.family = family;
		this.occurence = occurence;
		this.nativeness = nativeness;
	}
	
	

	public Species(String speciesId) {
		super();
		this.speciesId = speciesId;
	}



	public String getSpeciesId() {
		return speciesId;
	}



	public void setSpeciesId(String speciesId) {
		this.speciesId = speciesId;
	}



	public NationalParks getPark() {
		return park;
	}



	public void setPark(NationalParks park) {
		this.park = park;
	}



	public String getCommonName() {
		return commonName;
	}



	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}



	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}



	public String getFamily() {
		return family;
	}



	public void setFamily(String family) {
		this.family = family;
	}



	public String getOccurence() {
		return occurence;
	}



	public void setOccurence(String occurence) {
		this.occurence = occurence;
	}



	public String getNativeness() {
		return nativeness;
	}



	public void setNativeness(String nativeness) {
		this.nativeness = nativeness;
	}


}
