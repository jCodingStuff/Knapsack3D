public class Test {

  public static void main(String[] args) {

    Item A = new Item("A", 3, 2, 2, 4);
    Item B = new Item("B", 4, 2, 3, 4);
    Item C = new Item("C", 5, 3, 3, 3);

    Item[] items = new Item[]{A, B, C};
    // Item[] sorted = Item.jSort(items);
    // for (Item item : sorted) System.out.println(item.getName());
    // Item[] items = new Item[]{A, B};

    // Item[][][] cargo = new Item[33][5][8];
    Item[][][] cargo = new Item[15][9][4];
    // Backtracking.print3DArray(cargo);

    Backtracking.solveFor(items, cargo);

    // Solver mine = new Solver("Greedy", items, new Cargo("Cargo", 33, 5, 8));
    // mine.fillGreedyCargo();

  }

}
