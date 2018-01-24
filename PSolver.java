/**
* A greedy solver for pentominoes
*
* @author Julian Marrades
* @version 0.1, 13-01-2018
*
* @author Pierre Bongrand
* @version 0.2, 15-01-2018
*/
public class PSolver {

  private Pentomino[] pentos;
  private Cargo cargo;
  private String name;

  /**
  * Default Solver constructor
  * @param name the name of the solver
  * @param pentos the array of items
  * @param cargo the cargo object
  */
  public PSolver(String name, Pentomino[] pentos, Cargo cargo)
  {
    this.name = name;
    this.pentos = pentos;
    this.cargo = cargo;
  }

  /**
  * Get access to the name
  * @return the name of the solver
  */
  public String getName() {
    return this.name;
  }

  /**
  * Set a new name for the solver
  * @param name the new name
  */
  public void setName(String name) {
    this.name = name;
  }

  /**
  * Get access to the pentominoes
  * @return the pentominoes
  */
  public Pentomino[] getPentos()
  {
    return this.pentos;
  }
  /**
  * Sets the pentominoes
  * @param pentos the new array of pentos
  */
  public void setPentos(Pentomino[] pentos)
  {
    this.pentos = pentos;
  }
  /**
  * Gets access to cargo
  * @return cargo
  */
  public Cargo getCargo()
  {
    return this.cargo;
  }
  /**
  * Sets cargo
  * @param cargo the new cargo
  */
  public void setCargo(Cargo cargo)
  {
    this.cargo = cargo;
  }

  /**
  * Get a string representation
  * @return a string containing the detailed information
  */
  public String toString()
  {
    String result = this.getClass().getName() + ":\n";
    result += " - Name -> " + this.name;
    result += "\n - Cargo -> " + this.cargo.toString();
    result += "\n - Pentominoes:";
    for (Pentomino pento : this.pentos) {
      result += "\n\t- " + pento.toString();
    }
    return result;
  }

  /**
  * Clone the current Solver
  * @return the cloned solver object
  */
  public PSolver clone() {
    return new PSolver(this.name, Arrays.clonePArray(this.pentos), this.cargo.clone());
  }

  /**
  * Fill the cargo with the greedy algorithm
  * @param random will the greedy be random?
  * @param output is output wanted?
  */
  public void fillGreedyCargo(boolean random, boolean output) {
    Pentomino[] sorted = null;
    Pentomino[] all = null;
    if (random) {
      sorted = Pentomino.getAllShapes(this.pentos);
      all = shuffle(sorted);
    }
    else {
      sorted = Pentomino.sort(this.pentos);
      all = Pentomino.getAllShapes(sorted);
    }
    // Loop through the whole cargo and fill any empty space
    for (int j = 0; j < this.cargo.getHeight(); j++) {
      for (int i = 0; i < this.cargo.getWidth(); i++) {
        for (int k = 0; k < this.cargo.getDepth(); k++) {
          if (this.cargo.check(i, j, k) == null) {
            this.tryFill(all, i, j, k);
          }
        }
      }
    }
    // Backtracking.print3DArray(this.cargo.getShape());
    this.cargo.printSolution(Arrays.toItemArray(this.pentos), true, output);
  }

  /**
  * Try to fit an item from a set in a position
  * @param pentos the set of pentos which can be put in the cargo
  * @param i the position along the x-axis
  * @param j the position along the y-axis
  * @param k the position along the z-axis
  */
  private void tryFill(Pentomino[] pentos, int i, int j, int k) {
    boolean filled = false;
    int index = 0;
    while (!filled && index < pentos.length) {
      if (this.cargo.canBePut(pentos[index], i, j, k)) {
        this.cargo.put(pentos[index], i, j, k);
        filled = true;
      }
      index++;
    }
  }

  /**
  * Shuffle an array of elements
  * @param ori original arrey
  * @return  shuffled array
  */
  public static Pentomino[] shuffle(Pentomino[] ori) {
    Pentomino[] result = new Pentomino[ori.length];
    int i = 0;
    while(i < ori.length) {
      int j = (int) (Math.random()*ori.length);
      if(result[j] == null){
        result[j] = ori[i].clone();
        i++;
      }
    }
    return result;
  }

}
