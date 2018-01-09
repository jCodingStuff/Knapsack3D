/**
* A class to represent an Item for the Knapsack problem
*
* @author Julián Marrades
* @version 0.01, 08-01-2018

* @author Lucas Uberti-Bona
* @version 0.02, 09-01-2018
*/

public class Item {

  private String name;
  private int value;
  private int[][][] shape;

  /**
  * Construct a new Item
  * @param name the name of the item
  * @param value the value of the item
  * @param shape the 3D int matrix representing the item
  */
  public Item(String name, int value, int[][][] shape) {
    this.name = name;
    this.value = value;
    this.shape = shape;
  }

  /**
  * Construct a new Item
  * @param name the name of the item
  * @param value the value of the item
  * @param width the width of the item
  * @param height the height of the item
  * @param depth the depth of the item
  */
  public Item(String name, int value, int width, int height, int depth) {
    this.name = name;
    this.value = value;
    this.shape = new int[width][height][depth];
  }

  /**
  * Get access to the name of the item
  * @return the name of the item
  */
	public String getName() {
		return this.name;
	}

  /**
  * Give a new name to the item
  * @param name the new name of the item
  */
	public void setName(String name) {
		this.name = name;
	}

  /**
  * Get access to the shape of the item
  * @return the 3D int matrix representing the item
  */
  public int[][][] getShape() {
    return this.shape;
  }

  /**
  * Set a new shape for the item
  * @param shape the new 3D int matrix for the item
  */
  public void setShape(int[][][] shape) {
    this.shape = shape;
  }

  /**
  * Get access to the value of the item
  * @return the value of the item
  */
  public int getValue() {
    return this.value;
  }

  /**
  * Set a new value for the item
  * @param value the new value for the item
  */
  public void setValue(int value) {
    this.value = value;
  }

  /**
  * Get the width of the item
  * @return the width of the item
  */
  public int getWidth() {
    return this.shape.length;
  }

  /**
  * Get the height of the item
  * @return the height of the item
  */
  public int getHeight() {
    return this.shape[0].length;
  }

  /**
  * Get the depth of the item
  * @return the depth of the item
  */
  public int getDepth() {
    return this.shape[0][0].length;
  }

  /**
  * Get the volume of the item
  * @return the volume of the item
  */
  public int getVolume() {
    return this.getWidth() * this.getHeight() * this.getDepth();
  }

  /**
  * Get a string representation of the item
  * @return a string containing detailed information about the attributes
  */
  public String toString() {
    String result = this.getClass().getName() + "[name=" + this.name
    result += ", value=" + this.value + ", volume=" + this.getVolume();
    result += ", width=" + this.getWidth() + ", height=" + this.getHeight();
    result += ", depth=" + this.getDepth() + "]";
    return result;
  }

  /**
  * Clone the item
  * @return a new item, which is a clone of the current one
  */
  public Item clone() {
    int width = this.getWidth();
    int height = this.getHeight();
    int depth = this.getDepth();
    int[][][] new_matrix = new int[width][height][depth];
    return new Item(this.name, this.value, new_matrix);
  }

  /**
  * Returns all shapes of an Item
  * @return an array of items, all with different shapes
  */
  public Item[] getAllShapes() {
    int dimensions = 3;
    if(shape.length!=shape[0].length) {
      rotate(1, 2);
    }
    if(shape.length!=shape[0][0].length) {
      rotate(1, 3);
    }
    if(shape[0][0].length!=shape[0].length) {
      rotate(2, 3);
    }


  }

  /**
  * Rotates shape in the indicated axis
  * @param dim1 first dimension of the axis of rotation
  * @param dim2 second dimension of the axis of rotation
  * @return an item with a shape rotated 45 degrees in the indicated axis
  */
  public Item rotate(int dim1, int dim2) {
    int[][][] newShape = new int[shape.length][shape[0].length][shape[0][0].length];
    if(dim1==1&&dim2==2) {
      for(int i = 0; i< shape.length; i++){
        for (int j = 0; j < shape[i].length; j++){
          for (int k = 0; k < shape[i][j].length; k++){
                  newShape[i][j][k] = this.shape[j][i][k];
          }
        }
      }
    }
    
    else {}
  }

}
