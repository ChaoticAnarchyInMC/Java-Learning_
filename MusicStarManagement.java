class MusicStar extends Musician implements Instrumentalist {
    Guitar electricGuitar = new Guitar("Electric Guitar", "Fender", "Sunburst", 6);
    Piano grandPiano = new Piano("Grand Piano", "Steinway", "Black", 88);
    MusicStar johnDoe = new MusicStar("John Doe", 30, electricGuitar);
    MusicStar janeSmith = new MusicStar("Jane Smith", 28, grandPiano);

    johnDoe.perform(10);
    janeSmith.perform(7);

    private Instrument instrument;

    public MusicStar(String name, int age, Instrument instrument) {
        super(name, age);
        this.instrument = instrument;
    }

    @Override
    public void perform() {
        System.out.println(getName() + " is performing.");
        playInstrument();
    }

    // Method Overloading
    public void perform(int days) {
        System.out.println(getName() + " is performing for " + days + " days.");
    }

    @Override
    public void playInstrument() {
        instrument.play();
        if (instrument.getType().equalsIgnoreCase("Guitar")) {
            System.out.println("Square root of strings: " + Math.sqrt(instrument.getNumberOfStrings()));
        } else if (instrument.getType().equalsIgnoreCase("Piano")) {
            System.out.println("Max of keys and 100: " + Math.max(instrument.getNumberOfKeys(), 100));
        }
    }

    @Override
    public void tuneInstrument() {
        System.out.println("Tuning the instrument...");
        instrument.setColor("Tuned Blue");
        System.out.println("Instrument color is now: " + instrument.getColor());
    }
}

// Main Class
public class MusicStarAssignment {
    public static void main(String[] args) {
        try {
            // Redirecting output to a file
            PrintStream fileOut = new PrintStream(new FileOutputStream("music_log.txt"));
            System.setOut(fileOut);

            // Creating Instrument and MusicStar
            Instrument guitar = new Instrument("Guitar", 6, 0, "Red");
            MusicStar star = new MusicStar("John Doe", 28, guitar);

            // Demonstrating polymorphism, overloading, and composition
            star.perform();
            star.perform(5);
            star.tuneInstrument();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}



