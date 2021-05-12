package Program;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import Symulacja.Symulacja;

public class Program {
    public enum Instrukcja {
        LEWO, PRAWO, IDŹ, WĄCHAJ, JEDZ;
    }

    private ArrayList<Instrukcja> instrukcje;

    private boolean losujUsunięcieInst() {
        if (instrukcje.size() <= 0)
            return false;

        Random r = new Random();

        if (r.nextFloat() <= Symulacja.parametry.pr_usunięcia_instr)
            return true;

        return false;
    }

    public Program mutuj() {
        ArrayList<Instrukcja> i = new ArrayList<Instrukcja>();
        Collections.copy(i, this.instrukcje);
        
        if (losujUsunięcieInst()) {
            Random r = new Random();
            i.remove(r.nextInt(i.size()));
        }
        Program p = new Program();
        return p;
    }

    public ArrayList<Instrukcja> dajInstrukcje() {
        return this.instrukcje;
    }

    //ddd

    public Program() {
        this.instrukcje = new ArrayList<Instrukcja>();
    }

    public Program(ArrayList<Instrukcja> inst) {
        this.instrukcje = inst;
    }
}
