/**
* A class with a backtracking algorithm that tries to fill the total volume of
* a cargo
*
* @author Julián Marrades
* @version 0.1, 11-01-2018
*/

public class Backtracking {

  public static void solveFor(Item[] item, boolean[] used, Item[][][] shape) {

  }

  /**
  * Determine if a 3D Item array is full or not
  * @param shape the 3D array to check
  * @return true if it is full, false otherwise
  */
  public static boolean isFull(Item[][][] shape) {
    boolean full = true;
    int i = 0;
    while (full && i < shape.length) {
      int j = 0;
      while (full && j < shape[i].length) {
        int k = 0;
        while (full && k < shape[i][j].length) {
          if (shape[i][j][k] == null) {
            full = false;
          }
          k++;
        }
        j++;
      }
      i++;
    }
    return full;
  }

}
