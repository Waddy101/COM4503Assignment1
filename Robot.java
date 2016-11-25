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
	    leftEye.makeSpotlight(direction, 2f);
	    rightEye.makeSpotlight(direction, 2f);
	}

	public void drawRobot(GL2 gl, double rotate) {
		float[] matAmbientDiffuse = {0.8f, 0.2f, 0.8f, 1.0f};
    	float[] matSpecular = {0.5f,0.5f,0.5f, 1.0f};
	    float[] matShininess = {16.0f};
	    float[] matEmission = {0.0f, 0.0f, 0.0f, 1.0f};
	    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, matAmbientDiffuse, 0);
	    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, matSpecular, 0);
	    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SHININESS, matShininess, 0);
	    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_EMISSION, matEmission, 0);

		gl.glPushMatrix();
			//Robot Body
			gl.glTranslated(3 * Math.sin(0.1 * rotate), 0, 3 * Math.cos(0.1 * rotate));
			gl.glRotated(rotate, 0, 1, 0);
      		glut.glutSolidSphere(1.0f, 20, 20);
      		gl.glPushMatrix();
      			//Robot Eyes
      			gl.glPushMatrix();
      				gl.glRotated(30,1.0,1.0,0.0);
	      			gl.glTranslated(0.0f,0.f,-1.0f);
	      			leftEye.use(gl, glut, true);
	      			glut.glutSolidSphere(0.2f, 20, 20);
	    			gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, matAmbientDiffuse, 0);
				    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, matSpecular, 0);
				    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SHININESS, matShininess, 0);
				    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_EMISSION, matEmission, 0);
      			gl.glPopMatrix();
      			gl.glPushMatrix();
      				gl.glRotated(-30,-1.0,1.0,0.0);
      				gl.glTranslated(0.0f,0.0f,-1.0f);
      				rightEye.use(gl, glut, true);
	      			glut.glutSolidSphere(0.2f, 20, 20);
	      			gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, matAmbientDiffuse, 0);
				    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, matSpecular, 0);
				    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SHININESS, matShininess, 0);
				    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_EMISSION, matEmission, 0);
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

}