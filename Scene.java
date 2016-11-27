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

    private Light light1, light2, light3;

  	public Scene(GL2 gl, Camera camera) {
  		light1 = new Light(GL2.GL_LIGHT0);
      float[] position = new float[]{0.0f,-50.0f,0.0f, 1};
      light2 = new Light(GL2.GL_LIGHT6, position);
      position = new float[]{-50.0f, 0.0f, 0.0f, 1};
      light3 = new Light(GL2.GL_LIGHT7, position);
	    this.camera = camera;
	    this.robot = new Robot();
	    this.room = new Room(gl);
    }

    public void setCanvasSize(int w, int h) {
	    canvaswidth=w;
	    canvasheight=h;
    }

    public void incRotate() {
      rotate=(rotate+INC_ROTATE)%360;
    }

    public void setLightLevel(int value, int light) {
      float normalisedValue = (float)value/100;
      float normalisedAmbientValue = (float)value/1000;
      if (light == 1) {
        light1.setDiffuse(new float[]{normalisedValue, normalisedValue, normalisedValue});
        light1.setSpecular(new float[]{normalisedValue, normalisedValue, normalisedValue});        
        light1.setAmbient(new float[]{normalisedAmbientValue, normalisedAmbientValue, normalisedAmbientValue});
        System.out.println("Light 1 changed");
      } else if (light == 2) {
        light2.setDiffuse(new float[]{normalisedValue, normalisedValue, normalisedValue});
        light2.setSpecular(new float[]{normalisedValue, normalisedValue, normalisedValue});        
        light2.setAmbient(new float[]{normalisedAmbientValue, normalisedAmbientValue, normalisedAmbientValue});
        System.out.println("Light 2 changed");
      } else if (light == 3) {
        light3.setDiffuse(new float[]{normalisedValue, normalisedValue, normalisedValue});
        light3.setSpecular(new float[]{normalisedValue, normalisedValue, normalisedValue});        
        light3.setAmbient(new float[]{normalisedAmbientValue, normalisedAmbientValue, normalisedAmbientValue});
        System.out.println("Light 3 changed");
      }
    }

    public void update() {
    	incRotate();
    }
    
  	public void render(GL2 gl) {
  	  gl.glClear(GL2.GL_COLOR_BUFFER_BIT|GL2.GL_DEPTH_BUFFER_BIT);
  		gl.glLoadIdentity();
  		camera.view(glu);
  		doLights(gl);
  		room.doLights(gl, glut);
      robot.doEyeLights(gl, rotate);
  		robot.drawRobot(gl, rotate);
  		room.renderRoom(gl, rotate);
  	}

  	private void doLights(GL2 gl) {
  		gl.glPushMatrix();
  			if (light1.getSwitchedOn()) {
  				light1.use(gl, glut, true);
  			}
  			else light1.disable(gl);
  		gl.glPopMatrix();
      gl.glPushMatrix();
        if (light2.getSwitchedOn()) {
          light2.use(gl, glut, true);
        }
        else light2.disable(gl);
      gl.glPopMatrix();
      gl.glPushMatrix();
        if (light3.getSwitchedOn()) {
          light3.use(gl, glut, true);
        }
        else light3.disable(gl);
      gl.glPopMatrix();
  	}
  
}