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
  private String name;
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
  * Get access to the item of the pentomino
  * @return the item of the pentomino
  */
  public Item getItem() {
    return this.item;
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
    boolean[][][] shape = pent.getShape();
    boolean[][][] newShape = new boolean[shape.length][shape[0].length][shape[0][0].length];
    if (dim1 == 0 && dim2 == 1) {
      for (int i = 0; i < shape.length; i++) {
        for (int j = 0; j < shape[i].length; j++) {
          for (int k = 0; k < shape[i][j].length; k++) {
            newShape[i][j][shape[i][j].length - 1 - k] = shape[i][j][k];
          }
        }
      }
    }
    else if (dim1 == 0 && dim2 == 2) {
      for (int i = 0; i < shape.length; i++) {
        for (int j = 0; j < shape[i].length; j++) {
          for (int k = 0; k < shape[i][j].length; k++) {
            newShape[i][shape[i].length - 1 - j][k] = shape[i][j][k];
          }
        }
      }
    }
    else if (dim1 == 1 && dim2 == 2) {
      for (int i = 0; i < shape.length; i++) {
        for (int j = 0; j < shape[i].length; j++) {
          for (int k = 0; k < shape[i][j].length; k++) {
            newShape[shape.length - 1 - i][j][k] = shape[i][j][k];
          }
        }
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

  public boolean equals(Object obj) {
    if (obj == null) return false;
    Pentomino other = (Pentomino) obj;
    boolean[][][] oShape = other.getShape();
    if (this.shape.length != oShape.length || this.shape[0].length != oShape[0].length || this.shape[0][0].length != oShape[0][0].length) {
      return false;
    }
    for (int i = 0; i < this.shape.length; i++) {
      for (int j = 0; j < this.shape[i].length; j++) {
        for (int k = 0; k < this.shape[i][j].length; k++) {
          if (this.shape[i][j][k] != oShape[i][j][k]) return false;
        }
      }
    }
    return true;
  }

  /**
  * Get a string representation of the Pentomino
  * @return a string containing details about the attributes
  */
  public String toString() {
    return "[name=" + this.name + ", value=" + this.item.getValue() +"]";
  }

  /**
  * Returns all shapes of an Item
  * @param ori is an array containing the Items for which we want all shapes
  * @return an array of items, all with different rotations of the input Items
  */
  public static Pentomino[] getAllShapes(Pentomino[] ori) {
    ArrayList<Pentomino[]> rawResult = new ArrayList<Pentomino[]>();
    for (Pentomino i : ori) {
      rawResult.add(getAllShapes(i));
    }

    ArrayList<Pentomino> result = new ArrayList<Pentomino>();
    for (Pentomino[] i : rawResult){
      for(Pentomino j : i) {
        result.add(j);
      }
    }

    Object[] arrResult = result.toArray();
    Pentomino[] finResult = Arrays.asList(arrResult).toArray(new Pentomino[arrResult.length]);
    return finResult;
  }


  /**
  * Get all shapes of an Pentomino
  * @param ori Item that we want to get all shapes of
  * @return an array containing an array of Pentominoes with all the different getAllShapes
  */
  public static Pentomino[] getAllShapes(Pentomino ori) {
    Pentomino[] result = new Pentomino[24];
    for (Pentomino i : result) {
      i = ori.clone();
    }

    for (int i = 1; i < 4; i++) {
      result[i].setShape(rotate(result[i-1], 0, 2));
    }
    result[4].setShape(mirror(ori, 1, 2));
    for (int i = 5; i < 8; i++) {
      result[i].setShape(rotate(result[i-1], 0, 2));
    }
    result[8].setShape(rotate(result[8], 1, 2));
    for (int i = 9; i < 12; i++) {
      result[i].setShape(rotate(result[i-1], 0, 2));
    }
    result[12].setShape(mirror(result[12], 0, 2));
    for (int i = 13; i < 16; i++) {
      result[i].setShape(rotate(result[i-1], 0, 2));
    }
    result[16].setShape(rotate(result[16], 0, 1));
    for (int i = 17; i < 20; i++) {
      result[i].setShape(rotate(result[i-1], 0, 2));
    }
    result[20].setShape(mirror(result[20], 0, 2));
    for (int i = 21; i < 24; i++) {
      result[i].setShape(rotate(result[i-1], 0, 2));
    }

    ArrayList<Pentomino> cleanResult = new ArrayList<Pentomino>();
    for(Pentomino i : result) {
      if(!cleanResult.contains(i)){
        cleanResult.add(i);
      }
    }

    Object[] arrResult = cleanResult.toArray();
    Pentomino[] finResult = Arrays.asList(arrResult).toArray(new Pentomino[arrResult.length]);
    return finResult;
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
