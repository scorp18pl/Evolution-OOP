package Input;


import java.util.ArrayList;
import java.util.Scanner;

import Board.Board;
import Field.EmptyField;
import Field.Field;
import Field.FoodField;
import Parameters.Parameters;

public class Input {
    private static ArrayList<Field> toFields(String line) {
        ArrayList<Field> p = new ArrayList<Field>();
        for (int i = 0; i < line.length(); i++) {
            switch (line.charAt(i)) {
                case 'x':
                    p.add(new FoodField());
                    break;
                case ' ':
                    p.add(new EmptyField());
                    break;
                default:
                    System.out.println("Invalid Board");
                    break;
            }
        }
        
        return p;
    }

    public static Board scanBoard() {
        Scanner scanner = new Scanner(System.in);
        
        if (!scanner.hasNextLine())
            System.out.println("Invalid Board");
        
        ArrayList<Field> fields = new ArrayList<Field>();

        String line = scanner.nextLine();

        int board_size_x = line.length();
        int board_size_y = 1;

        fields.addAll(toFields(line));

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();

            if (line.length() != board_size_x)
                System.out.println("Invalid Board");
            
            fields.addAll(toFields(line));
            board_size_y++;
        }

        scanner.close();
        
        Board b = new Board(board_size_x, board_size_y);
        b.setFields(fields.toArray(new Field[0]));
        
        return b;
    }

    public static Parameters scanParameters() {
        Scanner scanner = new Scanner(System.in);
        Parameters p = new Parameters();

        while (scanner.hasNext()) {
            // String line = scanner.nextLine();
            // line.
            
        }

        scanner.close();
        return p;
    }
}
