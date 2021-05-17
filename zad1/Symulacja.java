package zad1;

import zad1.Evolution.Evolution;

public class Symulacja {
    //Wersja z kolorami. Nie działa w konsoli!!!!!!
    static final boolean color = false;
    public static void main(String[] args) throws Exception {
        Evolution e = new Evolution(args, Symulacja.color);
        e.start();
    }
}
