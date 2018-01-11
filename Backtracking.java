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

  /**
  * See if an item fits in a certain position of the cargo
  * @param item the item to check
  * @param shape the shape of the cargo
  * @param i the position on the x axis
  * @param j the position on the y axis
  * @param k the position on the z axis
  * @return true if the item fits, false otherwise
  */
  public static boolean canBePut(Item item, Item[][][] shape, int i, int j, int k) {
    boolean permission = true;
    int w = i;
    while (permission && w < i + item.getWidth()) {
      int h = j;
      while (permission && h < j + item.getHeight()) {
        int d = k;
        while (permission && d < k + item.getDepth()) {
          if (w >= shape.length || h >= shape[w].length || d >= shape[w][h].length) {
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
