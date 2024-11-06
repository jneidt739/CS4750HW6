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

    public void solve(){
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
    }

    private void setProps(Square square){
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
