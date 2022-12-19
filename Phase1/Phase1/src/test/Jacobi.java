package test;

import java.util.Arrays;

public class Jacobi {
    int n, significantDigits;
    int iterations;
    double[][] coef;
    double[] ans;
    precisionFinder precisionFinder = new precisionFinder();
    
    public Jacobi(double[][] ceof , int iterations, int digits) {
        this.coef = ceof;
        this.n = coef.length;
        this.significantDigits = digits;
        this.iterations = iterations;
        this.ans = new double[n];
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
    	for(int i=0; i<iterations ; i++) {
    		temp = ans;
    		for(int j=0 ; j<n ;j++) {
    			double sum = coef[j][n];
    			for(int k=0 ; k<n ; k++) {
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
