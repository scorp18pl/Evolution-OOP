package zad1.Input;


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import zad1.Field.*;
import zad1.Board.Board;
import zad1.Parameters.Parameters;
import zad1.Program.Program;

public class Input {
    private static void throwInvalidArguments() throws Exception {
        throw new Exception("Invalid Arguments");
    }

    private static void throwInvalidBoard() throws Exception {
        throw new Exception("Invalid Board");
    }

    private static void throwInvalidParameters() throws Exception {
        throw new Exception("Invalid Parameters");
    }

    private static boolean isValidUInt(int value) {
        return value >= 0;
    }

    private static boolean isValidFrac(float value) {
        return value >= 0.0f && value <= 1.0f;
    }

    private static ArrayList<Field> toFields(String line) throws Exception {
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
                    throwInvalidBoard();
                    break;
            }
        }
        
        return p;
    }

    private static int convertToInt(String value) throws Exception {
        int i = Integer.parseInt(value);
        
        if (!isValidUInt(i))
            throwInvalidParameters();

        return i;
    }

    private static float convertToFloat(String value) throws Exception {
        float f = Float.parseFloat(value);
        
        if (!isValidFrac(f))
            throwInvalidParameters();
        
        return f;
    }

    private static Program convertToProgram(String value) throws Exception {
        return new Program(value);
    }

    private static Parameters.Names toParamName(String string) {
        switch (string) {
            case "limit_powielenia":
                return Parameters.Names.DUPLICATING_LIMIT;
            case "pr_powielenia":
                return Parameters.Names.DUPLICATING_PROB;
            case "pocz_progr":
                return Parameters.Names.INIT_PROG;
            case "ile_daje_jedzenie":
                return Parameters.Names.FOOD_ENERGY;
            case "ile_rośnie_jedzenie":
                return Parameters.Names.FOOD_GROW_TIME;
            case "pr_dodania_instr":
                return Parameters.Names.INSTR_ADD_PROB;
            case "pr_zmiany_instr":
                return Parameters.Names.INSTR_CHG_PROB;
            case "pr_usunięcia_instr":
                return Parameters.Names.INSTR_DEL_PROB;
            case "spis_instr":
                return Parameters.Names.INSTR_LOG;
            case "ułamek_energii_rodzica":
                return Parameters.Names.PARENT_ENERGY_FRACTION;
            case "pocz_ile_robów":
                return Parameters.Names.INIT_ROB_COUNT;
            case "ile_tur":
                return Parameters.Names.ROUND_COUNT;
            case "koszt_tury":
                return Parameters.Names.ROUND_PRICE;
            case "pocz_energia":
                return Parameters.Names.INIT_ENERGY;
            case "co_ile_wypisz":
                return Parameters.Names.PRINT_PERIOD;
            default:
                return Parameters.Names.INVALID_PARAMETER;
        }
    }

    public static void checkArguments(String[] args) throws Exception {
        if (args.length != 2)
            throwInvalidArguments();
    }

    public static Board scanBoard(String[] args) throws Exception {
        String board_path = args[0];
        File board_file = new File(board_path);
        Scanner scanner = new Scanner(board_file);
        
        if (!scanner.hasNextLine())
            throwInvalidBoard();
        
        ArrayList<Field> fields = new ArrayList<Field>();

        String line = scanner.nextLine();

        int board_size_x = line.length();
        int board_size_y = 1;

        fields.addAll(toFields(line));

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();

            if (line.length() != board_size_x)
                throwInvalidBoard();
            
            fields.addAll(toFields(line));
            board_size_y++;
        }

        scanner.close();
        
        Board b = new Board(board_size_x, board_size_y);
        b.setFields(fields.toArray(new Field[0]));
        
        return b;
    }

    public static Parameters scanParameters(String[] args) throws Exception {
        String board_path = args[1];
        File board_file = new File(board_path);
        Scanner scanner = new Scanner(board_file);
        
        Parameters p = new Parameters();

        int parameter_loads = 0;
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] line_parts = line.split(" ");

            if (line_parts.length != 2)
                throwInvalidParameters();

            Parameters.Names param_name = toParamName(line_parts[0]);

            if (param_name == Parameters.Names.INVALID_PARAMETER)
                throwInvalidParameters();

            if (Parameters.isIntegerValue(param_name)) 
                p.setParameter(param_name, convertToInt(line_parts[1]));
            else if (Parameters.isFloatValue(param_name))
                p.setParameter(param_name, convertToFloat(line_parts[1]));
            else
                p.setParameter(param_name, convertToProgram(line_parts[1]));
            
            parameter_loads++;
        }
        if (parameter_loads > Parameters.count)
            throwInvalidParameters();

        if (!p.initialized())
            throwInvalidParameters();

        scanner.close();
        return p;
    }
}
