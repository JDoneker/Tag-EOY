import javax.swing.*;
import static com.jogamp.opengl.GL4.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;
import com.jogamp.opengl.util.*;
import com.jogamp.opengl.glu.GLU;

public final class Utils{
	public static int createShaderProgram(String vS, String fS){
		GL4 gl = (GL4) GLContext.getCurrentGL();
		String[] vshaderSource = readShaderSource(vS);
		String[] fshaderSource = readShaderSource(fS);
		
		
		int vShader = gl.glCreateShader(GL_VERTEX_SHADER);
		gl.glShaderSource(vShader, vshaderSource.length, vshaderSource, null, 0); // 3 is the count of lines of source code
		gl.glCompileShader(vShader);	
		
		int fShader=gl.glCreateShader(GL_FRAGMENT_SHADER);
		gl.glShaderSource(fShader, fshaderSource.length, fshaderSource, null, 0); // 4 is the count of lines of source code
		gl.glCompileShader(fShader);
		
		int vfProgram = gl.glCreateProgram();
		gl.glAttachShader(vfProgram, vShader);
		gl.glAttachShader(vfProgram, fShader);
		
		gl.glLinkProgram(vfProgram);
		gl.glDeleteShader(vShader);
		gl.glDeleteShader(fShader);
		return vfProgram;
	}
	public static int createShaderProgramE(String vS, String fS){
		GL4 gl = (GL4) GLContext.getCurrentGL();
		int[ ] vertCompiled = new int[1];
		int[ ] fragCompiled = new int[1];
		int[ ] linked = new int[1];
		String[] vshaderSource = readShaderSource(vS);
		String[] fshaderSource = readShaderSource(fS);
		
		
		int vShader = gl.glCreateShader(GL_VERTEX_SHADER);
		gl.glShaderSource(vShader, vshaderSource.length, vshaderSource, null, 0); // 3 is the count of lines of source code
		gl.glCompileShader(vShader);
		checkOpenGLError();
		gl.glGetShaderiv(vShader, GL_COMPILE_STATUS, vertCompiled, 0);
		if (vertCompiled[0] != 1){
			System.out.println("vertex compilation failed.");
			printShaderLog(vShader);
		}
		
		int fShader=gl.glCreateShader(GL_FRAGMENT_SHADER);
		gl.glShaderSource(fShader, fshaderSource.length, fshaderSource, null, 0); // 4 is the count of lines of source code
		gl.glCompileShader(fShader);
		gl.glCompileShader(fShader);
		checkOpenGLError();
		gl.glGetShaderiv(fShader, GL_COMPILE_STATUS, fragCompiled, 0);
		if (fragCompiled[0] != 1){
			System.out.println("fragment compilation failed.");
			printShaderLog(fShader);
		}
		if ((vertCompiled[0] != 1) || (fragCompiled[0] != 1)){
			System.out.println("\nCompilation error; return-flags:");
			System.out.println(" vertCompiled = " + vertCompiled[0] + " ; fragCompiled = " + fragCompiled[0]);
		}
		
		int vfProgram = gl.glCreateProgram();
		gl.glAttachShader(vfProgram, vShader);
		gl.glAttachShader(vfProgram, fShader);
		
		gl.glLinkProgram(vfProgram);
		checkOpenGLError();
		gl.glGetProgramiv(vfProgram, GL_LINK_STATUS, linked,0);
		if (linked[0] != 1){
			System.out.println("linking failed.");
			printProgramLog(vfProgram);
		}
		
		gl.glDeleteShader(vShader);
		gl.glDeleteShader(fShader);
		return vfProgram;
		
	}
	public static String[] readShaderSource(String filename){
		Vector<String> lines = new Vector<String>();
		Scanner sc;
		String[ ] program;
		try{
			sc = new Scanner(new File(filename));
			while (sc.hasNext()){
				lines.addElement(sc.nextLine());
			}
			program = new String[lines.size()];
			for (int i = 0; i < lines.size(); i++){
				program[i] = (String) lines.elementAt(i) + "\n";
			}
		}catch (IOException e){
			System.err.println("IOException reading file: " + e);
			return null;
		}
		return program;
	}
	public static void printShaderLog(int shader){ 
		GL4 gl = (GL4) GLContext.getCurrentGL();
		int[ ] len = new int[1];
		int[ ] chWrittn = new int[1];
		byte[ ] log = null;
		// determine the length of the shader compilation log
		gl.glGetShaderiv(shader, GL_INFO_LOG_LENGTH, len, 0);
		if (len[0] > 0){
			log = new byte[len[0]];
			gl.glGetShaderInfoLog(shader, len[0], chWrittn, 0, log, 0);
			System.out.println("Shader Info Log: ");
			for (int i = 0; i < log.length; i++){
				System.out.print((char) log[i]);
			} 
		}
	}
	public static void printProgramLog(int prog){
		GL4 gl = (GL4) GLContext.getCurrentGL();
		int[ ] len = new int[1];
		int[ ] chWrittn = new int[1];
		byte[ ] log = null;
		// determine the length of the program linking log
		gl.glGetProgramiv(prog,GL_INFO_LOG_LENGTH,len, 0);
		if (len[0] > 0){
			log = new byte[len[0]];
			gl.glGetProgramInfoLog(prog, len[0], chWrittn, 0,log, 0);
			System.out.println("Program Info Log: ");
			for (int i = 0; i < log.length; i++){
				System.out.print((char) log[i]);
			} 
		} 
	}
	public static boolean checkOpenGLError(){
		GL4 gl = (GL4) GLContext.getCurrentGL();
		boolean foundError = false;
		GLU glu = new GLU();
		int glErr = gl.glGetError();
		while (glErr != GL_NO_ERROR){
			System.err.println("glError: " + glu.gluErrorString(glErr));
			foundError = true;
			glErr = gl.glGetError();
		}
		return foundError;
	}
}