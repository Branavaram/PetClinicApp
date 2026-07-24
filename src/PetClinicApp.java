import java.io.File;
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

    // Shared across all methods - Scanner buffers System.in internally,
    // so separate Scanner instances on the same stream can drop input.
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Pet Clinic App!");
        loadFromFile();
        runMenu();
    }

    public static void loadFromFile() {
        File file = new File("ClinicData.txt");
        if (!file.exists()) {
            return;
        }

        try (Scanner reader = new Scanner(file)) {
            int loaded = 0;
            while (reader.hasNextLine() && ownerCount < owners.length) {
                String line = reader.nextLine().trim();
                if (line.isEmpty()) continue;

                // Expected format: "Owner: X | Pet: Y (Type) | Slot: N"
                String[] parts = line.split(" \\| ");
                if (parts.length != 3) continue;

                try {
                    String ownerName = parts[0].substring("Owner: ".length()).trim();
                    String petFull = parts[1].substring("Pet: ".length()).trim();

                    int openParen = petFull.lastIndexOf(" (");
                    int closeParen = petFull.lastIndexOf(')');
                    if (openParen < 0 || closeParen < 0 || closeParen <= openParen) continue;

                    String petName = petFull.substring(0, openParen);
                    String petType = petFull.substring(openParen + 2, closeParen);
                    int slot = Integer.parseInt(parts[2].substring("Slot: ".length()).trim());

                    owners[ownerCount++] = new PetOwner(ownerName, petName, petType, slot);

                    // Rebuild slot state so the same slot cannot be double-booked
                    int[] slots = getSlotsArrayByName(petType);
                    if (slots != null && slot >= 1 && slot <= slots.length) {
                        slots[slot - 1] = 1;
                    }
                    loaded++;
                } catch (Exception ignore) {
                    // Skip malformed lines instead of crashing on load
                }
            }
            if (loaded > 0) {
                System.out.println("Loaded " + loaded + " previous appointment(s) from ClinicData.txt");
            }
        } catch (IOException e) {
            System.out.println("Could not read ClinicData.txt");
        }
    }

    public static void runMenu() {
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
            System.out.println("| 5) List All Appointments     |");
            System.out.println("| 6) Cancel Appointment        |");
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
                case 5:
                    listAppointments();
                    break;
                case 6:
                    cancelAppointment();
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    // ============== To be implemented by students ==============

    public static void bookAppointment() {
        System.out.println("Enter pet type (1 = Dog, 2 = Cat, 3 = Rabbit):");
        int typeChoice;
        try {
            typeChoice = input.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }

        int[] slots = getSlotsArray(typeChoice);
        if (slots == null) {
            System.out.println("Invalid pet type selected.");
            return;
        }

        System.out.println("Enter slot number (1-" + slots.length + "):");
        int slotNumber;
        try {
            slotNumber = input.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }

        int index = slotNumber - 1;
        if (index < 0 || index >= slots.length) {
            System.out.println("Invalid slot number.");
            return;
        }

        if (slots[index] == 0) {
            slots[index] = 1;
            System.out.println("Appointment booked successfully.");
        } else {
            System.out.println("This slot is already booked.");
        }
    }

    public static void registerOwner() {
        if (ownerCount >= owners.length) {
            System.out.println("The system is at full capacity.");
            return;
        }

        input.nextLine(); // Consume leftover newline from the menu selection
        System.out.println("Enter owner name:");
        String ownerName = input.nextLine();

        System.out.println("Enter pet name:");
        String petName = input.nextLine();

        System.out.println("Enter pet type (1 = Dog, 2 = Cat, 3 = Rabbit):");
        int typeChoice;
        try {
            typeChoice = input.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input for pet type. Registration cancelled.");
            return;
        }

        String petType = getPetTypeName(typeChoice);
        if (petType == null) {
            System.out.println("Invalid pet type selected. Registration cancelled.");
            return;
        }

        System.out.println("Enter booked slot number:");
        int slotNumber;
        try {
            slotNumber = input.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input for slot number. Registration cancelled.");
            return;
        }

        owners[ownerCount] = new PetOwner(ownerName, petName, petType, slotNumber);
        ownerCount++;
        System.out.println("Owner registered successfully.");
    }

    public static void searchOwnerByPetName() {
        input.nextLine(); // Consume leftover newline from the menu selection
        System.out.println("Enter the pet name to search for:");
        String searchName = input.nextLine();

        boolean found = false;
        for (int i = 0; i < ownerCount; i++) {
            if (owners[i].getPetName().equalsIgnoreCase(searchName)) {
                System.out.println(owners[i].toString());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No owner found with a pet named " + searchName + ".");
        }
    }

    public static void listAppointments() {
        if (ownerCount == 0) {
            System.out.println("No appointments booked yet.");
            return;
        }
        System.out.println();
        System.out.println("All booked appointments:");
        System.out.println("--------------------------------");
        for (int i = 0; i < ownerCount; i++) {
            System.out.println((i + 1) + ". " + owners[i].toString());
        }
    }

    public static void saveToFile() {
        try {
            FileWriter fw = new FileWriter("ClinicData.txt");
            for (int i = 0; i < ownerCount; i++) {
                fw.write(owners[i].toString() + System.lineSeparator());
            }
            fw.close();
            System.out.println("Data saved to file.");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }

    public static void cancelAppointment() {
        if (ownerCount == 0) {
            System.out.println("No appointments to cancel.");
            return;
        }

        input.nextLine(); // Consume leftover newline from the menu selection
        System.out.println("Enter the pet name to cancel appointment for:");
        String searchName = input.nextLine();

        for (int i = 0; i < ownerCount; i++) {
            if (owners[i].getPetName().equalsIgnoreCase(searchName)) {
                // Free the previously reserved slot
                int[] slots = getSlotsArrayByName(owners[i].getPetType());
                int slotIdx = owners[i].getSlotNumber() - 1;
                if (slots != null && slotIdx >= 0 && slotIdx < slots.length) {
                    slots[slotIdx] = 0;
                }
                // Shift remaining owners left so the array stays compact
                for (int j = i; j < ownerCount - 1; j++) {
                    owners[j] = owners[j + 1];
                }
                owners[ownerCount - 1] = null;
                ownerCount--;
                System.out.println("Appointment for " + searchName + " cancelled successfully.");
                return;
            }
        }
        System.out.println("No appointment found for pet named " + searchName + ".");
    }

    private static int[] getSlotsArrayByName(String petType) {
        if (petType == null) return null;
        switch (petType) {
            case "Dog":
                return dogSlots;
            case "Cat":
                return catSlots;
            case "Rabbit":
                return rabbitSlots;
            default:
                return null;
        }
    }

    private static int[] getSlotsArray(int typeChoice) {
        switch (typeChoice) {
            case 1:
                return dogSlots;
            case 2:
                return catSlots;
            case 3:
                return rabbitSlots;
            default:
                return null;
        }
    }

    private static String getPetTypeName(int typeChoice) {
        switch (typeChoice) {
            case 1:
                return "Dog";
            case 2:
                return "Cat";
            case 3:
                return "Rabbit";
            default:
                return null;
        }
    }
}
