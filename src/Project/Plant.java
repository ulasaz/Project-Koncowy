package Project;

public class Plant extends Creature {
    public Plant(String name) {
        super(name);
    }

    @Override
    public void checkCollision(Creature[][] array, int row, int col) {
        if (isAdjacentTo(array, row, col, Mushrooms.class)) {
            eatAdjacent(array, row, col, Mushrooms.class);
        }
        if (isAdjacentTo(array, row, col, Bird.class)) {
            eatAdjacent(array, row, col, Bird.class);
        }
    }
}
