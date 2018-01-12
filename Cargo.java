/**
* A class to represent a Cargo for the Knapsack problem
* @author Julián Marrades
* @version 0.1, 09-01-2018
*/

public class Cargo {

  private String name;
  private Item[][][] shape;

  /**
  * Default Cargo constructor
  * @param name the name of the cargo
  * @param width the width of the cargo
  * @param height the height of the cargo
  * @param depth the depth of the cargo
  */
  public Cargo(String name, int width, int height, int depth) {
    this.name = name;
    this.shape = new Item[width][height][depth];
  }

  /**
  * Construct a new Cargo
  * @param name the name of the cargo
  * @param shape the 3D matrix representing the cargo
  */
  public Cargo(String name, Item[][][] shape) {
    this.name = name;
    this.shape = shape;
  }

  /**
  * Get access to the name of the cargo
  * @return the name of the cargo
  */
	public String getName() {
		return this.name;
	}

  /**
  * Give a new name to the cargo
  * @param name the new name of the cargo
  */
	public void setName(String name) {
		this.name = name;
	}

  /**
  * Get access to the shape of the cargo
  * @return the 3D Item matrix representing the cargo
  */
  public Item[][][] getShape() {
    return this.shape;
  }

  /**
  * Set a new shape for the cargo
  * @param shape the new 3D Item matrix for the cargo
  */
  public void setShape(Item[][][] shape) {
    this.shape = shape;
  }

  /**
  * Get the width of the cargo
  * @return the width of the cargo
  */
  public int getWidth() {
    return this.shape.length;
  }

  /**
  * Get the height of the cargo
  * @return the height of the cargo
  */
  public int getHeight() {
    return this.shape[0].length;
  }

  /**
  * Get the depth of the cargo
  * @return the depth of the cargo
  */
  public int getDepth() {
    return this.shape[0][0].length;
  }

  /**
  * Get the volume of the cargo
  * @return the volume of the cargo
  */
  public int getVolume() {
    return this.getWidth() * this.getHeight() * this.getDepth();
  }

  /**
  * See if an item fits in a certain position of the cargo
  * @param item the item to check
  * @param i the position on the x axis
  * @param j the position on the y axis
  * @param k the position on the z axis
  * @return true if the item fits, false otherwise
  */
  public boolean canBePut(Item item, int i, int j, int k) {
    boolean permission = true;
    int w = i;
    while (permission && w < i + item.getWidth()) {
      int h = j;
      while (permission && h < j + item.getHeight()) {
        int d = k;
        while (permission && d < k + item.getDepth()) {
          if (w >= this.getWidth() || h >= this.getHeight() || d >= this.getDepth()) {
            permission = false;
          }
          else if (this.shape[w][h][d] != null) {
            permission = false;
          }
          d++;
        }
        h++;
      }
      w++;
    }
    return permission;
  }

  /**
  * Put an item in a given position of the cargo
  * @param item the item to fit
  * @param i the position along the x-axis
  * @param j the position along the y-axis
  * @param k the position along the z-axis
  */
  public void put(Item item, int i, int j, int k) {
    Item newItem = item.clone();
    for (int w = i; w < i + item.getWidth(); w++) {
      for (int h = j; h < j + item.getHeight(); h++) {
        for (int d = k; d < k + item.getDepth(); d++) {
          this.shape[w][h][d] = newItem;
        }
      }
    }
  }

  /**
  * Get the content in a certain position of the container
  * @param i the position along the x-axis
  * @param j the position along the y-axis
  * @param k the position along the z-axis
  * @return the item at the position requested
  */
  public Item check(int i, int j, int k) {
    return this.shape[i][j][k];
  }

  /**
  * Get a string representation of the cargo
  * @return a string containing detailed information about the attributes
  */
  public String toString() {
    String result = this.getClass().getName() + "[name=" + this.name;
    result += ", volume=" + this.getVolume() + ", width=" + this.getWidth();
    result += ", height=" + this.getHeight() + ", depth=" + this.getDepth();
    result += "]";
    return result;
  }

  /**
  * Clone the current Cargo
  * @return a new cargo which is a copy of the current one
  */
  public Cargo clone() {
    return new Cargo(this.name, Arrays.clone3DMatrix(this.shape));
  }

}
