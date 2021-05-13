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
            case NE:
                neighbour_p.x++;
                neighbour_p.y--;
            case E:
                neighbour_p.x++;
            case SE:
                neighbour_p.x++;
                neighbour_p.y++;
            case S:
                neighbour_p.y++;
            case SW:
                neighbour_p.x--;
                neighbour_p.y++;
            case W:
                neighbour_p.x--;
            case NW:
                neighbour_p.x--;
                neighbour_p.y--;
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

    public void addRob(Rob rob) {
        robs.add(rob);
    }

    private void addOffspring() {
        robs.addAll(offspring);
        offspring.clear();
    }

    private void executeRobPrograms() {
        for (Rob rob : robs) {
            rob.executeProgram(this);

            if (rob.shouldDuplicate())
                offspring.add(rob.duplicate());
        }
    }

    private void removeDeadRobs() {
        for (Rob rob : robs) {
            if (!rob.alive() && !robs.remove(rob))
                System.out.println("Failed attempt to delete a rob.\n");
        }
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
    
    public Board(int board_size_x, int board_size_y) {
        this.board_size_x = board_size_x;
        this.board_size_y = board_size_y;

        fields = new Field[board_size_x * board_size_y];
    }
    
}
