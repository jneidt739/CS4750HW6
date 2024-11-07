import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.awt.Point;

public class Puzzle {
    private byte[][] board;
    public static HashSet<Byte> nums;

    //constructor for Puzzle class
    public Puzzle(byte[][] board){
        this.board = board;
    }

    //function to copy byte board for backtracking search
    public byte[][] copyBoard(byte[][] oldBoard){
        byte[][] newBoard = new byte[9][9];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                newBoard[i][j] = oldBoard[i][j];
            }
        }
        return newBoard;
    }

    //encapsulates recursive backtrack implementation
    public String solve(){
        return RecursiveBacktrack();
    }

    //Recursively called backtracking function
    public String RecursiveBacktrack(){
        //calls buildCSP function to get queue of possible moves
        PriorityQueue<Square> queue = buildCSP();
        //checks if null value returned, which indicates failure
        if(queue == null){
            return "FAIL";
        }
        //checks if empty queue is returned, indicating a solved puzzle
        if(queue.isEmpty()){
            System.out.println(this);//prints puzzle
            return "";//returns empty string for adding moves to
        }
        Square front = queue.poll();//fetches best Square
        //loops through remaining values for square front
        for(Byte num : front.mrv){
            byte[][] newBoard = copyBoard(board);
            newBoard[front.row][front.col] = num;//assigns move in board
            Puzzle newPuzzle = new Puzzle(newBoard);//creates new puzzle from board
            String result = newPuzzle.RecursiveBacktrack();//recursively calls function
            if(result == "FAIL")//checks if failure is reached
                continue;
            else{//adds move to passed string if successful
                return String.format("(%d, %d), Value:%d, Domain: %d, Degree: %d\n", front.row + 1, front.col + 1, num, front.mrv.size(), front.degree) + result;
            }
        }
        return "FAIL";//returns failure if no valid moves
    }

    //creates queue of all empty Squares and sets their properties
    public PriorityQueue<Square> buildCSP(){
        PriorityQueue<Square> queue = new PriorityQueue<Square>();
        //loops through each square
        for(byte i = 0; i < 9; i++){
            for(byte j = 0; j < 9; j++){
                //checks if square is empty
                if(board[i][j] == 0){
                    Square sq = new Square(i, j);//creates new Square
                    try{
                        setProps(sq);//assigns sq its MRV hash set and degree value
                    }catch(Exception e){
                        return null;//returns null if sq has no remaining values
                    }
                    queue.offer(sq);//adds sq to queue
                }
            }
        }
        return queue;
    }

    //sets mrv and degree properties of passed Square
    private void setProps(Square square) throws Exception{
        HashSet<Byte> remaining = new HashSet<Byte>(nums);//initializes set with all values in domain
        ArrayList<Point> nearby = square.getConstraints();//gets coordinates of all constraining squares
        int degree = 0;//initializes degree to 0
        //loops through each point
        for(Point point : nearby){
            byte value = board[(int)point.getX()][(int)point.getY()];//fetches value at point
            if(value == 0){
                degree++;//increments degree if square is empty
            }else{
                remaining.remove(value);//removes square's value from mrv if not empty
            }
        }
        if(remaining.isEmpty()){
            throw new Exception();//throws an exception if square has no valid values
        }
        //uses setters to store properties
        square.setMRV(remaining);
        square.setDegree(degree);
        return;
    }

    //prints out the state of the board
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
