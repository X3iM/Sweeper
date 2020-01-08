package sweeper;

import java.util.Random;

class Flag {

    private Matrix flagMap;
    private int totalFlags;
    private int countOfClosedBoxes;

    void start() {
        flagMap = new Matrix(Box.CLOSED);
        countOfClosedBoxes = Ranges.getSize().getX() * Ranges.getSize().getY();
//        flagMap.set(new Coord(4, 4), Box.OPENED);
//        flagMap.set(new Coord(4, 3), Box.OPENED);
//        flagMap.set(new Coord(5, 4), Box.OPENED);
//        flagMap.set(new Coord(3, 4), Box.OPENED);
//        flagMap.set(new Coord(4, 5), Box.OPENED);
    }

    Box get(Coord coord) {
        return flagMap.get(coord);
    }

    public void toggleFlag(Coord coord) {
        if (flagMap.get(coord) == Box.FLAGGED)
            setClosed(coord);
        else if (flagMap.get(coord) == Box.CLOSED)
            setFlagged(coord);
    }

    public void setCountOfClosedBoxes() {
        countOfClosedBoxes = 0;
        for (Coord coord : Ranges.getListOfCoord()) {
            if (flagMap.get(coord) == Box.CLOSED)
                countOfClosedBoxes++;
        }
    }

    public int getTotalFlags() {
        return totalFlags;
    }

    public int getCountOfClosedBoxes() {
        return countOfClosedBoxes;
    }

    public void setClosed(Coord coord) {
        flagMap.set(coord, Box.CLOSED);
    }

    public void setOpened(Coord coord) {
        //if (flagMap.get(coord) != Box.FLAGGED) {
            flagMap.set(coord, Box.OPENED);
            countOfClosedBoxes--;
        //}
    }

    public void setFlagged(Coord coord) {
        flagMap.set(coord, Box.FLAGGED);
    }

    public void setBombed(Coord coord) {
        flagMap.set(coord, Box.BOMBED);
    }

    public void setOpenedToClosedBomb(Coord coord) {
        if (flagMap.get(coord) == Box.CLOSED)
            flagMap.set(coord, Box.OPENED);
    }

    public void setNobombToFlagedBox(Coord coord) {
        if (flagMap.get(coord) == Box.FLAGGED)
            flagMap.set(coord, Box.NOBOMB);
    }
}