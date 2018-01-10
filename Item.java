/**
* A class to represent an Item for the Knapsack problem
* @author Julián Marrades
* @version 0.01, 08-01-2018
*
* @author Lucas Uberti-Bona
* @author Silvia Fallone
* @version 0.02, 09-01-2018
*
* @author Julián Marrades
* @version 0.03, 10-01-2018
*/

public class Item {

  private static int counter = 0;

  private String name;
  private int value;
  private int[] shape;
  private int serialNumber;

  /**
  * Construct a new Item
  * @param name the name of the item
  * @param value the value of the item
  * @param shape the int array representing the dimensions of the item
  */
  public Item(String name, int value, int[] shape) {
    counter++;
    this.serialNumber = counter;
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
    counter++;
    this.serialNumber = counter;
    this.name = name;
    this.value = value;
    this.shape = new int[]{width, height, depth};
  }

  /**
  * Get access to the serial number of the item
  * @return the serial number
  */
  public int serialNumber() {
    return this.serialNumber;
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
  * @return the int array representing the item
  */
  public int[] getShape() {
    return this.shape;
  }

  /**
  * Set a new shape for the item
  * @param shape the new int array for the item
  */
  public void setShape(int[] shape) {
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
    return this.shape[0];
  }

  /**
  * Set a new width for the item
  * @param width the new width
  */
  public void setWidth(int width) {
    this.shape[0] = width;
  }

  /**
  * Get the height of the item
  * @return the height of the item
  */
  public int getHeight() {
    return this.shape[1];
  }

  /**
  * Set a new height for the item
  * @param height the new height
  */
  public void setHeight(int height) {
    this.shape[1] = height;
  }

  /**
  * Get the depth of the item
  * @return the depth of the item
  */
  public int getDepth() {
    return this.shape[2];
  }

  /**
  * Set a new depth for the item
  * @param depth the new depth
  */
  public void setDepth(int depth) {
    this.shape[2] = depth;
  }

  /**
  * Get the volume of the item
  * @return the volume of the item
  */
  public int getVolume() {
    return this.shape[0] * this.shape[1] * this.shape[2];
  }

  /**
  * Get the ratio value/volume of the item
  * @return the ratio
  */
  public double getRatio() {
    return (double)this.value/(double)this.getVolume();
  }

  /**
  * Get a string representation of the item
  * @return a string containing detailed information about the attributes
  */
  public String toString() {
    String result = this.getClass().getName() + "[name=" + this.name;
    result += ", ratio=" + this.getRatio();
    result += ", value=" + this.value + ", volume=" + this.getVolume();
    result += ", width=" + this.shape[0] + ", height=" + this.shape[1];
    result += ", depth=" + this.shape[2] + "]";
    return result;
  }

  /**
  * Clone the item
  * @return a new item, which is a clone of the current one
  */
  public Item clone() {
    int width = this.shape[0];
    int height = this.shape[1];
    int depth = this.shape[2];
    int[] new_array = new int[]{width, height, depth};
    return new Item(this.name, this.value, new_array);
  }

  /**
  * Sort an array of items by decreasing ratio
  * @param input the array to sort
  */
  public static void sort(Item[] input) {

    Item x = null;
    Item y = null;
    int z = 0;

    for (int i = 0; i < input.length; i++) { //loops through given array
      x = input[i]; //stores current Item
      for (int j = i+1; j < input.length; j++) {
        /* loops through the rest of the array from i+1 onwards
        because the parts that comes before is already sorted
        */
        if (x.getRatio() < input[j].getRatio()) { //sorts based on ratio
          y = input[i];
          x = input[j];
          z = j;
        }
      }
      input[i] = x;
      input[z] = y;
    }
  }

  /**
  * Returns all shapes of an Item
  * @return an array of items, all with different shapes
  */
  public Item[] getAllShapes() {
    Item[] result = new Item[];
    if(shape.length!=shape[0].length) {
      shape = rotate(1, 2).clone();
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
    int[][][] newShape;
    if(dim1==1&&dim2==2) {
      newShape =  = new int[shape[0].length][shape.length][shape[0][0].length];
    }

    else if (dim1==1&&dim2==3) {
      newShape = new int[shape[0][0].length][shape[0].length][shape.length];
    }

    else if (dim1==2&&dim2==3) {
      newShape = new int[shape.length][shape[0][0].length][shape[0].length];
    }
  }

  return newShape;
}
