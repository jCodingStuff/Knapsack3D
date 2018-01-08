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

  public Item(String name, int value, int[][][] shape) {
    this.name = name;
    this.value = value;
    this.shape = shape;
  }

  public Item(String name, int value, int width, int height, int depth) {
    this.name = name;
    this.value = value;
    this.shape = new int[width][height][depth];
  }

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

  public int[][][] getShape() {
    return this.shape;
  }

  public void setShape(int[][][] shape) {
    this.shape = shape;
  }

  public int getValue() {
    return this.value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public int getWidth() {
    return this.shape.length;
  }

  public int getHeight() {
    return this.shape[0].length;
  }

  public int getDepth() {
    return this.shape[0][0].length;
  }

  public Item clone() {
    int width = this.getWidth();
    int height = this.getHeight();
    int depth = this.getDepth();
    int[][][] new_matrix = new int[width][height][depth];
    return new Item(this.name, this.value, new_matrix);
  }

}
