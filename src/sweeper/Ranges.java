package sweeper;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Ranges {

    private static Coord size;
    private static ArrayList<Coord> listOfCoord;
    private static Random random = new Random();

    public static void setSize(Coord size) {
        Ranges.size = size;

        listOfCoord = new ArrayList<Coord>();
        for (int i = 0; i < size.getY(); i++) {
            for (int j = 0; j < size.getX(); j++) {
                listOfCoord.add(new Coord(i, j));
            }
        }
    }

    static Coord getRandomCoord() {
        return new Coord(random.nextInt(size.getX()), random.nextInt(size.getY()));
    }

    static ArrayList<Coord> getCoordAround(Coord coord) {
        Coord around;
        ArrayList<Coord> list = new ArrayList<Coord>();
        for (int x = coord.getX() - 1; x <= coord.getX() + 1; x++) {
            for (int y = coord.getY() - 1; y <= coord.getY() + 1; y++) {
                if (inRange(around = new Coord(x, y))) {
                    if (!around.equals(coord))
                        list.add(around);
                }
            }
        }
        return list;
    }

    static void removeTheSameCoord(ArrayList<Coord> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i+1; j < list.size(); j++) {
                if (list.get(i).equals(list.get(j)))
                    list.remove(list.get(j));
            }
        }
    }

    static boolean inRange(Coord coord) {
        return  coord.getX() >= 0 && coord.getX() < size.getX()
                && coord.getY() >= 0 && coord.getY() < size.getY();
    }

    public static Coord getSize() {
        return size;
    }

    public static ArrayList<Coord> getListOfCoord() {
        return listOfCoord;
    }
}