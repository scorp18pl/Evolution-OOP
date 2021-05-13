package zad1.Helper;


public class Vector2i {
    public int x, y;

    public static Vector2i zero() {
        return new Vector2i(0, 0);
    }

    public Vector2i(Vector2i w) {
        this.x = w.x;
        this.y = w.y;
    }

    public Vector2i(int x, int y) {
        this.x = x;
        this.y = y;
    }
}