/*
A method that clones from Cargo
@author Sarah Waseem
@author Pierre Bongrad
@version 0.1, 08 December 2018
*/

public class Arrays {

  public static Item[] cloneArray(Item[] ori) {
    Item[] fut = new Item[ori.length];
    for (int i = 0; i < fut.length; i++) {
      fut[i] = ori[i].clone();
    }
    return fut;
  }

  public static Item[][][] clone3DMatrix(Item[][][] ori) {
    Item[][][] myInt = new Item [ori.length][ori[0].length][ori[0][0].length];
    for(int i = 0; i< ori.length; i++){
        for (int j = 0; j < ori[i].length; j++){
            for (int k = 0; k < ori[i][j].length; k++){
                myInt[i][j][k] = ori[i][j][k].clone();
            }
        }
    }
    return myInt;
  }

}
