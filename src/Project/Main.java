package Project;

public class Main {
    public static void main(String[] args) {
        //stas
        int size = 20; // Rozmiar tablicy
        int numberOfCreatures = 7; // Liczba istot każdego typu
        (new Simulation(size, numberOfCreatures)).run();
    }
}
