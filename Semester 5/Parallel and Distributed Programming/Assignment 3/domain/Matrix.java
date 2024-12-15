package domain;

import java.util.Random;

public class Matrix {
    public final int rowCount, columnCount;
    public int[][] matrix;
    public int flag;

    public Matrix(int rowCount, int columnCount, int flag) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.matrix = new int[rowCount][columnCount];
        this.flag=flag;
        generateMatrix(flag);
    }

    private void generateMatrix(int flag) {
        if(flag == 1){
            for(int i=0; i<rowCount; i++){
                for(int j=0; j<columnCount; j++){
                    matrix[i][j]=1;
                }
            }
        }
        else{
            Random rand = new Random();
            for(int i=0; i<rowCount; i++){
                for(int j=0; j<columnCount; j++){
                    matrix[i][j]=rand.nextInt(100)+1;
                }
            }
        }
    }

    public int getElementFromMatrix(int row, int column)
    {
        return matrix[row][column];
    }

    public void setElementFromMatrix(int row, int column, int value)
    {
        matrix[row][column]=value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                sb.append(matrix[i][j]).append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
