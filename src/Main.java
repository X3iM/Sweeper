import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import sweeper.Box;
import sweeper.Coord;
import sweeper.Game;
import sweeper.Ranges;

public class Main extends JFrame {

    private Game game;

    private JPanel panel;
    private JLabel label;
    public final int COLS = 9;
    public final int ROWS = 9;
    public final int IMAGE_SIZE = 50;
    public final int COUNT_OF_BOMBS = 10;

    public static void main(String[] args) {
        new Main();
    }

    private Main() {
        game = new Game(COLS, ROWS, COUNT_OF_BOMBS);
        game.start();
        Ranges.setSize(new Coord(COLS, ROWS));
        setImages();
        initLabel();
        initPanel();
        initFrame();
    }

    private void initLabel() {
        label = new JLabel("Hello!", SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sweeper");
        setResizable(false);
        setVisible(true);
        setIconImage(getImage("icon"));
        pack();
        setLocationRelativeTo(null);
    }

    private void initPanel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coord coord : Ranges.getListOfCoord()) {
                    //Coord coord = new Coord(box.ordinal() * IMAGE_SIZE, 0);
                    //g.drawImage(getImage("bomb"), 0, 0, this);
                    g.drawImage((Image)game.getBox(coord).image, coord.getX() * IMAGE_SIZE, coord.getY() * IMAGE_SIZE, this);
                 }
            }
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coord coord = new Coord(x, y);

                if (e.getButton() == MouseEvent.BUTTON1)
                    game.pressLeftButton(coord);
                if (e.getButton() == MouseEvent.BUTTON3)
                    game.pressRightButton(coord);

                panel.repaint();
            }
        });

        panel.setPreferredSize(new Dimension(Ranges.getSize().getX() * IMAGE_SIZE, Ranges.getSize().getY() * IMAGE_SIZE));
        add(panel);
    }

    private void setImages() {
        for (Box box : Box.values()) {
            box.image = getImage(box.name().toLowerCase());
        }
    }

    private Image getImage(String name) {
        String fileName = "img/" + name + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(fileName));
        return icon.getImage();
    }
}