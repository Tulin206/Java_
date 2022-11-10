package LinAlg3;

// class Field{

//     public static double abs (final double x){
//         return x < 0 ? -x : x;
//     }

//     public static double abs (final Complex z){
//         return z.abs();
//     }

//     public static double abs (final Number n){
//         return abs(n.doubleValue());
//     }

//     public static <F> double abs (final F f){
//         if(f instanceof Number){
//             return abs((Number)f);
//         } else if (f instanceof Complex){
//             return abs((Complex)f);
//         }
//         assert false : "Type F has no overload Field.abs";
//         return 0.0;
//     }
// }

public interface InnerProductSpace<F extends Field<F>, V extends InnerProductSpace<F, V>> 
                                                          extends NormedVectorSpace<F, V> {

    public F inner (final V other);
    public V copy();

    @Override
    default double norm(){
        return Math.sqrt(this.inner((V)this).abs());
        // return Math.sqrt(Field.abs(this.inner(this)));
    }
}