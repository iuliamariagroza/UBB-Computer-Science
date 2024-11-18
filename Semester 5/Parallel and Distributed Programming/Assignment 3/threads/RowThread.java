package threads;

import domain.Matrix;
import domain.Pairs;

import java.util.ArrayList;
import java.util.List;

public class RowThread extends Thread{
    public Matrix a,b,c;
    public int startRow;
    public int startCol;
    public int elementCount;
    public List<Pairs<Integer,Integer>> pairs;//will store pairs of integers,where each pair= row + column indices of the elements that the thread will compute

    public RowThread(Matrix a, Matrix b, Matrix c,int startRow, int startColumn, int elementCount){
        this.a = a;
        this.b = b;
        this.c = c;
        this.startRow = startRow;
        this.startCol = startColumn;
        this.elementCount = elementCount;
        this.pairs = new ArrayList<>();
        computeElementsForEachThread();
    }

    public void computeElementsForEachThread(){
        int elementsAdded = 0;

        for (int i = startRow; i < c.rowCount && elementsAdded < elementCount; i++) {
            int j = (i == startRow ? startCol : 0); //if the current row is the startRow, stsrt from startCol, otherwise 0

            while (j < c.columnCount && elementsAdded < elementCount) {
                pairs.add(new Pairs<>(i, j));
                elementsAdded++;
                j++;
            }
        }
    }

    @Override
    public void run(){
        for(Pairs<Integer, Integer> p: pairs){
            int row = p.first;
            int column = p.second;
            try{
                c.setElement(row, column, Matrix.computeElement(a, b, row, column));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
