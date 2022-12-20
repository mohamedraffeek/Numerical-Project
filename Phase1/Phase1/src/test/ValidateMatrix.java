package test;

public class ValidateMatrix {

	String[][] mat;
	boolean isIterative;
	int n;
	
	public ValidateMatrix(String[][] validateMat, boolean isIterative) {
		this.mat = validateMat;
		this.n = mat.length;
		this.isIterative = isIterative;
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
		/*double max = Double.parseDouble(mat[0][0]);
		for(int i=0 ; i<n ;i++) {
			for(int j=0 ; j<n ; j++) {
				if(Double.parseDouble(mat[i][j])>max) {
					max = Double.parseDouble(mat[i][j]);
				}
			}
			for(int k=0 ; k<n+1 ; k++) {
				mat[i][k]= Double.toString(Double.parseDouble(mat[i][k])/max);
				System.out.println(mat[i][k]);
			}
		}

		for(int i=0 ; i<n-1 ; i++) {
			for(int k=i+1; k<n ;k++) {
				boolean rawsEqual = false ;
				boolean BsDiff = false ;
				for(int j=0 ; j<n ;j++) {
					if(Double.parseDouble(mat[i][j]) == Double.parseDouble(mat[k][j])) {
						rawsEqual = true;
						System.out.println("rawsEqual = true");
					}else {
						rawsEqual = false;
						System.out.println("rawsEqual = false");
						break;
					}
				}
				if(Double.parseDouble(mat[i][n]) != Double.parseDouble(mat[k][n])) {
					BsDiff = true;
					System.out.println("BsDiff = true");
				}
				if(rawsEqual == true && BsDiff == true) {
					System.out.println("rawsEqual == true && BsDiff == true");
					mat[0][0] = "Error1";
					return mat;
				}
			}
		}*/
		if(isIterative) {
			for(int i=0 ; i<n ; i++) {
				if(Double.parseDouble(mat[i][i]) == 0) {
					mat[0][0] = "Error1";
					return mat;
					}
			}
		}
		precisionFinder precisionFinder = new precisionFinder(); 
		for(int k = 0; k < n - 1; k++) {
            for(int i = k + 1; i < n; i++) {
                double multiplier = Double.parseDouble(mat[i][k]) / Double.parseDouble(mat[k][k]);
                multiplier = precisionFinder.precision(multiplier, 7);
                for(int j = k; j < n + 1; j++) {
                	mat[i][j] = Double.toString(Double.parseDouble(mat[i][j]) - multiplier * Double.parseDouble(mat[k][j]));
                	mat[i][j] = Double.toString(precisionFinder.precision(Double.parseDouble(mat[i][j]), 7));
                }
            }
        }
		//
		for(int i=0 ; i<n ; i++) {
			for(int j=0 ; j<n+1 ; j++) {
				System.out.print(mat[i][j]+" ");
			}
			System.out.println();
		}
		//
		for(int i=0 ; i<n ; i++) {
			boolean isZero = false;
			boolean BsNotZero = false;
			for(int j=0 ; j<n ; j++) {
				if(Double.parseDouble(mat[i][j]) == 0){
					isZero = true;
				}else {
					isZero = false;
					break;
				}
			}
			if(Double.parseDouble(mat[i][n]) != 0) {
				BsNotZero = true;
			}
			if(isZero && BsNotZero) {
				mat[0][0] = "Error1";
				return mat;
			}
		}
		return mat;
	}
	
}
