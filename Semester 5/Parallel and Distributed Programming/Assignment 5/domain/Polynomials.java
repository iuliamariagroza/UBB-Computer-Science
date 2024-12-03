package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Polynomials {
    int polynomialDegree;
    List<Integer> polynomialCoefficients;

    public Polynomials(int degree){
        polynomialDegree = degree;
        polynomialCoefficients = new ArrayList<>(degree+1);
        Random seed = new Random();

        for(int i=0;i<degree;i++){
            polynomialCoefficients.add(seed.nextInt(10));
        }
        polynomialCoefficients.add(seed.nextInt(10));
    }

    public Polynomials(List<Integer> coefficients){
        polynomialCoefficients = coefficients;
        this.polynomialDegree = polynomialCoefficients.size()-1;//degree = size-1
    }

    public int getCoefficientCount(){
        return this.polynomialCoefficients.size();
    }

    public int getDegree(){
        return this.polynomialDegree;
    }

    public static Polynomials addZeros(Polynomials polynomial, int offset){
        List<Integer> coeff= IntStream.range(0, offset).mapToObj(i -> 0).collect(Collectors.toList());
        coeff.addAll(polynomial.polynomialCoefficients);
        return new Polynomials(coeff);
    }

    public static Polynomials add(Polynomials a, Polynomials b) {
        int minDegree = Math.min(a.polynomialDegree, b.polynomialDegree);
        int maxDegree = Math.max(a.polynomialDegree, b.polynomialDegree);
        Polynomials bigPolynomial;
        List<Integer> coefficients = new ArrayList<>(maxDegree + 1);

        if (a.polynomialDegree > b.polynomialDegree) {
            bigPolynomial = a;
        } else {
            bigPolynomial = b;
        }

        for (int i = 0; i <= minDegree; i++) {
            coefficients.add(a.polynomialCoefficients.get(i) + b.polynomialCoefficients.get(i));
        }

        if (minDegree != maxDegree) {
            for (int i = minDegree + 1; i <= maxDegree; i++) {
                coefficients.add(bigPolynomial.polynomialCoefficients.get(i));
            }
        }

        return new Polynomials(coefficients);
    }

    public static Polynomials subtract(Polynomials a, Polynomials b) {
        int minDegree = Math.min(a.polynomialDegree, b.polynomialDegree);
        int maxDegree = Math.max(a.polynomialDegree, b.polynomialDegree);
        Polynomials bigPolynomial;
        List<Integer> coefficients = new ArrayList<>(maxDegree + 1);

        if (a.polynomialDegree > b.polynomialDegree) {
            bigPolynomial = a;
        } else {
            bigPolynomial = b;
        }

        for (int i = 0; i <= minDegree; i++) {
            coefficients.add(a.polynomialCoefficients.get(i) - b.polynomialCoefficients.get(i));
        }

        if (minDegree != maxDegree) {
            for (int i = minDegree + 1; i <= maxDegree; i++) {
                coefficients.add(bigPolynomial.polynomialCoefficients.get(i));
            }
        }
        int i = coefficients.size() - 1;

        while (coefficients.get(i) == 0 && i > 0) {
            coefficients.remove(i);
            i--;
        }
        return new Polynomials(coefficients);
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(int i=polynomialDegree;i>=0;i--){
            int coefficient = polynomialCoefficients.get(i);

            if(coefficient == 0){
                continue;
            }
            if(!result.isEmpty()){
                result.append(coefficient > 0 ? "+" : "-");
            }else if(coefficient < 0){
                result.append("-");
            }
            int absCoefficient = Math.abs(coefficient);

            if (absCoefficient != 1 || i == 0) {
                result.append(absCoefficient); 
            }

            if (i > 0) {
                result.append("x"); 
                if (i > 1) {
                    result.append("^").append(i); 
                }
            }
        }
        return !result.isEmpty() ? result.toString() : "0";
    }
}
