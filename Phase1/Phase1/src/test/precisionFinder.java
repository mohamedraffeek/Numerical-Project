package test;

import java.math.BigDecimal;
import java.math.MathContext;

public class precisionFinder {
	public double precision(double num, int digits) {
		double result;
		BigDecimal resultBigDecimal = new BigDecimal(num);
		resultBigDecimal = resultBigDecimal.round(new MathContext(digits));
		result = resultBigDecimal.doubleValue();
		return result;
	}
}
