package zad1.Field;

import zad1.Evolution.Evolution;

public class FoodField extends Field {
    private boolean food;
    int counter;

    @Override
    public boolean hasFood() {
        return food;
    }

    @Override
    public void update() {
        if (this.counter != 0)
            this.counter--;
        else if (!this.food)
            this.food = true;
    }

    @Override
    public void removeFood() {
        this.food = false;
        this.counter = Evolution.getParameters().food_grow_time;
    }

    public FoodField() {
        super();
        this.counter = 0;
        this.food = true;
    }
}
