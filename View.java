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
		public static void main (String[] args) {
			View test = new View();

		}

		final int SIDE = 40;

		public void start(final Stage stage) {
			stage.setTitle("Project 1.4 - Cargo");

			Group rotationGroup = new Group();
			Group root = new Group();
			Scene scene = new Scene(root, 600, 600, true);
			scene.setFill(Color.ALICEBLUE);

			Item A = new Item("A", 3, 2, 2, 4);
			Item B = new Item("B", 4, 2, 3, 4);
			Item C = new Item("C", 5, 3, 3, 3);
			Item[] items = new Item[]{A, B, C};

			Item[][][] cargo = new Item[2][5][8];

			addToRoot(items, cargo, rotationGroup, root);
			addSlider(rotationGroup, root);

			scene.setCamera(new PerspectiveCamera());
			stage.setScene(scene);
			stage.show();
		}

		/*
		 * Adding everything to the rotation group
		 */
		public void addToRoot(Item[] items, Item[][][] cargo, Group rotationGroup, Group root) {

			// <here goes the solver, with items as items to use and cargo as cargo settings>
			// Backtracking.solveFor(items, cargo);
			// Cargo tmp = new Cargo("TMP", Backtracking.tmp.getShape());
			// tmp.printSolution(items, false);
			Item A = new Item("A",3,2,2,2, new Color(255,0,0));
			Item B = new Item("B",4,2,2,2, Color.WHITE);
			Item C = new Item("C",5,4,1,1, new Color(0,255,0));
			Item D = new Item("D",6,4,1,2, new Color(244, 170, 66));
			Item E = new Item("E",7,4,2,1, new Color(239, 52, 199));
			Item[][][] solution = new Item[3][4][3];
			solution[0][0][0] = A;
			solution[0][1][0] = A;
			solution[1][0][0] = A;
			solution[1][1][0] = A;
			solution[0][0][1] = A;
			solution[0][1][1] = A;
			solution[1][0][1] = A;
			solution[1][1][1] = A;

			solution[0][2][0] = B;
			solution[0][3][0] = B;
			solution[1][2][0] = B;
			solution[1][3][0] = B;
			solution[0][2][1] = B;
			solution[0][3][1] = B;
			solution[1][2][1] = B;
			solution[1][3][1] = B;

			solution[2][0][0] = C;
			solution[2][1][0] = C;
			solution[2][2][0] = C;
			solution[2][3][0] = C;

			solution[2][0][1] = D;
			solution[2][1][1] = D;
			solution[2][2][1] = D;
			solution[2][3][1] = D;
			solution[2][0][2] = D;
			solution[2][1][2] = D;
			solution[2][2][2] = D;
			solution[2][3][2] = D;

			solution[0][0][2] = E;
			solution[0][1][2] = E;
			solution[0][2][2] = E;
			solution[0][3][2] = E;
			solution[1][0][2] = E;
			solution[1][1][2] = E;
			solution[1][2][2] = E;
			solution[1][3][2] = E;


			addBoxes(rotationGroup, solution);
			setupRG(rotationGroup);
			root.getChildren().add(rotationGroup);
		}

		/*
		 * Create a box that corresponds to a specific item
		 */
		public Box makeBox(Item item) {
			Box box = new Box(SIDE,SIDE,SIDE);
			PhongMaterial mat = new PhongMaterial();
			mat.setSpecularColor(item.getColor());
			mat.setDiffuseColor(item.getColor());
			box.setMaterial(mat);
			return box;
		}


		public void addSlider(Group rotationGroup, Group root) {
			Slider s = new Slider(0,360,0);
			s.setBlockIncrement(1);
			s.setTranslateX(225);
			s.setTranslateY(575);
			rotationGroup.rotateProperty().bind(s.valueProperty());
			root.getChildren().add(s);
		}

		/*
		 * Add the boxes to the rotation group
		 * @param rotationGroup The group the items are added to
		 * @param items The solution matrix to retrieve the items from
		 */
		public void addBoxes(Group rotationGroup, Item[][][] items) {

			for (int i = 0; i < items.length; i++) {
				for (int j = 0; j < items[i].length; j++) {
					for (int k = 0; k < items[i][j].length; k++) {
						Box box = makeBox(items[i][j][k]);
						coordinates(box,i,j,k);
						rotationGroup.getChildren().add(box);
					}
				}
			}
		}

		/*
		 * Set the coordinates of a box
		 * @param Box of which to set the coordinates
		 * @param x The x coordinate
		 * @paran y The y coordinate
		 * @param z The z coordinate
		 */
		public void coordinates(Box box, double x, double y, double z) {
			box.setTranslateX(x*SIDE);
			box.setTranslateY(y*SIDE);
			box.setTranslateZ(z*SIDE);
		}

		/*
		 * Set up the rotation group
		 * @param rotationGroup The rotation group to set up
		 */
		public void setupRG(Group rotationGroup) {
			rotationGroup.setTranslateX(125);
			rotationGroup.setTranslateY(125);
			rotationGroup.setRotationAxis(Rotate.Y_AXIS);
		}
}
