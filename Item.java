/**
* A class to represent an Item for the Knapsack problem
*
* @author Julián Marrades
* @version 0.01, 08-01-2018
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

}
