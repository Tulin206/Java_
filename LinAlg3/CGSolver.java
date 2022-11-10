package LinAlg3;

import java.util.*;

public class CGSolver <F extends Field <F>, 
                       V extends InnerProductSpace <F, V>,
                       L extends LinearOperator<F, V>> 
                            implements LinearSolver <F, V> {


        // instance variable
        private final L lop;
        private double tol;
        private int maxIter;


        // constructor
        public CGSolver (L lop, double tol, int maxIter){
            this.lop = lop;
            this.tol = tol;
            this.maxIter = maxIter;
        }

        @Override
        public void apply(V x, final V b){
            
            // r0 = b − L[x0]
            V r = b.sub(lop.apply(x));
            
            // d0 = r0
            V d = r.copy();

            for(int k = 0; k <= maxIter; k++){
                // z = L[dk]
                V z = lop.apply(d);

                F r0 = r.inner(r);
                if (Math.sqrt(r0.abs()) < tol){
                    break;
                }

                // αk = <rk, rk> / <dk, z>
                F alpha = r0.div(d.inner(z));

                // xk+1 = xk + αk*dk
                x.axpy(alpha, d);

                // rk+1 = rk − αk*z
                r.axpy(alpha.neg(), z);

                // βk = <rk+1, rk+1> / <rk, rk>
                F bita = r.inner(r).div(r0);

                //dk+1 = rk+1 + βk*dk
                d.aypx(bita, r);

                // ||rk +1|| < tol
                if (r.norm() < tol){
                    break;
                }
            }
        }

}