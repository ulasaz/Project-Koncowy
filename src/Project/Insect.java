package Project;

public class Insect extends Creature {
    public Insect(String name) {
        super(name);
    }

    @Override
    public void checkCollision(Creature[][] array, int row, int col) {
        if (isAdjacentTo(array, row, col, Virus.class)) {
            eatAdjacent(array, row, col, Virus.class);
        }
        if (isAdjacentTo(array, row, col, Plant.class)) {
            eatAdjacent(array, row, col, Plant.class);
        }
    }
}
