public class MusicStarManagement {
    public static void main(String[] args) {
        Guitar electricGuitar = new Guitar("Electric Guitar", "Fender", "Sunburst", 6);
        Piano grandPiano = new Piano("Grand Piano", "Steinway", "Black", 88);
        MusicStar johnDoe = new MusicStar("John Doe", 30, electricGuitar);
        MusicStar janeSmith = new MusicStar("Jane Smith", 28, grandPiano);

        johnDoe.perform(10);
        janeSmith.perform(7);
    }
}
