// Robert Farino
// Southern New Hampshire University
// IT 145 - Foundation in App Development

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


public class Driver {
    private static ArrayList<Dog> dogList = new ArrayList<Dog>();
    private static ArrayList<Monkey> monkeyList = new ArrayList<Monkey>();
    
    private static ArrayList<String> validTrainingStatusList = new ArrayList<String>();
    private static ArrayList<String> monkeySpecies = new ArrayList<String>();
    	

    
    public static void main(String[] args) {

    	Scanner scanner = new Scanner(System.in);
    	String menuChoice = "";
    	
    	// Initialize lists of valid training statuses and monkey species.
    	initializeTrainingStatuses();
    	initializeMonkeySpecies();
    	
    	// Add test data to dog and monkey lists.
        initializeDogList();
        initializeMonkeyList();
        
        // Add a loop that displays the menu, accepts the users input
        // and takes the appropriate action.
        // For the project submission you must also include input validation
        // and appropriate feedback to the user.
        // Hint: create a Scanner and pass it to the necessary
        // methods 
        // Hint: Menu options 4, 5, and 6 should all connect to the printAnimals() method.
        
        while (true) {
        	
        	// Show the menu
        	displayMenu();
        	
        	// Get menu choice from user
        	try {
	        	menuChoice = scanner.nextLine();
	        	
	        	// Process menu choice
	        	if (menuChoice.equals("1"))
	        		intakeNewDog(scanner);
	        	else if (menuChoice.equals("2"))
	        		intakeNewMonkey(scanner);
	        	else if (menuChoice.equals("3"))
	        		reserveAnimal(scanner);
	        	else if (menuChoice.equals("4"))
	        		printAnimals("dog", scanner);
	        	else if (menuChoice.equals("5"))
	        		printAnimals("monkey", scanner);
	        	else if (menuChoice.equals("6"))
	        		printAnimals("available", scanner);
	        	else if (menuChoice.equals("q"))
	        		break;
	        	else 
	        		throw new Exception("Invalid menu selection. Please try again.");
        	}
        	catch (Exception excpt) {
               	System.out.println(excpt.getMessage());
        	}
    	}
        
        System.out.println();
        System.out.println("Thank you for using the Rescue Animal System");
    }

    
    // This method checks if string is blank.
    public static boolean isStringBlank(String str, String label) {
    	
    	// Return false if the string is null or blank.
    	if (str == null || str.isBlank()) {
    		System.out.println("\n\n" + label + " cannot be blank.\n\n");
    		return true;
    	}

    	return false;
    }
    
    // This method checks if string is alphabet only.
    public static boolean isStringAlphabetOnly(String str, String label) {
    	
    	// Verify that the string is not blank, and then
    	// verify that it only contains letters and up to 
    	// one space between each word.
    	if (isStringBlank(str, label)) {
    		return false;
    	}
    	else if(str.matches("^[a-zA-Z]+( [a-zA-Z]+)*$") == false) {
    		System.out.println("\n\n" + label + " must be alphabetic.\n\n");
    		return false;
    	}
    	
    	return true;
    }
    
    // This method checks if string is a number greater than 0.
    public static boolean isStringNumberGreaterThan0(String str, String label) {
    	
    	try
    	{
        	// Verify that the string is not blank, and then
        	// verify that it is integer greater than 0.
        	if (isStringBlank(str, label)) {
        		return false;
        	}
	    	else if (Integer.parseInt(str) <= 0) {
	    		throw new Exception();
	    	}
    	}
    	catch (Exception ex) {
    		System.out.println("\n\n" + label + " must be an integer greater than 0.\n\n");
    		return false;
    	}
    	
    	return true;
    }
    
    // This method checks if string is a valid age (greater than or equal to 0).
    public static boolean isStringValidAge(String str, String label) {
    	
    	try
    	{
        	// Verify that the string is not blank, and then
        	// verify that it is integer greater than or equal to 0.
        	if (isStringBlank(str, label)) {
        		return false;
        	}
	    	else if (Integer.parseInt(str) < 0) {
	    		throw new Exception();
	    	}
    	}
    	catch (Exception ex) {
    		System.out.println("\n\n" + label + " must be an integer greater than or equal to 0.\n\n");
    		return false;
    	}
    	
    	return true;
    }
    
    // This method checks if string is a valid date.
    public static boolean isStringValidDate(String str, String label) {
    	
    	try
    	{
        	// Verify that the string is not blank.
        	if (isStringBlank(str, label)) {
        		return false;
        	}
	    	
        	// Verify that the string is a valid date between 1980 and today's date
        	// in the format MM/dd/yyyy.
	    	LocalDate date = LocalDate.parse(str, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
	    	if(date.getYear() < 1980 ||  LocalDate.now().compareTo(date) < 0) {
	    		System.out.println("\n\n" + label + " must be between the year 1980 and the current date.\n\n");
	    		return false;
	    	}
    	}
    	catch (Exception ex) {
    		System.out.println("\n\n" + label + " must be a valid date in the format mm/dd/yyyy.\n\n");
    		return false;
    	}
    	
    	return true;
    }
    
    // This method checks if string is a valid boolean value.
    public static boolean isStringBooleanValue(String str, String label) {
    	
    	// Verify that the string is not blank, and then
    	// verify that it equals "true" or "false", case insensitive.
    	if (isStringBlank(str, label)) {
    		return false;
    	}
    	else if(str.equalsIgnoreCase("True") == false && str.equalsIgnoreCase("False") == false) {
    		System.out.println("\n\n" + label + " must be \"true\" or \"false\".\n\n");
    		return false;
    	}
    	
    	return true;
    }
    
    // This method checks if string is in the provided ArrayList, case insensitive.
    public static boolean isStringInListCaseInsensitive(String str, String label, ArrayList<String> stringList) {
    	
    	boolean valueFound = false;
    	
    	// Verify that the string is not blank, and then
    	// verify that it is in the provided list, case insensitive.
    	if (isStringBlank(str, label)) {
    		return false;
    	}
    	else {
		    for (String s : stringList) {
		    	if(str.equalsIgnoreCase(s)) {
		    		valueFound = true;
		        }
	        }
		    
	    	// Print error if the value was not found.
	    	if (valueFound == false)
	    		System.out.println("\n\nPlease enter a valid " + label + ".\n\n");
    	}
	    
	    return valueFound;
    }
    
    
    // This method prints the menu options
    public static void displayMenu() {
        System.out.println("\n\n");
        System.out.println("\t\t\t\tRescue Animal System Menu");
        System.out.println("[1] Intake a new dog");
        System.out.println("[2] Intake a new monkey");
        System.out.println("[3] Reserve an animal");
        System.out.println("[4] Print a list of all dogs");
        System.out.println("[5] Print a list of all monkeys");
        System.out.println("[6] Print a list of all animals that are not reserved");
        System.out.println("[q] Quit application");
        System.out.println();
        System.out.println("Enter a menu selection");
    }

    // This method sets up the list of valid training statuses.
    public static void initializeTrainingStatuses()
    {
    	validTrainingStatusList.add("intake");
    	validTrainingStatusList.add("Phase I");
    	validTrainingStatusList.add("Phase II");
    	validTrainingStatusList.add("Phase III");
    	validTrainingStatusList.add("Phase IV");
    	validTrainingStatusList.add("Phase V");
    	validTrainingStatusList.add("in service");
    }
    
    // This method sets up the list of monkey species.
    public static void initializeMonkeySpecies()
    {
    	monkeySpecies.add("Capuchin");
    	monkeySpecies.add("Guenon");
    	monkeySpecies.add("Macaque");
    	monkeySpecies.add("Marmoset");
    	monkeySpecies.add("Squirrel monkey");
    	monkeySpecies.add("Tamarin");
    }
    
    // Adds dogs to a list for testing
    public static void initializeDogList() {
        Dog dog1 = new Dog("Spot", "German Shepherd", "male", "1", "25.6", "05-12-2019", "United States", "intake", false, "United States");
        Dog dog2 = new Dog("Rex", "Great Dane", "male", "3", "35.2", "02-03-2020", "United States", "Phase I", false, "United States");
        Dog dog3 = new Dog("Bella", "Chihuahua", "female", "4", "25.6", "12-12-2019", "Canada", "in service", true, "Canada");

        dogList.add(dog1);
        dogList.add(dog2);
        dogList.add(dog3);
    }


    // Adds monkeys to a list for testing
    //Optional for testing
    public static void initializeMonkeyList() {
    	Monkey monkey1 = new Monkey("Bingo", "Marmoset", "male", "9", "0.56", "0.7", "1.4", "2.6", "07-01-2020", "Canada", "Phase I", false, "Canada");
    	Monkey monkey2 = new Monkey("Fiona", "Squirrel Monkey", "female", "12", "1.7", "1.1", "1.4", "2.7", "09-10-2023", "United States", "intake", false, "United States");
    	Monkey monkey3 = new Monkey("Nkima", "Tamarin", "female", "10", "1.4", "0.8", "1.3", "2.4", "04-15-2018", "United States", "in service", false, "United States");
    	Monkey monkey4 = new Monkey("Jack", "Capuchin", "male", "4", "6.7", "1.5", "1.5", "2.5", "04-15-2018", "United States", "in service", false, "United States");
    	
    	monkeyList.add(monkey1);
    	monkeyList.add(monkey2);
    	monkeyList.add(monkey3);
    	monkeyList.add(monkey4);
    }


    // Complete the intakeNewDog method
    // The input validation to check that the dog is not already in the list
    // is done for you
    public static void intakeNewDog(Scanner scanner) {
    	
    	boolean valueIsValid;
    	String name;
    	String breed;
    	String gender;
    	String age;
    	String weight;
    	String acquisitionDate;
    	String acquisitionCountry;
    	String trainingStatus;
    	String reservedStr;
    	String inServiceCountry;
    	boolean reserved;
    	
    	
    	// Enter Name and validate that it is alphabetic and not already in the system.
    	do {
    		System.out.println("What is the dog's name?");
    		name = scanner.nextLine();
    	} while(isStringAlphabetOnly(name, "Name") == false);
    	
        for(Dog dog: dogList) {
            if(dog.getName().equalsIgnoreCase(name)) {
                System.out.println("\n\nThis dog is already in our system.\n\n");
                return; //returns to menu
            }
        }
    
        // Enter Breed and validate that it is alphabetic.
    	do {
    		System.out.println("What is the dog's breed?");
    		breed = scanner.nextLine();
    	} while(isStringAlphabetOnly(breed, "Breed") == false);
    
	    // Enter Gender and validate that it is set to "male" or "female".
    	do {
		    System.out.println("What is the dog's gender?");
		    gender = scanner.nextLine();
		    valueIsValid = gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female");
		    
		    if (valueIsValid == false) {
		    	System.out.println("\n\nThe gender must be \"male\" or \"female\".\n\n");
		    }
    	} while(valueIsValid == false);
    
        // Enter Age and validate that it is an integer greater than or equal to 0.
    	do {
	        System.out.println("What is the dog's age?");
	        age = scanner.nextLine();
    	} while(isStringValidAge(age, "Age") == false);
        
        // Enter Weight and validate that it is a number greater than 0.
    	do {
	        System.out.println("What is the dog's weight?");
	        weight = scanner.nextLine();
    	} while(isStringNumberGreaterThan0(weight, "Weight") == false);
        
        // Enter Acquisition Date and validate that it is a valid date between 1980 and today.
    	do {
    		System.out.println("What is the dog's acquisition date?");
        	acquisitionDate = scanner.nextLine();
    	} while(isStringValidDate(acquisitionDate, "Acquisition date") == false);
        
        // Enter Acquisition Country and validate that it is alphabetic.
    	do {
    		System.out.println("What is the dog's acquisition country?");
    		acquisitionCountry = scanner.nextLine();
    	} while(isStringAlphabetOnly(acquisitionCountry, "Acquisition country") == false);
        
        // Enter Training Status and verify this it is an available training status.
    	do {
	        System.out.println("What is the dog's training status (intake, Phase I");
	        System.out.println("Phase II, Phase III, Phase IV, Phase V, or in service)?");
	        trainingStatus = scanner.nextLine();
    	} while(isStringInListCaseInsensitive(trainingStatus, "Training Status", validTrainingStatusList) == false);
        
        // Enter Reserved Status and validate that it is "true" or "false".
        do {
        	System.out.println("Is the dog reserved (true or false)?");
        	reservedStr = scanner.nextLine();
        }
        while(isStringBooleanValue(reservedStr, "Reserved status") == false);
        reserved = reservedStr.equalsIgnoreCase("true");
        
        // Enter Service Country and validate that it is alphabetic.
    	do {
	        System.out.println("What is the dog's in service country?");
	        inServiceCountry = scanner.nextLine();
    	} while(isStringAlphabetOnly(inServiceCountry, "In Service Country") == false);
        
    	
        // Add the code to instantiate a new dog and add it to the appropriate list
        Dog dog = new Dog(name, breed, gender, age, weight, acquisitionDate, acquisitionCountry, 
        		          trainingStatus, reserved, inServiceCountry);
        dogList.add(dog);
    }


    // Complete intakeNewMonkey
	// Instantiate and add the new monkey to the appropriate list
    // For the project submission you must also  validate the input
	// to make sure the monkey doesn't already exist and the species type is allowed
    public static void intakeNewMonkey(Scanner scanner) {
    	
    	boolean valueIsValid;
    	String name;
    	String species;
    	String gender;
    	String age;
    	String weight;
    	String height;
    	String tailLength;
    	String bodyLength;
    	String acquisitionDate;
    	String acquisitionCountry;
    	String trainingStatus;
    	String reservedStr;
    	String inServiceCountry;
    	boolean reserved;
    	

    	// Enter Name and validate that it is alphabetic.
    	do {
	        System.out.println("What is the monkey's name?");
	        name = scanner.nextLine();
    	} while(isStringAlphabetOnly(name, "Name") == false);

        // Check if monkey is already in the system.
        for(Monkey monkey: monkeyList) {
        	if(monkey.getName().equalsIgnoreCase(name)){
        		System.out.println("\n\nThis monkey is already in our system.\n\n");
        		return;
        	}
        }
        
        // Enter Species and verify that it is a registered monkey species. 
    	do {
	        System.out.println("What is the monkey's species (Capuchin, Guenon");
	        System.out.println("Macaque, Marmoset, Squirrel monkey, or Tamarin)?");
	        species = scanner.nextLine();
    	} while(isStringInListCaseInsensitive(species, "Species", monkeySpecies) == false);
        
        // Enter Gender and validate that it is set to "true" or "false".
    	do {
	        System.out.println("What is the monkey's gender?");
	        gender = scanner.nextLine();
		    valueIsValid = gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female");
		    
		    if (valueIsValid == false) {
		    	System.out.println("\n\nThe gender must be \"male\" or \"female\".\n\n");
		    }
		} while(valueIsValid == false);
        
        // Enter Age and validate that it is an integer greater than or equal to 0.
    	do {
	        System.out.println("What is the monkey's age?");
	        age = scanner.nextLine();
    	} while(isStringValidAge(age, "Age") == false);
        
        // Enter Weight and validate that it is a number greater than 0.
    	do {
    		System.out.println("What is the monkey's weight?");
        	weight = scanner.nextLine();
    	} while(isStringNumberGreaterThan0(weight, "Weight") == false);
        
        // Enter Height and validate that it is a number greater than 0.
    	do {
	        System.out.println("What is the monkey's height?");
	        height = scanner.nextLine();
    	} while(isStringNumberGreaterThan0(height, "Height") == false);
        
        // Enter Tail Length and validate that it is a number greater than 0.
    	do {
	        System.out.println("What is the monkey's tail length?");
	        tailLength = scanner.nextLine();
    	} while(isStringNumberGreaterThan0(tailLength, "Tail length") == false);
        
        // Enter Body Length and validate that it is a number greater than 0.
    	do {
	        System.out.println("What is the monkey's body length?");
	        bodyLength = scanner.nextLine();
    	} while(isStringNumberGreaterThan0(bodyLength, "Body length") == false);
        
        // Enter Acquisition Date and validate that it is a valid date between 1980 and today.
    	do {
	        System.out.println("What is the monkey's acquisition date?");
	        acquisitionDate = scanner.nextLine();
    	} while(isStringValidDate(acquisitionDate, "Acquisition date") == false);
        
        // Enter Acquisition Country and validate that it is alphabetic.
    	do {
    		System.out.println("What is the monkey's acquisition country?");
        	acquisitionCountry = scanner.nextLine();
    	} while(isStringAlphabetOnly(acquisitionCountry, "Acquisition country") == false);
        
        // Enter Training Status and verify this it is an available training status.
    	do {
	        System.out.println("What is the monkey's training status (intake, Phase I");
	        System.out.println("Phase II, Phase III, Phase IV, Phase V, or in service)?");
	        trainingStatus = scanner.nextLine();
    	} while(isStringInListCaseInsensitive(trainingStatus, "Training Status", validTrainingStatusList) == false);
        
        // Enter Reserved Status and validate that it is "true" or "false".
        do {
        	System.out.println("Is the monkey reserved (true or false)?");
        	reservedStr = scanner.nextLine();
        }
        while(isStringBooleanValue(reservedStr, "Reserved status") == false);
        reserved = reservedStr.equalsIgnoreCase("true");
        
        // Enter Service Country and validate that it is alphabetic.
    	do {
	        System.out.println("What is the monkey's in service country?");
	        inServiceCountry = scanner.nextLine();
    	} while(isStringAlphabetOnly(inServiceCountry, "In Service Country") == false);
        
        // Add the code to instantiate a new monkey and add it to the appropriate list
        Monkey monkey = new Monkey(name, species, gender, age, weight, height, tailLength, bodyLength, 
        		                   acquisitionDate, acquisitionCountry, trainingStatus, reserved, inServiceCountry);
        monkeyList.add(monkey);
    }	

    // Complete reserveAnimal
    // You will need to find the animal by animal type and in service country
    public static void reserveAnimal(Scanner scanner) {
    	boolean valueIsValid;
    	boolean animalFound = false;
    	String animalType;
    	String inServiceCountry;
    	
    	// Enter Animal Type
    	do {
	        System.out.println("What is the animal type (dog or monkey)?");
	        animalType = scanner.nextLine();
	        
	        valueIsValid = animalType.equalsIgnoreCase("dog") || animalType.equalsIgnoreCase("monkey");
	        if(valueIsValid == false) {
	        	System.out.println("Please enter a valid animal type.");
	        }
    	} while(valueIsValid == false);
        
    	// Enter Service Country
    	do {
    		System.out.println("What is the animal's in service country?");
    		inServiceCountry = scanner.nextLine();
    	} while(isStringAlphabetOnly(inServiceCountry, "In Service Country") == false);
        
        
        if (animalType.equalsIgnoreCase("dog")) {
        	
        	// Set Reserved flag for the first dog with the matching the in-service country.
        	for (Dog d : dogList) {
        		if (d.getReserved() == false && 
        				d.getTrainingStatus().equalsIgnoreCase("in service") && 
        				d.getInServiceCountry().equalsIgnoreCase(inServiceCountry)) {
        			
        			d.setReserved(true);
        			animalFound = true;
        			break;
        		}
        	}
        }
        else if (animalType.equalsIgnoreCase("monkey")) {
        	 
        	// Set Reserved flag for the first monkey with the matching the in-service country.
        	for (Monkey m : monkeyList) {
        		if (m.getReserved() == false && 
        				m.getTrainingStatus().equalsIgnoreCase("in service") && 
        				m.getInServiceCountry().equalsIgnoreCase(inServiceCountry)) {
        			
        			m.setReserved(true);
        			animalFound = true;
        			break;
        		}
        	}
        }
        
        if (animalFound == false)
        	System.out.println("\n\nNo matches found.");
    }

    // Complete printAnimals
    // Include the animal name, status, acquisition country and if the animal is reserved.
    // Remember that this method connects to three different menu items.
    // The printAnimals() method has three different outputs
    // based on the listType parameter
    // dog - prints the list of dogs
    // monkey - prints the list of monkeys
    // available - prints a combined list of all animals that are
    // fully trained ("in service") but not reserved 
    // Remember that you only have to fully implement ONE of these lists. 
    // The other lists can have a print statement saying "This option needs to be implemented".
    // To score "exemplary" you must correctly implement the "available" list.
    public static void printAnimals(String printType, Scanner scanner) {

    	ArrayList<RescueAnimal> animals = new ArrayList<RescueAnimal>();
    	
    	// Setup list of animals to print.
    	if (printType == "dog") {
    		for (Dog d : dogList)
    			animals.add(d);
    	} else if (printType == "monkey") {
    		for (Monkey m : monkeyList)
    			animals.add(m);
    	} else if( printType == "available") {
    		for (Dog d : dogList)
    			if (d.getTrainingStatus().equalsIgnoreCase("in service") && d.getReserved() == false)
    				animals.add(d);
    		for (Monkey m : monkeyList)
    			if (m.getTrainingStatus().equalsIgnoreCase("in service") && m.getReserved() == false)
    				animals.add(m);
    	}
    	else {
    		return;
    	}
    	
    	// Print table header.
    	System.out.println("Species   Name            Status        Aquisition Country   In Service Country   Reserved");
    	System.out.println("------------------------------------------------------------------------------------------");
    	
    	// Print animal info.
    	for (RescueAnimal a : animals) {
    		System.out.printf("%-10s%-16s%-14s%-21s%-21s%-8s\n", a.getAnimalType(), a.getName(), 
    				           a.getTrainingStatus(), a.getAcquisitionLocation(),
    				           a.getInServiceCountry(), (a.getReserved() ? "Yes" : "No"));
    	}
    	
    	System.out.println();
    	System.out.println("Press Enter to Continue...");
    	scanner.nextLine();
    }
}

