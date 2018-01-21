import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class DivideAndConquer {

  public static Item[][][] solve(Pentomino[] pentos, Item[][][] cargo, int limit, boolean optimized) {
    int axis = getMaxDim(cargo);
    int max = 0;
    switch (axis) {
      case 0: max = cargo.length; break;
      case 1: max = cargo[0].length; break;
      case 2: max = cargo[0][0].length; break;
    }

    Map<Integer, Item[][][]> map = new HashMap<Integer, Item[][][]>();
    fillMap(map, pentos, cargo, axis, limit, optimized);

    int[] keys = new int[map.size()];
    int counter = 0;
    for (int i = 1; i <= limit; i++) {
      if (map.containsKey(i)) {
        keys[counter] = i;
        counter++;
      }
    }

    // Create a new dynamic programming table
    boolean[][] T = new boolean[keys.length + 1][max + 1];
    fillDynamic(T, keys);

    int[] amounts = retrace(T, keys);
    ArrayList<Item[][][]> set = getSet(map, keys, amounts);
    Item[][][] result = new Item[cargo.length][cargo[0].length][cargo[0][0].length];
    merge(result, set, axis);
    return result;
  }

  public static Item[][][] solve(Item[] items, Item[][][] cargo, int limit, boolean optimized) {
    int axis = getMaxDim(cargo);
    int max = 0;
    switch (axis) {
      case 0: max = cargo.length; break;
      case 1: max = cargo[0].length; break;
      case 2: max = cargo[0][0].length; break;
    }

    Map<Integer, Item[][][]> map = new HashMap<Integer, Item[][][]>();
    fillMap(map, items, cargo, axis, limit, optimized);

    int[] keys = new int[map.size()];
    int counter = 0;
    for (int i = 1; i <= limit; i++) {
      if (map.containsKey(i)) {
        keys[counter] = i;
        counter++;
      }
    }

    // Create a new dynamic programming table
    boolean[][] T = new boolean[keys.length + 1][max + 1];
    fillDynamic(T, keys);

    int[] amounts = retrace(T, keys);
    ArrayList<Item[][][]> set = getSet(map, keys, amounts);
    Item[][][] result = new Item[cargo.length][cargo[0].length][cargo[0][0].length];
    merge(result, set, axis);
    return result;
  }

  private static ArrayList<Item[][][]> getSet(Map<Integer, Item[][][]> map, int[] keys, int[] amounts) {
    ArrayList<Item[][][]> result = new ArrayList<Item[][][]>();
    for (int i = 0; i < keys.length; i++) {
      for (int j = 0; j < amounts[i]; j++) {
        result.add(smartCopy(map.get(keys[i])));
      }
    }
    return result;
  }

  private static Item[][][] smartCopy(Item[][][] ori) {
    Map<Integer, Integer> oldToNew = new HashMap<Integer, Integer>();
    Map<Integer, Item> newToItem = new HashMap<Integer, Item>();
    Item[][][] fut = new Item[ori.length][ori[0].length][ori[0][0].length];
    for (int i = 0; i < ori.length; i++) {
      for (int j = 0; j < ori[0].length; j++) {
        for (int k = 0; k < ori[0][0].length; k++) {
          if (ori[i][j][k] != null) {
            if (oldToNew.get(ori[i][j][k].serialNumber()) == null) {
              fut[i][j][k] = ori[i][j][k].clone();
              oldToNew.put(ori[i][j][k].serialNumber(), fut[i][j][k].serialNumber());
              newToItem.put(fut[i][j][k].serialNumber(), fut[i][j][k]);
            }
            else {
              fut[i][j][k] = newToItem.get(oldToNew.get(ori[i][j][k].serialNumber()));
            }
          }
        }
      }
    }
    return fut;
  }

  private static void fillDynamic(boolean[][] T, int[] keys) {
    // Fill first row
    for (int i = 0; i < T.length; i++) T[i][0] = true;
    // Fill the matrix
    for (int i = 1; i < T.length; i++) {
      for (int j = 1; j < T[0].length; j++) {
        if (j < keys[i-1]) {
          T[i][j] = T[i-1][j];
        }
        else {
          T[i][j] = T[i-1][j] || T[i][j-keys[i-1]];
        }
      }
    }
  }

  private static void fillMap(Map<Integer, Item[][][]> map, Pentomino[] pentos, Item[][][] cargo, int axis, int limit, boolean optimized) {
    int width = cargo.length, height = cargo[0].length, depth = cargo[0][0].length;
    for (int index = 1; index <= limit; index++) {
      switch (axis) {
        case 0:
          PBacktracking.solveFor(pentos, new Item[index][height][depth], optimized, 0);
          break;
        case 1:
          PBacktracking.solveFor(pentos, new Item[width][index][depth], optimized, 0);
          break;
        case 2:
          PBacktracking.solveFor(pentos, new Item[width][height][index], optimized, 0);
          break;
      }
      if (PBacktracking.tmp != null) { // If solved
        map.put(index, PBacktracking.tmp.getShape());
      }
    }
  }

  private static void fillMap(Map<Integer, Item[][][]> map, Item[] items, Item[][][] cargo, int axis, int limit, boolean optimized) {
    int width = cargo.length, height = cargo[0].length, depth = cargo[0][0].length;
    for (int index = 1; index <= limit; index++) {
      switch (axis) {
        case 0:
          Backtracking.solveFor(items, new Item[index][height][depth], optimized, 0);
          break;
        case 1:
          Backtracking.solveFor(items, new Item[width][index][depth], optimized, 0);
          break;
        case 2:
          Backtracking.solveFor(items, new Item[width][height][index], optimized, 0);
          break;
      }
      if (Backtracking.tmp != null) { // If solved
        map.put(index, Backtracking.tmp.getShape());
      }
    }
  }

  private static int[] retrace(boolean[][] T, int[] keys) {
    int[] amounts = new int[keys.length];
    int i = T.length - 1;
    int j = T[0].length - 1;
    while (T[i][j] == false) {
      j--;
    }
    while (j > 0) {
      if (T[i-1][j] == true) {
        i--;
      }
      else {
        amounts[i-1]++;
        j -= keys[i-1];
      }
    }
    return amounts;
  }

  /**
  * Get the longest axis of a cargo
  * @param cargo the cargo to analize
  * @return which axis is the longest
  */
  private static int getMaxDim(Item[][][] cargo) {
    int width = cargo.length;
    int height = cargo[0].length;
    int depth = cargo[0][0].length;
    int result = Math.max(width, Math.max(height, depth));
    if (result == width) return 0;
    else if (result == height) return 1;
    else if (result == depth) return 2;
    else return -1;
  }

  /**
  * Merge a set of Item[][][] to a bigger one
  * @param set the set of items to merge
  * @param axis the axis that follows the merge
  * @return the merged result
  */
  private static void merge(Item[][][] cargo, ArrayList<Item[][][]> set, int axis) {
    for (Item[][][] shape : set) {
      put(cargo, shape, axis);
    }
  }

  /**
  * Put a cargo in the next empty spot of the bigger one
  * @param result the bigger cargo
  * @param shape the little cargo
  * @param axis the axis that is being followed
  */
  private static void put(Item[][][] result, Item[][][] shape, int axis) {
    boolean flag = false;
    switch (axis) {
      case 0: // X
        int i = 0;
        while (!flag && i < result.length) {
          if (result[i][0][0] == null) {
            insert(result, shape, i, 0, 0);
            flag = true;
          }
          i++;
        }
        break;
      case 1: // Y
        int j = 0;
        while (!flag && j < result[0].length) {
          if (result[0][j][0] == null) {
            insert(result, shape, 0, j, 0);
            flag = true;
          }
          j++;
        }
        break;
      case 2: // Z
        int k = 0;
        while (!flag && k < result[0][0].length) {
          if (result[0][0][k] == null) {
            insert(result, shape, 0, 0, k);
            flag = true;
          }
          k++;
        }
        break;
      default: // DUMB
        System.out.println("Axis input invalid");
        break;
    }
  }

  /**
  * Put a little cargo in a spot of a bigger one
  * @param result the bigger cargo
  * @param shape the little cargo
  * @param i the position along the x-axis
  * @param j the position along the y-axis
  * @param k the position along the z-axis
  */
  private static void insert(Item[][][] result, Item[][][] shape, int i, int j, int k) {
    for (int w = 0; w < shape.length; w++) {
      for (int h = 0; h < shape[0].length; h++) {
        for (int d = 0; d < shape[0][0].length; d++) {
          result[w+i][h+j][d+k] = shape[w][h][d];
        }
      }
    }
  }


  /**
  * Sum the size of a dimension of a set of cargos
  * @param set the set of cargos
  * @param axis the dimension to sum
  * @return the sum
  */
  private static int sumDimension(ArrayList<Item[][][]> set, int axis) {
    int result = 0;
    for (Item[][][] shape : set) {
      switch (axis) {
        case 0: // X
          result += shape.length;
          break;
        case 1: // Y
          result += shape[0].length;
          break;
        case 2: // Z
          result += shape[0][0].length;
          break;
        default: // DUMB
          System.out.println("Axis input invalid");
          break;
      }
    }
    return result;
  }

}
