package LinAlg3;

public interface LinearOperator<F extends Field<F>, V extends VectorSpace<F,V>> {
    V apply (final V b);
}