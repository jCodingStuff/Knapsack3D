import java.io.FileWriter;
import java.io.IOException;

public class Test {

  public static void main(String[] args) {
    //
    // FileWriter writer = null;
    //
    // try {
    //   writer = new FileWriter("results.txt");
    // }
    // catch (Exception e) {
    //   System.out.println("You are dumb!");
    //   System.exit(0);
    // }

    Item A = new Item("A", 3, 2, 2, 4);
    Item B = new Item("B", 4, 2, 3, 4);
    Item C = new Item("C", 5, 3, 3, 3);

    Item[] items = new Item[]{A, B, C};
    Item[] sorted = Item.jSort(items);
    // for (Item item : sorted) System.out.println(item.getName());
    // Item[] items = new Item[]{A, B};
    // Pentomino L = new Pentomino("L", 3);
    // Pentomino P = new Pentomino("P", 4);
    // Pentomino T = new Pentomino("T", 5);
    //
    // Pentomino[] items = new Pentomino[] {L, P, T};

    // Item[][][] cargo = new Item[33][5][8];
    int width = Integer.parseInt(args[0]);
    int height = Integer.parseInt(args[1]);
    int depth = Integer.parseInt(args[2]);
    Item[][][] cargo = new Item[width][height][depth];
    // Backtracking.print3DArray(cargo);
    long t1 = System.nanoTime();
    Backtracking.solveFor(sorted, cargo, 0);
    long t2 = System.nanoTime();

    System.out.println(t2-t1);

    if (Backtracking.tmp != null) {
      Cargo tmp = new Cargo("TMP", Backtracking.tmp.getShape());
      tmp.printSolution(items, false, true);
    }
    else {
      System.out.println("The cargo could not be filled :(");
    }
    // for (int i = 0; i < 20_000; i++) {
    //   PSolver mine = new PSolver("Greedy", items, new Cargo("Cargo", width, height, depth));
    //   mine.fillGreedyCargo();
    //   if (mine.getCargo().getValue() > 238) {
    //     try {
    //       writer.write("\n" + mine.getCargo().getResult());
    //     }
    //     catch (IOException e) {
    //       System.out.println("Motherfucker you sick dumb ass get my try!");
    //     }
    //   }
    // }
    //
    // try {
    //   writer.close();
    // }
    // catch (Exception e) {
    //   System.out.println("Could not close the writer sdoignarewoignaeriogn");
    // }

  }

}
