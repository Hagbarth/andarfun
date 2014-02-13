package dk.au.cs.ubi.andarfun;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import edu.dhbw.andar.util.GraphicsUtil;

public class ScalableSimpleBox {
	private FloatBuffer box;
	private FloatBuffer normals;
	public ScalableSimpleBox(float scale) {
		float boxf[] =  {
				// FRONT
				-25.0f * scale, -25.0f * scale,  25.0f * scale,
				 25.0f * scale, -25.0f * scale,  25.0f * scale,
				-25.0f * scale,  25.0f * scale,  25.0f * scale,
				 25.0f * scale,  25.0f * scale,  25.0f * scale,
				// BACK
				-25.0f * scale, -25.0f * scale, -25.0f * scale,
				-25.0f * scale,  25.0f * scale, -25.0f * scale,
				 25.0f * scale, -25.0f * scale, -25.0f * scale,
				 25.0f * scale,  25.0f * scale, -25.0f * scale,
				// LEFT
				-25.0f * scale, -25.0f * scale,  25.0f * scale,
				-25.0f * scale,  25.0f * scale,  25.0f * scale,
				-25.0f * scale, -25.0f * scale, -25.0f * scale,
				-25.0f * scale,  25.0f * scale, -25.0f * scale,
				// RIGHT
				 25.0f * scale, -25.0f * scale, -25.0f * scale,
				 25.0f * scale,  25.0f * scale, -25.0f * scale,
				 25.0f * scale, -25.0f * scale,  25.0f * scale,
				 25.0f * scale,  25.0f * scale,  25.0f * scale,
				// TOP
				-25.0f * scale,  25.0f * scale,  25.0f * scale,
				 25.0f * scale,  25.0f * scale,  25.0f * scale,
				 -25.0f * scale,  25.0f * scale, -25.0f * scale,
				 25.0f * scale,  25.0f * scale, -25.0f * scale,
				// BOTTOM
				-25.0f * scale, -25.0f * scale,  25.0f * scale,
				-25.0f * scale, -25.0f * scale, -25.0f * scale,
				 25.0f * scale, -25.0f * scale,  25.0f * scale,
				 25.0f * scale, -25.0f * scale, -25.0f * scale,
			};
		float normalsf[] =  {
				// FRONT
				0.0f * scale, 0.0f * scale,  1.0f * scale,
				0.0f * scale, 0.0f * scale,  1.0f * scale,
				0.0f * scale, 0.0f * scale,  1.0f * scale,
				0.0f * scale, 0.0f * scale,  1.0f * scale,
				// BACK
				0.0f * scale, 0.0f * scale,  -1.0f * scale,
				0.0f * scale, 0.0f * scale,  -1.0f * scale,
				0.0f * scale, 0.0f * scale,  -1.0f * scale,
				0.0f * scale, 0.0f * scale,  -1.0f * scale,
				// LEFT
				-1.0f * scale, 0.0f * scale,  0.0f * scale,
				-1.0f * scale, 0.0f * scale,  0.0f * scale,
				-1.0f * scale, 0.0f * scale,  0.0f * scale,
				-1.0f * scale, 0.0f * scale,  0.0f * scale,
				// RIGHT
				1.0f * scale, 0.0f * scale,  0.0f * scale,
				1.0f * scale, 0.0f * scale,  0.0f * scale,
				1.0f * scale, 0.0f * scale,  0.0f * scale,
				1.0f * scale, 0.0f * scale,  0.0f * scale,
				// TOP
				0.0f * scale, 1.0f * scale,  0.0f * scale,
				0.0f * scale, 1.0f * scale,  0.0f * scale,
				0.0f * scale, 1.0f * scale,  0.0f * scale,
				0.0f * scale, 1.0f * scale,  0.0f * scale,
				// BOTTOM
				0.0f * scale, -1.0f * scale,  0.0f * scale,
				0.0f * scale, -1.0f * scale,  0.0f * scale,
				0.0f * scale, -1.0f * scale,  0.0f * scale,
				0.0f * scale, -1.0f * scale,  0.0f * scale,
			};
		
		box = GraphicsUtil.makeFloatBuffer(boxf);
		normals = GraphicsUtil.makeFloatBuffer(normalsf);
	}
	
	
	public final void draw(GL10 gl) {	
	    gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
	    gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
	    
	    gl.glVertexPointer(3, GL10.GL_FLOAT, 0, box);
	    gl.glNormalPointer(GL10.GL_FLOAT,0, normals);
	    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
	    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 4, 4);
	    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 8, 4);
	    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 12, 4);
	    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 16, 4);
	    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 20, 4);
	    gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	    gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
	}
}
