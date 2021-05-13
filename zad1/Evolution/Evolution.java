package zad1.Evolution;

import zad1.Board.Board;
import zad1.Parameters.Parameters;
import zad1.Rob.Rob;
import zad1.Input.Input;

public class Evolution {
    private static Parameters parameters;

    private Board board;

    public static Parameters getParameters() {
        return parameters.copy();
    }

    private void addRobs() {
        for (int i = 0; i < Evolution.parameters.init_rob_count; i++) {
            Rob r = new Rob(Evolution.parameters.init_prog, Evolution.parameters.init_energy);
            r.setPosition(board.getRandomFieldPosition());
            
            board.addRob(r);
        }
    }

    public void start() {
        addRobs();

        for (int i = 0; i < Evolution.parameters.round_count; i++) {
            this.board.updateFields();
            this.board.updateRobs();
        }
    }

    public Evolution(String[] args) throws Exception {
        Input.checkArguments(args);

        this.board = Input.scanBoard(args);
        Evolution.parameters = Input.scanParameters(args);
    }
}
