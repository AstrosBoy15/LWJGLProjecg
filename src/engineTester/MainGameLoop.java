package engineTester;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import org.lwjgl.opengl.GL;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Renderer;
import utils.Transform;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();

		
		GL.createCapabilities();
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		
		float[] vertices = {
				    -0.5f, 0.5f, 0f,
				    -0.5f, -0.5f, 0f,
				    0.5f, -0.5f, 0f,
				    0.5f, 0.5f, 0f,
				  };
		
		int[] indices = {
				0,1,3,	//Top left triangle
				3,1,2	//Bottom right triangle
		};

		RawModel model = loader.loadToVAO(vertices, indices);
		
		while(!glfwWindowShouldClose(DisplayManager.getWindow())) {
			renderer.prepare();
			//game logic
			renderer.render(model);
			DisplayManager.updateDisplay();
			
		}
		
		loader.cleanUp();
		DisplayManager.closeDisplay();
		
	}
	
}
