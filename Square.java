import java.util.HashSet;
import java.util.ArrayList;
import java.awt.Point;

public class Square implements Comparable<Square> {
    byte row;
    byte col;
    HashSet<Byte> mrv;
    int degree;

    public Square(byte row, byte col){
        this.row = row;
        this.col = col;
    }

    public void setMRV(HashSet<Byte> mrv){
        this.mrv = mrv;
    }

    public void setDegree(int degree){
        this.degree = degree;
    }

    public ArrayList<Point> getConstraints(){
        ArrayList<Point> pts = new ArrayList<>();
        pts.addAll(getRow(row, col));
        pts.addAll(getCol(row, col));
        pts.addAll(getSubGrid(row, col));
        return pts;
    }

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

    public ArrayList<Point> getSubGrid(byte row, byte col){
        ArrayList<Point> pts = new ArrayList<>();
        HashSet<Byte> rows = getSubGridRows(row);
        rows.remove(row);
        HashSet<Byte> cols = getSubGridRows(col);
        rows.remove(col);
        for(Byte rowNum : rows){
            for(Byte colNum : cols){
                pts.add(new Point(rowNum, colNum));
            }
        }

        return pts;
    }

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

    @Override
    public int compareTo(Square sq1){
        if(this.mrv.size() != sq1.mrv.size()){
            return this.mrv.size() - sq1.mrv.size();
        }else if(this.degree != sq1.degree){
            return 0-(this.degree - sq1.degree);
        }else if(this.col != sq1.col){
            return this.col - sq1.col;
        }else{
            return this.row - sq1.row;
        }
    }
}
