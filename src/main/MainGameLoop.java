package main;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import org.lwjgl.opengl.GL;

import render.DisplayManager;
import render.Loader;
import render.Renderer;
import shaders.StaticShader;
import world.World;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();

		
		GL.createCapabilities();
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();
		World world = new World(loader);
		
		while(!glfwWindowShouldClose(DisplayManager.getWindow())) {
			//game logic
			renderer.prepare();
			shader.start();
			world.render(renderer, shader);
			shader.stop();
			DisplayManager.updateDisplay();
			
		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
		
	}
	
}
