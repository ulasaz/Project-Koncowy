package Project;

    public class Virus extends Creature {
        public Virus(String name) {
            super(name);
        }

    @Override
    public void checkCollision(Creature[][] array, int row, int col) {
        if (isAdjacentTo(array, row, col, Mushrooms.class)) {
            eatAdjacent(array, row, col, Mushrooms.class);
        }
        if (isAdjacentTo(array, row, col, Plant.class)) {
            eatAdjacent(array, row, col, Plant.class);
        }
    }
}
