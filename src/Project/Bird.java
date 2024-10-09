package Project;

public class Bird extends Creature {
    public Bird(String name) {
        super(name);
    }

    @Override
    public void checkCollision(Creature[][] array, int row, int col) {
        if (isAdjacentTo(array, row, col, Virus.class)) {
            eatAdjacent(array, row, col, Virus.class);
        }
        if (isAdjacentTo(array, row, col, Insect.class)) {
            eatAdjacent(array, row, col, Insect.class);
        }
    }
}
