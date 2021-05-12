import Board.Board;
import Input.Input;
import Parameters.Parameters;
import Rob.Rob;

public class Symulacja {
    private Board board;
    private Parameters parameters;

    private void addRobs() {
        for (int i = 0; i < this.parameters.rob_start_count; i++) {
            Rob r = new Rob(this.parameters.first_prog, this.parameters.start_energy);
            r.setPosition(board.getRandomFieldPosition());
            
            board.addRob(r);
        }
    }

    public void start() {
        addRobs();

        for (int i = 0; i < this.parameters.round_count; i++) {
            this.board.updateFields();
            this.board.updateRobs();
        }
    }

    public Symulacja() {
        this.board = Input.scanBoard();
        this.parameters = Input.scanParameters();
    }

    public static void main(String[] args) {
        Symulacja s = new Symulacja();
        // s.start();
    }
}
