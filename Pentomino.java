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
