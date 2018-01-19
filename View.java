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
			stage.setTitle("Project 1.4 - Cargo");
			
			Group rotationGroup = new Group();
			Group root = new Group();
			Scene scene = new Scene(root, 1000, 1000, true);
			scene.setFill(Color.ALICEBLUE);
			

			addSlider(rotationGroup, root);
			
			scene.setCamera(new PerspectiveCamera());
			stage.setScene(scene);
			stage.show();
		}
		
		/*
		 * Adding everything to the rotation group
		 */
		public void addToRoot(Item[] items, Cargo cargo, Group rotationGroup, Group root) {
			
			<here goes the solver, with items as items to use and cargo as cargo settings>
		    
			
			addBoxes(rotationGroup, <here goes the solution matrix>);
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