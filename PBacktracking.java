/**
* A backtracking algorithm for Pentominoes
*/
public class PBacktracking {

  public static Cargo tmp;
  public static boolean solved = false;
  public static long iterations = 0;

  /**
  * A recursive method to fill the cargo with pentominoes
  * @param pentominoes the set of Pentominoes available
  * @param cargo the cargo to fill
  * @param optimized is optimization wanted?
  * @param counter call counter
  */
  public static void solveFor(Pentomino[] pentominoes, Item[][][] cargo, boolean optimized, int counter) {
    // iterations++;
    if (counter == 0) {
      tmp = null;
      solved = false;
    }
    // if (iterations%500_000==0) System.out.println(iterations + " iterations");
    if (solved) return;
    if (Backtracking.isFull(cargo)) {
      solved = true;
      Backtracking.print3DArray(cargo);
      tmp = new Cargo("tmp", cargo);
      // tmp.printSolution(Arrays.toItemArray(pentominoes));
      return;
    }
    for (int i = 0; i < cargo.length; i++) {
      for (int j = 0; j < cargo[i].length; j++) {
        for (int k = 0; k < cargo[i][j].length; k++) {
          if (cargo[i][j][k] == null) {
            for (int t = 0; t < pentominoes.length; t++) {
              for (Pentomino pentomino : Pentomino.getAllShapes(pentominoes[t])) {
                if (canBePut(pentomino, cargo, i, j, k)) {
                  // System.out.println("Putting " + pentomino.getItem().getName());
                  Item[][][] newCargo = insert(pentomino, cargo, i, j, k);
                  if (optimized) {
                    if (Backtracking.shouldContinue(newCargo)) {
                      solveFor(pentominoes, newCargo, optimized, counter + 1);
                    }
                  }
                  else {
                    solveFor(pentominoes, newCargo, optimized, counter + 1);
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
  * Create a copy of the cargo and insert the pentomino in it
  * @param pent the Pentomino
  * @param cargo the cargo
  * @param i the position along the x-axis
  * @param j the position along the y-axis
  * @param k the position along the z-axis
  * @return the new cargo
  */
  public static Item[][][] insert(Pentomino pent, Item[][][] cargo, int i, int j, int k) {
    Pentomino newPent = pent.clone();
    Item item = newPent.getItem();
    boolean[][][] shape = newPent.getShape();
    int[] start = pent.getStartCoordinates();
    Item[][][] newCargo = new Item[cargo.length][cargo[0].length][cargo[0][0].length];
    for (int w = 0; w < cargo.length; w++) {
      for (int h = 0; h < cargo[i].length; h++) {
        for (int d = 0; d < cargo[i][j].length; d++) {
          newCargo[w][h][d] = cargo[w][h][d];
        }
      }
    }
    for (int w = 0; w < shape.length; w++) {
      for (int h = 0; h < shape[w].length; h++) {
        for (int d = 0; d < shape[w][h].length; d++) {
          int x = w + i - start[0], y = h + j - start[1], z = d + k - start[2];
          // System.out.println("PUT: x=" + x + ", y=" + y + ", z=" + z);
          if (shape[w][h][d] == true) {
            newCargo[x][y][z] = item;
          }
        }
      }
    }
    return newCargo;
  }

  /**
  * See if a Pentomino fits in a certain position of the cargo
  * @param pent the Pentomino
  * @param cargo the cargo
  * @param i the position along the x-axis
  * @param j the position along the y-axis
  * @param k the position along the z-axis
  * @return true if it fits, false otherwise
  */
  public static boolean canBePut(Pentomino pent, Item[][][] cargo, int i, int j, int k) {
    boolean permission = true;
    boolean[][][] shape = pent.getShape();
    int[] start = pent.getStartCoordinates();
    int w = 0;
    while (permission && w < shape.length) {
      int h = 0;
      while (permission && h < shape[w].length) {
        int d = 0;
        while (permission && d < shape[w][h].length) {
          int x = w + i - start[0], y = h + j - start[1], z = d + k - start[2];
          // System.out.println("CHECK: x=" + x + ", y=" + y + ", z=" + z);
          if (x < 0 || y < 0 || z < 0) {
            permission = false;
          }
          if (x >= cargo.length || y >= cargo[0].length || z >= cargo[0][0].length) {
            permission = false;
          }
          if (permission && shape[w][h][d] == true && cargo[x][y][z] != null) {
            permission = false;
          }
          d++;
        }
        h++;
      }
      w++;
    }
    // System.out.println(permission);
    return permission;
  }

}
