import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.*;
import javafx.scene.transform.*;

public class View extends Application {

		private final int SIDE = 40;
		private final double SCENE_WIDTH = 600.0;
		private final double  SCENE_HEIGHT = 600.0;
		private Group rotGroup = new Group();
		private RotationCamera rotCam = new RotationCamera();
		private Group root = new Group();
		private PerspectiveCamera camera = new PerspectiveCamera(true);
		private static final double CAMERA_ID = -5000;
	    private static final double CAMERA_NC = 0.1;
	    private static final double CAMERA_FC = 10000.0;
	    double currentX, currentY, oldX, oldY, deltaX, deltaY;
	    private Map<Integer, Color> map = new HashMap<Integer, Color>();

		public void start(final Stage stage) {

			root.getChildren().add(rotGroup);
	        setupCam();

	        Pentomino L = new Pentomino("L", 3);
	        Pentomino P = new Pentomino("P", 4);
	        Pentomino T = new Pentomino("T", 5);

	        Pentomino[] items = new Pentomino[] {L,P,T};
	        Item[][][] shape = new Item[33][5][8];
	        Cargo cargo = new Cargo("cargo",shape);
			dynamicToRoot(items,cargo,1,true,rotGroup);

			Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT, true);
			scene.setFill(Color.ALICEBLUE);
			setupMouse(scene);
			stage.setTitle("Project 1.3 - Cargo");
			stage.setScene(scene);
			stage.show();
			scene.setCamera(camera);
		}

		public void dynamicToRoot(Item[] items, Cargo cargo, int limit, boolean optimized, Group rotGroup) {
			Item[][][] result = DivideAndConquer.solve(items, cargo.getShape(), limit, optimized);
			addBoxes(rotGroup, result);
			setupRG(rotGroup, cargo);
		}

		public void dynamicToRoot(Pentomino[] pentos, Cargo cargo, int limit, boolean optimized, Group rotGroup) {
			Item[][][] result = DivideAndConquer.solve(pentos, cargo.getShape(), limit, optimized);
			addBoxes(rotGroup, result);
			setupRG(rotGroup, cargo);
		}

		/**
		*	Solve with greedy and add to rotation group
		*	@param items items given to solve
		* 	@param cargo cargo that will be represented
		*/
		public void greedyToRoot(Item[] items, Cargo cargo, Group rotGroup, boolean random) {
			Solver mySolver = new Solver("mySolver", items, cargo);
			mySolver.fillGreedyCargo(random, false);
			setupRG(rotGroup, cargo);
			addBoxes(rotGroup, mySolver.getCargo().getShape());
		}

		/**
		*	Solve with greedy and add to rotation group
		*	@param pentos items given to solve
		*	@param cargo cargo that will be represented
		*/
		public void greedyToRoot(Pentomino[] pentos, Cargo cargo, Group rotGroup, boolean random) {
			PSolver mySolver = new PSolver("mySolver", pentos, cargo);
			mySolver.fillGreedyCargo(random, false);

			addBoxes(rotGroup, cargo.getShape());
			setupRG(rotGroup, cargo);
		}


		/**
		 * Adding Pentominoes to the rotation group
		 * @param pentominoes pentominoes given to solve
		 * @param cargo cargo that will be represented
		 */
		public void btToRoot(Pentomino[] pentominoes, Cargo cargo, Group rotGroup, boolean optimized) {
			PBacktracking.solveFor(pentominoes, cargo.getShape(), optimized, 0);

			if (Backtracking.tmp != null ) {
				addBoxes(rotGroup, Backtracking.tmp.getShape());
			} else {
				System.out.println("BT couldn't solve this cargo");
			}
			setupRG(rotGroup, cargo);
		}

		/**
		 * Create a box that corresponds to a specific item
		 */
		public Box makeBox(Item item) {
			Box box = new Box(SIDE,SIDE,SIDE);
			if (item == null) {
				PhongMaterial mat = new PhongMaterial();
				mat.setSpecularColor(Color.BLACK);
				mat.setDiffuseColor(Color.BLACK);
				box.setMaterial(mat);
				box.setDrawMode(DrawMode.LINE);
			} else {
				Color color = null;
				if (this.map.get(item.serialNumber()) != null) {
					color = map.get(item.serialNumber());
				}
				else {
					double red = Math.random();
					double blue = Math.random();
					double green = Math.random();
					color = new Color(red, green, blue, 1);
					this.map.put(item.serialNumber(), color);
				}
				PhongMaterial mat = new PhongMaterial();
				mat.setDiffuseColor(color);
				mat.setSpecularColor(Color.WHITE);
				box.setMaterial(mat);
				//box.setDrawMode(DrawMode.LINE);
			}
			return box;
		}

		/**
		 * Add the boxes to the rotation group
		 * @param rotGroup The group the items are added to
		 * @param items The solution matrix to retrieve the items from
		 */
		public void addBoxes(Group rotGroup, Item[][][] items) {

			for (int i = 0; i < items.length; i++) {
				for (int j = 0; j < items[i].length; j++) {
					for (int k = 0; k < items[i][j].length; k++) {
						Box box = makeBox(items[i][j][k]);
						coordinates(box,i,j,k);
						rotGroup.getChildren().add(box);
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
			box.setTranslateX(x*SIDE);
			box.setTranslateY(y*SIDE);
			box.setTranslateZ(z*SIDE);
		}

		/**
		 * Set up the rotation group
		 * @param rotGroup The rotation group to set up
		 */
		public void setupRG(Group rotGroup, Cargo cargo) {
			double cW = (double) (cargo.getWidth())/2.0;
			double cH = (double) (cargo.getHeight())/2.0;
			double cD = (double) (cargo.getDepth())/2.0;
			final Translate t = new Translate(0.0, 0.0, 0.0);
		    final Rotate rx = new Rotate(0,cW, cH, cD, Rotate.X_AXIS);
		    final Rotate ry = new Rotate(0, cW, cH, cD, Rotate.Y_AXIS);
		    final Rotate rz = new Rotate(0, 0, 0, 0, Rotate.Z_AXIS);

		    rotGroup.getTransforms().addAll(t, rx, ry, rz);
		}

		public void setupCam() {
	        rotCam.getChildren().add(camera);
			root.getChildren().add(rotCam);
	        camera.setNearClip(CAMERA_NC);
	        camera.setFarClip(CAMERA_FC);
	        camera.setTranslateZ(CAMERA_ID);
		}

		public void setupMouse(Scene scene) {
		        scene.setOnMousePressed((MouseEvent event) -> {
		            currentX = event.getSceneX();
		            currentY = event.getSceneY();
		            oldX = event.getSceneX();
		            oldY = event.getSceneY();
		        });
		        scene.setOnMouseDragged((MouseEvent event) -> {
		            oldX = currentX;
		            oldY = currentY;
		            currentX = event.getSceneX();
		            currentY = event.getSceneY();
		            deltaX = (currentX - oldX);
		            deltaY = (currentY - oldY);
		            if (event.isPrimaryButtonDown()) {
		                rotCam.ry(deltaX * 180.0 / scene.getWidth());
		                rotCam.rx(-deltaY * 180.0 / scene.getHeight());
		            } else if (event.isSecondaryButtonDown()) {
		                camera.setTranslateZ(camera.getTranslateZ() + deltaY*100);
		            }
		        });
		}
}

class RotationCamera extends Group {
    Point3D px = new Point3D(1.0, 0.0, 0.0);
    Point3D py = new Point3D(0.0, 1.0, 0.0);
    Transform t = new Rotate();
    Rotate r;

    public RotationCamera() {
        super();
    }

    public void rx(double angle) {
        r = new Rotate(angle, px);
        this.t = t.createConcatenation(r);
        this.getTransforms().clear();
        this.getTransforms().addAll(t);
    }

    public void ry(double angle) {
        r = new Rotate(angle, py);
        this.t = t.createConcatenation(r);
        this.getTransforms().clear();
        this.getTransforms().addAll(t);
    }
}
