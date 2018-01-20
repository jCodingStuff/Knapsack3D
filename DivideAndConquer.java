import java.util.ArrayList;

public class DivideAndConquer {

  public static Item[][][] solve(Item[] items, Item[][][] cargo) {

  }

  /**
  * Merge a set of Item[][][] to a bigger one
  * @param set the set of items to merge
  * @param axis the axis that follows the merge
  * @return the merged result
  */
  public static Item[][][] merge(ArrayList<Item[][][]> set, int axis) {
    Item[][][] result = null;
    int sum = sumDimension(set, axis);
    switch (axis) {
      case 0: // X
        result = new Item[sum][set.get(0)[0].length][set.get(0)[0][0].length];
        break;
      case 1: // Y
        result = new Item[set.get(0).length][sum][set.get(0)[0][0].length];
        break;
      case 2: // Z
        result = new Item[set.get(0).length][set.get(0)[0].length][sum];
        break;
      default: // DUMB
        System.out.println("Axis input invalid");
        break;
    }
    for (Item[][][] shape : set) {
      put(result, shape, axis);
    }
    return result;
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
