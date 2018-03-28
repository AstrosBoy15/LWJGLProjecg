package entities;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;

import org.joml.Vector3f;

import models.RawModel;
import render.DisplayManager;
import render.Loader;

public class Player extends Entity{
	

	public Player(RawModel model, Vector3f position, String asset, float rotX, float rotY, float rotZ,
			float scale, boolean isMap, Loader loader) {
		super(model, asset, position, rotX, rotY, rotZ, scale, isMap, loader);
	}

	@Override
	public void update(Camera camera) {
		Vector3f movement = new Vector3f(0,0,0);
		if(DisplayManager.getInput().isKeyDown(GLFW_KEY_A))
			movement.x-=.1;
		if(DisplayManager.getInput().isKeyDown(GLFW_KEY_S))
			movement.y-=.1;
		if(DisplayManager.getInput().isKeyDown(GLFW_KEY_D))
			movement.x+=.1;
		if(DisplayManager.getInput().isKeyDown(GLFW_KEY_W))
			movement.y+=.1;
		
		movement.add(getPosition());
		
		setPosition(movement);

		if(getPosition().x<-2.5)
			setPosition(new Vector3f(-2.5f,getPosition().y,getPosition().z));
		if(getPosition().x>2.5)
			setPosition(new Vector3f(2.5f,getPosition().y,getPosition().z));
		if(getPosition().y<-4.9)
			setPosition(new Vector3f(getPosition().x,-4.9f,getPosition().z));
		if(getPosition().y>4.9)
			setPosition(new Vector3f(getPosition().x,4.9f,getPosition().z));
		
		System.out.println(getPosition());
		
		camera.setPosition(new Vector3f(getPosition().x, getPosition().y, camera.getPosition().z));
	}

}
