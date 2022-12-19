package test;

import test.GaussElimination.*;

public class Solve {
	
	private String method = "Gauss Elimination";
	private double coef[][];
	double ans[];
	int significantDigits;
	
	public Solve(String method, double[][] coef, int digits) {
		this.method = method;
		this.coef = coef;
		this.significantDigits = digits;
	}
	
	public double[] chooseMethod() {
		switch(method) {
			case "Gauss Elimination" : {
				GaussElimination obj = new GaussElimination(coef, true, significantDigits);
				ans = obj.solve();
			}
		}
		return ans;
	}

}
