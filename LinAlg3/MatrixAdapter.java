package LinAlg3;

public class MatrixAdapter implements LinearOperator<Real, VectorD> {

    private MatrixD matrix;

    public MatrixAdapter (MatrixD matrix){
        this.matrix = matrix;
    }

    public void setMatrix (MatrixD matrix){
        this.matrix = matrix;
    }



    @Override
    public VectorD apply(final VectorD x){
        //assert x instanceof VectorD;
        //VectorD vector = (VectorD)x;
        return this.matrix.mul(x);
    }
}
