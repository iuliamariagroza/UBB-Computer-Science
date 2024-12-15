package threads;

import domain.Matrix;
import domain.MatrixUtils;
import domain.Pair;

import java.util.ArrayList;
import java.util.List;

public class KThread extends Thread{
    public Matrix a, b, c;
    public int startRow, startColumn;
    public int elementCount;
    public List<Pair<Integer, Integer>> pairs;
    public int k;

    public KThread(int startRow, int startColumn, int k, int elementCount, Matrix a, Matrix b, Matrix c) {
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.k = k;
        this.elementCount = elementCount;
        this.a = a;
        this.b = b;
        this.c = c;
        this.pairs = new ArrayList<>();
        computeElements();
    }

    public void computeElements(){
        int i = startRow;
        int j = startColumn;
        int size = elementCount;

        while(i < c.rowCount && size > 0){
            pairs.add(new Pair<>(i,j));
            size--;
            i = i + (j + k) / c.columnCount;
            j = (j + k) % c.columnCount;
        }
    }

    @Override
    public void run() {
        for (Pair<Integer, Integer> pair : pairs) {
            int row = pair.getFirst();
            int column = pair.getSecond();
            try {
                c.setElementFromMatrix(row, column, MatrixUtils.computeElement(a, b, row, column));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
