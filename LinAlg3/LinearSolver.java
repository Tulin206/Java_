package LinAlg3;

public interface LinearSolver <F extends Field<F>, V extends VectorSpace<F, V>> 
                                                        extends LinearOperator <F, V> {

    /**
    * Solve the linear system ‘L*x = b‘
    * @param[inout] initial and final solution
    * @param[in]
    right-hand side of the linear system
    **/


    void apply(V x, final V b);


    @Override
    default V apply (final V b){
        V x = b.zero();
        this.apply(x, b);
        return x;
    }
}
