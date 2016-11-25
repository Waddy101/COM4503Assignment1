/*
I delcare that this is my own work
Author: Adam Wadsworth AMWadsworth1@sheffield.ac.uk
Last updated: 17 November 2016
*/

import java.awt.*;
import java.awt.event.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.*;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Main extends JFrame implements GLEventListener, ActionListener, ItemListener, ChangeListener, MouseMotionListener {
	
	public final static int WIDTH=800;
  	public final static int HEIGHT=800;
	private static final float NEAR_CLIP=0.1f;
	private static final float FAR_CLIP=100.0f;    
	private static final boolean CONTINUOUS_ANIMATION = false;

  	private Point lastpoint;
	private int width, height;

	private JSlider light1Slider, light2Slider, light3Slider;
	private Button startAnim, pauseAnim, resetScene;
	private boolean continuousAnimation = CONTINUOUS_ANIMATION;

	private Camera camera;
	private Scene scene;
	private GLCanvas canvas;

	public static void main(String[] args) {
	    Main gl = new Main();
	    gl.setVisible(true);
	}

	public Main() {
	    setTitle("Animated Robot");
	    setSize(WIDTH, HEIGHT);

	    GLProfile glp = GLProfile.getDefault();
	    GLCapabilities caps = new GLCapabilities(glp);
	    canvas = new GLCanvas(caps);
	    add(canvas, "Center");
	    canvas.addGLEventListener(this);
	        
	    addWindowListener(new WindowAdapter() {
	      public void windowClosing(WindowEvent e) {
	        System.exit(0);
	      }
	    } );

	    MenuBar menuBar = new MenuBar();
	    setMenuBar(menuBar);
	      Menu fileMenu = new Menu("File");
	        MenuItem quitItem = new MenuItem("Quit");
	        quitItem.addActionListener(this);
	        fileMenu.add(quitItem);
	    menuBar.add(fileMenu);

	    JPanel p = new JPanel(new GridLayout(2,1));
	      JPanel p1 = new JPanel(new GridLayout(6,1));
	      	p1.add(new JLabel("Light 1"));
	        light1Slider = addSlider(p1, this);
	        p1.add(new JLabel("Light 2"));
	        light2Slider = addSlider(p1, this);
	        p1.add(new JLabel("Light 3"));
	        light3Slider = addSlider(p1, this);
	      p.add(p1);
	      p1 = new JPanel(new GridLayout(4,1));
	        startAnim = new Button("Start animation");
	        startAnim.setActionCommand("StartAnim");
	        startAnim.addActionListener(this);
	        p1.add(startAnim);
	        pauseAnim = new Button("Pause animation");
	        pauseAnim.setActionCommand("PauseAnim");
	        pauseAnim.addActionListener(this);
	        p1.add(pauseAnim);
	        resetScene = new Button("Reset scene");
	        resetScene.setActionCommand("ResetScene");
	        resetScene.addActionListener(this);
	        p1.add(resetScene);
	      p.add(p1);
	    add(p, "East");

	    canvas.addMouseMotionListener(this); // link mouse motion events

	    FPSAnimator animator = new FPSAnimator(canvas, 30);
	    //animator.add(canvas);
	    animator.start();
    }

    private JSlider addSlider(JPanel p, ChangeListener a) {
	    JSlider s = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
	    s.addChangeListener(a);
	    p.add(s);
	    return s;
    }

    public void stateChanged(ChangeEvent e) {
	    JSlider source = (JSlider)e.getSource();
    }

    public void actionPerformed(ActionEvent e) {
	    if(e.getActionCommand().equalsIgnoreCase("quit")) {
	      System.exit(0);
	    }
	    else if (e.getActionCommand().equalsIgnoreCase("startanim")) {
	      setContinuousAnimation(true);
	    }
	    else if (e.getActionCommand().equalsIgnoreCase("pauseanim")) {
	      setContinuousAnimation(false);
	    }
	    //else if (e.getActionCommand().equalsIgnoreCase("resetscene")) {
	     // reset();
	    //}
    }

    public void itemStateChanged(ItemEvent e) {
	    Object source = e.getSource();
    }
  
    private void setContinuousAnimation(boolean b) {
    	continuousAnimation = b;
    }

    /*
    * METHODS DEFINED BY GLEventListener
    */

  	/* initialisation */
  	public void init (GLAutoDrawable drawable) {
	    GL2 gl = drawable.getGL().getGL2();
	    gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
	    gl.glEnable(GL2.GL_DEPTH_TEST);
	    gl.glEnable(GL2.GL_CULL_FACE);
	    gl.glCullFace(GL2.GL_BACK);
	    gl.glShadeModel(GL2.GL_SMOOTH);
	    gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL); 
	    gl.glEnable(GL2.GL_LIGHTING);   
	    gl.glEnable(GL2.GL_LIGHT0);    
	    gl.glEnable(GL2.GL_NORMALIZE);  			
	    double radius = 50.0;
	    double theta = Math.toRadians(-45);
	    double phi = Math.toRadians(30);
	    camera = new Camera(theta, phi, radius);
	    scene = new Scene(gl, camera);
  	}
   
  	/* Called to indicate the drawing surface has been moved and/or resized  */
  	public void reshape (GLAutoDrawable drawable, int x, int y, int width, int height) {
	    GL2 gl = drawable.getGL().getGL2();

	    this.width=width;
	    this.height=height;
	    
	    scene.setCanvasSize(width,height);
	    
	    float fAspect=(float) width/height;
	    float fovy=60.0f;

	    gl.glViewport(0, 0, width, height);
	    gl.glMatrixMode(GL2.GL_PROJECTION);
	    gl.glLoadIdentity();
	    
	    float top=(float) Math.tan(Math.toRadians(fovy*0.5))*NEAR_CLIP;
	    float bottom=-top;
	    float left=fAspect*bottom;
	    float right=fAspect*top;
	    
	    gl.glFrustum(left, right, bottom, top, NEAR_CLIP, FAR_CLIP);
	    gl.glMatrixMode(GL2.GL_MODELVIEW);
	}

	/* draw */
	public void display(GLAutoDrawable drawable) {
	  	GL2 gl = drawable.getGL().getGL2();
	    if (continuousAnimation) scene.update();
	    scene.render(gl);
	}

    public void dispose(GLAutoDrawable drawable) {
    }


	/**
	 * mouse is used to control camera position
	 *
	 * @param e  instance of MouseEvent
	*/    
	public void mouseDragged(MouseEvent e) {
	    Point ms = e.getPoint();
	    
	    float dx=(float) (ms.x-lastpoint.x)/width;
	    float dy=(float) (ms.y-lastpoint.y)/height;
	    
	    if (e.getModifiers()==MouseEvent.BUTTON1_MASK)
	    {
	      camera.updateThetaPhi(-dx*2.0f, dy*2.0f);
	    }
	    else if (e.getModifiers()==MouseEvent.BUTTON3_MASK)
	      camera.updateRadius(-dy);
	    
	    lastpoint = ms;
  	}

    /**
     * mouse is used to control camera position
     *
     * @param e  instance of MouseEvent
     */  
    public void mouseMoved(MouseEvent e) {   
    	lastpoint = e.getPoint(); 
    }

}