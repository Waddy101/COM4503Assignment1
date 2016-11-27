/*
I delcare that this is my own work
Author: Adam Wadsworth AMWadsworth1@sheffield.ac.uk
Last updated: 17 November 2016
*/

import java.io.File;
import java.awt.image.*;
import javax.imageio.*;
import com.jogamp.opengl.util.awt.*;

import com.jogamp.opengl.*;
import com.jogamp.opengl.util.*;
import com.jogamp.opengl.util.texture.*;
import com.jogamp.opengl.util.texture.awt.*;

import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

public class Scene {
	private GLU glu = new GLU();
 	private GLUT glut = new GLUT();

  	private boolean objectsOn = true;

  	private int canvaswidth=0, canvasheight=0;

    private final double INC_ROTATE=2.0;
    private double rotate=0.0;

    private Camera camera;
    private Robot robot;
    private Room room;
    private Render plane;

    private Light light;

  	public Scene(GL2 gl, Camera camera) {
  		light = new Light(GL2.GL_LIGHT0);
    
	    this.camera = camera;
	    this.robot = new Robot();
	    this.room = new Room(gl);
    }

    public void setCanvasSize(int w, int h) {
	    canvaswidth=w;
	    canvasheight=h;
    }

    public Light getLight() {
    	return light;
  	}

    public void incRotate() {
      rotate=(rotate+INC_ROTATE)%360;
    }


    public void update() {
    	incRotate();
    }
    
  	public void render(GL2 gl) {
  	  gl.glClear(GL2.GL_COLOR_BUFFER_BIT|GL2.GL_DEPTH_BUFFER_BIT);
  		gl.glLoadIdentity();
  		camera.view(glu);
  		//doLight(gl);
  		room.doLights(gl, glut);
      robot.doEyeLights(gl, rotate);
  		robot.drawRobot(gl, rotate);
  		room.createRoom(gl);
  		room.renderRoom(gl);
  	}

  	private void doLight(GL2 gl) {
		gl.glPushMatrix();
			if (light.getSwitchedOn()) {
				light.use(gl, glut, true);
			}
			else light.disable(gl);
		gl.glPopMatrix();
  	}
  
}