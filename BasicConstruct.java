import java.util.*;

// BorderLayout stuff
import java.awt.*;
import javax.swing.*;

// Canvas3D
import javax.media.j3d.Canvas3D;

// The Universe
import com.sun.j3d.utils.universe.SimpleUniverse;

// The BranchGroup
import javax.media.j3d.BranchGroup;

// For the Box
import com.sun.j3d.utils.geometry.Box;
import javax.vecmath.*;

// The directional light
import javax.media.j3d.DirectionalLight;

// For the bouding sphere of the light source
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
// Transformgroup
import javax.media.j3d.TransformGroup;
import com.sun.j3d.utils.behaviors.mouse.*;

public class BasicConstruct extends JFrame {

  protected SimpleUniverse simpleU;
  protected BranchGroup bg;

  /**
   * Constructor that constructs the window with the given name.
   *
   * @param name
   *            The name of the window, in String format
   */
  public BasicConstruct(String name) {
    super(name);

    initial_setup();
  }

  /**
   * Perform the essential setups for the Java3D
   */
  protected void initial_setup() {

    getContentPane().setLayout(new BorderLayout());
    GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
    Canvas3D canvas3D = new Canvas3D(config);
    getContentPane().add("Center", canvas3D);
    simpleU = new SimpleUniverse(canvas3D);
    bg = new BranchGroup();
  }

  /**
   * Adds a light source to the universe
   *
   * @param direction
   *            The inverse direction of the light
   * @param color
   *            The color of the light
   */
  public void addDirectionalLight(Vector3f direction, Color3f color) {
    // Creates a bounding sphere for the lights
    BoundingSphere bounds = new BoundingSphere();
    bounds.setRadius(1000d);

    // Then create a directional light with the given
    // direction and color
    DirectionalLight lightD = new DirectionalLight(color, direction);
    lightD.setInfluencingBounds(bounds);

    // Then add it to the root BranchGroup
    bg.addChild(lightD);
  }

  /**
   * Adds a box to the universe
   *
   * @param x
   *            The x dimension of the box
   * @param y
   *            The y dimension of the box
   * @param z
   *            The z dimension of the box
   */
  public void addBox(float x, float y, float z, Color3f diffuse, Color3f spec, Vector3f vector) {
    // Add a box with the given dimension

    // First setup an appearance for the box
    Appearance app = new Appearance();
    Material mat = new Material();
    mat.setDiffuseColor(diffuse);
    mat.setSpecularColor(spec);
    mat.setShininess(5.0f);

    app.setMaterial(mat);
    Box box = new Box(x*5, y*5, z*5, app);

    // Create a TransformGroup and make it the parent of the box
    TransformGroup tg = new TransformGroup();

    Transform3D transform = new Transform3D();
    transform.setTranslation(vector);
    tg.setTransform(transform);

    tg.addChild(box);

    // Then add it to the bg
    bg.addChild(tg);

    tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

    MouseRotate myMouseRotate = new MouseRotate();
    myMouseRotate.setTransformGroup(tg);
    myMouseRotate.setSchedulingBounds(new BoundingSphere());
    bg.addChild(myMouseRotate);

    MouseTranslate myMouseTranslate = new MouseTranslate();
    myMouseTranslate.setTransformGroup(tg);
    myMouseTranslate.setSchedulingBounds(new BoundingSphere());
    bg.addChild(myMouseTranslate);

    MouseZoom myMouseZoom = new MouseZoom();
    myMouseZoom.setTransformGroup(tg);
    myMouseZoom.setSchedulingBounds(new BoundingSphere());
    bg.addChild(myMouseZoom);
  }

  /**
   * Calculating the vector for each item translation
   * @param x the x-coordinate to start from
   * @param y the y-coordinate to start from
   * @param z the z-coordinate to start from
   * @param cargo the cargo which the item has to be drawn into
   * @param item the item that has to be drawn
   * @return the vector for the transformation requested
   */
  public static Vector3f vec(float x, float y, float z, Cargo cargo, Item item) {

	  float cW = ((float)(cargo.getWidth())*5f);
	  float cH = ((float)(cargo.getHeight())*5f);
	  float cD = ((float)(cargo.getDepth())*5f);
	  float iW = ((float)(item.getWidth())*5f);
	  float iH = ((float)(item.getHeight())*5f);
	  float iD = ((float)(item.getDepth())*5f);

	  float vecX = x - cW + iW;
	  float vecY = y + cH - iH;
	  float vecZ = z - cD + iD;

	  Vector3f vector = new Vector3f(vecX,vecY,vecZ);

	  return vector;

	  /*Vector3f vector = new Vector3f(
			  ( (float)(i) - ( (float)(cargo.getWidth()) / 2f) + ( (float)(tmp.getWidth()) / 2f) ),
			  ( (float)(j) + ( (float)(cargo.getHeight()) / 2f) - ( (float)(tmp.getHeight() / 2f) ) ),
			  ( (float)(k) - ( (float)(cargo.getDepth()) / 2f) + ( (float)(tmp.getDepth()) / 2f) ) );
			  */

  }

  /**
   * Finalise everything to get ready
   */
  public void finalise() {
    // Then add the branch group into the Universe
    simpleU.addBranchGraph(bg);

    // And set up the camera position
    simpleU.getViewingPlatform().setNominalViewingTransform();
  }

  public static void main(String[] argv) {

    BasicConstruct bc = new BasicConstruct("Foo");

    bc.setSize(250, 250);

    ArrayList<Integer> numbers = new ArrayList<Integer>();

    Item A = new Item("A",3,2,2,4, new Color(255,0,0));
    Item B = new Item("B",4,2,3,4, new Color(0,0,255));
    Item C = new Item("C",5,3,3,3, new Color(0,255,0));
    Item[] items = {A,B,C};
    Cargo cargo = new Cargo("cargo",20,5,8);


    Backtracking.solveFor(items,cargo.getShape());
    Item[][][] solution = Backtracking.tmp.getShape();
    System.out.println("cargo solved");

    for (int i = 0; i<solution.length; i++) {
    	System.out.println("Hello");
    	for (int j = 0; j<solution[i].length; j++) {
    		for (int k = 0; k < solution[i][j].length; k++) {
    			if (!solution[i][j][k].matchSN(numbers)) {
    				numbers.add(solution[i][j][k].serialNumber());
    				Item tmp = solution[i][j][k];
    				Vector3f vector = vec(i,j,k,cargo,tmp);
    				bc.addBox((float)tmp.getWidth(), (float)tmp.getHeight(), (float)tmp.getDepth(), new Color3f(tmp.getColor()), new Color3f(tmp.getColor()), vector);
    			}
    		}
    	}
    }

    bc.addDirectionalLight(new Vector3f(0f, 0f, -1),new Color3f(1f, 1f, 0f));
    bc.finalise();

    bc.show();

    return;
  }
}
