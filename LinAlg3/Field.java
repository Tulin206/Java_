package LinAlg3;

public interface Field <F extends Field<F>> {
    public F add(F other);
    public F sub(F other);
    public F mul(F other);
    public F div(F other);
    public F neg ();
    public double abs();    
}
