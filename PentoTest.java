public class PentoTest {
  public static void main (String[] args) {
    Pentomino L = new Pentomino("L", 3);
    Pentomino P = new Pentomino("P", 4);
    Pentomino T = new Pentomino("T", 5);

    Pentomino[] items = new Pentomino[] {L, P, T};

    PSolver mine = new PSolver("Greedy", items, new Cargo("Cargo", 5, 5, 8));
    mine.fillGreedyCargo();

    System.out.println(mine.getCargo().getResult());
  }
}
