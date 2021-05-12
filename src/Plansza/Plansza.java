package Plansza;

import java.util.ArrayList;
import java.util.Random;

import Pole.Pole;
import Pomocnicze.Wektor2i;
import Rob.Rob;

public class Plansza {
    static public enum Kierunek {
        N, NE, E, SE, S, SW, W, NW;
    }

    private int rozmiar_planszy_x, rozmiar_planszy_y;
    private Pole[] pola;

    private ArrayList<Rob> roby;
    private ArrayList<Rob> potomstwo;

    static public Kierunek dajPrzeciwnyKierunek(Kierunek k) {
        //Działa tylko dla kierunków głównych (N, E, S, W)
        
        switch (k) {
            case N:
                return Kierunek.S;
            case E:
                return Kierunek.W;
            case S:
                return Kierunek.N;
            default:
                return Kierunek.E;
        }
    }

    private int indeksPola(Wektor2i w_pola) {
        return w_pola.y * this.rozmiar_planszy_x + w_pola.x;
    }

    public Pole dajPole(Wektor2i w_pola) {
        return pola[indeksPola(w_pola)];
    }

    public Wektor2i dajLosowePoleW() {
        Random r = new Random();
        return new Wektor2i(r.nextInt(rozmiar_planszy_x), r.nextInt(rozmiar_planszy_y));
    }

    public Wektor2i dajPoleSąsiadująceW(Wektor2i w_pola, Kierunek k) {
        Wektor2i return_w = new Wektor2i(w_pola);

        switch (k) {
            case N:
                return_w.y--;
            case NE:
                return_w.x++;
                return_w.y--;
            case E:
                return_w.x++;
            case SE:
                return_w.x++;
                return_w.y++;
            case S:
                return_w.y++;
            case SW:
                return_w.x--;
                return_w.y++;
            case W:
                return_w.x--;
            case NW:
                return_w.x--;
                return_w.y--;
        }

        if (return_w.x == -1)
            return_w.x = this.rozmiar_planszy_x - 1;

        if (return_w.x == this.rozmiar_planszy_x)
            return_w.x = 0;
        
        if (return_w.y == -1)
            return_w.y = this.rozmiar_planszy_y - 1;

        if (return_w.y == this.rozmiar_planszy_y)
            return_w.y = 0;
        
        return return_w;
    }

    public void dodajRoba(Rob rob) {
        roby.add(rob);
    }

    public void aktualizujRoby() {
        roby.addAll(potomstwo);
        potomstwo.clear();

        for (Rob rob : roby) {
            rob.wykonajProgram(this);
            if (rob.czyPowielać())
                potomstwo.add(rob.powiel());
        }
    }

    public void aktualizujPola() {
        for (Pole p : pola)
            p.aktualizuj();
    }
    
    public Plansza(int rozmiar_planszy_x, int rozmiar_planszy_y) {
        this.rozmiar_planszy_x = rozmiar_planszy_x;
        this.rozmiar_planszy_y = rozmiar_planszy_y;

        pola = new Pole[rozmiar_planszy_x * rozmiar_planszy_y];
    }
    
}
