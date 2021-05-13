package zad1.Parameters;

import zad1.Program.Program;

public class Parameters {
    public static enum Names {
        ROUND_COUNT, INIT_ROB_COUNT, INIT_ENERGY, ROUND_PRICE, FOOD_ENERGY,
        FOOD_GROW_TIME, DUPLICATING_PROB, PARENT_ENERGY_FRACTION,
        DUPLICATING_LIMIT, INSTR_DEL_PROB, INSTR_ADD_PROB,
        INSTR_CHG_PROB, INSTR_LOG, INIT_PROG, PRINT_PERIOD,
        INVALID_PARAMETER;
    }

    public final static int count = 15;

    //Symulacja
    public int round_count;
    public int init_rob_count;
    public int print_period;

    //Energia
    public int init_energy;
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
    public Program instr_log;
    public Program init_prog;

    private static boolean contains(Parameters.Names[] array, Parameters.Names value) {
        for (Parameters.Names name : array) {
            if (name == value)
                return true;
        }

        return false;
    }

    public static boolean isIntegerValue(Names value) {
        Names[] ints = {Names.DUPLICATING_LIMIT, Names.FOOD_ENERGY,
                        Names.FOOD_GROW_TIME, Names.INIT_ENERGY,
                        Names.INIT_ROB_COUNT, Names.PRINT_PERIOD,
                        Names.ROUND_COUNT, Names.ROUND_PRICE};

        return contains(ints, value);
    }

    public static boolean isFloatValue(Names value) {
        Names[] flts = {Names.DUPLICATING_PROB, Names.INSTR_ADD_PROB,
                        Names.INSTR_CHG_PROB, Names.INSTR_DEL_PROB,
                        Names.PARENT_ENERGY_FRACTION};
        
        return contains(flts, value);
    }

    public Parameters copy() {
        return new Parameters(this);
    }

    public boolean initialized() {
        boolean initialized = true;
        initialized = initialized && this.round_count != -1;
        initialized = initialized && this.init_rob_count != -1; 
        initialized = initialized && this.print_period != -1;

        initialized = initialized && this.init_energy != -1;
        initialized = initialized && this.round_price != -1;

        initialized = initialized && this.food_energy != -1;
        initialized = initialized && this.food_grow_time != -1;

        initialized = initialized && this.duplicating_prob != -1.0f;
        initialized = initialized && this.parent_energy_fraction != -1.0f;
        initialized = initialized && this.duplicating_limit != -1;
        
        initialized = initialized && this.instr_del_prob != -1.0f;
        initialized = initialized && this.instr_add_prob != -1.0f;
        initialized = initialized && this.instr_chg_prob != -1.0f;

        return initialized;
    }

    public <T> void setParameter(Names parameter, T value) {
        switch (parameter) {
            case DUPLICATING_LIMIT:
                this.duplicating_limit = (int)value;
                break;
            case DUPLICATING_PROB:
                this.duplicating_prob = (float)value;
                break;
            case FOOD_ENERGY:
                this.food_energy = (int)value;
                break;
            case FOOD_GROW_TIME:
                this.food_grow_time = (int)value;
                break;
            case INIT_ENERGY:
                this.init_energy = (int)value;
                break;
            case INIT_PROG:
                this.init_prog = (Program)value;
                break;
            case INIT_ROB_COUNT:
                this.init_rob_count = (int)value;
                break;
            case INSTR_ADD_PROB:
                this.instr_add_prob = (float)value;
                break;
            case INSTR_CHG_PROB:
                this.instr_chg_prob = (float)value;
                break;
            case INSTR_DEL_PROB:
                this.instr_del_prob = (float)value;
                break;
            case INSTR_LOG:
                this.instr_log = (Program)value;
                break;
            case PARENT_ENERGY_FRACTION:
                this.parent_energy_fraction = (float)value;
                break;
            case PRINT_PERIOD:
                this.print_period = (int)value;
                break;
            case ROUND_COUNT:
                this.round_count = (int)value;
                break;
            case ROUND_PRICE:
                this.round_price = (int)value;
                break;
            default:
                break;

        }
    }

    public Parameters() {
        this.round_count = -1;
        this.init_rob_count = -1;
        this.print_period = -1;

        this.init_energy = -1;
        this.round_price = -1;

        this.food_energy = -1;
        this.food_grow_time = -1;

        this.duplicating_prob = -1.0f;
        this.parent_energy_fraction = -1.0f;
        this.duplicating_limit = -1;

        this.instr_del_prob = -1.0f;
        this.instr_add_prob = -1.0f;
        this.instr_chg_prob = -1.0f;
    }

    public Parameters(Parameters p) {
        this.round_count = p.round_count;
        this.init_rob_count = p.init_rob_count;
        this.print_period = p.print_period;

        this.init_energy = p.init_energy;
        this.round_price = p.round_price;

        this.food_energy = p.food_energy;
        this.food_grow_time = p.food_grow_time;

        this.duplicating_prob = p.duplicating_prob;
        this.parent_energy_fraction = p.parent_energy_fraction;
        this.duplicating_limit = p.duplicating_limit;

        this.instr_del_prob = p.instr_del_prob;
        this.instr_add_prob = p.instr_add_prob;
        this.instr_chg_prob = p.instr_chg_prob;
    }
}
