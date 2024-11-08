import java.util.HashSet;

public class Main{

    public static void main(String args[]){
        //initializes static set of domain options
        HashSet<Byte> options = new HashSet<>();
        options.add((byte)1);
        options.add((byte)2);
        options.add((byte)3);
        options.add((byte)4);
        options.add((byte)5);
        options.add((byte)6);
        options.add((byte)7);
        options.add((byte)8);
        options.add((byte)9);
        Puzzle.nums = options;
        //initializes each sudoku board and Puzzle class
        byte[][] board1 = {
            {0,0,1,0,0,2,0,0,0},
            {0,0,5,0,0,6,0,3,0},
            {4,6,0,0,0,5,0,0,0},
            {0,0,0,1,0,4,0,0,0},
            {6,0,0,8,0,0,1,4,3},
            {0,0,0,0,9,0,5,0,8},
            {8,0,0,0,4,9,0,5,0},
            {1,0,0,3,2,0,0,0,0},
            {0,0,9,0,0,0,3,0,0},
        };
        Puzzle puzzle1 = new Puzzle(board1);
        byte[][] board2 = {
            {0,0,5,0,1,0,0,0,0},
            {0,0,2,0,0,4,0,3,0},
            {1,0,9,0,0,0,2,0,6},
            {2,0,0,0,3,0,0,0,0},
            {0,4,0,0,0,0,7,0,0},
            {5,0,0,0,0,7,0,0,1},
            {0,0,0,6,0,3,0,0,0},
            {0,6,0,1,0,0,0,0,0},
            {0,0,0,0,7,0,0,5,0},
        };
        Puzzle puzzle2 = new Puzzle(board2);
        byte[][] board3 = {
            {6,7,0,0,0,0,0,0,0},
            {0,2,5,0,0,0,0,0,0},
            {0,9,0,5,6,0,2,0,0},
            {3,0,0,0,8,0,9,0,0},
            {0,0,0,0,0,0,8,0,1},
            {0,0,0,4,7,0,0,0,0},
            {0,0,8,6,0,0,0,9,0},
            {0,0,0,0,0,0,0,1,0},
            {1,0,6,0,5,0,0,7,0},
        };
        Puzzle puzzle3 = new Puzzle(board3);
        
        //prints solutions with first four moves and execution times
        long time = System.nanoTime();
        System.out.println(puzzle1.solve().substring(0,155));
        System.out.println((System.nanoTime() - time)/1000000 + "ms");
        time = System.nanoTime();
        System.out.println(puzzle2.solve().substring(0,155));
        System.out.println((System.nanoTime() - time)/1000000 + "ms");
        time = System.nanoTime();
        System.out.println(puzzle3.solve().substring(0,155));
        System.out.println((System.nanoTime() - time)/1000000 + "ms");
    }
}