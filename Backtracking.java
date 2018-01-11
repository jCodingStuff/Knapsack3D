/**
* A class with a backtracking algorithm that tries to fill the total volume of
* a cargo
*
* @author Julián Marrades
* @version 0.1, 11-01-2018
*/

public class Backtracking {

  /**
  * Try to fill the cargo with certain types of items
  * @param items the set of items that can be used
  * @param shape the cargo
  */
  public static void solveFor(Item[] items, Item[][][] shape) {
    if (isFull(shape)) {
      print3DArray(shape);
      System.exit(0);
    }
    for (int i = 0; i < shape.length; i++) {
      for (int j = 0; j < shape[i].length; j++) {
        for (int k = 0; k < shape[i][j].length; k++) {
          if (shape[i][j][k] == null) {
            for (int t = 0; t < items.length; t++) {
              for (Item item : Item.getAllShapes(items[t])) {
                if (canBePut(item, )) {

                }
              }
            }
            return;
          }
        }
      }
    }
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
  * Insert an item in a given position of the cargo, creating a new version of it
  * @param item the item to fit
  * @param shape the cargo that recieves the item
  * @param i the position along the x-axis
  * @param j the position along the y-axis
  * @param k the position along the z-axis
  * @return new version of shape with the item inside
  */
  public Item[][][] insert(Item item, Item[][][] shape, int i, int j, int k) {
    Item[][][] newShape = Arrays.clone3DMatrix(shape);
    Item newItem = item.clone();
    for (int w = i; w < i + item.getWidth(); w++) {
      for (int h = j; j < j + item.getHeight(); h++) {
        for (int d = k; d < k + item.getDepth(); d++) {
          shape[w][h][d] = newItem;
        }
      }
    }
    return newShape;
  }

  /**
  * Print the slices of a 3D array to the screen
  * @param shape the 3D array to print
  */
  public static void print3DArray(Item[][][] shape) {
    for (Item[][] matrix : shape) {
      printMatrix(matrix);
      System.out.println();
    }
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
