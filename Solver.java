/**
* A class to solve using items and cargo
* @author Sarah Waseem
* @version 0.01, 09-01-2018
*
* @author Julián Marrades
* @version 0.02, 09-01-2018
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
  * See if an item fits in a certain position of the cargo
  * @param item the item to check
  * @param i the position on the x axis
  * @param j the position on the y axis
  * @param k the position on the z axis
  * @return true if the item fits, false otherwise
  */
  private boolean canBePut(Item item, int i, int j, int k) {
    int iWidth = i + item.getWidth();
    int iHeight = j + item.getHeight();
    int iDepth = k + item.getDepth();
    int cWidth = this.cargo.getWidth();
    int cHeight = this.cargo.getHeight();
    int cDepth = this.cargo.getDepth();
    if (iWidth <= cWidth && iHeight <= cHeight && iDepth <= cDepth) {
      return true;
    }
    else {
      return false;
    }
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

}
