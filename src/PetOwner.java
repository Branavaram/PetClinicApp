public class PetOwner {
    private String ownerName;
    private String petName;
    private String petType;
    private int slotNumber;

    public PetOwner(String ownerName, String petName, String petType, int slotNumber) {
        this.ownerName = ownerName;
        this.petName = petName;
        this.petType = petType;
        this.slotNumber = slotNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    @Override
    public String toString() {
        return "Owner: " + ownerName + " | Pet: " + petName + " (" + petType + ") | Slot: " + slotNumber;
    }
}
