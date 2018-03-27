package entities;

import org.joml.Vector3f;

import models.RawModel;
import models.TexturedModel;
import render.Loader;
import textures.ModelTexture;

public abstract class Entity {

	private RawModel model;
	private Vector3f position;
	private float rotX, rotY, rotZ;
	private float scale;
	private ModelTexture texture;
	private TexturedModel texturedModel;
	
	public Entity(RawModel model, String asset, Vector3f position, float rotX, float rotY, float rotZ, float scale, Loader loader) {
		this.model = model;
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
		texture = new ModelTexture(loader.loadTexture(asset));
		this.texturedModel = new TexturedModel(model, texture);
		
	}
	
	public void increasePosition(float dx, float dy, float dz) {
		this.position.x+=dx;
		this.position.y+=dy;
		this.position.z+=dz;
	}
	
	public void increaseRotation(float dx, float dy, float dz) {
		this.rotX+=dx;
		this.rotY+=dy;
		this.rotZ+=dz;
	}
	
	public TexturedModel getModel() {
		return texturedModel;
	}
	
	public void setModel(TexturedModel model) {
		this.texturedModel = model;
	}
	
	public Vector3f getPosition() {
		return position;
	}
	
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	
	public float getRotX() {
		return rotX;
	}
	
	public void setRotX(float rotX) {
		this.rotX = rotX;
	}
	
	public float getRotY() {
		return rotY;
	}
	
	public void setRotY(float rotY) {
		this.rotY = rotY;
	}
	
	public float getRotZ() {
		return rotZ;
	}
	
	public void setRotZ(float rotZ) {
		this.rotZ = rotZ;
	}
	
	public float getScale() {
		return scale;
	}
	
	public void setScale(float scale) {
		this.scale = scale;
	}
	
}
