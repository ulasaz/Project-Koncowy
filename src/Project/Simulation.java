package Project;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.swing.SwingUtilities;

    public class Simulation {

        private final Creature[][] myArray;
        private final Random random;
        private final int size;
        private final int numberOfCreatures;
        private SimulationWindow window;

        public Simulation(int size, int numberOfCreatures) {
            this.size = size;
            this.numberOfCreatures = numberOfCreatures;
            myArray = new Creature[size][size];
            random = new Random();
        }

    public void run() {
        initializeCreatures();
        window = new SimulationWindow(myArray, size);

        while (countCreatures(myArray)) {
            checkCollisions(myArray);
            updateWindow();

            sleepOneSecond();

            Creature.shiftAllElements(myArray);
            updateWindow();

            sleepOneSecond();
        }

        determineWinner();
    }

    private void initializeCreatures() {
        for (int k = 0; k < numberOfCreatures; k++) {
            int randomRow, randomCol;
            do {
                randomRow = random.nextInt(size);
                randomCol = random.nextInt(size);
            } while (myArray[randomRow][randomCol] != null);
            myArray[randomRow][randomCol] = new Mushrooms("\uD83C\uDF44");

            int randomRow1, randomCol1;
            do {
                randomRow1 = random.nextInt(size);
                randomCol1 = random.nextInt(size);
            } while (myArray[randomRow1][randomCol1] != null);
            myArray[randomRow1][randomCol1] = new Virus("\uD83E\uDDA0");

            int randomRow2, randomCol2;
            do {
                randomRow2 = random.nextInt(size);
                randomCol2 = random.nextInt(size);
            } while (myArray[randomRow2][randomCol2] != null);
            myArray[randomRow2][randomCol2] = new Insect("\uD83E\uDD9F");

            int randomRow3, randomCol3;
            do {
                randomRow3 = random.nextInt(size);
                randomCol3 = random.nextInt(size);
            } while (myArray[randomRow3][randomCol3] != null);
            myArray[randomRow3][randomCol3] = new Plant("\uD83C\uDF3F");

            int randomRow4, randomCol4;
            do {
                randomRow4 = random.nextInt(size);
                randomCol4 = random.nextInt(size);
            } while (myArray[randomRow4][randomCol4] != null);
            myArray[randomRow4][randomCol4] = new Bird("\uD83E\uDD85");
        }
    }

    private boolean countCreatures(Creature[][] array) {
        int countMushrooms = 0;
        int countVirus = 0;
        int countInsect = 0;
        int countPlant = 0;
        int countBird = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] instanceof Mushrooms) {
                    countMushrooms++;
                }
                if (array[i][j] instanceof Virus) {
                    countVirus++;
                }
                if (array[i][j] instanceof Insect) {
                    countInsect++;
                }
                if (array[i][j] instanceof Plant) {
                    countPlant++;
                }
                if (array[i][j] instanceof Bird) {
                    countBird++;
                }
            }
        }
        int sum = countMushrooms + countVirus + countInsect + countPlant + countBird;
        return !(sum == countMushrooms || sum == countVirus || sum == countInsect || sum == countPlant || sum == countBird);
    }

    private void checkCollisions(Creature[][] array) {
        Creature[][] arrayCopy = createArrayCopy(array);
        for (int i = 0; i < arrayCopy.length; i++) {
            for (int j = 0; j < arrayCopy[i].length; j++) {
                if (arrayCopy[i][j] != null) {
                    arrayCopy[i][j].checkCollision(array, i, j);
                }
            }
        }
    }

    private Creature[][] createArrayCopy(Creature[][] array) {
        Creature[][] arrayCopy = new Creature[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) {
            System.arraycopy(array[i], 0, arrayCopy[i], 0, array[i].length);
        }
        return arrayCopy;
    }

    private void updateWindow() {
        SwingUtilities.invokeLater(() -> window.drawGrid());
    }

    private void sleepOneSecond() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void determineWinner() {
        int countMushrooms = 0;
        int countVirus = 0;
        int countInsect = 0;
        int countPlant = 0;
        int countBird = 0;

        for (int i = 0; i < myArray.length; i++) {
            for (int j = 0; j < myArray[i].length; j++) {
                if (myArray[i][j] instanceof Mushrooms) {
                    countMushrooms++;
                }
                if (myArray[i][j] instanceof Virus) {
                    countVirus++;
                }
                if (myArray[i][j] instanceof Insect) {
                    countInsect++;
                }
                if (myArray[i][j] instanceof Plant) {
                    countPlant++;
                }
                if (myArray[i][j] instanceof Bird) {
                    countBird++;
                }
            }
        }

        String winnerName = null;
        String winnerIcon = null;

        if (countMushrooms > 0 && countVirus == 0 && countInsect == 0 && countPlant == 0 && countBird == 0) {
            winnerName = "Mushrooms";
            winnerIcon = "\uD83C\uDF44";
        } else if (countVirus > 0 && countMushrooms == 0 && countInsect == 0 && countPlant == 0 && countBird == 0) {
            winnerName = "Virus";
            winnerIcon = "\uD83E\uDDA0";
        } else if (countInsect > 0 && countMushrooms == 0 && countVirus == 0 && countPlant == 0 && countBird == 0) {
            winnerName = "Insects";
            winnerIcon = "\uD83E\uDD9F";
        } else if (countPlant > 0 && countMushrooms == 0 && countVirus == 0 && countInsect == 0 && countBird == 0) {
            winnerName = "Plants";
            winnerIcon = "\uD83C\uDF3F";
        } else if (countBird > 0 && countMushrooms == 0 && countVirus == 0 && countInsect == 0 && countPlant == 0) {
            winnerName = "Birds";
            winnerIcon = "\uD83E\uDD85";
        } else {
            System.out.println("It's a tie!");
        }

        if (winnerName != null && winnerIcon != null) {
            window.showWinner(winnerName, winnerIcon);
        }
    }
}
