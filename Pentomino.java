import java.awt.Color;

/**
* A class to represent a Pentomino for the Knapsack problem
*
* @author Julián Marrades
* @version 0.1, 16-01-2018
*
* @author Lucas Uberti-Bona
* @version 0.2, 16-01-2018
*
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
  * Build an T-type pentomino
  */
  private void buildT() {
    this.item = new Item(this.name, 5, 1, 1, 1, new Color(0, 0, 255));
    this.shape = new boolean[][][]{{{true, false, false}},
                                   {{true, true, true}},
                                   {{true, false, false}}};
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

  /**
  * Get all shapes of an Pentomino
  * @param ori Item that we want to get all shapes of
  * @return an array containing an array of Pentominoes with all the different getAllShapes
  */
  public static Pentomino[] getAllShapes(Pentomino ori) {

  }

  /**
  * Rotates shape in the indicated axis
  * @param ori the Pentomino we want to rotate
  * @param dim1 first dimension of the axis of rotation
  * @param dim2 second dimension of the axis of rotation
  * @return an item with a shape rotated 45 degrees in the indicated axis
  */
  public static boolean[][][] rotate(Pentomino ori, int dim1, int dim2) {
    boolean[][][] newShape;
    boolean[][][] shape = ori.getShape();
    if (dim1==0&&dim2==1) {
      newShape = new boolean[shape[0].length][shape.length][shape[0][0].length];
      for (int i = 0; i < shape.length; i++) {
        for (int j = 0; j < shape[0].length; j++) {
          for (int k = 0; k < shape[0][0].length; k++) {
            newShape[shape[0].length-j][i][k] = shape[i][j][k];
          }
        }
      }
    }

    else if (dim1==0&&dim2==2) {
      newShape = new boolean[shape[0][0].length][shape[0].length][shape.length];
      for (int i = 0; i < shape.length; i++) {
        for (int j = 0; j < shape[0].length; j++) {
          for (int k = 0; k < shape[0][0].length; k++) {
            newShape[k][j][shape[0].length-i] = shape[i][j][k];
          }
        }
      }
    }

    else if (dim1==1&&dim2==2) {
      newShape = new boolean[shape.length][shape[0][0].length][shape[0].length];
      for (int i = 0; i < shape.length; i++) {
        for (int j = 0; j < shape[0].length; j++) {
          for (int k = 0; k < shape[0][0].length; k++) {
            newShape[i][shape[0].length-k][j] = shape[i][j][k];
          }
        }
      }
    }
    else {
      newShape = new boolean[1][1][1];
    }
    return newShape;
  }

}
