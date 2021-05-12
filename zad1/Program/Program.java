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

    private static boolean losujPr(float pr) {
        Random r = new Random();
        return r.nextFloat() <= pr;
    }

    private static boolean losujUsunięcieInst() {
        return losujPr(Symulacja.parametry.pr_usunięcia_instr);
    }

    private static boolean losujZmianęInst() {
        return losujPr(Symulacja.parametry.pr_zmiany_instr);
    }

    private static boolean losujDodanieInstrukcji() {
        return losujPr(Symulacja.parametry.pr_dodania_instr);
    }

    private static Instrukcja losujInstrukcję() {
        Random r = new Random();
        return Symulacja.parametry.spis_instr[r.nextInt(Symulacja.parametry.spis_instr.length)];
    }

    private void usuńInstrukcję() {
        this.instrukcje.remove(this.instrukcje.size() - 1);
    }

    private void zmieńInstrukcję() {
        Random r = new Random();
        Instrukcja i = Program.losujInstrukcję();
    
        this.instrukcje.set(r.nextInt(this.instrukcje.size()), i);
    }

    private void dodajInstrukcję() {
        Instrukcja i = Program.losujInstrukcję();

        this.instrukcje.add(i);
    }

    public Program mutuj() {
        ArrayList<Instrukcja> i = new ArrayList<Instrukcja>();
        Collections.copy(i, this.instrukcje);
        
        Program p = new Program(i);

        if (this.instrukcje.size() > 0 && Program.losujUsunięcieInst())
            p.usuńInstrukcję();

        if (this.instrukcje.size() > 0 && Program.losujDodanieInstrukcji())
            p.dodajInstrukcję();

        if (Program.losujZmianęInst())
            p.zmieńInstrukcję();
            
        return p;
    }

    public ArrayList<Instrukcja> dajInstrukcje() {
        return this.instrukcje;
    } 

    public Program() {
        this.instrukcje = new ArrayList<Instrukcja>();
    }

    public Program(ArrayList<Instrukcja> inst) {
        this.instrukcje = inst;
    }
}