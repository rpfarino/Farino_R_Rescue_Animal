
public class Monkey extends RescueAnimal{
	
	//Instance Variables
	private String species;
	private String height;
	private String tailLength;
	private String bodyLength;
	
	//Constructor
	public Monkey(String name, String species, String gender, String age, String weight, String height, 
				  String tailLength, String bodyLength, String acquisitionDate, String acquisitionCountry, String trainingStatus, 
				  boolean reserved, String inServiceCountry) {
		setName(name);
		setSpecies(species);
	    setGender(gender);
	    setAge(age);
	    setWeight(weight);
		setHeight(height);
		setTailLength(tailLength);
		setBodyLength(bodyLength);
	    setAcquisitionDate(acquisitionDate);
	    setAcquisitionLocation(acquisitionCountry);
	    setTrainingStatus(trainingStatus);
	    setReserved(reserved);
	    setInServiceCountry(inServiceCountry);
	    setAnimalType("Monkey");
	}
	
    // Accessor Method
	
    public String getSpecies() {
        return species;
    }
    
    
    // Mutator Method
    
    public void setSpecies(String monkeySpecies) {
        species = monkeySpecies;
    }
    
	public String getHeight() {
		return height;
	}
	
	public void setHeight(String height) {
		this.height = height;
	}
	
	public String getTailLength() {
		return tailLength;
	}
	
	public void setTailLength(String tailLength) {
		this.tailLength = tailLength;
	}
	
	public String getBodyLength() {
		return bodyLength;
	}
	
	public void setBodyLength(String bodyLength) {
		this.bodyLength = bodyLength;
	}
    
}
