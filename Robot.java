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
	
	public Robot() {

	}

	public void drawRobot(GL2 gl) {
		gl.glPushMatrix();
			//Robot Body
      		glut.glutSolidSphere(1.0f, 20, 20);
			gl.glPushMatrix();
				//Upper Arm
				gl.glRotated(45.0,0.0,1.0,0.0);
				gl.glTranslated(1.9f, 0.0f, 0.0f);
				gl.glScaled(1.0, 0.4, 0.4);
				glut.glutSolidSphere(1.0f, 20, 20);
				gl.glPushMatrix();
					//Lower Arm
					gl.glTranslated(0.9f, 0.0f, 0.0f);
					gl.glRotated(60.0,0.0,1.0,0.0);
					gl.glTranslated(1.0f, 0.0f,0.0f);
					gl.glScaled(1.0, 0.4, 0.4);
					glut.glutSolidSphere(1.0f, 20, 20);

					gl.glPushMatrix();
						//Hand
						gl.glPushMatrix();
							//Finger
						gl.glPopMatrix();
						gl.glPushMatrix();
							//Finger
						gl.glPopMatrix();
						gl.glPushMatrix();
							//Finger
						gl.glPopMatrix();
						gl.glPushMatrix();
							//Finger
						gl.glPopMatrix();
					gl.glPopMatrix();
				gl.glPopMatrix();
			gl.glPopMatrix();
			gl.glPushMatrix();
				//Upper Arm
				gl.glRotated(-45.0,0.0,1.0,0.0);
				gl.glTranslated(-1.9f, 0.0f, 0.0f);
				gl.glScaled(1.0, 0.4, 0.4);
				glut.glutSolidSphere(1.0f, 20, 20);

				gl.glPushMatrix();

					gl.glTranslated(-0.9f, 0.0f, 0.0f);
					gl.glRotated(-60.0,0.0,1.0,0.0);
					gl.glTranslated(-1.0f, 0.0f,0.0f);
					gl.glScaled(1.0, 0.4, 0.4);
					glut.glutSolidSphere(1.0f, 20, 20);
					gl.glPushMatrix();
						//Hand
						gl.glPushMatrix();
							//Finger
						gl.glPopMatrix();
						gl.glPushMatrix();
							//Finger
						gl.glPopMatrix();
						gl.glPushMatrix();
							//Finger
						gl.glPopMatrix();
						gl.glPushMatrix();
							//Finger
						gl.glPopMatrix();
					gl.glPopMatrix();
				gl.glPopMatrix();

			gl.glPopMatrix();
		gl.glPopMatrix();	
	}

}