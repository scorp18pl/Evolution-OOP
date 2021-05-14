package zad1.Board;

import java.util.ArrayList;

import zad1.Helper.ValueI;
import zad1.Rob.Rob;

public class Stats {
    private int round, rob_count, food_field_count;
    private ValueI rob_program_length, rob_energy, rob_age;

    private ValueI calculateProgramLengthV(ArrayList<Rob> robs) {
        ValueI v = new ValueI(0, 0, 0);

        int length = 0;
        for (Rob rob : robs) {
            int value = rob.getProgramLength();
           
            v.mean = v.mean += (float)value;
            v.min = v.min < value ? v.min : value;
            v.max = v.max > value ? v.max : value;
            
            length++;
        }
        v.mean = v.mean / length;

        return v;
    }

    private ValueI calculateRobEnergyV(ArrayList<Rob> robs) {
        ValueI v = new ValueI(0, 0, 0);

        int length = 0;
        for (Rob rob : robs) {
            int value = rob.getEnergy();
           
            v.mean = v.mean += (float)value;
            v.min = v.min < value ? v.min : value;
            v.max = v.max > value ? v.max : value;
            
            length++;
        }
        v.mean = v.mean / length;

        return v;
    }

    private ValueI calculateRobAgeV(ArrayList<Rob> robs) {
        ValueI v = new ValueI(0, 0, 0);

        int length = 0;
        for (Rob rob : robs) {
            int value = rob.getAge();
           
            v.mean = v.mean += (float)value;
            v.min = v.min < value ? v.min : value;
            v.max = v.max > value ? v.max : value;
            
            length++;
        }
        v.mean = length == 0.f ? 0.f : v.mean / length;

        return v;
    }

    private void updateRobStats(ArrayList<Rob> robs) {
        this.rob_program_length = this.calculateProgramLengthV(robs);
        this.rob_energy = this.calculateRobEnergyV(robs);
        this.rob_age = this.calculateRobAgeV(robs);
    }

    public void update(Board b) {
        this.round++;
        this.rob_count = b.getRobCount();
        this.food_field_count = b.getFoodFieldCount();

        this.updateRobStats(b.getRobs());
    }

    public void print() {
        System.out.println(this.round + ", rob: " + this.rob_count + ", żyw: " + 
                            this.food_field_count + ", prg: " + this.rob_program_length.toString() + 
                            ", energ: " + this.rob_energy.toString() + ", wiek: " + this.rob_age.toString());
    }

    public Stats() {
        this.round = 0;
        this.rob_count = 0;
        this.food_field_count = 0;

        this.rob_program_length = new ValueI(0, 0, 0);
        this.rob_energy = new ValueI(0, 0, 0);
        this.rob_age = new ValueI(0, 0, 0);
    }
}
