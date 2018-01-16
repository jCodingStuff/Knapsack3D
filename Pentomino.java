import java.awt.Color;

/**
* A class to represent a Pentomino for the Knapsack problem
*
* @author Julián Marrades
* @version 0.1, 16-01-2018
*/

public class Pentomino {

  private Item item;
  private int name;
  private boolean[][][] shape;

  /**
  * Construct a new Pentomino
  * @param name the type of the Pentomino that is wanted
  */
  public Pentomino(String name) {
    this.name = name;
    switch (name) {
      case "L":
        this.buildL();
        break;
      case "P":
        this.buildP();
        break;
      case "T":
        this.buildT();
        break;
    }
  }

  /**
  * Build an L-type pentomino
  */
  private void buildL() {
    this.item = new Item(this.name, 3, 1, 1, 1, new Color(255, 0, 0));
    this.shape = new boolean[][][]{{{true, true, true, true}},
                                   {{true, false, false, false}}};
  }

  /**
  * Build an P-type pentomino
  */
  private void buildP() {
    this.item = new Item(this.name, 4, 1, 1, 1, new Color(0, 255, 0));
    this.shape = new boolean[][][]{{{true, true, true}},
                                   {{true, true, false}}};
  }

  /**
  * Build an T-type pentomino
  */
  private void buildT() {
    this.item = new Item(this.name, 5, 1, 1, 1, new Color(0, 0, 255));
    this.shape = new boolean[][][]{{{true, false, false}},
                                   {{true, true, true}},
                                   {{true, false, false}}};
  }

  /**
  * Get the starting coordinates of a pentomino
  * @return a integer array containing [x, y, z] positions
  */
  public int[] getStartCoordinates() {
    int[] tmp = new int[3];
    boolean stop = false;
    int i = 0;
    while (!stop && i < this.shape.length) {
      int j = 0;
      while (!stop && j < this.shape[i].length) {
        int k = 0;
        while (!stop && k < this.shape[i][j].length) {
          if (this.shape[i][j][k] == true) {
            tmp = new int[]{i, j, k};
          }
          k++;
        }
        j++;
      }
      i++;
    }
    return tmp;
  }

  /**
  * Get access to the shape of the Pentomino
  */
  public boolean[][][] getShape() {
    return this.shape;
  }

  /**
  * Set a new shape for the Pentomino
  * @param shape the new shape
  */
  public void setShape(boolean[][][] shape) {
    this.shape = shape;
  }

  /**
  * Get access to a certain position of the shape
  * @param i the position along the x-axis
  * @param j the position along the y-axis
  * @param k the position along the z-axis
  * @return the value of that position
  */
  public boolean check(int i, int j, int k) {
    return this.shape[i][j][k];
  }

  /**
  * Mirror a pentomino
  * @param pent the pentomino to rotate
  * @param dim1 the first axis
  * @param dim2 the second axis
  * @return the mirrored shape
  */
  public static boolean[][][] mirror(Pentomino pent, int dim1, int dim2) {
    boolean[][][] newShape = newShape = new boolean[shape.length][shape[0].length][shape[0][0].length];
    boolean[][][] shape = pent.getShape();
    int i1 = 0, j1 = 0, k1 = 0;
    if (dim1 == 0 && dim2 == 1) {
      int i2 = shape.length, j2 = 0, k2 = 0;
      while (i1 < shape.length) {
        j1 = 0;
        j2 = 0;
        while (j1 < shape[i1].length) {
          k1 = 0;
          k2 = 0;
          while (k1 < shape[i1][j1].length) {
            newShape[i2][j2][k2] = shape[i1][j1][k1];
            k1++;
            k2++;
          }
          j1++;
          j2++;
        }
        i1++;
        i2--;
      }
    }
    else if (dim1 == 0 && dim2 == 2) {
      int i2 = 0, j2 = 0, k2 = shape[0][0].length;
      while (i1 < shape.length) {
        j1 = 0;
        j2 = 0;
        while (j1 < shape[i1].length) {
          k1 = 0;
          k2 = shape[0][0].length;
          while (k1 < shape[i1][j1].length) {
            newShape[i2][j2][k2] = shape[i1][j1][k1];
            k1++;
            k2--;
          }
          j1++;
          j2++;
        }
        i1++;
        i2++;
      }
    }
    else if (dim1 == 1 && dim2 == 2) {
      int i2 = 0, j2 = 0, k2 = shape[0][0].length;
      while (i1 < shape.length) {
        j1 = 0;
        j2 = 0;
        while (j1 < shape[i1].length) {
          k1 = 0;
          k2 = shape[0][0].length;
          while (k1 < shape[i1][j1].length) {
            newShape[i2][j2][k2] = shape[i1][j1][k1];
            k1++;
            k2--;
          }
          j1++;
          j2++;
        }
        i1++;
        i2++;
      }
    }
    return newShape;
  }

  /**
  * Get a copy of the current Pentomino
  * @return the deep copy of the Pentomino
  */
  public Pentomino clone() {
    return new Pentomino(this.name);
  }

  /**
  * Get a string representation of the Pentomino
  * @return a string containing details about the attributes
  */
  public String toString() {
    return "[name=" + this.name + ", value=" + this.item.getValue() +"]";
  }

}
