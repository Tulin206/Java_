package LinAlg3;

import java.util.Arrays;


public class LinearSolverTest {
    
    private static void fillMatrix(MatrixD A, Real h){
        final int n  = A.getRows();

        Real[][] elem  = new Real[n][n];
        int i = 0;
        int j = 0;

        // for (Real[][] r : elem){
        //     Arrays.fill(r, new Real());
        // }

        for (i = 0; i < n; i++){
            for (j = 0; j < n; j++){
                elem[i][j] = new Real();
            }
        }

        for (i = 0; i < 1; i++){
            for (j = 0; j < n; j++){
                if (j == 0){
                    elem[i][j] = new Real(2.0).div(h.mul(h));
                    break;
                } else if (j == 1){
                    elem[i][j] = new Real(-1.0).div(h.mul(h));
                    break;
                } else {
                    elem[i][j] = new Real();
                }
            }
        }
        

        for (i = 1; i < n-1; i++){
            for (j = 0; j < i; j++){
                elem[i][i-1] = new Real(-1.0).div(h.mul(h));
                elem[i][i] = new Real(2.0).div(h.mul(h));
                elem[i][i + 1] = new Real(-1.0).div(h.mul(h));
                break;
            }
        }

        elem[n - 1][n - 2] = new Real(-1.0).div(h.mul(h));
        elem[n - 1][n - 1] = new Real(-1.0).div(h.mul(h));

        A.setElem(elem);
    }
    

    public static void fillVector (VectorD x, double n){
        final int l = x.getSize();
        Real elem[] = new Real[l];
        for (int i = 0; i < l; i++){
            elem[i] = new Real(n);
        }
        x.setElem(elem);
    }
    
    public static void main (final String args[]){
        int n = 100;
        
        if (args.length > 0){
            n  = Integer.parseInt(args[0]);
        }

        MatrixD A = new MatrixD (n, n);
        fillMatrix(A, new Real(1.e-2));

        VectorD x = new VectorD (n);
        fillVector(x, 0.0);

        VectorD b = new VectorD (n);
        fillVector(b, 1.0);

        MatrixAdapter L = new MatrixAdapter (A); 
        CGSolver <Real, VectorD, MatrixAdapter> solver = new CGSolver<> (L, 1.e-2, n);

            solver.apply(x, b);
            
            VectorD r = b.sub(L.apply(x));
            System.out.println("|b - L*x|: = " + r.norm());
    }
}
