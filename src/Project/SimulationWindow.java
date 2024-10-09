package Project;

import javax.swing.*;
import java.awt.*;

public class SimulationWindow extends JFrame {
    private final Creature[][] myArray;
    private final int size;
    private final JPanel gridPanel;

    public SimulationWindow(Creature[][] myArray, int size) {
        this.myArray = myArray;
        this.size = size;
        this.gridPanel = new JPanel(new GridLayout(size, size));

        setTitle("Simulation");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(gridPanel);
        setVisible(true);

        drawGrid();
    }

    public void drawGrid() {
        gridPanel.removeAll();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JLabel label;
                if (myArray[i][j] != null) {
                    label = new JLabel(myArray[i][j].getName(), SwingConstants.CENTER);
                } else {
                    label = new JLabel("\u25A1", SwingConstants.CENTER); // Biały kwadrat jako puste pole
                }
                label.setFont(new Font("Serif", Font.PLAIN, 24));
                gridPanel.add(label);
            }
        }
        gridPanel.revalidate();
        gridPanel.repaint();
    }

    public void showWinner(String winnerName, String winnerIcon) {
        ImageIcon icon = new ImageIcon(new JLabel(winnerIcon).getText());
        JOptionPane.showMessageDialog(this,
                "Winner: " + winnerName + winnerIcon,
                "End of Simulation",
                JOptionPane.INFORMATION_MESSAGE,
                icon);
    }
}
