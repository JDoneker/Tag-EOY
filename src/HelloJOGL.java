import com.jogamp.newt.Display;
import com.jogamp.newt.NewtFactory;
import com.jogamp.newt.Screen;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.Animator;

public class HelloJOGL {
	private GLWindow window;
	private Animator anim;
	
	public HelloJOGL() {
		GLProfile glp = GLProfile.getMaxProgrammableCore(true);
		GLCapabilities caps = new GLCapabilities(glp);
		caps.setDoubleBuffered(true);
		caps.setHardwareAccelerated(true);
		
		Display disp = NewtFactory.createDisplay("Demo");
		Screen screen = NewtFactory.createScreen(disp, 0);
		window = GLWindow.create(screen, caps);
		window.setSize(800,600);
		window.setTitle("Hello Java OpenGL Test commit");
		window.setVisible(true);
		
		anim = new Animator(window);
		anim.start();
		
	}
}