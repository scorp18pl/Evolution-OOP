package Parametry;
import Program.Program;

public class Parametry {
    //Symulacja
    public int ile_tur;
    public int pocz_ile_robów;

    //Energia
    public int pocz_energia;
    public int koszt_tury;

    //Jedzenie
    public int ile_daje_jedzenie;
    public int ile_rośnie_jedzenie;

    //Powielanie
    public float pr_powielenia;
    public int ułamek_energii_rodzica;
    public int limit_powielania;

    //Instrukcje
    public int pr_usunięcia_instr;
    public int pr_dodania_instr;
    public int pr_zmiany_instr;
    public Program.Instrukcja[] spis_instr;
    public Program pocz_progr;

    public Parametry() {
        ile_tur = 0;
        pocz_ile_robów = 0;

        pocz_energia = 0;
        koszt_tury = 0;

        ile_daje_jedzenie = 0;
        ile_rośnie_jedzenie = 0;

        pr_powielenia = 0.0f;
        ułamek_energii_rodzica = 0;
        limit_powielania = 0;

        pr_usunięcia_instr = 0;
        pr_dodania_instr = 0;
        pr_zmiany_instr = 0;
    }

    public void czytajParametry() {

    }
}
