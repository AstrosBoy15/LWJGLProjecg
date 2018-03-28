package entities;

import static org.lwjgl.glfw.GLFW.*;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFWKeyCallback;

import render.DisplayManager;

public class Camera {

	private Vector3f position = new Vector3f(0,0,1);
	private float pitch;
	private float yaw;
	private float roll;
	
	public Camera() {}
	
	public void move() {
		if(DisplayManager.getInput().isKeyDown(GLFW_KEY_A))
			position.x-=0.1f;
		if(DisplayManager.getInput().isKeyDown(GLFW_KEY_S))
			position.z+=0.1f;
		if(DisplayManager.getInput().isKeyDown(GLFW_KEY_D))
			position.x+=0.1f;
		if(DisplayManager.getInput().isKeyDown(GLFW_KEY_W))
			position.z-=0.1f;
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}
	
}
