package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Board {
    private int size;
    private List<List<Cell>> grid;

    public Board(int size) {
        this.size = size;
        this.grid = new ArrayList<>();
        for (int i=0; i<size; ++i){
            this.grid.add(new ArrayList<>());
            for (int j=0; j<size; ++j){
                this.grid.get(i).add(new Cell(i, j));
            }
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<List<Cell>> getGrid() {
        return grid;
    }

    public void setGrid(List<List<Cell>> grid) {
        this.grid = grid;
    }

    public void print(){
        for (List<Cell> row : grid){
            System.out.print("|");
            for (Cell cell : row){
                cell.print();
                System.out.print("|");
            }
            System.out.println();
        }
    }
}
