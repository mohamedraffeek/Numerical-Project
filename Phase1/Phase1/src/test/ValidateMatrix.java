package test;

public class ValidateMatrix {

	String[][] mat;
	int n;
	
	public ValidateMatrix(String[][] validateMat) {
		this.mat = validateMat;
		this.n = mat.length;
	}
	
	public String[][] validate() {
		for(int i = 0; i < n; ++i) {
			for(int j = 0; j < n + 1; ++j) {
				//check for blank entries
				if(mat[i][j].isBlank()) {
					mat[i][j] = "0";
					continue;
				}
				//check for non-numeric entries
				try {
					double temp = Double.parseDouble(mat[i][j]);
				} catch (Exception e) {
					mat[0][0] = "Error";
					return mat;
				}
			}
		}
		return mat;
	}
	
}
