package LinAlg3;

public interface NormedVectorSpace<F extends Field<F>, V extends NormedVectorSpace<F, V>> 
                                                                extends VectorSpace<F, V> {
    public double norm ();    
}