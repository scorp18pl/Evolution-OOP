package zad1.Program;


import java.util.ArrayList;
import java.util.Random;

import zad1.Evolution.Evolution;

// import Symulacja;

public class Program {
    public static enum Instruction {
        LEFT, RIGHT, MOVE, SMELL, EAT;
    }

    private ArrayList<Instruction> instructions;

    private static void throwInvalidChar() throws Exception {
        throw new Exception("Invalid Character");
    }

    private static boolean isAllowed(char c) {
        char[] allowed = {'l', 'p', 'i', 'w', 'j'};

        for (char allowed_char : allowed)
            if (c == allowed_char)
                return true;
        
        return false;
    }

    private static Instruction toInstruction(char c) throws Exception {
        if (!isAllowed(c))
            throwInvalidChar();

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

    private String instrToString(Instruction instr) {
        switch (instr) {
            case EAT:
                return "jedz";
            case LEFT:
                return "lewo";
            case MOVE:
                return "idź";
            case RIGHT:
                return "prawo";
            default:
                return "wąchaj";
            
        }
    }

    public String toString() {
        String string = "";
        for (int i = 0; i < this.instructions.size(); i++)
            string = string.concat(instrToString(this.instructions.get(i)) + " ");
        return string;
    }

    public Instruction getRandomInstruction() {
        Random r = new Random();

        return this.instructions.get(r.nextInt(this.instructions.size()));
    }

    public boolean initialized() {
        return this.instructions != null;
    }

    public int getLength() {
        return this.instructions.size();
    }

    private void deleteInstruction() {
        this.instructions.remove(this.instructions.size() - 1);
    }

    private void changeInstruction() {
        Random r = new Random();
        Instruction i = Evolution.getParameters().instr_log.getRandomInstruction();
        if (this.instructions == null || this.instructions.size() <= 0)
            return;

        this.instructions.set(r.nextInt(this.instructions.size()), i);
    }

    private void addInstruction() {
        if (Evolution.getParameters().instr_log == null)
            System.out.println("null instr_log");

        Instruction i = Evolution.getParameters().instr_log.getRandomInstruction();

        this.instructions.add(i);
    }

    public Program mutate() {
        Program p = new Program(this.copyInstructions());

        if (this.instructions.size() > 0 && Program.drawInstrDeletion())
            p.deleteInstruction();

        if (this.instructions.size() > 0 && Program.drawInstrAddition())
            p.addInstruction();

        if (this.instructions.size() > 0 && Program.drawInstrChange())
            p.changeInstruction();
            
        return p;
    }

    public ArrayList<Instruction> copyInstructions() {
        ArrayList<Instruction> instructions = new ArrayList<Instruction>();
        instructions.addAll(this.instructions);
        
        return instructions;
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

    public Program(Program p) {
        this.instructions = new ArrayList<Instruction>();
        instructions.addAll(p.instructions);
    }

    public Program(String string) throws Exception {
        this.instructions = new ArrayList<Instruction>();

        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);

            this.instructions.add(Program.toInstruction(c));
        }
    }
}
