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

    //not done
    public ArrayList<Point> getGrid(){
        ArrayList<Point> pts = new ArrayList<>();
      /*   byte x = (byte)(col % 3);
        byte y = (byte)(row % 3);
        for(int i = 1; i < 9; i++){
            
        }*/   //previous code

         // Add cells in the same row
    for (int colIndex = 0; colIndex < 9; colIndex++) {
        if (colIndex != this.col) {
            pts.add(new Point(this.row, colIndex));
        }
    }

    // Add cells in the same column
    for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
        if (rowIndex != this.row) {
            pts.add(new Point(rowIndex, this.col));
        }
    }

    // Add cells in the same 3x3 subgrid
    int subgridRowStart = (this.row / 3) * 3;
    int subgridColStart = (this.col / 3) * 3;
    for (int r = subgridRowStart; r < subgridRowStart + 3; r++) {
        for (int c = subgridColStart; c < subgridColStart + 3; c++) {
            if (r != this.row || c != this.col) {
                pts.add(new Point(r, c));
            }
        }
    }

        return pts;
    }

    @Override
    public int compareTo(Square sq1){
        if(this.mrv != sq1.mrv){
            return this.mrv.size() - sq1.mrv.size();
        }else if(this.degree != sq1.degree){
            return this.degree - sq1.degree;
        }else if(this.col != sq1.col){
            return this.col - sq1.col;
        }else{
            return this.row - sq1.row;
        }
    }
}
