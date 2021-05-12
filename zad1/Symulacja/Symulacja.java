package Symulacja;

import Input.Input;
import Parametry.Parametry;
import Plansza.Plansza;
import Rob.Rob;

public class Symulacja {
    public final static Parametry parametry = ustawParametry();
    private Plansza plansza;

    private static Parametry ustawParametry() {
        Parametry pom_p = new Parametry();
        pom_p.czytajParametry();
        return pom_p;
    }

    private Plansza ustawPlanszę() {
        Plansza pom_p;
        pom_p = Input.Plansza();
        return pom_p;
    }

    private void umieśćRoby() {
        for (int i = 0; i < Symulacja.parametry.pocz_ile_robów; i++) {
            Rob r = new Rob(Symulacja.parametry.pocz_progr, Symulacja.parametry.pocz_energia);
            r.ustawPole(plansza.dajLosowePoleW());
            
            plansza.dodajRoba(r);
        }
    }

    public void start() {
        umieśćRoby();

        for (int i = 0; i < Symulacja.parametry.ile_tur; i++) {
            this.plansza.aktualizujPola();
            this.plansza.aktualizujRoby();
        }
    }

    public Symulacja() {
        plansza = ustawPlanszę();
    }

    public static void main(String[] args) {
        Symulacja s = new Symulacja();
        s.start();
    }
}
