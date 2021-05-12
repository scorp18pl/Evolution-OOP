package Rob;

import java.util.Random;

import Pomocnicze.Wektor2i;
import Program.Program;
import Symulacja.Symulacja;
import Plansza.Plansza;

public class Rob {
    private Wektor2i pole;

    private int energia;
    private Program program;

    private Plansza.Kierunek kierunek;

    public Wektor2i dajPole() {
        return this.pole;
    }

    public boolean żyje() {
        return this.energia >= 0;
    }

    public void ustawPole(Wektor2i pole) {
        this.pole = pole;
    }

    public void ustawKierunek(Plansza.Kierunek kierunek) {
        this.kierunek = kierunek;
    }

    private void obróć(boolean w_lewo) {
        if (this.kierunek == Plansza.Kierunek.N)
            this.kierunek = (w_lewo ? Plansza.Kierunek.W : Plansza.Kierunek.E);
        
        if (this.kierunek == Plansza.Kierunek.E)
            this.kierunek = (w_lewo ? Plansza.Kierunek.N : Plansza.Kierunek.S);

        if (this.kierunek == Plansza.Kierunek.S)
            this.kierunek = (w_lewo ? Plansza.Kierunek.E : Plansza.Kierunek.W);

        if (this.kierunek == Plansza.Kierunek.W)
            this.kierunek = (w_lewo ? Plansza.Kierunek.S : Plansza.Kierunek.N);
    }

    private void idź(Plansza plansza) {
        this.pole = plansza.dajPoleSąsiadująceW(this.pole, this.kierunek);
        if (plansza.dajPole(this.pole).posiadaPożywienie()) {
            plansza.dajPole(this.pole).usuńPożywienie();
            this.energia += Symulacja.parametry.ile_daje_jedzenie;
        }
    }

    private void wąchaj(Plansza plansza) {
        Plansza.Kierunek[] kierunki = {Plansza.Kierunek.N, Plansza.Kierunek.E, 
                              Plansza.Kierunek.S, Plansza.Kierunek.W};
        for (Plansza.Kierunek kierunek : kierunki) {
            if (plansza.dajPole(plansza.dajPoleSąsiadująceW(this.pole, kierunek)).posiadaPożywienie()) {
                this.kierunek = kierunek;
                break;
            }
        }
    }

    private void jedz(Plansza plansza) {

    }

    public void wykonajProgram(Plansza plansza) {
        for (Program.Instrukcja inst : this.program.dajInstrukcje()) {
            switch (inst) {
                case LEWO:
                    obróć(true);
                    break;
                case PRAWO:
                    obróć(false);
                    break;
                case IDŹ:
                    idź(plansza);
                    break;
                case WĄCHAJ:
                    wąchaj(plansza);
                    break;
                case JEDZ:
                    jedz(plansza);
                    break;
            }


            this.energia--;

            if(this.energia < 0)
                break;
        }
    }

    public boolean czyPowielać() {
        Random r = new Random();
        return r.nextFloat() <= Symulacja.parametry.pr_powielenia;
    }

    public Rob powiel() {

        Rob r = new Rob();
        r.ustawKierunek(Plansza.dajPrzeciwnyKierunek());
        return r;
    }

    public Rob(Program program, int energia) {
        this.program = program;
        this.energia = energia;
        this.kierunek = Plansza.Kierunek.N;
    }
}
