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

    public byte[][] copyBoard(byte[][] oldBoard){
        byte[][] newBoard = new byte[9][9];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                newBoard[i][j] = oldBoard[i][j];
            }
        }
        return newBoard;
    }

    public void solve(){
        RecursiveBacktrack();
    }

    public boolean RecursiveBacktrack(){
        PriorityQueue<Square> queue = buildCSP();
        if(queue == null){
            return false;
        }
        if(queue.isEmpty()){
            System.out.println(this);
            return true;
        }
        Square front = queue.poll();
        for(Byte num : front.mrv){
            byte[][] newBoard = copyBoard(board);
            newBoard[front.row][front.col] = num;
            Puzzle newPuzzle = new Puzzle(newBoard);
            if(newPuzzle.RecursiveBacktrack())
                return true;
            else{
                continue;
            }
        }
        return false;
    }

    public PriorityQueue<Square> buildCSP(){
        PriorityQueue<Square> queue = new PriorityQueue<Square>();
        for(byte i = 0; i < 9; i++){
            for(byte j = 0; j < 9; j++){
                if(board[i][j] == 0){
                    Square sq = new Square(i, j);
                    try{
                        setProps(sq);
                    }catch(Exception e){
                        return null;
                    }
                    queue.offer(sq);
                }
            }
        }
        return queue;
    }

    private void setProps(Square square) throws Exception{
        HashSet<Byte> remaining = new HashSet<Byte>(nums);
        ArrayList<Point> nearby = square.getConstraints();
        int degree = 0;
        for(Point point : nearby){
            byte value = board[(int)point.getX()][(int)point.getY()];
            if(value == 0){
                degree++;
            }else{
                remaining.remove(value);
            }
        }
        if(remaining.isEmpty()){
            throw new Exception();
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
