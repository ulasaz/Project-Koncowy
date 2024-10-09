package Project;

public class Main {
    public static void main(String[] args) {
        int size = 20; // Rozmiar tablicy
        //stas sosulka
        //stas krutaja sosulka
        int numberOfCreatures = 7; // Liczba istot każdego typu
        (new Simulation(size, numberOfCreatures)).run();
    }
}
