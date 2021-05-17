package zad1.Rob;


import java.util.Random;

import zad1.Helper.Colors;
import zad1.Board.Board;
import zad1.Evolution.Evolution;
import zad1.Helper.Vector2i;
import zad1.Program.Program;

public class Rob {
    private Vector2i position;

    private int offspring;
    private int index;
    private int age;
    private int energy;
    private StatColors stat_colors;
    private Program program;

    private Board.Direction direction;

    public Vector2i getPosition() {
        return this.position;
    }

    public boolean alive() {
        return this.energy >= 0;
    }

    public void setPosition(Vector2i position) {
        this.position = position;
    }

    public void setDirection(Board.Direction direction) {
        this.direction = direction;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }

    public void incrementAge() {
        this.age++;
    }

    public void colorUpdate() {
        this.stat_colors.update(this);
    }

    private boolean canEat(Board board) {
        return board.getField(this.position).hasFood();
    }

    private void eatFood(Board board) {
        board.getField(this.position).removeFood();
        this.energy += Evolution.getParameters().food_energy;
    }

    public int getAge() {
        return this.age;
    }

    public int getEnergy() {
        return this.energy;
    }

    public int getOffspring() {
        return this.offspring;
    }

    public int getProgramLength() {
        return this.program.getLength();
    }

    private String dirToString() {
        switch (this.direction) {
            case N:
                return "Północ";
            case E:
                return "Wschód";
            case S:
                return "Południe";
            default:
                return "Zachód";
        }
    }

    public void printDir(boolean color) {
        if (color) System.out.print(this.stat_colors.getEnergyColor());

        switch (this.direction) {
            case E:
                System.out.print(">");
                break;
            case N:
                System.out.print("^");
                break;
            case S:
                System.out.print("v");
                break;
            default:
                System.out.print("<");
                break;

        }
    }

    public void print(boolean color) {
        System.out.println((color ? Colors.YELLOW : "") + " Rob " + (color ? Colors.WHITE : "") + this.index + ": ");
        System.out.println((color ? Colors.WHITE_BOLD : "") + "  Położenie: " + (color ? Colors.WHITE : "") + this.position.toString());
        System.out.println((color ? Colors.WHITE_BOLD : "") + "  Kierunek: " + (color ? Colors.WHITE : "") + this.dirToString());
        System.out.println((color ? Colors.WHITE_BOLD : "") + "  Wiek: " + (color ? this.stat_colors.getAgeColor() : "") + this.age);
        System.out.println((color ? Colors.WHITE_BOLD : "") + "  Energia: " + (color ? this.stat_colors.getEnergyColor() : "") + this.energy);
        System.out.println((color ? Colors.WHITE_BOLD : "") + "  Program: " + (color ? Colors.WHITE : "") + this.program.toString());
        System.out.println((color ? Colors.WHITE_BOLD : "") + "  Potomstwo: " + (color ? this.stat_colors.getOffspringColor() : "") + this.offspring);
    }

    private void turn(boolean left) {
        if (this.direction == Board.Direction.N)
            this.direction = (left ? Board.Direction.W : Board.Direction.E);
        
        if (this.direction == Board.Direction.E)
            this.direction = (left ? Board.Direction.N : Board.Direction.S);

        if (this.direction == Board.Direction.S)
            this.direction = (left ? Board.Direction.E : Board.Direction.W);

        if (this.direction == Board.Direction.W)
            this.direction = (left ? Board.Direction.S : Board.Direction.N);
    }

    private void turnLeft() {
        turn(true);
    }

    private void turnRight() {
        turn(false);
    }

    private void move(Board board) {
        this.position = board.getNeighbourPosition(this.position, this.direction);

        if (canEat(board))
            eatFood(board);
    }

    private void smell(Board board) {
        Board.Direction[] directions = {Board.Direction.N, Board.Direction.E, 
                                        Board.Direction.S, Board.Direction.W};

        for (Board.Direction direction : directions) {
            Vector2i neighbour_field = board.getNeighbourPosition(this.position, direction);
            
            if (board.getField(neighbour_field).hasFood()) {
                this.direction = direction;
                break;
            }   
        }
    }

    private void eat(Board board) {
        Board.Direction[] directions = {Board.Direction.N, Board.Direction.NE, Board.Direction.E, 
                                        Board.Direction.SE, Board.Direction.S, Board.Direction.SW, 
                                        Board.Direction.W, Board.Direction.NW};
        
        for (Board.Direction direction : directions) {
            Vector2i neighbour_field = board.getNeighbourPosition(this.position, direction);
            
            if (board.getField(neighbour_field).hasFood()) {
                this.position = neighbour_field;
                eatFood(board);
            }
        }
    }

    public void executeProgram(Board board) {
        for (Program.Instruction inst : this.program.getInstruction()) {
            switch (inst) {
                case LEFT:
                    turnLeft();
                    break;
                case RIGHT:
                    turnRight();
                    break;
                case MOVE:
                    move(board);
                    break;
                case SMELL:
                    smell(board);
                    break;
                case EAT:
                    eat(board);
                    break;
            }

            this.energy--;

            if(this.energy < 0)
                break;
        }
    }

    public boolean shouldDuplicate() {
        if (this.energy < Evolution.getParameters().duplicating_limit)
            return false;

        Random random = new Random();

        return random.nextFloat() <= Evolution.getParameters().duplicating_prob;
    }

    public Rob duplicate() {
        offspring++;
        Program program = this.program.mutate();
        int energy = (int)((float)this.energy * Evolution.getParameters().parent_energy_fraction);
        this.energy -= energy;

        Rob r = new Rob(program, energy);
        r.setDirection(Board.getOppositeDirection(this.direction));
        r.setPosition(this.position);

        return r;
    }

    public Rob(Program program, int energy) {
        this.program = program;
        this.energy = energy;
        this.stat_colors = new StatColors(Colors.WHITE);
        this.direction = Board.Direction.N;
        this.index = 0;
        this.age = 0;
        this.offspring = 0;
    }
}
