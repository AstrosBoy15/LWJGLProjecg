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
			movement.x-=.2;
		if(DisplayManager.getInput().isKeyDown(GLFW_KEY_S))
			movement.y-=.2;
		if(DisplayManager.getInput().isKeyDown(GLFW_KEY_D))
			movement.x+=.2;
		if(DisplayManager.getInput().isKeyDown(GLFW_KEY_W))
			movement.y+=.2;
		
		movement.add(getPosition());
		
		setPosition(movement);

		if(getPosition().x<-62.5)
			setPosition(new Vector3f(-62.5f,getPosition().y,getPosition().z));
		if(getPosition().x>62.5)
			setPosition(new Vector3f(62.5f,getPosition().y,getPosition().z));
		if(getPosition().y<-62.5)
			setPosition(new Vector3f(getPosition().x,-62.5f,getPosition().z));
		if(getPosition().y>62.5)
			setPosition(new Vector3f(getPosition().x,62.5f,getPosition().z));
		
		
		camera.getPosition().lerp(new Vector3f(getPosition().x/20, getPosition().y/11.25f,camera.getPosition().z), .1f);
		
		if(camera.getPosition().x<-1.8)
			camera.setPosition(new Vector3f(-1.8f, camera.getPosition().y, camera.getPosition().z));
		if(camera.getPosition().x>1.8)
			camera.setPosition(new Vector3f(1.8f, camera.getPosition().y, camera.getPosition().z));
		if(camera.getPosition().y<-4.3)
			camera.setPosition(new Vector3f(camera.getPosition().x, -4.3f, camera.getPosition().z));
		if(camera.getPosition().y>4.3)
			camera.setPosition(new Vector3f(camera.getPosition().x, 4.3f, camera.getPosition().z));
		
		System.out.println(camera.getPosition() + "; " + getPosition());
		
	}

}
