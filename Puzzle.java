import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.awt.Point;

public class Puzzle {
    private byte[][] board;
    public static HashSet<Byte> nums;

    public Puzzle(byte[][] board){
        this.board = board;
    }

    public boolean solve(){
        PriorityQueue<Square> queue = new PriorityQueue<Square>();
        for(byte i = 0; i < 9; i++){
            for(byte j = 0; j < 9; j++){
                if(board[i][j] == 0){
                    Square sq = new Square(i, j);
                    setProps(sq);
                    queue.offer(sq);
                }
            }
        }
        return backtrackSolve(queue);
    }

    private boolean backtrackSolve(PriorityQueue<Square> queue) {
        if (queue.isEmpty()) {
            // Puzzle solved if queue is empty
            System.out.println(this); // Print solved puzzle
            return true;
        }
    
        Square sq = queue.poll(); // Get cell with minimum remaining values
        for (Byte num : sq.mrv) {
            if (isValidPlacement(sq.row, sq.col, num)) {
                board[sq.row][sq.col] = num;
                PriorityQueue<Square> newQueue = new PriorityQueue<>(queue);
    
                // Update properties for affected cells
                for (Square s : newQueue) {
                    setProps(s);
                }
    
                if (backtrackSolve(newQueue)) {
                    return true; // Puzzle solved
                }
    
                board[sq.row][sq.col] = 0; // Reset on backtrack
            }
        }
        return false; // Trigger backtracking if no valid placement
    }

    private boolean isValidPlacement(int row, int col, byte num) {
        // Check row
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }
    
        // Check column
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }
    
        // Check 3x3 subgrid
        int subgridRowStart = (row / 3) * 3;
        int subgridColStart = (col / 3) * 3;
        for (int r = subgridRowStart; r < subgridRowStart + 3; r++) {
            for (int c = subgridColStart; c < subgridColStart + 3; c++) {
                if (board[r][c] == num) {
                    return false;
                }
            }
        }
    
        return true;
    }
    
    private void setProps(Square square){
        HashSet<Byte> remaining = new HashSet<Byte>(nums);
        ArrayList<Point> nearby = square.getGrid();
        int degree = 0;

        for(Point point : nearby){
            byte value = board[(int)point.getX()][(int)point.getY()];
            if(value == 0){
                degree++;
            }else{
                remaining.remove(value);
            }
        }
        square.setMRV(remaining);
        square.setDegree(degree);
        return;
    }

    @Override
    public String toString(){
        String result = "-----------------\n";
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                result += board[i][j];
                if((j + 1) % 3 == 0){
                    result += '|';
                }else{
                    result += ' ';
                }
            }
            if((i + 1) % 3 == 0){
                result += "\n-----------------\n";
            }else{
                result += "\n";
            }
        }

        return result;
    }

}
