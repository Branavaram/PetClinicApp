import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PetClinicApp {
    // Global array of PetOwner objects
    static PetOwner[] owners = new PetOwner[20];
    static int ownerCount = 0;

    // Slot availability per pet type
    static int[] dogSlots = new int[10];
    static int[] catSlots = new int[10];
    static int[] rabbitSlots = new int[10]; // Question 1 - new pet type

    public static void main(String[] args) {
        System.out.println("Welcome to the Pet Clinic App!");
        runMenu();
    }

    public static void runMenu() {
        Scanner input = new Scanner(System.in);
        int option;
        boolean cont = true;
        while (cont) {
            System.out.println();
            System.out.println("+------------------------------+");
            System.out.println("|         MAIN MENU           |");
            System.out.println("+------------------------------+");
            System.out.println("| 1) Book Appointment          |");
            System.out.println("| 2) Register Pet Owner        |");
            System.out.println("| 3) Search Owner by Pet Name  |");
            System.out.println("| 4) Save Data to File         |");
            System.out.println("| 0) Exit                      |");
            System.out.println("+------------------------------+");
            System.out.print("Select an option: ");
            option = input.nextInt();

            switch (option) {
                case 0:
                    cont = false;
                    break;
                case 1:
                    bookAppointment();
                    break;
                case 2:
                    registerOwner();
                    break;
                case 3:
                    searchOwnerByPetName();
                    break;
                case 4:
                    saveToFile();
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    // ============== To be implemented by students ==============

    public static void bookAppointment() {
        // Task 2 - Add input validation for slot numbers
    }

    public static void registerOwner() {
        // Task 4 - Add code to register PetOwner into array
    }

    public static void searchOwnerByPetName() {
        // Task 5 - Search PetOwner[] by pet name
    }

    public static void saveToFile() {
        try {
            FileWriter fw = new FileWriter("ClinicData.txt");
            // Task 6 - Save owner data in readable format
            fw.close();
            System.out.println("Data saved to file.");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }
}
