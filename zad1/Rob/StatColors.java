package zad1.Rob;

import zad1.Helper.Colors;

public class StatColors {
    String energy_color, age_color, offspring_color;

    public void update(Rob rob) {
        int energy = rob.getEnergy();
        
        if (energy <= 5)
            energy_color = Colors.RED;
        else if (energy <= 25)
            energy_color = Colors.YELLOW;
        else
            energy_color = Colors.GREEN;

        int age = rob.getAge();
        if (age <= 10)
            age_color = Colors.WHITE;
        else if (age <= 25)
            age_color = Colors.YELLOW_BOLD;
        else if (age <= 50)
            age_color = Colors.RED_BOLD;
        else if (age <= 100)
            age_color = Colors.PURPLE_BOLD;
        else
            age_color = Colors.PURPLE_BOLD_BRIGHT;

        int offspring = rob.getOffspring();

        if (offspring <= 5)
            offspring_color = Colors.WHITE;
        else if (offspring <= 10)
            offspring_color = Colors.YELLOW_BOLD;
        else if (offspring <= 50)
            offspring_color = Colors.RED_BOLD;
        else if (age <= 100)
            offspring_color = Colors.PURPLE_BOLD;
        else
            offspring_color = Colors.PURPLE_BOLD_BRIGHT;
        
    }

    public String getEnergyColor() {
        return this.energy_color;
    }

    public String getAgeColor() {
        return this.age_color;
    }

    public String getOffspringColor() {
        return this.offspring_color;
    }

    public StatColors(String init_color) {
        this.energy_color = init_color;
        this.age_color = init_color;
        this.offspring_color = init_color;
    }
}
