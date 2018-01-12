/**
* A method that clones from Cargo
* @author Sarah Waseem
* @author Pierre Bongrad
* @version 0.1, 08-01-2018
*
* @author Julián Marrades
* @version 0.2, 10-01-2018
*
* @author Lucas Uberti-Bona
* @version 0.3, 10-01-2018
*
* @author Julián Marrades
* @version 0.4, 12-01-2018
*/

public class Arrays {

  public static Item[] cloneArray(Item[] ori) {
    Item[] fut = new Item[ori.length];
    for (int i = 0; i < fut.length; i++) {
      if (ori[i] != null) {
        fut[i] = ori[i].clone();
      }
    }
    return fut;
  }

  public static Item[][][] clone3DMatrix(Item[][][] ori) {
    Item[][][] myInt = new Item [ori.length][ori[0].length][ori[0][0].length];
    for(int i = 0; i< ori.length; i++){
        for (int j = 0; j < ori[i].length; j++){
            for (int k = 0; k < ori[i][j].length; k++){
              if (ori[i][j][k] != null) {
                myInt[i][j][k] = ori[i][j][k].clone();
              }
            }
        }
    }
    return myInt;
  }

  /**
  * Find the index of an item inside an array
  * @param item the item to search
  * @param items the array
  * @return the index if it finds it, -1 otherwise
  */
  public static int findIndex(Item item, Item[] items) {
    int index = -1;
    boolean found = false;
    int i = 0;
    while (!found && i < items.length) {
      if (item.equals(items[i])) {
        found = true;
        index = i;
      }
      i++;
    }
    return index;
  }

}
