package render;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.IntBuffer;

import org.lwjgl.glfw.*;
import org.lwjgl.system.MemoryStack;

import utils.Input;

public class DisplayManager {
	
	private static long window;
	
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	
	private static Input input;

	public static void createDisplay() {
		GLFWErrorCallback.createPrint(System.err).set();

		if ( !glfwInit() )
			throw new IllegalStateException("Unable to initialize GLFW");

		glfwDefaultWindowHints();
		GLFW.glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		GLFW.glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
		GLFW.glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		GLFW.glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
		GLFW.glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		GLFW.glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);

		window = glfwCreateWindow(WIDTH, HEIGHT, "LWJGL Project", NULL, NULL);
		
				try ( MemoryStack stack = stackPush() ) {
					IntBuffer pWidth = stack.mallocInt(1);
					IntBuffer pHeight = stack.mallocInt(1);

					glfwGetWindowSize(window, pWidth, pHeight);

					GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

					glfwSetWindowPos(
						window,
						(vidmode.width() - pWidth.get(0)) / 2,
						(vidmode.height() - pHeight.get(0)) / 2
					);
				}

				glfwMakeContextCurrent(window);
				glfwSwapInterval(1);

				glfwShowWindow(window);
				
				input = new Input(window);
			}

	
	public static void updateDisplay() {
		glfwSwapBuffers(window);
		glfwPollEvents();
	}
	
	public static void closeDisplay() {
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);
		
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}
	
	public static long getWindow() {
		return window;
	}
	
	public static int getWidth() {
		return WIDTH;
	}
	
	public static int getHeight() {
		return HEIGHT;
	}
	
	public static Input getInput() {
		return input;
	}
}
