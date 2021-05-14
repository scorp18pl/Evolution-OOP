package zad1.Board;

import java.util.ArrayList;
import java.util.Random;

import zad1.Helper.Vector2i;
import zad1.Rob.Rob;
import zad1.Field.Field;

public class Board {
    static public enum Direction {
        N, NE, E, SE, S, SW, W, NW;
    }

    private int board_size_x, board_size_y;
    private Field[] fields;

    private Stats stats;

    private ArrayList<Rob> robs;
    private ArrayList<Rob> offspring;

    static public Direction getOppositeDirection(Direction k) {
        //Działa tylko dla kierunków głównych (N, E, S, W)
        
        switch (k) {
            case N:
                return Direction.S;
            case E:
                return Direction.W;
            case S:
                return Direction.N;
            default:
                return Direction.E;
        }
    }

    private int getFieldIndex(Vector2i field_position) {
        return field_position.y * this.board_size_x + field_position.x;
    }

    public Field getField(Vector2i field_position) {
        return fields[getFieldIndex(field_position)];
    }

    public Vector2i getRandomFieldPosition() {
        Random r = new Random();
        return new Vector2i(r.nextInt(board_size_x), r.nextInt(board_size_y));
    }

    public Vector2i getNeighbourPosition(Vector2i field_position, Direction k) {
        Vector2i neighbour_p = new Vector2i(field_position);

        switch (k) {
            case N:
                neighbour_p.y--;
                break;
            case NE:
                neighbour_p.x++;
                neighbour_p.y--;
                break;
            case E:
                neighbour_p.x++;
                break;
            case SE:
                neighbour_p.x++;
                neighbour_p.y++;
                break;
            case S:
                neighbour_p.y++;
                break;
            case SW:
                neighbour_p.x--;
                neighbour_p.y++;
                break;
            case W:
                neighbour_p.x--;
                break;
            case NW:
                neighbour_p.x--;
                neighbour_p.y--;
                break;
        }

        if (neighbour_p.x == -1)
            neighbour_p.x = this.board_size_x - 1;

        if (neighbour_p.x == this.board_size_x)
            neighbour_p.x = 0;
        
        if (neighbour_p.y == -1)
            neighbour_p.y = this.board_size_y - 1;

        if (neighbour_p.y == this.board_size_y)
            neighbour_p.y = 0;
        
        return neighbour_p;
    }

    public void setFields(Field[] fields) {
        this.fields = fields;
    }

    public int getRobCount() {
        return this.robs.size();
    }

    public int getFoodFieldCount() {
        int count = 0;

        for (Field field : this.fields)
            if (field.hasFood())
                count++;

        return count;
    }

    public ArrayList<Rob> getRobs() {
        return this.robs;
    }

    public void addRob(Rob rob) {
        this.robs.add(rob);
    }

    private void addOffspring() {
        this.robs.addAll(offspring);
        offspring.clear();
    }

    private void executeRobPrograms() {
        for (Rob rob : this.robs) {
            rob.executeProgram(this);
            rob.incrementAge();

            if (rob.shouldDuplicate())
                offspring.add(rob.duplicate());
        }
    }

    private void removeDeadRobs() {
        for (int i = 0; i < this.robs.size(); i++) {
            if (!this.robs.get(i).alive()) {
                this.robs.remove(i);
                i--;
            }
        }
    }

    private void printRobs() {
        System.out.println("");
        System.out.println("Roby: ");
        for(Rob rob : this.robs)
            rob.print();
    }

    private void printBoard() {
        
        for (int i = -1; i < this.board_size_x + 1; i++)
            System.out.print("#");
        System.out.print("\n");

        for (int i = 0; i < this.fields.length; i++) {
            if (i % this.board_size_x == 0)
                System.out.print("#");

            if (this.fields[i].hasFood())
                System.out.print("*");
            else if (this.fields[i].isRegenerating())
                System.out.print("`");
            else
                System.out.print(" ");
            
            if ((i + 1) % this.board_size_x == 0)
                System.out.print("#\n");
        }

        for (int i = -1; i < this.board_size_x + 1; i++)
            System.out.print("#");
        System.out.print("\n");
        
    }

    public void updateRobs() {
        addOffspring();
        executeRobPrograms();
        removeDeadRobs();
    }

    public void updateFields() {
        for (Field f : fields)
            f.update();
    }

    public void printState() {
        printBoard();
        printRobs();
    }

    public void printStats() {
        this.stats.update(this);
        this.stats.print();
    }
    
    public Board(int board_size_x, int board_size_y) {
        this.board_size_x = board_size_x;
        this.board_size_y = board_size_y;

        this.robs = new ArrayList<Rob>();
        this.offspring = new ArrayList<Rob>();
        fields = new Field[board_size_x * board_size_y];
        this.stats = new Stats();
    }
    
}
