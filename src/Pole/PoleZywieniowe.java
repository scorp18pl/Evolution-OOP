package Pole;

import Symulacja.Symulacja;

public class PoleZywieniowe extends Pole {
    private boolean pożywienie;
    int licznik;

    @Override
    public boolean posiadaPożywienie() {
        return pożywienie;
    }

    @Override
    public void aktualizuj() {
        if (this.licznik != 0)
            this.licznik--;
        else if (!this.pożywienie)
            this.pożywienie = true;
    }

    @Override
    public void usuńPożywienie() {
        this.pożywienie = false;
        this.licznik = Symulacja.parametry.ile_rośnie_jedzenie;
    }

    public PoleZywieniowe() {
        super();
        this.licznik = 0;
        this.pożywienie = true;
    }
}
