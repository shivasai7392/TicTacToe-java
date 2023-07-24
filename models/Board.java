package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Board {
    private int size;
    private List<List<Cell>> grid;
    private final HashMap<Integer, HashMap<Character, Integer>> rowCounter;
    private final HashMap<Integer, HashMap<Character, Integer>> colCounter;

    public HashMap<Integer, HashMap<Character, Integer>> getRowCounter() {
        return rowCounter;
    }

    public HashMap<Integer, HashMap<Character, Integer>> getColCounter() {
        return colCounter;
    }

    public HashMap<Integer, HashMap<Character, Integer>> getDiagCounter() {
        return diagCounter;
    }

    private final HashMap<Integer, HashMap<Character, Integer>> diagCounter;

    public Board(int size) {
        this.size = size;
        this.grid = new ArrayList<>();
        for (int i=0; i<size; ++i){
            this.grid.add(new ArrayList<>());
            for (int j=0; j<size; ++j){
                this.grid.get(i).add(new Cell(i, j));
            }
        }
        this.colCounter = new HashMap<>();
        this.rowCounter = new HashMap<>();
        this.diagCounter = new HashMap<>();
        this.diagCounter.put(1, new HashMap<>());
        this.diagCounter.put(2, new HashMap<>());
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
