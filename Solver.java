/**
* A class to solve using items and cargo
* @author Sarah Waseem
* @version 0.1, 09-01-2018
*
* @author Julián Marrades
* @version 0.2, 09-01-2018
*
* @author Julián Marrades
* @version 0.3, 10-01-2018
*
* @author Julián Marrades
* @version 0.4, 12-01-2018
*/
public class Solver
{
  private Item[] items;
  private Cargo cargo;
  private String name;

  /**
  * Default Solver constructor
  * @param name the name of the solver
  * @param items the array of items
  * @param cargo the cargo object
  */
  public Solver(String name, Item[] items, Cargo cargo)
  {
    this.name = name;
    this.items = items;
    this.cargo = cargo;
  }

  /**
  * Get access to the name
  * @return the name of the solver
  */
  public String getName() {
    return this.name;
  }

  /**
  * Set a new name for the solver
  * @param name the new name
  */
  public void setName(String name) {
    this.name = name;
  }

  /**
  * Get access to the items
  * @return the items
  */
  public Item[] getItems()
  {
    return this.items;
  }
  /**
  * Sets the items
  * @param items the new array of items
  */
  public void setItems(Item[] items)
  {
    this.items = items;
  }
  /**
  * Gets access to cargo
  * @return cargo
  */
  public Cargo getCargo()
  {
    return this.cargo;
  }
  /**
  * Sets cargo
  * @param cargo the new cargo
  */
  public void setCargo(Cargo cargo)
  {
    this.cargo = cargo;
  }

  /**
  * Get a string representation
  * @return a string containing the detailed information
  */
  public String toString()
  {
    String result = this.getClass().getName() + ":\n";
    result += " - Name -> " + this.name;
    result += "\n - Cargo -> " + this.cargo.toString();
    result += "\n - Items:";
    for (Item item : this.items) {
      result += "\n\t- " + item.toString();
    }
    return result;
  }

  /**
  * Clone the current Solver
  * @return the cloned solver object
  */
  public Solver clone() {
    Item[] new_items = Arrays.cloneArray(this.items);
    return new Solver(this.name, new_items, this.cargo.clone());
  }

  /**
  * Fill the cargo with the greedy algorithm
  * @param random is random filling wanted?
  * @param output want to get detailed solution?
  */
  public void fillGreedyCargo(boolean random, boolean output) {
    Item[] sorted = null;
    Item[] all = null;
    if (random) {
      sorted = Item.getAllShapes(this.items);
      all = shuffle(sorted);
    }
    else {
      sorted = Item.jSort(this.items);
      all = Item.getAllShapes(sorted);
    }
    // Loop through the whole cargo and fill any empty space
    for (int j = 0; j < this.cargo.getHeight(); j++) {
      for (int i = 0; i < this.cargo.getWidth(); i++) {
        for (int k = 0; k < this.cargo.getDepth(); k++) {
          if (this.cargo.check(i, j, k) == null) {
            this.tryFill(all, i, j, k);
          }
        }
      }
    }
    // Backtracking.print3DArray(this.cargo.getShape());
    this.cargo.printSolution(this.items, false, output);
  }

  /**
  * Shuffle an array of elements
  * @param ori original arrey
  * @return  shuffled array
  */
  public static Item[] shuffle(Item[] ori) {
    Item[] result = new Item[ori.length];
    int i = 0;
    while(i < ori.length) {
      int j = (int) (Math.random()*ori.length);
      if(result[j] == null){
        result[j] = ori[i].clone();
        i++;
      }
    }
    return result;
  }


  /**
  * Try to fit an item from a set in a position
  * @param items the set of items which can be put in the cargo
  * @param i the position along the x-axis
  * @param j the position along the y-axis
  * @param k the position along the z-axis
  */
  private void tryFill(Item[] items, int i, int j, int k) {
    boolean filled = false;
    int index = 0;
    while (!filled && index < items.length) {
      if (this.cargo.canBePut(items[index], i, j, k)) {
        this.cargo.put(items[index], i, j, k);
        filled = true;
      }
      index++;
    }
  }

}
