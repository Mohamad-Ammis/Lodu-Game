package Classes;

import java.util.List;

public class PrintBoard {
    static String[][] cells = new String[15][15] ;

    static public void initialize_cells(){
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                cells[i][j] = "     " ;
            }
        }
    }

    static public String set_cell(Position position){
        String x = "[";
        if(!position.getPieces().isEmpty()){
            for (int i = 0; i < position.getPieces().size(); i++) {
                x+=position.getPieces().get(i).getColor().toString();
            }
        }else{
            x+="   ";
        }
        x += "]";
        return x ;
    }

    static public String set_cell(Position position , boolean h){
        String x = "[";
        if(h){
            x+="S";
        }
        if(!position.getPieces().isEmpty()){
            for (int i = 0; i < position.getPieces().size(); i++) {
                x+=position.getPieces().get(i).getColor().toString();
            }
        }else{
            x+="  ";
        }
        x += "]";
        return x ;
    }

    public static void  print_board(List<Position> positions) {
        int i = 0 ;
        int p = 23 ;
        for (int j=6 ; j <= 8 ; j++){
            cells[i][j] = set_cell(positions.get(p));
            p++ ;
        }
        i = 6 ;
        p = 12 ;
        for (int j=0 ; j <= 5 ; j++){
            if(p == 15){
                cells[i][j] = set_cell(positions.get(p),true);
            }else {
                cells[i][j] = set_cell(positions.get(p));
            }
            p++;
        }
        p=31;
        for (int j=9 ; j <= 14 ; j++){
            cells[i][j] = set_cell(positions.get(p));
            p++;
        }
        i = 8 ;
        p = 5 ;
        for (int j=5 ; j >= 0 ; j--){
            cells[i][j] = set_cell(positions.get(p));
            p++;
        }
        p = 38 ;
        for (int j=14 ; j >= 9 ; j--){
            cells[i][j] = set_cell(positions.get(p));
            p++;
        }
        i = 14 ;
        p = 49 ;
        for (int j=8 ; j >= 6 ; j--){
            cells[i][j] = set_cell(positions.get(p));
            p++;
        }
        cells[7][0] = set_cell(positions.get(11));
        cells[7][14] = set_cell(positions.get(37));
        int j = 6 ;
        p = 18 ;
        for (i=5 ; i >= 1 ; i--){
            cells[i][j] = set_cell(positions.get(p));
            p++;
        }
        p=0 ;
        for (i=13 ; i >= 9 ; i--){
            if(p == 0){
                cells[i][j] = set_cell(positions.get(p),true);
            }else {
                cells[i][j] = set_cell(positions.get(p));
            }
            p++;
        }
        j = 8 ;
        p = 26;
        for (i=1 ; i <= 5 ; i++){
            cells[i][j] = set_cell(positions.get(p));
            p++;
        }
        p=44;
        for (i = 9; i <= 13; i++) {
            cells[i][j] = set_cell(positions.get(p));
            p++;
        }
        print();
    }

    static void print(){
        for (int i = 0; i < 15; i++) {
            System.out.println();
            for (int j = 0; j < 15; j++) {
                System.out.print(cells[i][j]);
            }
        }
    }
}
