package test;

import java.util.Arrays;

public class Jacobi {
    int n;
    int iterations;
    double[][] coef;
    double[] ans;
    
    public Jacobi(double[][] ceof , int iterations) {
        this.coef = ceof;
        this.n = coef.length;
        this.iterations = iterations;
        this.ans = new double[n];
    }
    private double[] temp = new double[n];
    public double[] solve() {
    	Arrays.fill(ans,0);
    	for(int i=0; i<iterations ; i++) {
    		temp = ans;
    		for(int j=0 ; j<n ;j++) {
    			double sum = coef[j][n];
    			for(int k=0 ; k<n ; k++) {
    				if(k!=j) {
    					sum-= coef[j][k]*temp[k];
    				}
    			}
    			ans[j]= sum/coef[j][j];
    		}
    	}
    	return ans;
    }
}
