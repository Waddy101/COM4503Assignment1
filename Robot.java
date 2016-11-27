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
import com.jogamp.opengl.util.gl2.GLUT;

public class Robot {
	
	private GLU glu = new GLU();
 	private GLUT glut = new GLUT();
	
	private Light leftEye, rightEye;

	public Robot() {
		float[] leftEyePos = {0.0f,0.0f,0.0f, 1};
		float[] rightEyePos = {0.0f,0.0f,0.0f, 1};
		leftEye = new Light(GL2.GL_LIGHT4, leftEyePos);
		rightEye = new Light(GL2.GL_LIGHT5, rightEyePos);
		float[] direction = {0,0,-1};
	    leftEye.makeSpotlight(direction, 5f);
	    rightEye.makeSpotlight(direction, 5f);
	}

	public void doEyeLights(GL2 gl, double x, double y, double z, double rotateY) {
		gl.glPushMatrix();
			gl.glTranslated(x, y, z);
			gl.glRotated(rotateY, 0, 1, 0);
			gl.glPushMatrix();
				gl.glRotated(30,1.0,1.0,0.0);
	      		gl.glTranslated(0.0f,0.f,-1.0f);
	      		leftEye.use(gl, glut, false);
			gl.glPopMatrix();
			gl.glPushMatrix();
				gl.glRotated(-30,-1.0,1.0,0.0);
      			gl.glTranslated(0.0f,0.0f,-1.0f);
      			rightEye.use(gl, glut, false);
			gl.glPopMatrix();		
		gl.glPopMatrix();
	}

	public void drawRobot(GL2 gl, double x, double y, double z, double rotateY) {
		setMaterial(gl,0,0,0,0,false);

		gl.glPushMatrix();
			//Robot Body
			gl.glTranslated(x, y, z);
			gl.glRotated(rotateY, 0, 1, 0);
      		glut.glutSolidSphere(1.0f, 20, 20);
      		gl.glPushMatrix();
      			//Robot Eyes
      			gl.glPushMatrix();
      				gl.glRotated(30,1.0,1.0,0.0);
	      			gl.glTranslated(0.0f,0.f,-1.0f);
	      			setMaterial(gl,0,0,0,0,true);	      			
	      			glut.glutSolidSphere(0.2f, 20, 20);
	      			setMaterial(gl,0,0,0,0,false);
      			gl.glPopMatrix();
      			gl.glPushMatrix();
      				gl.glRotated(-30,-1.0,1.0,0.0);
      				gl.glTranslated(0.0f,0.0f,-1.0f);
      				setMaterial(gl,0,0,0,0,true);
    	      		glut.glutSolidSphere(0.2f, 20, 20);
    	      		setMaterial(gl,0,0,0,0,false);
      			gl.glPopMatrix();
      		gl.glPopMatrix();

			gl.glPushMatrix();
				//Upper Arm
				gl.glTranslated(0.9f, 0.0f, 0.0f);
				gl.glRotated(45.0,0.0,1.0,0.0);
				gl.glTranslated(1.0f, 0.0f, 0.0f);
				gl.glPushMatrix();
						gl.glScaled(1.0, 0.4, 0.4);
						glut.glutSolidSphere(1.0f, 20, 20);
				gl.glPopMatrix();
				gl.glPushMatrix();
					//Lower Arm
					gl.glTranslated(0.9f, 0.0f, 0.0f);
					gl.glRotated(60.0,0.0,1.0,0.0);
					gl.glTranslated(1.0f, 0.0f,0.0f);
					gl.glPushMatrix();
						gl.glScaled(1.0, 0.4, 0.4);
						glut.glutSolidSphere(1.0f, 20, 20);
					gl.glPopMatrix();

					gl.glPushMatrix();
						//Hand
						gl.glTranslated(1.0f, 0.0f, 0.0f);
						glut.glutSolidSphere(0.2f, 20, 20);
						gl.glPushMatrix();
							//Finger
							gl.glRotated(30,0.0,1.0,0.0);
							gl.glTranslated(0.2f, 0.0f, 0.0f);
							gl.glPushMatrix();
								gl.glScaled(1.0, 0.2, 0.2);
								glut.glutSolidSphere(0.2f, 20, 20);
							gl.glPopMatrix();
						gl.glPopMatrix();
						gl.glPushMatrix();
							//Finger
							gl.glRotated(-30,0.0,1.0,0.0);
							gl.glTranslated(0.2f, 0.0f, 0.0f);
							gl.glPushMatrix();
								gl.glScaled(1.0, 0.2, 0.2);
								glut.glutSolidSphere(0.2f, 20, 20);
							gl.glPopMatrix();
						gl.glPopMatrix();
						gl.glPushMatrix();
							//Finger
							gl.glRotated(30,0.0,0.0,1.0);
							gl.glTranslated(0.2f, 0.0f, 0.0f);
							gl.glPushMatrix();
								gl.glScaled(1.0, 0.2, 0.2);
								glut.glutSolidSphere(0.2f, 20, 20);
							gl.glPopMatrix();
						gl.glPopMatrix();
						gl.glPushMatrix();
							//Finger
							gl.glRotated(-30,0.0,0.0,1.0);
							gl.glTranslated(0.2f, 0.0f, 0.0f);
							gl.glPushMatrix();
								gl.glScaled(1.0, 0.2, 0.2);
								glut.glutSolidSphere(0.2f, 20, 20);
							gl.glPopMatrix();
						gl.glPopMatrix();
					gl.glPopMatrix();
				gl.glPopMatrix();
			gl.glPopMatrix();
			gl.glPushMatrix();
				//Upper Arm
				gl.glTranslated(-0.9f, 0.0f, 0.0f);
				gl.glRotated(-45.0,0.0,1.0,0.0);
				gl.glTranslated(-1.0f, 0.0f, 0.0f);
				gl.glPushMatrix();
						gl.glScaled(1.0, 0.4, 0.4);
						glut.glutSolidSphere(1.0f, 20, 20);
				gl.glPopMatrix();

				gl.glPushMatrix();

					gl.glTranslated(-0.9f, 0.0f, 0.0f);
					gl.glRotated(-60.0,0.0,1.0,0.0);
					gl.glTranslated(-1.0f, 0.0f,0.0f);
					gl.glPushMatrix();
						gl.glScaled(1.0, 0.4, 0.4);
						glut.glutSolidSphere(1.0f, 20, 20);
					gl.glPopMatrix();
					gl.glPushMatrix();
						//Hand
						gl.glTranslated(-1.0f, 0.0f, 0.0f);
						glut.glutSolidSphere(0.2f, 20, 20);

						gl.glPushMatrix();
							//Finger
							gl.glRotated(30,0.0,1.0,0.0);
							gl.glTranslated(-0.2f, 0.0f, 0.0f);
							gl.glPushMatrix();
								gl.glScaled(1.0, 0.2, 0.2);
								glut.glutSolidSphere(0.2f, 20, 20);
							gl.glPopMatrix();
						gl.glPopMatrix();
						gl.glPushMatrix();
							//Finger
							gl.glRotated(-30,0.0,1.0,0.0);
							gl.glTranslated(-0.2f, 0.0f, 0.0f);
							gl.glPushMatrix();
								gl.glScaled(1.0, 0.2, 0.2);
								glut.glutSolidSphere(0.2f, 20, 20);
							gl.glPopMatrix();
						gl.glPopMatrix();
						gl.glPushMatrix();
							//Finger
							gl.glRotated(30,0.0,0.0,1.0);
							gl.glTranslated(-0.2f, 0.0f, 0.0f);
							gl.glPushMatrix();
								gl.glScaled(1.0, 0.2, 0.2);
								glut.glutSolidSphere(0.2f, 20, 20);
							gl.glPopMatrix();
						gl.glPopMatrix();
						gl.glPushMatrix();
							//Finger
							gl.glRotated(-30,0.0,0.0,1.0);
							gl.glTranslated(-0.2f, 0.0f, 0.0f);
							gl.glPushMatrix();
								gl.glScaled(1.0, 0.2, 0.2);
								glut.glutSolidSphere(0.2f, 20, 20);
							gl.glPopMatrix();
						gl.glPopMatrix();
					gl.glPopMatrix();
				gl.glPopMatrix();

			gl.glPopMatrix();
		gl.glPopMatrix();	
	}

	private void setMaterial(GL2 gl, int ambient, int specular, int shininess, int emission, boolean eyes) {
		float[] matAmbientDiffuse;
		float[] matSpecular;
		float[] matShininess;
		float[] matEmission;
		if (!eyes) {
			matAmbientDiffuse = new float[]{0.8f, 0.2f, 0.8f, 1.0f};
	    	matSpecular = new float[]{0.5f,0.5f,0.5f, 1.0f};
		    matShininess = new float[]{16.0f};
		    matEmission = new float[]{0.0f, 0.0f, 0.0f, 1.0f};
		} else {
			matAmbientDiffuse = new float[]{0.1f, 0.1f, 0.1f, 1.0f};
		    matSpecular = new float[]{0.0f, 0.0f, 0.0f, 0.0f};
		    matShininess = new float[]{1.0f};
		    matEmission = new float[]{0.9f, 0.9f, 0.9f, 1.0f};
		}		
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, matAmbientDiffuse, ambient);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, matSpecular, specular);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SHININESS, matShininess, shininess);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_EMISSION, matEmission, emission);
	}

}