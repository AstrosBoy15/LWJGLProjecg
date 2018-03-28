package entities;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import render.DisplayManager;

public class Camera {

	private Vector3f position = new Vector3f(0,0,1);
	private Matrix4f projection;
	private float pitch;
	private float yaw;
	private float roll;
	
	public Camera(int width, int height) {
		setProjection(width, height);
	}
	
	public void move() {
		/*if(DisplayManager.getInput().isKeyDown(GLFW.GLFW_KEY_LEFT))
			position.x-=0.1f;
		if(DisplayManager.getInput().isKeyDown(GLFW.GLFW_KEY_DOWN))
			position.y-=0.1f;
		if(DisplayManager.getInput().isKeyDown(GLFW.GLFW_KEY_RIGHT))
			position.x+=0.1f;
		if(DisplayManager.getInput().isKeyDown(GLFW.GLFW_KEY_UP))
			position.y+=0.1f;
	*/	
		if(position.x<-1.8)
			position.x=-1.8f;
		if(position.x>1.8)
			position.x=1.8f;
		if(position.y<-4.2)
			position.y=-4.2f;
		if(position.y>4.2)
			position.y=4.2f;
			
	}
	
	public void setProjection(int width, int height){
		projection = new Matrix4f().setOrtho2D(-width/2, width/2, -height/2, height/2);
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
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
	
	public Matrix4f getProjection() {
		return projection.translate(position, new Matrix4f());
	}
	
}
