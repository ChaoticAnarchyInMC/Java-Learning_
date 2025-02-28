import java.util.Random;

// Base class Instrument
class Instrument {
    private String type;
    private String brand;
    private String color;

    public Instrument(String type, String brand, String color) {
        this.type = type;
        this.brand = brand;
        this.color = color;
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public void play() {
        System.out.println("Playing the " + type);
    }
}

class Guitar extends Instrument {
    private int numberOfStrings;

    public Guitar(String type, String brand, String color, int numberOfStrings) {
        super(type, brand, color);
        this.numberOfStrings = numberOfStrings;
    }

    public int getNumberOfStrings() { return numberOfStrings; }
    public void setNumberOfStrings(int numberOfStrings) { this.numberOfStrings = numberOfStrings; }

    public void strum() {
        System.out.println("Strumming the guitar with " + numberOfStrings + " strings.");
    }
}

class Piano extends Instrument {
    private int numberOfKeys;

    public Piano(String type, String brand, String color, int numberOfKeys) {
        super(type, brand, color);
        this.numberOfKeys = numberOfKeys;
    }

    public int getNumberOfKeys() { return numberOfKeys; }
    public void setNumberOfKeys(int numberOfKeys) { this.numberOfKeys = numberOfKeys; }

    public void pressKeys() {
        System.out.println("Pressing keys on the piano with " + numberOfKeys + " keys.");
    }
}

class MusicStar {
    private String name;
    private int age;
    private Instrument mainInstrument;

    public MusicStar(String name, int age, Instrument mainInstrument) {
        this.name = name;
        this.age = age;
        this.mainInstrument = mainInstrument;
    }

    public void perform(int days) {
        Random random = new Random();
        int totalHoursPlayed = 0;
        System.out.println(name + " is performing with a " + mainInstrument.getColor() + " " + mainInstrument.getType() + " for " + days + " days.");
        for (int day = 1; day <= days; day++) {
            int hoursPlayed = random.nextInt(5) + 1;
            totalHoursPlayed += hoursPlayed;
            System.out.println("Day " + day + ": " + name + " played for " + hoursPlayed + " hours.");
            if (day % 3 == 0) {
                System.out.println("Special performance today!");
            }
            if (day % 5 == 0) {
                System.out.println("Milestone reached!");
            }
        }
        System.out.println("Total hours played: " + totalHoursPlayed);
    }
}



