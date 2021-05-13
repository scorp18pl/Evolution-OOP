package Parameters;

import Program.Program;

public class Parameters {
    public static enum Names {
        ROUND_COUNT, ROB_START_COUNT, START_ENERGY, ROUND_PRICE, 
        FOOD_ENERGY, FOOD_GROW_TIME, DUPLICATING_PROB, 
        PARENT_ENERGY_FRACTION, DUPLICATING_LIMIT, INSTR_DEL_PROB, 
        INSTR_ADD_PROB, INSTR_CHG_PROB, INSTR_LOG, FIRST_PROG,
        INVALID_PARAMETER;
    }

    //Symulacja
    public int round_count;
    public int rob_start_count;

    //Energia
    public int start_energy;
    public int round_price;

    //Jedzenie
    public int food_energy;
    public int food_grow_time;

    //Powielanie
    public float duplicating_prob;
    public float parent_energy_fraction;
    public int duplicating_limit;

    //Instrukcje
    public float instr_del_prob;
    public float instr_add_prob;
    public float instr_chg_prob;
    public Program.Instruction[] instr_log;
    public Program first_prog;

    public <T> void setParameter(Parameters.Names parameter, T value) {
        switch (parameter) {
            case FOOD_ENERGY:
                this.food_energy = (int)value;
                break;
            case FOOD_GROW_TIME:
                this.food_grow_time = (int)value;
                break;
            case ROUND_COUNT:
                this.round_count = (int)value;
                break;
            case ROUND_PRICE:
                this.round_price = (int)value;
                break;
            case DUPLICATING_LIMIT:
                this.duplicating_limit = (int)value;
                break;
            case START_ENERGY:
                this.start_energy = (int)value;
                break;
            case ROB_START_COUNT:
                this.rob_start_count = (int)value;
                break;
            case FIRST_PROG:
                this.first_prog = (Program)value;
                break;
            case INSTR_ADD_PROB:
                this.instr_add_prob = (float)value;
                break;
            case DUPLICATING_PROB:
                this.duplicating_prob = (float)value;
                break;
            case INSTR_DEL_PROB:
                this.instr_del_prob = (float)value;
                break;
            case INSTR_CHG_PROB:
                this.instr_chg_prob = (float)value;
                break;
            case INSTR_LOG:
                this.instr_log = (Program.Instruction[])value;
                break;
            case PARENT_ENERGY_FRACTION:
                this.parent_energy_fraction = (float)value;
                break;
            case INVALID_PARAMETER:
                System.out.println("Invalid parameter");
                break;
            default:
                break;
            
        }
    }

    // public static Parametry.Nazwy naNazwę(String ) 

    public Parameters() {
        round_count = 0;
        rob_start_count = 0;

        start_energy = 0;
        round_price = 0;

        food_energy = 0;
        food_grow_time = 0;

        duplicating_prob = 0.0f;
        parent_energy_fraction = 0;
        duplicating_limit = 0;

        instr_del_prob = 0;
        instr_add_prob = 0;
        instr_chg_prob = 0;
    }
}
