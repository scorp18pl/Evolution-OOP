package zad1.Helper;

public class ValueI {
    public int min, max;
    public float mean;

    public String toString() {
        return (this.min + "/" + String.format("%.02f", mean) + "/" + this.max).replace(',', '.');
    }

    public ValueI(int min, float mean, int max) {
        this.min = min;
        this.mean = mean;
        this.max = max;
    }

    public ValueI() {
        this.min = 0;
        this.mean = 0.f;
        this.max = 0;
    }
}
