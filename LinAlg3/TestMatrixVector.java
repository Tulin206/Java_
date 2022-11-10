package LinAlg3;

public class TestMatrixVector {

  // check almost equality between double values
  private static boolean almost_equals(double x, double y, double tol) {
    return Math.abs(x-y) <= tol * Math.abs(x+y);
  }

  // overload of the almost_equals method without a tolerance argument
  private static boolean almost_equals(double x, double y) {
    return almost_equals(x,y,1.e-15);
  }

  /// Check some methods of the vectors, assuming they are given as in the main() method
  private static <F> void testVector(VectorSpace<F> x, VectorSpace<F> b) {
    var xb = x.add(b);
    assert b.add(x).equals(xb);

    double n = ((NormedVectorSpace<F>)x).norm();
    assert almost_equals(n, Math.sqrt(Field.abs(((InnerProductSpace<F>)x).inner(x))));
  }

  private static <F> void testInerfaces(LinearOperator<F> A, VectorSpace<F> x, VectorSpace<F> b)
  {
    var r = b.sub(A.apply(x));
    double residuum = ((NormedVectorSpace<F>)r).norm();
    assert almost_equals(residuum, 0.0);
  }


  public static void main(String[] args) {
    { // test double type
      MatrixD A = new MatrixD(2,2);
      double[][] arr = new double[2][2];
      arr[0][0] = 1.0;
      arr[0][1] = 0.0;
      arr[1][0] = 0.0;
      arr[1][1] = 1.0;
      A.setElem(arr);

      VectorD x = new VectorD(2);
      double[] array = new double[2];
      array[0] = 4.0;
      array[1] = 2.0;
      x.setElem(array);

      VectorD b = A.mul(x);

      testVector(x,b);

      MatrixDAdapter linOp = new MatrixDAdapter(A);
      testInerfaces(linOp,x,b);
    }

    { // test Complex type
      MatrixC A = new MatrixC(2,2);
      Complex[][] elem = new Complex[2][2];
      elem[0][0] = new Complex(1.0);
      elem[0][1] = new Complex(0.0);
      elem[1][0] = new Complex(0.0);
      elem[1][1] = new Complex(1.0);
      A.setElem(elem);

      VectorC x = new VectorC(2);
      Complex[] element = new Complex[2];
      element[0] = new Complex(4.0, -1.0);
      element[1] = new Complex(2.0, 1.0);
      x.setElem(element);

      VectorC b = A.mul(x);

      testVector(x,b);

      MatrixCAdapter linOp = new MatrixCAdapter(A);
      testInerfaces(linOp,x,b);
    }
  }
}
