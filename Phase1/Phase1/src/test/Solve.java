package test;

import test.GaussElimination.*;

public class Solve {
	
	private String method = "Gauss Elimination";
	private String LUType = "Dolittle";
	private double coef[][], specialCoef[][];
	private double b[];
	double ans[];
	int significantDigits;
	
	public Solve(String method, String LUType, double[][] coef, double[][] specialCoef, double[] b, int digits) {
		this.method = method;
		this.LUType = LUType;
		this.coef = coef;
		this.specialCoef = specialCoef;
		this.b = b;
		this.significantDigits = digits;
	}
	
	public double[] chooseMethod() {
		switch(method) {
			case "Gauss Elimination" : {
				GaussElimination obj = new GaussElimination(coef, true, significantDigits);
				ans = obj.solve();
				break;
			}
			case "LU Decomposition" : {
				switch (LUType) {
					case "Dolittle" : {
						DoolittleLU obj = new DoolittleLU(specialCoef, b, true, significantDigits);
						ans = obj.Solve();
						break;
					}
					case "Crout" : {
						CroutDecomposition obj = new CroutDecomposition(coef, true, significantDigits);
						ans = obj.Solve();
						break;
					}
					case "Cholesky" : {
						
						break;
					}
				}
				break;
			}
			case "Gauss Jordan" : {
				GaussJordan obj = new GaussJordan(coef, true, significantDigits);
				ans = obj.solve();
				break;
			}
			case "Gauss Seidel" : {
				GaussSeidel obj = new GaussSeidel(coef, 10, significantDigits);
				ans = obj.solve();
				break;
			}
			case "Jacobi Iteration" : {
				Jacobi obj = new Jacobi(coef, 10, significantDigits);
				ans = obj.solve();
				break;
			}
		}
		return ans;
	}

}
