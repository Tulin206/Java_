package LinAlg3;

public class MatrixCAdapter implements LinearOperator<Complex, VectorC>{

    private MatrixC matrix;

    public MatrixCAdapter (MatrixC matrix){
        this.matrix = matrix;
    }

    public void setMatrix (MatrixC matrix){
        this.matrix = matrix;
    }



    @Override
    public VectorC apply(final VectorC x){
        // assert x instanceof VectorC;
        // VectorC vector = (VectorC)x;
        return this.matrix.mul(x);
    }
}
