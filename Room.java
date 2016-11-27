/*
I delcare that this is my own work
Author: Adam Wadsworth AMWadsworth1@sheffield.ac.uk
Last updated: 17 November 2016
*/

import com.jogamp.opengl.*;
import com.jogamp.opengl.util.*;
import com.jogamp.opengl.util.awt.*;
import com.jogamp.opengl.util.texture.*;
import com.jogamp.opengl.util.texture.awt.*;

import java.io.File;
import java.awt.image.*;
import javax.imageio.*;

import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.gl2.GLUT;

public class Room {
	
	private Mesh meshFloor, meshCeiling, meshWall, meshWall2, meshWall3, meshWall4;

	private Render floor, ceiling, wall1, wall2, wall3, wall4;

	private Light spotlight1, spotlight2;

	private GLUT glut;
	private GLU glu;
	private GLUquadric quadric;

	public Room(GL2 gl) {
		glut = new GLUT();
		glu = new GLU();
		float[] position1 = {0,14.5f,-7.5f,1};
	    spotlight1 = new Light(GL2.GL_LIGHT1, position1);
	    float[] direction1 = {0,-1,0};
	    spotlight1.makeSpotlight(direction1, 10f);
	    float[] position2 = {0,14.5f,7.5f,1};
	    spotlight2 = new Light(GL2.GL_LIGHT2, position2);
	    float[] direction2 = {0,-1,0};
	    spotlight2.makeSpotlight(direction2, 10f);
	    createRoom(gl);
	}

	public void doLights(GL2 gl, GLUT glut) {
		spotlight1.use(gl, glut, true);
		spotlight2.use(gl, glut, true);
	}

	public void createRoom(GL2 gl) {
		Texture floorTex = loadTexture(gl, "floor.jpg");
		meshFloor = ProceduralMeshFactory.createPlane(30, 30, 50, 50, 1, 1);
    	floor = new Render(meshFloor, floorTex); 
    	floor.initialiseDisplayList(gl, true);  
    	Texture ceilingTex = loadTexture(gl, "ceiling.jpg");
    	meshCeiling = ProceduralMeshFactory.createPlane(30, 30, 50, 50, 1, 1);
    	ceiling = new Render(meshCeiling, ceilingTex);  
    	ceiling.initialiseDisplayList(gl, true);
    	Texture wallTex = loadTexture(gl, "wall.jpg"); 
    	meshWall = ProceduralMeshFactory.createPlane(30, 30, 50, 50, 1, 1);
    	wall1 = new Render(meshWall, wallTex);  
    	wall1.initialiseDisplayList(gl, true); 
    	wall2 = new Render(meshWall, wallTex);  
    	wall2.initialiseDisplayList(gl, true); 
    	wall3 = new Render(meshWall, wallTex);  
    	wall3.initialiseDisplayList(gl, true); 
    	wall4 = new Render(meshWall, wallTex);  
    	wall4.initialiseDisplayList(gl, true); 
    	quadric = glu.gluNewQuadric();
	}

	public void renderRoom(GL2 gl, double rotate) {
		gl.glPushMatrix();
			gl.glTranslated(0.0,-15.0, 0.0);
			floor.renderDisplayList(gl);
    	gl.glPopMatrix();
    	gl.glPushMatrix();
	    	gl.glTranslated(0.0,15.0, 0.0);
	    	gl.glRotated(180, 1.0, 0.0, 0.0);
	    	ceiling.renderDisplayList(gl);			
	    gl.glPopMatrix();
	    gl.glPushMatrix();
	    	gl.glRotated(-90, 1, 0, 0);
	    	gl.glTranslated(-15.0, 0.0 ,0.0);
	    	gl.glRotated(-90, 0.0, 0.0, 1.0);
	    	wall1.renderDisplayList(gl);
	    gl.glPopMatrix();
	    gl.glPushMatrix();
	    	gl.glRotated(180, 0, 0, 1);
	    	gl.glTranslated(0.0,0.0,-15.0);
	    	gl.glRotated(90, 1.0, 0.0, 0.0);
			wall2.renderDisplayList(gl);
	    gl.glPopMatrix();
	    gl.glPushMatrix();
	    	gl.glRotated(-90, 1, 0, 0);
	    	gl.glTranslated(15.0,0.0,0.0);
	    	gl.glRotated(90, 0.0, 0.0, 1.0);
			wall3.renderDisplayList(gl);
	    gl.glPopMatrix();
	    gl.glPushMatrix();
	    	gl.glTranslated(0.0,0.0, 15.0);
	    	gl.glRotated(-90, 1.0, 0.0, 0.0);
	    	wall4.renderDisplayList(gl); 
	    gl.glPopMatrix();
	    gl.glPushMatrix();
	    	//Drawing lamps
	    	gl.glTranslated(0,14.5f,7.5f);
	    	gl.glPushMatrix();
		    	gl.glRotated(-90, 1, 0, 0);
		    	glut.glutSolidCone(0.8, 0.3, 10, 10);
	    	gl.glPopMatrix();
	    	gl.glPushMatrix();
		    	gl.glScaled(1.0, 0.05, 1.0);
		    	glut.glutSolidSphere(0.8, 10, 10);
	    	gl.glPopMatrix();
	    	gl.glTranslated(0.0, 0.3, 0.0);
 	    	gl.glRotated(-90, 1, 0, 0);    	
 	    	glu.gluCylinder(quadric, 0.1, 0.1, 0.2, 10, 10);
	    gl.glPopMatrix();
	    gl.glPushMatrix();
	    	gl.glTranslated(0,14.5f,-7.5f);
	    	gl.glPushMatrix();
		    	gl.glRotated(-90, 1, 0, 0);
		    	glut.glutSolidCone(0.8, 0.3, 10, 10);
	    	gl.glPopMatrix();
	    	gl.glPushMatrix();
		    	gl.glScaled(1.0, 0.05, 1.0);
		    	glut.glutSolidSphere(0.8, 10, 10);
	    	gl.glPopMatrix();
	    	gl.glTranslated(0.0, 0.3, 0.0); 
 	    	gl.glRotated(-90, 1, 0, 0);
 	    	glu.gluCylinder(quadric, 0.1, 0.1, 0.2, 10, 10);
	    gl.glPopMatrix();
	    gl.glPushMatrix();
	    	//Drawing stack of cubes
	    	gl.glTranslated(5.0, -13.0, 5.0);
	    	glut.glutSolidCube(4.0f); 
	    	gl.glPushMatrix();
	    		gl.glTranslated(0, 4.3, 0.0);
	    		gl.glPushMatrix();
	    			gl.glRotated(rotate,0,1,0);
	    			gl.glRotated(45, 0, 0 , 1);
	    			gl.glRotated(45, 1, 0, 0);
	    			glut.glutSolidCube(3.0f);
	    		gl.glPopMatrix();
	    		gl.glPushMatrix();	
	    			gl.glTranslated(0, 3.3, 0);
	    			glut.glutSolidCube(2.0f);
	    		gl.glPopMatrix();
	    	gl.glPopMatrix();
	    gl.glPopMatrix();
	    gl.glPushMatrix();
	    	//Drawing torus
	    	gl.glTranslated(-5.0, 10.5, -5.0);
	    	gl.glPushMatrix();
		    	gl.glRotated(-45, 0, 1, 0);
		    	glut.glutSolidTorus(1.0, 3.0, 10, 50);
	    	gl.glPopMatrix();
	    	gl.glTranslated(0.0, 3.9, 0.0);
	    	gl.glRotated(-90, 1, 0, 0);
 	    	glu.gluCylinder(quadric, 0.3, 0.3, 0.6, 10, 10);
    	gl.glPopMatrix();
    	gl.glPushMatrix();
    		//Drawing cylinders on the walls
    		gl.glTranslated(-3.0, -3.0, 10.0);
    		gl.glPushMatrix();
    			gl.glScaled(1.0, 1.0, 0.05);
    			glut.glutSolidSphere(1.0, 10, 10);
    		gl.glPopMatrix();
    		glu.gluCylinder(quadric, 1.0, 1.0, 5.0, 10, 10);
    	gl.glPopMatrix();
    	gl.glPushMatrix();
    		gl.glTranslated(-3.0, -8.0, 10.0);
    		gl.glPushMatrix();
    			gl.glScaled(1.0, 1.0, 0.05);
    			glut.glutSolidSphere(1.0, 10, 10);
    		gl.glPopMatrix();
    		glu.gluCylinder(quadric, 1.0, 1.0, 5.0, 10, 10);
    	gl.glPopMatrix();

	    //Draw some objects in the room
	    //Make a window thing
	}

	private Texture loadTexture(GL2 gl, String filename) {
	    Texture tex = null;
	    try {
	      File f = new File(filename);
	      BufferedImage img = ImageIO.read(f);
	      ImageUtil.flipImageVertically(img);
	      tex = AWTTextureIO.newTexture(GLProfile.getDefault(), img, true);
	      tex.setTexParameteri(gl, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR_MIPMAP_LINEAR);
	      tex.setTexParameteri(gl, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR_MIPMAP_LINEAR);
	    }
	    catch(Exception e) {
	      System.out.println("Error loading texture " + filename); 
	    }
	    return tex;
	}
  }