package Input;

import Plansza.Plansza;
import Parametry.Parametry;

public class Input {
    public static Parametry Parametry() {
        return new Parametry();
    }

    public static Plansza Plansza() {
        return new Plansza(0, 0);
    }
}
