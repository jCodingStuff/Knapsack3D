import java.io.FileWriter;
import java.io.IOException;

public class Test {

  public static void main(String[] args) {

    FileWriter writer = null;

    try {
      writer = new FileWriter("results.txt");
    }
    catch (Exception e) {
      System.out.println("You are dumb!");
      System.exit(0);
    }

    Item A = new Item("A", 3, 2, 2, 4);
    Item B = new Item("B", 4, 2, 3, 4);
    Item C = new Item("C", 5, 3, 3, 3);

    Item[] items = new Item[]{A, B, C};
    Item[] sorted = Item.jSort(items);
    // for (Item item : sorted) System.out.println(item.getName());
    // Item[] items = new Item[]{A, B};

    // Item[][][] cargo = new Item[33][5][8];
    int height = Integer.parseInt(args[0]);
    int width = Integer.parseInt(args[1]);
    int depth = Integer.parseInt(args[2]);
    Item[][][] cargo = new Item[height][width][depth];
    // Backtracking.print3DArray(cargo);

    // Backtracking.solveFor(sorted, cargo);
    for (int i = 0; i < 20_000_000; i++) {
      Solver mine = new Solver("Greedy", items, new Cargo("Cargo", height, width, depth));
      mine.fillGreedyCargo();
      if (mine.getCargo().getValue() > 238) {
        try {
          writer.write("\n" + mine.getCargo().getResult());
        }
        catch (IOException e) {
          System.out.println("Motherfucker you sick dumb ass get my try!");
        }
      }
    }

    try {
      writer.close();
    }
    catch (Exception e) {
      System.out.println("Could not close the writer sdoignarewoignaeriogn");
    }

  }

}
