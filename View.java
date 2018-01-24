import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.*;
import javafx.scene.transform.*;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;

/**
 * This is a class to view the 3D representation of the cargo
 */
public class View {

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
	    private double currentX, currentY, oldX, oldY, deltaX, deltaY;
	    private Map<Integer, Color> map = new HashMap<Integer, Color>();
	    private Stage stage = new Stage();
	    Cargo cargo; // fix this.cargo in each method
	    Item[][][] shape;
	    Item[] items;
	    Pentomino[] pentos;
		Stage infoStage;

		/**
		 * Open window with information about the current cargo content
		 */
		private void openInfoWindow() {
			infoStage = new Stage();
			infoStage.getIcons().add(Window.information);
			infoStage.setX(275);
			infoStage.setY(350);
			infoStage.setTitle("Information");
			HBox layout = new HBox();
			Label label = new Label(this.cargo.getResult());
			layout.setPadding(new Insets(20, 20, 20, 20));
			layout.setAlignment(Pos.CENTER);
			layout.getChildren().add(label);
			Scene newScene = new Scene(layout, 300, 200);
			infoStage.setResizable(false);
			infoStage.setScene(newScene);
			infoStage.show();
		}

		/**
		 * Show the cargo solved by the greedy algorithm using parcels
		 * @param random if true, sets the greedy to random
		 * @param aValue value of the first kind of parcel
		 * @param bValue value of the second kind of parcel
		 * @param cValue value of the third kind of parcel
		 * @param width width of the  cargo
		 * @param height height of the cargo
		 * @param depth depth of the cargo
		 */
		public void showParcelGreedy(boolean random, int aValue, int bValue, int cValue, int width, int height, int depth) {

			buildItems(aValue, bValue, cValue);
			buildCargo(width, height, depth);
			greedyToRoot(items, cargo, rotGroup, random);

			initialSetup();
			openInfoWindow();
		}
		
		 /**
		 * Show the cargo solved by the dynamic algorithm using parcels
		 * @param aValue value of the first kind of parcel
		 * @param bValue value of the second kind of parcel
		 * @param cValue value of the third kind of parcel
		 * @param width width of the  cargo
		 * @param height height of the cargo
		 * @param depth depth of the cargo
		 * @param limit highest value of the dimension to manipulate
		 * @param optimized if true, the algorithm implements the optimizations
		 */
		public void showParcelDynamic(int aValue, int bValue, int cValue, int width, int height, int depth, int limit, boolean optimized) {

			buildItems(aValue, bValue, cValue);
			buildCargo(width, height, depth);
			dynamicToRoot(items, cargo, limit, optimized, rotGroup);

			initialSetup();
			openInfoWindow();
		}
		
		/**
		 * Show the cargo solved by the backtracking algorithm using parcels
		 * @param aValue value of the first kind of parcel
		 * @param bValue value of the second kind of parcel
		 * @param cValue value of the third kind of parcel
		 * @param width width of the  cargo
		 * @param height height of the cargo
		 * @param depth depth of the cargo
		 * @param optimized if true, the algorithm implements the optimizations
		 */
		public void showParcelBT(int aValue, int bValue, int cValue, int width, int height, int depth, boolean optimized) {

			buildItems(aValue, bValue, cValue);
			buildCargo(width, height, depth);
			btToRoot(items, cargo, rotGroup, optimized);

			initialSetup();
			openInfoWindow();
		}
		
		/**
		 * Show the cargo solved by the greedy algorithm using pentominoes
		 * @param random if true, sets the greedy to random
		 * @param aValue value of the first kind of pentominoe
		 * @param bValue value of the second kind of pentominoe
		 * @param cValue value of the third kind of pentominoe
		 * @param width width of the  cargo
		 * @param height height of the cargo
		 * @param depth depth of the cargo
		 */
		public void showPentoGreedy(boolean random, int aValue, int bValue, int cValue, int width, int height, int depth) {

			buildPentos(aValue, bValue, cValue);
			buildCargo(width, height, depth);
			greedyToRoot(pentos, cargo, rotGroup, random);

			initialSetup();
			openInfoWindow();
		}
		
		 /**
		 * Show the cargo solved by the dynamic algorithm using pentominoes
		 * @param aValue value of the first kind of pentominoe
		 * @param bValue value of the second kind of pentominoe
		 * @param cValue value of the third kind of pentominoe
		 * @param width width of the  cargo
		 * @param height height of the cargo
		 * @param depth depth of the cargo
		 * @param limit highest value of the dimension to manipulate
		 * @param optimized if true, the algorithm implements the optimizations
		 */

		public void showPentoDynamic(int aValue, int bValue, int cValue, int width, int height, int depth, int limit, boolean optimized) {

			buildPentos(aValue, bValue, cValue);
			buildCargo(width, height, depth);
			dynamicToRoot(pentos, cargo, limit, optimized, rotGroup);

			initialSetup();
			openInfoWindow();
		}
		
		/**
		 * Show the cargo solved by the backtracking algorithm using pentominoes
		 * @param aValue value of the first kind of pentominoe
		 * @param bValue value of the second kind of pentominoe
		 * @param cValue value of the third kind of pentominoe
		 * @param width width of the  cargo
		 * @param height height of the cargo
		 * @param depth depth of the cargo
		 * @param optimized if true, the algorithm implements the optimizations
		 */
		public void showPentoBT(int aValue, int bValue, int cValue, int width, int height, int depth, boolean optimized) {

			buildPentos(aValue, bValue, cValue);
			buildCargo(width, height, depth);
			btToRoot(pentos, cargo, rotGroup, optimized);

			initialSetup();
			openInfoWindow();
		}
		
		/**
		 * Assign a value to each kind of parcel and build it
		 * @param aValue value of the first kind of parcel
		 * @param bValue value of the second kind of parcel
		 * @param cValue value of the third kind of parcel
		 */
		public void buildItems(int aValue, int bValue, int cValue) {
			Item A = new Item("A", aValue, 2, 2, 4);
			Item B = new Item("B", bValue, 2, 3, 4);
			Item C = new Item("C", cValue, 3, 3, 3);

	        this.items = new Item[] {A, B, C};
		}
		
		/**
		 * Assign a value to each kind of pentominoe and build it
		 * @param aValue value of the first kind of pentominoe
		 * @param bValue value of the second kind of pentominoe
		 * @param cValue value of the third kind of pentominoe
		 */
		public void buildPentos(int aValue, int bValue, int cValue) {
			Pentomino L = new Pentomino("L", aValue);
	        Pentomino P = new Pentomino("P", bValue);
	        Pentomino T = new Pentomino("T", cValue);

	        this.pentos = new Pentomino[] {L,P,T};
		}
		
		/**
		 * Build cargo with given width, height and depth
		 * @param width width of the  cargo
		 * @param height height of the cargo
		 * @param depth depth of the cargo
		 */
		public void buildCargo(int width, int height, int depth) {
			this.shape = new Item[width][height][depth];
	        this.cargo = new Cargo("cargo", shape);
		}

//		public void start(final Stage stage) {
//
//	        Pentomino L = new Pentomino("L", 3);
//	        Pentomino P = new Pentomino("P", 4);
//	        Pentomino T = new Pentomino("T", 5);
//
//	        Pentomino[] items = new Pentomino[] {L,P,T};
//	        Item[][][] shape = new Item[33][5][8];
//	        Cargo cargo = new Cargo("cargo",shape);
//			dynamicToRoot(items,cargo,1,true,rotGroup);
//
//			Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT, true);
//			scene.setFill(Color.ALICEBLUE);
//			setupMouse(scene);
//			stage.setTitle("Project 1.3 - Cargo");
//			stage.setScene(scene);
//			scene.setCamera(camera);
//			stage.show();
//		}


		/**
		 * Create the basic elements to build the scene, finalize and show
		 */
		public void initialSetup() {
			root.getChildren().add(rotGroup);
	        setupCam();
			Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT, true);
			scene.setFill(Color.ALICEBLUE);
			setupMouse(scene);
			stage.setTitle("Project 1.3 - Cargo");
			stage.setScene(scene);
			scene.setCamera(camera);
			stage.setOnCloseRequest(e -> this.infoStage.close());
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.getIcons().add(Window.icon);
			stage.show();
		}

		/**
		 * Solve by using the dynamic algorithm, add shapes to the scene
		 * @param items set of parcels to use
		 * @param limit highest value of the dimension to manipulate
		 * @param optimized if true, the algorithm implements the optimizations
		 * @param rotGroup rotational group to which the scene objects are added
		 */
		public void dynamicToRoot(Item[] items, int limit, boolean optimized, Group rotGroup) {
			Item[][][] result = DivideAndConquer.solve(items, cargo.getShape(), limit, optimized);
			this.cargo = new Cargo("whatever", result);
			this.cargo.printSolution(items, false, false);
			addBoxes(rotGroup, result);

		}
		
		/**
		 * Solve by using dynamic programming, add shapes to the scene
		 * @param pentos set of pentominoes to use
		 * @param limit highest value of the dimension to manipulate
		 * @param optimized if true, the algorithm implements the optimizations
		 * @param rotGroup rotational group to which the scene objects are added
		 */
		public void dynamicToRoot(Pentomino[] pentos, int limit, boolean optimized, Group rotGroup) {
			Item[][][] result = DivideAndConquer.solve(pentos, cargo.getShape(), limit, optimized);
			this.cargo = new Cargo("whatever", result);
			this.cargo.printSolution(Arrays.toItemArray(pentos), true, false);
			addBoxes(rotGroup, result);
		}

		/**
		*	Solve with greedy and add to rotation group
		*	@param items items given to solve
		* 	@param cargo cargo that will be represented
		*/
		public void greedyToRoot(Item[] items, Group rotGroup, boolean random) {
			Solver mySolver = new Solver("mySolver", items, cargo);
			mySolver.fillGreedyCargo(random, false);
			addBoxes(rotGroup, mySolver.getCargo().getShape());
		}

		/**
		*	Solve with greedy and add to rotation group
		*	@param pentos items given to solve
		*	@param cargo cargo that will be represented
		*/
		public void greedyToRoot(Pentomino[] pentos, Group rotGroup, boolean random) {
			PSolver mySolver = new PSolver("mySolver", pentos, cargo);
			mySolver.fillGreedyCargo(random, false);

			addBoxes(rotGroup, cargo.getShape());
		}


		/**
		 * Adding Pentominoes to the rotation group
		 * @param pentominoes pentominoes given to solve
		 * @param cargo cargo that will be represented
		 */
		public void btToRoot(Pentomino[] pentominoes, Group rotGroup, boolean optimized) {
			PBacktracking.solveFor(pentominoes, cargo.getShape(), optimized, 0);

			if (PBacktracking.tmp != null ) {
				addBoxes(rotGroup, PBacktracking.tmp.getShape());
				this.cargo = new Cargo("whatever", PBacktracking.tmp.getShape());
				this.cargo.printSolution(Arrays.toItemArray(pentominoes), true, false);
			} else {
				System.out.println("BT couldn't solve this cargo");
			}
		}

		/**
		 * Adding Items to the rotation group
		 * @param items items given to solve
		 * @param cargo cargo that will be represented
		 */
		public void btToRoot(Item[] items, Group rotGroup, boolean optimized) {
			Backtracking.solveFor(items, cargo.getShape(), optimized, 0);

			if (Backtracking.tmp != null ) {
				addBoxes(rotGroup, Backtracking.tmp.getShape());
				this.cargo = new Cargo("whatever", Backtracking.tmp.getShape());
				this.cargo.printSolution(items, false, false);
			} else {
				System.out.println("BT couldn't solve this cargo");
			}
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
						coordinates(box, i, j, k);
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

/**
 * A class to set up the basics of the camera
 */
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
