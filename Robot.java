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
      			//Robot Eyes
      			gl.glPushMatrix();
      				gl.glRotated(30,1.0,1.0,0.0);
	      			gl.glTranslated(0.0f,0.f,-1.0f);
	      			glut.glutSolidSphere(0.2f, 20, 20);
      			gl.glPopMatrix();
      			gl.glPushMatrix();
      				gl.glRotated(-30,-1.0,1.0,0.0);
      				gl.glTranslated(0.0f,0.0f,-1.0f);
	      			glut.glutSolidSphere(0.2f, 20, 20);
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