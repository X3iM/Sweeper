package sweeper;

class Matrix {

    private Box[][] map;

    Matrix(Box defaultBox) {
        map = new Box[Ranges.getSize().getX()][Ranges.getSize().getY()];
        for (Coord coord : Ranges.getListOfCoord()) {
            map[coord.getX()][coord.getY()] = defaultBox;
        }
    }

    Box get(Coord coord) {

        if (Ranges.inRange(coord))
            return map[coord.getX()][coord.getY()];
        return  null;
    }

    void set(Coord coord, Box box) {
        if (Ranges.inRange(coord))
            map[coord.getX()][coord.getY()] = box;
    }
}