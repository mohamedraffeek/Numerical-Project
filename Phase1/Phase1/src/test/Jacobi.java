package test;

import java.util.Arrays;

public class Jacobi {
    int n, significantDigits;
    int iterations;
    double[][] coef;
    double[] ans;
    double[] Guess;
    precisionFinder precisionFinder = new precisionFinder();
    
    public Jacobi(double[][] ceof , int iterations, int digits, double[] Guess) {
        this.coef = ceof;
        this.n = coef.length;
        this.significantDigits = digits;
        this.iterations = iterations;
        this.ans = new double[n];
        this.Guess = Guess;
    }
    
    private void significantDigits() {
		for(int i = 0; i < n; ++i) {
			for(int j = 0; j < n; ++j) {
				coef[i][j] = precisionFinder.precision(coef[i][j], significantDigits);
			}
		}
	}
    
    private double[] temp = new double[n];
    public double[] solve() {
    	significantDigits();
    	Arrays.fill(ans,0);
    	Arrays.fill(temp,0);
    	/*for(int i=0 ; i<n ;i++) {
    		ans[i] =  precisionFinder.precision(Guess[i], significantDigits);
    	}*/
    	for(int i=0; i<1 ; i++) {
    		temp = ans;
    		for(int j=0 ; j<n-1 ;j++) {
    			double sum = coef[j][n-1];
    			for(int k=0 ; k<n-1 ; k++) {
    				if(k!=j) {
    					sum-= coef[j][k]*temp[k];
    					sum = precisionFinder.precision(sum, significantDigits);
    				}
    			}
    			ans[j]= sum/coef[j][j];
    			ans[j] = precisionFinder.precision(ans[j], significantDigits);
    		}
    	}
    	return ans;
    }
}
