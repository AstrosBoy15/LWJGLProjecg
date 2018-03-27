package utils;

import org.joml.Vector2f;
import org.joml.Vector3f;

import render.DisplayManager;

public class Transform {

	public Vector3f toOpenGLCoords(Vector2f coords) {
		float x = coords.x/DisplayManager.getWidth()*2-1;
		float y = coords.y/DisplayManager.getHeight()*2-1;
		coords.set(x, -y);
		Vector3f glCoords = new Vector3f(coords.x, coords.y, 0f);
		return glCoords;
	}
	
}
