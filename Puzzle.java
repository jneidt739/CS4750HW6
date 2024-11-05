public class Puzzle {
    private byte[][] board;

    public Puzzle(byte[][] board){
        this.board = board;
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
