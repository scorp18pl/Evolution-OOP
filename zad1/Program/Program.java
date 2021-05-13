package Program;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

// import Symulacja;

public class Program {
    public static enum Instruction {
        LEFT, RIGHT, MOVE, SMELL, EAT;
    }

    private ArrayList<Instruction> instructions;

    // private static boolean drawProb(float pr) {
    //     Random r = new Random();
    //     return r.nextFloat() <= pr;
    // }

    private static boolean drawInstrDeletion() {
        // return drawProb(Symulacja.parametry.pr_usunięcia_instr);
        return true;
    }

    private static boolean drawInstrChange() {
        // return drawProb(Symulacja.parametry.pr_zmiany_instr);
        return true;

    }

    private static boolean drawInstrAddition() {
        // return drawProb(Symulacja.parametry.pr_dodania_instr);
        return true;

    }

    private static Instruction getRandomInstruction() {
        // Random r = new Random();
        // return Symulacja.parametry.spis_instr[r.nextInt(Symulacja.parametry.spis_instr.length)];
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
}
