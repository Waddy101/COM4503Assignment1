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

import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.gl2.GLUT;

public class Room {
	
	private Mesh meshFloor, meshCeiling, meshWall1, meshWall2, meshWall3, meshWall4;

	private Render floor, ceiling, wall1, wall2, wall3, wall4;

	private Light spotlight1, spotlight2;

	private GLUT glut;
	private GLU glu;

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
	}

	public void doLights(GL2 gl, GLUT glut) {
		spotlight1.use(gl, glut, true);
		spotlight2.use(gl, glut, true);
	}

	public void createRoom(GL2 gl) {
		meshFloor = ProceduralMeshFactory.createPlane(30, 30, 100, 100, 1, 1);
    	floor = new Render(meshFloor);  
    	floor.initialiseDisplayList(gl);  
    	meshCeiling = ProceduralMeshFactory.createPlane(30, 30, 100, 100, 1, 1);
    	ceiling = new Render(meshCeiling);  
    	ceiling.initialiseDisplayList(gl); 
    	meshWall1 = ProceduralMeshFactory.createPlane(30, 30, 100, 100, 1, 1);
    	wall1 = new Render(meshWall1);  
    	wall1.initialiseDisplayList(gl); 
    	meshWall2 = ProceduralMeshFactory.createPlane(30, 30, 100, 100, 1, 1);
    	wall2 = new Render(meshWall2);  
    	wall2.initialiseDisplayList(gl); 
    	meshWall3 = ProceduralMeshFactory.createPlane(30, 30, 100, 100, 1, 1);
    	wall3 = new Render(meshWall3);  
    	wall3.initialiseDisplayList(gl); 
    	meshWall4 = ProceduralMeshFactory.createPlane(30, 30, 100, 100, 1, 1);
    	wall4 = new Render(meshWall4);  
    	wall4.initialiseDisplayList(gl); 
	}

	public void renderRoom(GL2 gl) {
		GLUquadric quadric = glu.gluNewQuadric();
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
	    	gl.glTranslated(-15.0, 0.0 ,0.0);
	    	gl.glRotated(-90, 0.0, 0.0, 1.0);
	    	wall1.renderDisplayList(gl);
	    gl.glPopMatrix();
	    gl.glPushMatrix();
	    	gl.glTranslated(0.0,0.0,-15.0);
	    	gl.glRotated(90, 1.0, 0.0, 0.0);
			wall2.renderDisplayList(gl);
	    gl.glPopMatrix();
	    gl.glPushMatrix();
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
	    	gl.glTranslated(0.0, 0.3, 0.0); 
	    	gl.glRotated(-90, 1, 0, 0);
	    	glu.gluCylinder(quadric, 0.1, 0.1, 0.2, 10, 10);
	    gl.glPopMatrix();
	    glu.gluDeleteQuadric(quadric);
	    //Draw some objects in the room
	    //Make a window thing
	}
} 