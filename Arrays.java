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
  * Print a matrix to the screen
  * @param matrix the matrix to print
  */
  public static void printMatrix(Item[][] matrix) {
    for (Item[] array : matrix) {
      printArray(array);
    }
  }

  /**
  * Print an array to the screen
  * @param array the array to print
  */
  public static void printArray(Item[] array) {
    System.out.print("[ ");
    for (Item item : array) {
      System.out.print(item.getName() + " ");
    }
    System.out.println("]");
  }

}
