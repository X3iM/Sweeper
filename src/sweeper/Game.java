package sweeper;

import javax.swing.*;
import java.awt.*;

public class Game {

    private Flag flag;
    private Bomb bomb;
    private GameStat gameStat;
    private GameStat state;

    public Game(int cols, int rows, int bombs) {
        Ranges.setSize(new Coord(cols, rows));
        bomb = new Bomb(bombs);
        flag = new Flag();
        state = GameStat.PLAYED;
    }

    public GameStat getStat() {
        return gameStat;
    }

    public void start() {
        bomb.start();
        flag.start();
        gameStat = GameStat.PLAYED;
        //bomMap = new Matrix(Box.ZERO);
        //bomMap.set(new Coord(0, 0), Box.BOMB);
        //bomMap.set(new Coord(0, 1), Box.NUM1);
        //bomMap.set(new Coord(1, 1), Box.NUM1);
        //bomMap.set(new Coord(1, 0), Box.NUM1);
    }

    private void checkWinner() {
        if (state == GameStat.PLAYED) {
            if (flag.getCountOfClosedBoxes() == bomb.getCountOfBombs())
                state = GameStat.WINNER;
        }
    }

    public Box getBox(Coord coord) {
        //return Box.values()[(coord.getX() + coord.getY()) % Box.values().length];
        if (flag.get(coord) == Box.OPENED)
            return bomb.get(coord);

        return flag.get(coord);
    }

    public void openCoord(Coord coord) {
        switch (flag.get(coord)) {
            case OPENED: return;
            case FLAGGED: return;
            case CLOSED:
                switch (bomb.get(coord)) {
                    case ZERO: openCoordAround(coord); return;
                    case BOMB: openBombs(coord); return;
                    default: flag.setOpened(coord);
                }
        }
    }

    private void openBombs(Coord coord) {
        state = GameStat.BOMBED;
        flag.setBombed(coord);

//        JFrame jFrame = new JFrame("Sweeper");
//        jFrame.setSize(250, 150);
//        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        jFrame.setTitle("Sweeper");
//        jFrame.setResizable(true);
//        jFrame.setVisible(true);
//        //jFrame.pack();
//        jFrame.setLocationRelativeTo(null);
//        jFrame.getContentPane().add(new My());

        for (Coord coord1 : Ranges.getListOfCoord()) {
            if (bomb.get(coord) == Box.BOMB)
                flag.setOpenedToClosedBomb(coord1);
            else
                flag.setNobombToFlagedBox(coord1);
        }
    }

//    private class My extends JPanel {
//        @Override
//        protected void paintComponent(Graphics g) {
//            super.paintComponent(g);
//            Font font = new Font("", Font.BOLD, 20);
//            g.setFont(font);
//            //g.drawString("Ты пидар", 80, 50);
//        }
//    }

    private void openCoordAround(Coord coord) {
        flag.setOpened(coord);
        for (Coord coord1 : Ranges.getCoordAround(coord)) {
            openCoord(coord1);
        }
    }

    public void pressLeftButton(Coord coord) {
        //flag.setOpened(coord);
        openCoord(coord);
        checkWinner();
    }

    public void pressRightButton(Coord coord) {
        //flag.setFlagged(coord);
        flag.toggleFlag(coord);
    }
}