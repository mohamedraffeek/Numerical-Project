package test;

public class Cholesky {

    private int n;
    private double[][] coef;
    private double[][] L;
    private double[][] U;
    private double[] ans;
    private double[] b;

    public Cholesky(double[][] ceof,double[] b) {
        this.coef = ceof;
        this.b = b;
        this.n = coef.length;
        this.U = new double[n][n];
        this.L = new double[n][n];
        this.ans = new double[n];
    }


    //check symmetric
    public static boolean CS(double[][] Arr, int n){
        double[][] Transpose=new double[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                Transpose[i][j]=Arr[j][i];
            }
        }
        boolean sym=true;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(Transpose[i][j]!=Arr[j][i]){
                    sym=false;
                }
                if(sym==false)break;
            }
        }
        return sym;
    }

    private void MainMethod() {
        for(int j=0;j<n;j++){
            for(int i=j;i<n;i++){
                if(i==j){
                    double sum=0;
                    for(int k=0;k<j;k++){
                        sum+=Math.pow(L[i][k],2);
                    }
                    L[i][j]=Math.sqrt(coef[i][j]-sum);
                }else{
                    double sum=0;
                    for(int k=0;k<j;k++){
                        sum+=(L[i][k]*L[j][k]);
                    }
                    L[i][j]=(coef[i][j]-sum)/L[j][j];
                }
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                U[i][j]=L[j][i];
            }
        }
    }

    private void Substitution() {
        double[] y = new double[n];
        //forward substitute
        for(int i=0;i<n;i++){
            double sum=0;
            for(int j=0;j<i;j++){
                sum+=(L[i][j]*y[j]);/*sd*/
            }
            y[i]=(1/L[i][i])*(b[i]-sum);
        }
        //backword substitute
        for(int i=n-1;i>=0;i--){
            double sum=0;
            for(int j=i+1;j<n;j++){
                sum+=(U[i][j]*ans[j]);
            }
            ans[i]=(1/U[i][i])*(y[i]-sum);
        }
    }

    public double[] Solve() {
        //return ans ={0} if matrix not symmetic
        if(!CS(coef,n))return ans;
        MainMethod();
        Substitution();
        return ans;
    }
     /*
       //testing
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(L[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(U[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("DONE CHELOSKY TIME OF LU");
    //test y
        for(int i=0;i<n;i++){
        System.out.print(y[i]+" ");
    }
        System.out.println();
    //test ans
        for(int i=0;i<n;i++){
        System.out.print(ans[i]+" ");
    }*/

    public static void main(String[] args) {
        Cholesky test;
        double[][] testCoef = {
                {6, 15, 55},
                {15, 55, 225},
                {55, 225, 979}
        };
        double[] testB = {
                76, 295, 1259
        };
        test = new Cholesky(testCoef, testB);
        double[] testAns = test.Solve();
        for(int i = 0; i < 3; ++i){
            System.out.println(testAns[i]);
        }
    }
}
