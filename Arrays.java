import java.util.ArrayList;

/**
* A method that clones from Cargo
* @author Sarah Waseem
* @author Pierre Bongrand
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
*
* @author Julián Marrades
* @version 0.5, 13-01-2018
*/

public class Arrays {

  /**
  * Create a deep copy of a Pentomino array
  * @param ori the array to clone
  * @return the cloned array
  */
  public static Pentomino[] clonePArray(Pentomino[] ori) {
    Pentomino[] fut = new Pentomino[ori.length];
    for (int i = 0; i < fut.length; i++) {
      if (ori[i] != null) {
        fut[i] = ori[i].clone();
      }
    }
    return fut;
  }

  /**
  * Transforms an array of Pentominoes to an array of Items
  * @param ori the array of Pentominoes
  * @return the array of items
  */
  public static Item[] toItemArray(Pentomino[] ori) {
    Item[] fut = new Item[ori.length];
    for (int i = 0; i < ori.length; i++) {
      if (ori[i] != null) {
        fut[i] = ori[i].getItem().clone();
      }
    }
    return fut;
  }

  /**
  * Create a deep copy of an array of Items
  * @param ori the array to copy
  * @return the deep copy of the input array
  */
  public static Item[] cloneArray(Item[] ori) {
    Item[] fut = new Item[ori.length];
    for (int i = 0; i < fut.length; i++) {
      if (ori[i] != null) {
        fut[i] = ori[i].clone();
      }
    }
    return fut;
  }

  /**
  * Create a deep copy of a 3D-array of items
  * @param ori the 3D-array to copy
  * @return the deep copy of the input 3D-array
  */
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
      if (item.getName().equals(items[i].getName())) {
        found = true;
        index = i;
      }
      i++;
    }
    return index;
  }

  /**
  * Compare if two 3D Item arrays have the same filled spots
  * @param arr1 one array
  * @param arr2 another array
  * @return true if they match, false otherwise
  */
  public static boolean boolCompare(Item[][][] arr1, Item[][][] arr2) {
    boolean match = true;
    int i = 0;
    while (match && i < arr1.length) {
      int j = 0;
      while (match && j < arr1[i].length) {
        int k = 0;
        while (match && k < arr1[i][j].length) {
          if (arr1[i][j][k] != null && arr2[i][j][k] == null) {
            match = false;
          }
          else if (arr1[i][j][k] == null && arr2[i][j][k] != null) {
            match = false;
          }
          k++;
        }
        j++;
      }
      i++;
    }
    return match;
  }

  /**
  * Check if a list contains a 3D array that satisfies boolCompare
  * @param shape the shape to compare
  * @param shapes the list to search in
  * @return true if the list contains one shape that matches, false otherwise
  */
  public static boolean listContains(Item[][][] shape, ArrayList<Item[][][]> shapes) {
    boolean cont = false;
    int i = 0;
    while (!cont && i < shapes.size()) {
      if (boolCompare(shape, shapes.get(i))) {
        cont = true;
      }
      i++;
    }
    return cont;
  }

  /**
  * Create a deep copy of a 3D boolean array
  * @param ori the array to copy
  * @return the deep copy of the input array
  */
  public static boolean[][][] cloneBool3DArray(boolean[][][] ori) {
    boolean[][][] fut = new boolean[ori.length][ori[0].length][ori[0][0].length];
    for (int i = 0; i < ori.length; i++) {
      for (int j = 0; j < ori[i].length; j++) {
        for (int k = 0; k < ori[i][j].length; k++) {
          fut[i][j][k] = ori[i][j][k];
        }
      }
    }
    return fut;
  }

}
