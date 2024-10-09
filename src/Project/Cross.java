package Project;

public class Cross extends Creature {
    public Cross() {
        super("\u274C"); // Krzyżyk
    }

    @Override
    public void checkCollision(Creature[][] array, int row, int col) {
        // Krzyżyk nie wchodzi w kolizje
    }
}
