public class GreedyAndBack {

  // public static void main(String[] args) {
  //   Item A = new Item("A", 3, 2, 2, 4);
  //   Item B = new Item("B", 4, 2, 3, 4);
  //   Item C = new Item("C", 5, 3, 3, 3);
  //   Item[] items = new Item[]{A, B, C};
  //   Pentomino L = new Pentomino("L", 3);
  //   Pentomino P = new Pentomino("P", 4);
  //   Pentomino T = new Pentomino("T", 5);
  //   Pentomino[] pentos = new Pentomino[]{L, P, T};
  //   int width = Integer.parseInt(args[0]);
  //   int height = Integer.parseInt(args[1]);
  //   int depth = Integer.parseInt(args[2]);
  //   Item[][][] shape = new Item[width][height][depth];
  //   solve(true, pentos, shape);
  // }

  public static void solve(boolean random, Item[] items, Item[][][] shape) {
    Cargo cargo = new Cargo("tmpCargo", shape);
    Solver solver = new Solver("solver", items, cargo);
    solver.fillGreedyCargo(random, false);
    Backtracking.solveFor(items, cargo.getShape(), false, 0);
    if (Backtracking.tmp != null) cargo.setShape(Backtracking.tmp.getShape());
    cargo.printSolution(items, false, true);
  }

  public static void solve(boolean random, Pentomino[] pentos, Item[][][] shape) {
    Cargo cargo = new Cargo("tmpCargo", shape);
    PSolver solver = new PSolver("solver", pentos, cargo);
    solver.fillGreedyCargo(random, false);
    PBacktracking.solveFor(pentos, cargo.getShape(), true, 0);
    if (PBacktracking.tmp != null) cargo.setShape(PBacktracking.tmp.getShape());
    cargo.printSolution(Arrays.toItemArray(pentos), true, true);
  }

}
