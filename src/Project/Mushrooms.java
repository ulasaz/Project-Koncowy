package Project;

public class Mushrooms extends Creature {
    public Mushrooms(String name) {
        super(name);
    }

    @Override
    public void checkCollision(Creature[][] array, int row, int col) {
        if (isAdjacentTo(array, row, col, Bird.class)) {
            eatAdjacent(array, row, col, Bird.class);
        }
        if (isAdjacentTo(array, row, col, Insect.class)) {
            eatAdjacent(array, row, col, Insect.class);
        }
    }
}
