package sweeper;

public class Bomb {
    private Matrix bombMap;
    private int countOfBombs;

    Bomb(int bombs) {
        this.countOfBombs = bombs;
    }

    void start() {
        bombMap = new Matrix(Box.ZERO);
        for (int i = 0; i < countOfBombs; i++)
            placeBomb();
    }

    Box get(Coord coord) {
        return bombMap.get(coord);
    }

    private void fixBombCount() {
        int maxBombs = Ranges.getSize().getX() * Ranges.getSize().getY() / 3;
        if (countOfBombs > maxBombs)
            countOfBombs = maxBombs;
    }

    private void placeBomb() {
        while (true) {
            Coord coord = Ranges.getRandomCoord();
            if (bombMap.get(coord) == Box.BOMB)
                continue;
            bombMap.set(new Coord(coord.getX(), coord.getY()), Box.BOMB);
            incNumbersAroundBomb(coord);
            break;
        }
    }

    private void incNumbersAroundBomb(Coord coord) {
        for (Coord around : Ranges.getCoordAround(coord)) {
            if (Box.BOMB != bombMap.get(around))
                bombMap.set(around, bombMap.get(around).getNextBox());
        }
    }

    public int getCountOfBombs() {
        return countOfBombs;
    }

    public Matrix getBombMap() {
        return bombMap;
    }
}