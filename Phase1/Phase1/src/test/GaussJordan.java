package test;

public class GaussJordan {

    int n;
    boolean enableScaling;
    double[][] coef;
    double[] scale;
    double[] ans;

    public GaussJordan(double[][] ceof, boolean enableScaling) {
        this.coef = ceof;
        this.enableScaling = enableScaling;
        this.n = coef.length;
        this.scale = new double[n];
        this.ans = new double[n];
    }

    private void scaling() {
        if(!enableScaling) {
            for(int i = 0; i < n; i++) {
                scale[i] = 1;
            }
            return;
        }
        for(int i = 0; i < n; i++) {
            scale[i] = Math.abs(coef[i][0]);
            for(int j = 1; j < n; j++) {
                if(Math.abs(coef[i][j]) > scale[i]) {
                    scale[i] = Math.abs(coef[i][j]);
                }
            }
        }
    }

    private void partialPivoting(int k) {
        double pivot = Math.abs(coef[k][k] / scale[k]);
        int pivotRow = k;
        for(int i = k + 1; i < n; i++) {
            if(Math.abs(coef[i][k] / scale[i]) > pivot) {
                pivot = Math.abs(coef[i][k] / scale[i]);
                pivotRow = i;
            }
        }
        if(pivotRow == k) return;
        for(int j = k; j < n + 1; j++) {
            double tmp = coef[k][j];
            coef[k][j] = coef[pivotRow][j];
            coef[pivotRow][j] = tmp;
        }
    }

    private void elimination() {
        for(int k = 0; k < n; k++) {
            partialPivoting(k);
            for(int i = 0; i < n && i != k; i++) {
                double multiplier = coef[i][k] / coef[k][k];
                for(int j = k; j < n + 1; j++) {
                    coef[i][j] = coef[i][j] - multiplier * coef[k][j];
                }
            }
        }
    }

    private void substitution() {
        for(int i = 0; i < n; i++) {
            ans[i] = coef[i][n] / coef[i][i];
        }
    }

    public double[] solve() {
        scaling();
        elimination();
        substitution();
        return ans;
    }

}
