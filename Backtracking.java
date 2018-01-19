import java.util.ArrayList;

/**
* A class with a backtracking algorithm that tries to fill the total volume of
* a cargo
*
* @author Julián Marrades
* @version 0.1, 11-01-2018
*
* @author Julián Marrades
* @version 0.2, 12-01-2018
*/

public class Backtracking {

  public static ArrayList<Item[][][]> cargos = new ArrayList<Item[][][]>();
  public static Cargo tmp;
  public static boolean solved = false;

  /**
  * Try to fill the cargo with certain types of items
  * @param items the set of items that can be used
  * @param shape the cargo
  */
  public static long iterations = 0;
  public static void solveFor(Item[] items, Item[][][] shape) {
    iterations++;
    if(iterations%500_000 == 0) {
      System.out.println(iterations + " iterations");
    }
    if (solved) return;
    if (isFull(shape)) {
      // System.out.println("The cargo is full");
      // print3DArray(shape);
      tmp = new Cargo("WTF", shape);
      solved = true;
      // tmp.printSolution(items);
      // System.exit(0);
      return;
    }
    for (int j = 0; j < shape[0].length; j++) {
      for (int k = 0; k < shape[0][0].length; k++) {
        for (int i = 0; i < shape.length; i++) {
          if (shape[i][j][k] == null) {
            for (int t = 0; t < items.length; t++) {
              for (Item item : Item.getAllShapes(items[t])) {
                // System.out.println(canBePut(item, shape, i, j, k));
                if (canBePut(item, shape, i, j, k)) {
                  Item[][][] newShape = insert(item, shape, i, j, k);
                  // System.out.println("Inserting " + item.getName());
                  if (shouldContinue(newShape)) {
                    solveFor(items, newShape);
                  }
                }
              }
            }
            // System.out.println("Going back!");
            return;
          }
        }
      }
    }
  }

  /**
  * Decide if continue the search
  * @param shape the current state of the cargo
  * @return if it should continue, false otherwise
  */
  public static boolean shouldContinue(Item[][][] shape) {
    for (int i = 0; i < shape.length; i++) {
      for (int j = 0; j < shape[i].length; j++) {
        for (int k = 0; k < shape[i][j].length; k++) {
          if (shape[i][j][k] == null && isolated(shape, i, j, k)) {
            // System.out.println("Isolated cell found!");
            return false;
          }
        }
      }
    }
    // System.out.println("Continuing...");
    return true;
  }

  /**
  * Check if a certain cell is isolated
  * @param shape the cargo that contains the cell
  * @param i the position along the x-axis
  * @param j the position along the y-axis
  * @param k the position along the z-axis
  * @return true is the cell is isolated, false otherwise
  */
  public static boolean isolated(Item[][][] shape, int i, int j, int k) {
    boolean iso = false;
    if (checkPosition(shape, i, j-1, k)) {  // TOP
      if (checkPosition(shape, i, j+1, k)) {  // BOTTOM
        if (checkPosition(shape, i, j, k+1)) {  // FRONT
          if (checkPosition(shape, i, j, k-1)) {  // REAR
            if (checkPosition(shape, i-1, j, k)) {  // LEFT
              if (checkPosition(shape, i+1, j, k)) {  // RIGHT
                iso = true;
              }
            }
          }
        }
      }
    }
    return iso;
  }

  /**
  * Check if a certain cell is full or out of bounds
  * @param shape the cargo that contains the cell
  * @param i the position along the x-axis
  * @param j the position along the y-axis
  * @param k the position along the z-axis
  * @return true is the cell is out or full, false otherwise
  */
  public static boolean checkPosition(Item[][][] shape, int i, int j, int k) {
    boolean off = false;
    if (i < 0 || i >= shape.length) {
      off = true;
    }
    else if (j < 0 || j >= shape[0].length) {
      off = true;
    }
    else if (k < 0 || k >= shape[0][0].length) {
      off = true;
    }
    else if (shape[i][j][k] != null) {
      off = true;
    }
    return off;
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
    int w = 0;
    while (permission && w < item.getWidth()) {
      int h = 0;
      while (permission && h < item.getHeight()) {
        int d = 0;
        while (permission && d < item.getDepth()) {
          if (w + i >= shape.length || h + j >= shape[w + i].length || d + k >= shape[w + i][h + j].length) {
            permission = false;
          }
          else if (shape[w+i][h+j][d+k] != null) {
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
  public static Item[][][] insert(Item item, Item[][][] shape, int i, int j, int k) {
    Item[][][] newShape = new Item[shape.length][shape[0].length][shape[0][0].length];
    for (int x = 0; x < newShape.length; x++) {
      for (int y = 0; y < newShape[x].length; y++) {
        for (int z = 0; z < newShape[x][y].length; z++) {
          newShape[x][y][z] = shape[x][y][z];
        }
      }
    }
    Item newItem = item.clone();
    for (int w = 0; w < item.getWidth(); w++) {
      for (int h = 0; h < item.getHeight(); h++) {
        for (int d = 0; d < item.getDepth(); d++) {
          newShape[w + i][h + j][d + k] = newItem;
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
      if (item != null) {
        System.out.print(item.getName());
      }
      else {
        System.out.print("N");
      }
      System.out.print(" ");
    }
    System.out.println("]");
  }

}
