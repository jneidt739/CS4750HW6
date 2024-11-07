import java.util.HashSet;
import java.util.ArrayList;
import java.awt.Point;

public class Square implements Comparable<Square> {
    byte row;
    byte col;
    HashSet<Byte> mrv;
    int degree;

    //constructor
    public Square(byte row, byte col){
        this.row = row;
        this.col = col;
    }

    /* Setters */

    public void setMRV(HashSet<Byte> mrv){
        this.mrv = mrv;
    }

    public void setDegree(int degree){
        this.degree = degree;
    }

    //returns list of all points in the same row, column, or subgrid as square
    public ArrayList<Point> getConstraints(){
        ArrayList<Point> pts = new ArrayList<>();
        pts.addAll(getRow(row, col));
        pts.addAll(getCol(row, col));
        pts.addAll(getSubGrid(row, col));
        return pts;
    }

    //returns all points in same row as square, excluding the square itself
    public ArrayList<Point> getRow(byte row, byte col){
        ArrayList<Point> pts = new ArrayList<>();
        for(byte i = 0; i < 9; i++){
            if(i == col){
                continue;
            }
            pts.add(new Point(row, i));
        }
        return pts;
    }

    //returns all points in same column as square, excluding the square itself
    public ArrayList<Point> getCol(byte row, byte col){
        ArrayList<Point> pts = new ArrayList<>();
        for(byte i = 0; i < 9; i++){
            if(i == row){
                continue;
            }
            pts.add(new Point(i, col));
        }
        return pts;
    }

    //gets all of the points in the same subgrid as the square that are not in the same row or column
    public ArrayList<Point> getSubGrid(byte row, byte col){
        ArrayList<Point> pts = new ArrayList<>();
        HashSet<Byte> rows = getSubGridRows(row);
        rows.remove(row);
        HashSet<Byte> cols = getSubGridRows(col);
        rows.remove(col);
        for(Byte rowNum : rows){//loops through the four nodes in the subgrid that don't share a row/column
            for(Byte colNum : cols){
                pts.add(new Point(rowNum, colNum));
            }
        }

        return pts;
    }

    //helper that returns set of row numbers in square's subgrid
    public HashSet<Byte> getSubGridRows(byte row){
        HashSet<Byte> rows = new HashSet<>();
        if(row < 3){
            rows.add((byte)0);
            rows.add((byte)1);
            rows.add((byte)2);
        }else if(row > 5){
            rows.add((byte)6);
            rows.add((byte)7);
            rows.add((byte)8);
        }else{
            rows.add((byte)3);
            rows.add((byte)4);
            rows.add((byte)5);
        }
        return rows;
    }

    //helper that returns set of column numbers in square's subgrid
    public HashSet<Byte> getSubGridCols(byte col){
        HashSet<Byte> cols = new HashSet<>();
        if(col < 3){
            cols.add((byte)0);
            cols.add((byte)1);
            cols.add((byte)2);
        }else if(col > 5){
            cols.add((byte)6);
            cols.add((byte)7);
            cols.add((byte)8);
        }else{
            cols.add((byte)3);
            cols.add((byte)4);
            cols.add((byte)5);
        }
        return cols;
    }

    //used for comparison of Square objects in PriorityQueue
    @Override
    public int compareTo(Square sq1){
        //compares number of minimum remaining values
        if(this.mrv.size() != sq1.mrv.size()){
            return this.mrv.size() - sq1.mrv.size();
        }else if(this.degree != sq1.degree){//compares degree of each square
            return 0-(this.degree - sq1.degree);
        }else if(this.col != sq1.col){//ties are broken with column
            return this.col - sq1.col;
        }else{//then row number
            return this.row - sq1.row;
        }
    }
}
