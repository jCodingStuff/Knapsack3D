public class PBacktracking {

  public static void solveFor(Pentomino[] pentominoes, Item[][][] cargo) {
    if (Backtracking.isFull(cargo)) {
      Backtracking.print3DArray(cargo);
      System.exit(0);
    }
    for (int i = 0; i < cargo.length; i++) {
      for (int j = 0; j < cargo[i].length; j++) {
        for (int k = 0; k < cargo[i][j].length; k++) {
          if (cargo[i][j][k] == null) {
            for (int t = 0; t < pentominoes.length; t++) {
              for (Pentomino pentomino : getAllShapes(pentominoes[t])) {
                if (canBePut(pentomino, cargo, i, j, k)) {
                  Item[][][] newCargo = insert(pentomino, shape, i, j, k);
                  solveFor(pentomino, newCargo);
                }
              }
            }
            return;
          }
        }
      }
    }
  }

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
      for (int h = 0; h < shape[i].length; h++) {
        for (int d = 0; d < shape[i][j].length; d++) {
          int x = w + i - start[0], y = h + j - start[1], z = d + k - start[2];
          if (shape[w][h][d] == true) {
            newCargo[x][y][z] = item;
          }
        }
      }
    }
    return newCargo;
  }

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
          if (x < 0 || y < 0 || z < 0 ||) {
            permission = false;
          }
          else if (x >= cargo.length; y >= cargo[0].length; z >= cargo[0][0].length) {
            permission = false;
          }
          else if (shape[w][h][d] == true && cargo[x][y][z] != null) {
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

}
