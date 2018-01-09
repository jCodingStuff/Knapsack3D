/**
* A class to solve using items and cargo
* @author Sarah Waseem
* @version 0.01, 09-01-2018
*/
public class Solver
{
  private Item[] items;
  private Cargo cargo;

  /**
  * Default Solver constructor
  * @param items the array of items
  * @param cargo the cargo object
  */

  public Solver(Item[] items, Cargo cargo)
  {
    this.items = items;
    this.cargo = cargo;
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
    String result = "Items=" + this.getItems() + " , Cargo=" + this.getCargo;
    return result;
  }
}
