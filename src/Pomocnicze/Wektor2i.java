package Pomocnicze;

public class Wektor2i {
    public int x, y;

    public static Wektor2i zero() {
        return new Wektor2i(0, 0);
    }

    public Wektor2i(Wektor2i w) {
        this.x = w.x;
        this.y = w.y;
    }

    public Wektor2i(int x, int y) {
        this.x = x;
        this.y = y;
    }
}