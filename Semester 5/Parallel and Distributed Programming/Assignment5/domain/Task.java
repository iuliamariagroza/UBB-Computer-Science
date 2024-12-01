package domain;

public class Task implements Runnable{
    private int start;
    private int end;
    private Polynomials a;
    private Polynomials b;
    private Polynomials c;

    public Task(int start, int end, Polynomials a, Polynomials b, Polynomials c){
        this.start = start;
        this.end = end;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public void run() {
        for(int index=start; index<end; index++){
            if(index>c.getCoefficientCount())
                return;
            for(int j=0;j<=index;j++){
                if(j<a.getCoefficientCount() && (index-j)<b.getCoefficientCount()){
                    int val = a.polynomialCoefficients.get(j)*b.polynomialCoefficients.get(index-j);
                    c.polynomialCoefficients.set(index, c.polynomialCoefficients.get(index)+val);
                }
            }
        }
    }
}
