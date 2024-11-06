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
        byte x = (byte)(col % 3);
        byte y = (byte)(row % 3);
        for(int i = 1; i < 9; i++){
            
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
