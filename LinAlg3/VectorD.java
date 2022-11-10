package LinAlg3;

import java.util.Scanner;

public class VectorD implements InnerProductSpace<Real, VectorD> {
    private static Scanner scanner;
    private int size;


    //Store the vector elements in a one-dimensional (non-public) array of type double.
    private Real [] elem;


    //Copy Constructor
    public VectorD(VectorD v){
      this.size = v.size;
      this.elem = v.elem;
    }

    @Override
    //copy method
    public VectorD copy(){
      return new VectorD (this);
    }

    //constructor
    //read the number of size as well as the vector entries from keyboard input.
    public VectorD(){
      this(true);
    }

    //with passed number of size.
    public VectorD (int size){
      this.size = size;
      this.elem = new Real [size];
    }

    //with passed number of array size. 
    //If the argument keyboardIn is true, read the vector entries from keyboard input.
    public VectorD(int size, boolean keyboardIn){
      this(size);
      if(keyboardIn == true){
        readVector();
      }
    }

    //read the number of rows and columns from keyboard input. 
    //If the argument keyboardIn is true, read the matrix entries(row-wise) from keyboard input.
    public VectorD(boolean keyboardIn){
      System.out.print("Enter the number of size: "); 
      this.size = scanner.nextInt();
      if(keyboardIn){
        readVector();
      }
    }

    //Method for input
    public void readVector(){
      this.elem = new Real[size];
      System.out.println("Enter the elements of the array: ");
      for (int i=0; i<elem.length; i++){
        double number = 0.0;
        number = scanner.nextDouble();
        elem [i] = new Real(number);
      }
    }

    public int getSize() {
      return size;
    }

    public void setSize(int size) {
      this.size = size;
    }

    public Real[] getElem() {
      return elem;
    }

    public void setElem(Real[] elem) {
      this.elem = elem;
    }

    @Override
    //Method to compute the Euclidean inner product
    public Real inner(final VectorD other){
      // assert other instanceof VectorD;
      // VectorD x = (VectorD)other;
      Real sum = new Real();
      // double result = 0;
      if (this.size == other.size){
        for (int i=0; i<elem.length; i++){
          // result = this.elem[i] * other.elem[i];
          // sum = sum.add(new Real (result));
          sum = sum.add((this.elem[i].mul(other.elem[i])));
        }
      }
      return sum;
    }

    //@Override
    // public Real norm (){
    //   // double sum = 0;
    //   Real normalize = new Real();
    //   normalize = Math.sqrt(this.inner(this));
    //   // for (int i=0; i<elem.length; i++){
    //   //   sum = sum + (this.elem [i] * this.elem[i]);
    //   // }
    //   // normalize = Math.sqrt(sum);
    //   return normalize;
    // }

    @Override
    // this = this + (alpha * other)
    public VectorD axpy (final Real value, final VectorD other){
      // assert other instanceof VectorD;
      // VectorD v = (VectorD)other;
      if (this.size == other.size){
        for (int i=0; i<elem.length; i++){
      
          this.elem[i] = this.elem[i].add(other.elem[i].mul(value));
        }
      } else {
        System.out.println("Vectors are not equal");
      }
      return this;
    }

    // this = (alpha * this) + other
    public VectorD aypx (final Real value, final VectorD other){
      // assert other instanceof VectorD;
      // VectorD v = (VectorD)other;
      if (this.size == other.size){
        for (int i=0; i<elem.length; i++){
      
          this.elem[i] = this.elem[i].mul(value).add(other.elem[i]);
        }
      } else {
        System.out.println("Vectors are not equal");
      }
      return this;
    }

    // this + other
    @Override
    public VectorD add (final VectorD other){
      Real value = new Real(1.0);
      return new VectorD(this).axpy(value, other);
    }

  // public VectorD add(VectorD other){
  //   VectorD temp = new VectorD(this.size);
  //   if (this.size == other.size){
  //     for (int i=0; i<elem.length; i++){
  //         temp.elem[i] = this.elem[i] + other.elem[i];
  //     }
  //   } else {
  //     System.out.println("Vectors are not equal");
  //   }
  //   return temp;
  // }

  
    // this - other
    @Override
    public VectorD sub (final VectorD other){
      Real value = new Real(-1.0);
      return new VectorD(this).axpy(value, other);
    }

  // public VectorD sub(VectorD other){
  //   VectorD temp = new VectorD(this.size);
  //   if (this.size == other.size){
  //     for (int i=0; i<elem.length; i++){
  //         temp.elem[i] = this.elem[i] - other.elem[i];
  //     }
  //   } else {
  //     System.out.println("Vectors are not equal");
  //   }
  //   return temp;
  // }

  
  // return this *= factor
    public VectorD scale (Real value){
      for (int i = 0; i < this.size; i++){
        this.elem[i] = this.elem [i].mul(value);
      }
      return this;
    }

    public VectorD zero() {
      Real value = new Real();
      for (int i = 0; i < this.size; i++){
        this.elem[i] = this.elem [i].mul(value);
      }
      return this;
    }

    // return this*factor
    @Override
    public VectorD mul (final Real value){
      return new VectorD(this).scale(value);
    }
}