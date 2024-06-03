import java.nio.*;
import javax.swing.*;
import java.lang.Math;
import static com.jogamp.opengl.GL4.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GLContext;
import org.joml.*;
import com.jogamp.opengl.util.*;
import java.util.ArrayList;
import java.awt.Toolkit;
import java.awt.Point; 

public class PlayerFrame extends JFrame implements GLEventListener {
	private int renderingProgram;
	private int vao[ ] = new int[1];
	private int vbo[ ] = new int[2];
	private FloatBuffer vals = Buffers.newDirectFloatBuffer(16);
	private int mvLoc, pLoc;
	private int PlayerIndex;
	private GLCanvas myCanvas;
	private ArrayList<Integer> KeyCodes;
	private Vector4f PlayerColor;
	private Vector3f PlayerPosition;
	private Vector3f PlayerRotation;
	private boolean strafe;
	private long elapsedTime,startTime;
	private float aspect;
	private Matrix4f pMat = new Matrix4f(); // perspective matrix
	private Matrix4f vMat = new Matrix4f(); // view matrix
	private Matrix4f mMat = new Matrix4f(); // model matrix
	private Matrix4f mvMat = new Matrix4f();
	
	public PlayerFrame(int i, ArrayList<Integer> KeyCodes1) {
		strafe = false;
		PlayerIndex = i;
		KeyCodes = KeyCodes1;
		PlayerPosition = new Vector3f(0.0f,0.0f,8.0f);
		PlayerRotation = new Vector3f(0.0f,0.0f,0.0f);
		
		switch(i) {
		case 0:
			PlayerColor = new Vector4f(1.0f,0.0f,0.0f, 1.0f);//RED
			break;
		case 1:
			PlayerColor = new Vector4f(0.0f,1.0f,0.0f, 1.0f);//GREEN
			break;
		case 2: 
			PlayerColor = new Vector4f(0.0f,0.0f,1.0f, 1.0f);//BLUE
			break;
		case 3:
			PlayerColor = new Vector4f(1.0f,0.4f,0.0f, 1.0f);//ORANGE
			break;
		}
		
		setTitle("Player "+Integer.toString(i+1));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setFocusable(false);
		setSize(500,320);
		myCanvas = new GLCanvas();
		myCanvas.addGLEventListener(this);
		myCanvas.setFocusable(false);
		this.add(myCanvas);
		
		
		int screenLeft = (int)(0.25*Toolkit.getDefaultToolkit().getScreenSize().getWidth()-0.5*this.getWidth());
		int screenRight = (int)(0.75*Toolkit.getDefaultToolkit().getScreenSize().getWidth()-0.5*this.getWidth());
		int screenTop = (int)(0.25*Toolkit.getDefaultToolkit().getScreenSize().getHeight()-0.5*this.getHeight());
		int screenBottom = (int)(0.75*Toolkit.getDefaultToolkit().getScreenSize().getHeight()-0.59*this.getHeight());
		Point[] windowLocations = {
				new Point(screenLeft,screenTop),
				new Point(screenRight,screenTop),
				new Point(screenLeft,screenBottom),
				new Point(screenRight,screenBottom)
		};
		
		setLocation(windowLocations[i]);

		this.setVisible(true);
		Animator animtr = new Animator(myCanvas);
		animtr.start();
	}
	public ArrayList<Integer> getKeyCodes(){
		return KeyCodes;
	}
	public void moveF() {
		
		PlayerPosition.add((float)(-Math.cos(PlayerRotation.y)), 0.0f,(float)( Math.sin(PlayerRotation.y)) );	
		System.out.println("Player Position: "+PlayerPosition.toString() + ", Player Rotation: "+PlayerRotation.toString());
	}
	public void moveB() {
		System.out.println("Player Position: "+PlayerPosition.toString() + ", Player Rotation: "+PlayerRotation.toString());
	}
	public void moveL() {
		if(strafe){
			System.out.println("Strafe Left");
		}else{
			PlayerRotation.add(0.0f,-0.01f,0.0f);
		}
		
		System.out.println("Player Position: "+PlayerPosition.toString() + ", Player Rotation: "+PlayerRotation.toString());
	}
	public void moveR() {
		if(strafe){
			System.out.println("Strafe Right");
		}else{
			PlayerRotation.add(0.0f,0.01f,0.0f);
		}
		System.out.println("Player Position: "+PlayerPosition.toString() + ", Player Rotation: "+PlayerRotation.toString());
	}
	public void moveJ(){
		System.out.println("Player Position: "+PlayerPosition.toString() + ", Player Rotation: "+PlayerRotation.toString());
	}
	public void strafeTrue(){
		strafe = true;
		System.out.println(strafe);
	}	
	public void strafeFalse(){
		strafe = false;
		System.out.println(strafe);
	}
	
	public void init(GLAutoDrawable drawable){
		GL4 gl = (GL4) GLContext.getCurrentGL();
		renderingProgram = Utils.createShaderProgramE("vertShader.glsl", "fragShader.glsl");
		
		setupVertices();
		startTime = System.currentTimeMillis();
	}
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) { }
	public void dispose(GLAutoDrawable drawable) { }

	public void display(GLAutoDrawable drawable){
		GL4 gl = (GL4) GLContext.getCurrentGL();
		gl.glClear(GL_DEPTH_BUFFER_BIT);
		gl.glClear(GL_COLOR_BUFFER_BIT);
		gl.glUseProgram(renderingProgram);
		
		elapsedTime = System.currentTimeMillis() - startTime;
		
		
		// get references to the uniform variables for the MV and projection matrices
		mvLoc = gl.glGetUniformLocation(renderingProgram, "mv_matrix");
		pLoc = gl.glGetUniformLocation(renderingProgram, "p_matrix");
		// build perspective matrix. This one has fovy=60, aspect ratio matches the screen window.
		// Values for near and far clipping planes can vary as discussed in Section 4.9
	 	aspect = (float) myCanvas.getWidth() / (float) myCanvas.getHeight();
	 	pMat.setPerspective((float) Math.toRadians(60.0f), aspect, 0.1f, 1000.0f);
		// build view matrix, model matrix, and model-view matrix
		
		vMat.identity();
		/*
		vMat.rotate((float) Math.toRadians(PlayerRotation.z), new Vector3f(0, 0, 1));
        vMat.rotate((float) Math.toRadians(PlayerRotation.x), new Vector3f(1, 0, 0));
        vMat.rotate((float) Math.toRadians(PlayerRotation.y), new Vector3f(0, 1, 0));
		*/
        // Apply translation
        vMat.translate(PlayerPosition);
		
		
		
		
		
		mMat.identity();
	 	mMat.translation(0.0f,-2.0f,0.0f);
		mMat.rotateXYZ(0.0f,0.0f,0.0f);
		
		mvMat.identity();
		mvMat.mul(vMat);
		mvMat.mul(mMat);
		
		// copy perspective and MV matrices to corresponding uniform variables
		gl.glUniformMatrix4fv(mvLoc, 1, false, mvMat.get(vals));
		gl.glUniformMatrix4fv(pLoc, 1, false, pMat.get(vals));
		// associate VBO with the corresponding vertex attribute in the vertex shader
		gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[0]);
		gl.glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		gl.glEnableVertexAttribArray(0);
		// adjust OpenGL settings and draw model
		
		gl.glEnable(GL_DEPTH_TEST);
		gl.glDepthFunc(GL_LEQUAL);
		gl.glDrawArrays(GL_TRIANGLES, 0, 36);
	}
	private void setupVertices(){
		GL4 gl = (GL4) GLContext.getCurrentGL();
		// 36 vertices of the 12 triangles making up a 2 x 2 x 2 cube centered at the origin
		float[ ] vertexPositions ={
			-1.0f, 1.0f, -1.0f, -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, -1.0f,
			1.0f, -1.0f, -1.0f, 1.0f, 1.0f, -1.0f, -1.0f, 1.0f, -1.0f,
			1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, 1.0f, -1.0f,
			1.0f, -1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, -1.0f,
			1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
			-1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
			-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, -1.0f, -1.0f, 1.0f, 1.0f,
			-1.0f, -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f,
			-1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f, -1.0f,
			1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, 1.0f,
			-1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, 1.0f, 1.0f,
			1.0f, 1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, -1.0f
		};
		
		gl.glGenVertexArrays(vao.length, vao, 0);
		gl.glBindVertexArray(vao[0]);
		gl.glGenBuffers(vbo.length, vbo, 0);
		gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[0]);
		FloatBuffer vertBuf = Buffers.newDirectFloatBuffer(vertexPositions);
		gl.glBufferData(GL_ARRAY_BUFFER, vertBuf.limit()*4, vertBuf, GL_STATIC_DRAW);
		gl.glGenVertexArrays(vao.length, vao, 0);
		
	
	}
	
}