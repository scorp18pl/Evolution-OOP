package Rob;

import java.util.Random;

import Board.Board;
import Helper.Vector2i;
import Program.Program;

public class Rob {
    private Vector2i position;

    private int energy;
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

    private boolean canEat(Board board) {
        return board.getField(this.position).hasFood();
    }

    private void eatFood(Board board) {
        board.getField(this.position).removeFood();
        // this.energy += Symulacja.parametry.ile_daje_jedzenie;
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
        // if (this.energy < Symulacja.parametry.limit_powielania)
        //     return false;
        // Random random = new Random();
        // return random.nextFloat() <= Symulacja.parametry.pr_powielenia;

        return false;
    }

    public Rob duplicate() {
        Program program = this.program.mutate();
        // int energy = this.energy * Symulacja.parametry.ułamek_energii_rodzica;
        int energy = 0;

        this.energy -= energy;

        Rob r = new Rob(program, energy);
        r.setDirection(Board.getOppositeDirection(this.direction));

        return r;
    }

    public Rob(Program program, int energy) {
        this.program = program;
        this.energy = energy;
        this.direction = Board.Direction.N;
    }
}
