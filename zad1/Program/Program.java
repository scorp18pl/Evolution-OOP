package zad1.Program;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import zad1.Evolution.Evolution;

// import Symulacja;

public class Program {
    public static enum Instruction {
        LEFT, RIGHT, MOVE, SMELL, EAT;
    }

    private static void exitInvalidChar() {
        System.out.println("Invalid Character.");
        System.exit(1);
    }

    private static boolean isAllowed(char c) {
        char[] allowed = {'l', 'p', 'i', 'w', 'j'};

        for (char allowed_char : allowed)
            if (c == allowed_char)
                return true;
        
        return false;
    }

    private static Instruction toInstruction(char c) {
        if (!isAllowed(c))
            exitInvalidChar();

        switch (c) {
            case 'l' :
                return Instruction.LEFT;
            case 'p' :
                return Instruction.RIGHT;
            case 'i' :
                return Instruction.MOVE;
            case 'w' :
                return Instruction.SMELL;
            default :
                return Instruction.EAT;
        }
    }

    private ArrayList<Instruction> instructions;

    private static boolean drawProb(float pr) {
        Random r = new Random();
        return r.nextFloat() <= pr;
    }

    private static boolean drawInstrDeletion() {
        return drawProb(Evolution.getParameters().instr_del_prob);
    }

    private static boolean drawInstrChange() {
        return drawProb(Evolution.getParameters().instr_chg_prob);
    }

    private static boolean drawInstrAddition() {
        return drawProb(Evolution.getParameters().instr_add_prob);
    }

    private static Instruction getRandomInstruction() {
        Random r = new Random();
        // return Evolution.getParameters().instr_log[r.nextInt(Evolution.getParameters().instr_log.length)];
        return Instruction.LEFT;
    }

    private void deleteInstruction() {
        this.instructions.remove(this.instructions.size() - 1);
    }

    private void changeInstruction() {
        Random r = new Random();
        Instruction i = Program.getRandomInstruction();
    
        this.instructions.set(r.nextInt(this.instructions.size()), i);
    }

    private void addInstruction() {
        Instruction i = Program.getRandomInstruction();

        this.instructions.add(i);
    }

    public Program mutate() {
        ArrayList<Instruction> i = new ArrayList<Instruction>();
        Collections.copy(i, this.instructions);
        
        Program p = new Program(i);

        if (this.instructions.size() > 0 && Program.drawInstrDeletion())
            p.deleteInstruction();

        if (this.instructions.size() > 0 && Program.drawInstrAddition())
            p.addInstruction();

        if (Program.drawInstrChange())
            p.changeInstruction();
            
        return p;
    }

    public ArrayList<Instruction> getInstruction() {
        return this.instructions;
    } 

    public Program() {
        this.instructions = new ArrayList<Instruction>();
    }

    public Program(ArrayList<Instruction> inst) {
        this.instructions = inst;
    }

    public Program(String string) {
        this.instructions = new ArrayList<Instruction>();

        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);

            this.instructions.add(Program.toInstruction(c));
        }
    }
}
