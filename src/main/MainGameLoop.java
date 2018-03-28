package main;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import org.lwjgl.opengl.GL;

import entities.Camera;
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
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		World world = new World(loader);
		Camera camera = new Camera();
		
		while(!glfwWindowShouldClose(DisplayManager.getWindow())) {
			//game logic
			camera.move();
			renderer.prepare();
			shader.start();
			shader.loadViewnMatrix(camera);
			world.render(renderer, shader);
			shader.stop();
			DisplayManager.updateDisplay();
			
		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
		
	}
	
}
