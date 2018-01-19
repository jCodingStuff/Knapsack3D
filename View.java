import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import java.awt.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;

public class View extends Application {

		final int SIDE = 40;

		public void start(final Stage stage) {
			System.out.println("Start");
			stage.setTitle("Project 1.3 - Cargo");

			Group rotationGroup = new Group();
			Group root = new Group();
			Scene scene = new Scene(root, 600, 600, true);
			scene.setFill(Color.ALICEBLUE);
			System.out.println("Initial setup done");

			Item A = new Item("A", 3, 2, 2, 4, Color.GREEN);
			Item B = new Item("B", 4, 2, 3, 4, Color.RED);
			Item C = new Item("C", 5, 3, 3, 3, Color.BLUE);
			Item[] items = new Item[]{A, B, C};

			Item[][][] cargo = new Item[10][5][8];

			addToRoot(items, cargo, rotationGroup, root);
			addSlider(rotationGroup, root);
			System.out.println("Finalizing");

			scene.setCamera(new PerspectiveCamera());
			stage.setScene(scene);
			stage.show();
		}

		/**
		 * Adding Items to the rotation group
		 * @param items items given to solve
		 * @param cargo cargo that will be represented
		 */
		public void addToRoot(Item[] items, Item[][][] cargo, Group rotationGroup, Group root) {

			// <here goes the solver, with items as items to use and cargo as cargo settings>
			Backtracking.solveFor(items, cargo);
			Cargo tmp = new Cargo("TMP", Backtracking.tmp.getShape());
			tmp.printSolution(items, false);

			addBoxes(rotationGroup, tmp.getShape());
			setupRG(rotationGroup);
			root.getChildren().add(rotationGroup);

			System.out.println("AddToRoot done");
		}

		/**
		 * Adding Pentominoes to the rotation group
		 * @param pentominoes pentominoes given to solve
		 * @param cargo cargo that will be represented
		 */
		public void addToRoot(Pentomino[] pentominoes, Pentomino[][][] cargo, Group rotationGroup, Group root) {

			// <here goes the solver, with items as items to use and cargo as cargo settings>
			Backtracking.solveFor(items, cargo);
			Cargo tmp = new Cargo("TMP", Backtracking.tmp.getShape());
			tmp.printSolution(items, false);

			addBoxes(rotationGroup, tmp.getShape());
			setupRG(rotationGroup);
			root.getChildren().add(rotationGroup);

			System.out.println("AddToRoot done");
		}

		/**
		 * Create a box that corresponds to a specific item
		 * @param item item from which the box will be created
		 */
		public Box makeBox(Item item) {
			System.out.println("Making box");
			Box box = new Box(SIDE,SIDE,SIDE);
			PhongMaterial mat = new PhongMaterial();
			mat.setSpecularColor(item.getColor());
			mat.setDiffuseColor(item.getColor());
			box.setMaterial(mat);
			return box;
		}

		/**
		* Adds a slider to rotate the 3d representation in the x
		*/
		public void addSlider(Group rotationGroup, Group root) {
			System.out.println("Adding slider");
			Slider s = new Slider(0,360,0);
			s.setBlockIncrement(1);
			s.setTranslateX(225);
			s.setTranslateY(575);
			rotationGroup.rotateProperty().bind(s.valueProperty());
			root.getChildren().add(s);
		}

		/**
		 * Add the boxes to the rotation group
		 * @param rotationGroup The group the items are added to
		 * @param items The solution matrix to retrieve the items from
		 */
		public void addBoxes(Group rotationGroup, Item[][][] items) {
			System.out.println("Adding Boxes");

			for (int i = 0; i < items.length; i++) {
				System.out.println("Loop 1");
				for (int j = 0; j < items[i].length; j++) {
					System.out.println("Loop 2");
					for (int k = 0; k < items[i][j].length; k++) {
						System.out.println("Loop 3");
						Box box = makeBox(items[i][j][k]);
						coordinates(box,i,j,k);
						rotationGroup.getChildren().add(box);
					}
				}
			}
		}

		/**
		 * Set the coordinates of a box
		 * @param Box of which to set the coordinates
		 * @param x The x coordinate
		 * @param y The y coordinate
		 * @param z The z coordinate
		 */
		public void coordinates(Box box, double x, double y, double z) {
			System.out.println("Setting Coordinates");
			box.setTranslateX(x*SIDE);
			box.setTranslateY(y*SIDE);
			box.setTranslateZ(z*SIDE);
		}

		/**
		 * Set up the rotation group
		 * @param rotationGroup The rotation group to set up
		 */
		public void setupRG(Group rotationGroup) {
			System.out.println("SettingRG");
			rotationGroup.setTranslateX(125);
			rotationGroup.setTranslateY(125);
			rotationGroup.setRotationAxis(Rotate.Y_AXIS);
		}
}
