package Input;

import java.io.File;
import java.util.Scanner;

import Plansza.Plansza;
import Parametry.Parametry;

public class Input {
    public static Parametry Parametry() {
        return new Parametry();
    }

    public static Plansza Plansza() {
        // Scanner scanner = new Scanner(System.in);
        // String row = scanner.nextLine();

        return new Plansza(0, 0);
    }
}
