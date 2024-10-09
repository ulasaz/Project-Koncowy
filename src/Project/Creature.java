package Project;

import java.util.Random;

public abstract class Creature {
    private final String name;

    public Creature(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void checkCollision(Creature[][] array, int row, int col);

    public static void shiftAllElements(Creature[][] array) {
        Random random = new Random();
        Creature[][] newArray = new Creature[array.length][array[0].length];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] != null) {
                    boolean moved = false;
                    while (!moved) {
                        int direction = random.nextInt(4);
                        int newRow = i, newCol = j;
                        switch (direction) {
                            case 0: newRow = i - 1; break; // Up
                            case 1: newRow = i + 1; break; // Down
                            case 2: newCol = j - 1; break; // Left
                            case 3: newCol = j + 1; break; // Right
                        }
                        if (isValidPosition(array, newRow, newCol) && newArray[newRow][newCol] == null) {
                            newArray[newRow][newCol] = array[i][j];
                            moved = true;
                        } else if (!isValidPosition(array, newRow, newCol) || newArray[newRow][newCol] != null) {
                            newArray[i][j] = array[i][j];
                            moved = true;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                // Usuwanie krzyżyków po jednym kroku
                if (newArray[i][j] instanceof Cross) {
                    newArray[i][j] = null;
                }
                array[i][j] = newArray[i][j];
            }
        }
    }

    public static boolean isValidPosition(Creature[][] array, int row, int col) {
        return row >= 0 && row < array.length && col >= 0 && col < array[0].length;
    }

    protected boolean isAdjacentTo(Creature[][] array, int row, int col, Class<? extends Creature> type) {
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (isValidPosition(array, newRow, newCol) && type.isInstance(array[newRow][newCol])) {
                return true;
            }
        }
        return false;
    }

    protected void eatAdjacent(Creature[][] array, int row, int col, Class<? extends Creature> type) {
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (isValidPosition(array, newRow, newCol) && type.isInstance(array[newRow][newCol])) {
                array[newRow][newCol] = new Cross(); // Ustawienie krzyżyka na miejscu zjedzonego elementu
            }
        }
    }
}
