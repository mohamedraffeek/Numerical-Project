package test;

public class CroutDecomposition {
    private int n = 3;
    private boolean enableScaling, error;
    private double[][] coef;

    private double[] b;
    private double[] scale;
    private int[] o;
    private double[] ans;
    private double tol;

    CroutDecomposition(double[][] coef, boolean enableScaling, double tol){
        this.coef = coef;
        this.enableScaling = enableScaling;
        this.n = coef.length;
        this.tol = tol;
        this.b = new double[n];
        this.scale = new double[n];
        this.o = new int[n];
        this.ans = new double[n];
        this.error = false;
    }
    double[][] lower = new double[n][n];
    double[][] upper = new double[n][n];

    public void initiate(){
    	for(int i=0; i<n; i++) {
    		b[i] = coef[i][n];
    	}
        for(int i =0; i <n; i++){
            for(int j=0; j<n; j++){
                lower[i][j] = 0;
                upper[i][j] = 0;
            }
        }
        for(int i=0; i<n; i++){
            upper[i][i] = 1;
        }
    }

    public void pivot(int k){
        int p = k;
        double big = Math.abs(coef[o[k]][k] / scale[o[k]]);
        for(int i = k + 1; i < n; ++i){
            double dummy1 = Math.abs(coef[o[i]][k] / scale[o[i]]);
            if(dummy1 > big){
                big = dummy1;
                p = i;
            }
        }
        int dummy2 = o[p];
        o[p] = o[k];
        o[k] = dummy2;
        double[] dummy3 = coef[p];
        coef[p] = coef[k];
        coef[k] = dummy3;
    }

    private void getLower_Upper(){
        initiate();
        if(!enableScaling){
            for(int i = 0; i < n; ++i) {
                o[i] = i;
                scale[i] = 1;
            }
        }else{
            for(int i = 0; i < n; ++i){
                o[i] = i;
                scale[i] = Math.abs(coef[i][0]);
                for(int j = 1; j < n; ++j){
                    if(Math.abs(coef[i][j]) > scale[i]){
                        scale[i] = Math.abs(coef[i][j]);
                    }
                }
            }
        }
        pivot(0);
        for (int j = 0; j < n; j++) {
            double sum;
            for (int i = j; i < n; i++) {
                sum = 0;
                for (int k = 0; k < j; k++) {
                    sum += lower[i][k] * upper[k][j];
                }
                lower[i][j] = coef[i][j] - sum;
            }
            for (int i = j; i < n; i++) {
                sum = 0;
                for (int k = 0; k < j; k++) {
                    sum += lower[j][k] * upper[k][i];
                }
                if (lower[j][j] != 0) {
                    upper[j][i] = (coef[j][i] - sum) / lower[j][j];
                }else
                    System.out.println(0);
            }
        }
        if(Math.abs(coef[o[n - 1]][n - 1]) / scale[o[n - 1]] < tol){
            error = true;
        }
    }
    public void Substitute(){
        double[] y = new double[n];
        //forward substitution
        for(int i = 0; i < n; ++i){
            double sum = b[o[i]];
            for(int j = 0; j < i; ++j){
                sum = sum - lower[i][j] * y[j];
            }
            y[i] = sum/lower[i][i];
        }
        //backward substitution
        ans[n - 1] = y[n - 1] ;
        for(int i = n - 2; i >= 0; --i){
            double sum = 0;
            for(int j = i + 1; j < n; ++j){
                sum = sum + upper[i][j] * ans[j];
            }
            ans[i] = (y[i] - sum);
        }
    }
    private double[] Solve(){
        getLower_Upper();
        Substitute();
        return ans;
    }

    //Testing
    /*public static void main(String[] args) {
        CroutDecomposition test;
        double[][] testCoef = {
                {25, 5, 1,0},
                {64, 8, 1,0},
                {144, 12, 1,1}
        };
        double[] testB = {
                0, 0, 1
        };
        test = new CroutDecomposition(testCoef, true, 1e-6);
        double[] testAns = test.Solve();
        for(int i = 0; i < 3; ++i){
            System.out.println(testAns[i]);
        }
    }
*/
}
