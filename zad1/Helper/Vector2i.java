package zad1.Helper;


public class Vector2i {
    public int x, y;

    public static Vector2i zero() {
        return new Vector2i(0, 0);
    }

    public String toString() {
        return "<" + this.x + ", " + this.y + ">";
    }

    public Vector2i(Vector2i v) {
        if (v == null)
            System.out.println("null v");

        this.x = v.x;
        this.y = v.y;
    }

    public Vector2i(int x, int y) {
        this.x = x;
        this.y = y;
    }
}