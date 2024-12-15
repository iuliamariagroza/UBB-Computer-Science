package domain;

public class MatrixUtils {
    public static int computeElement(Matrix a, Matrix b, int i, int j)throws Exception{
        if(i < a.rowCount && j < b.columnCount){
            int result = 0;
            for(int k = 0; k < a.columnCount; k++){
                result += a.getElementFromMatrix(i,k) * b.getElementFromMatrix(k,j);
            }
            return result;
        } else{
            throw new Exception("Out of bounds");
        }
    }
}
