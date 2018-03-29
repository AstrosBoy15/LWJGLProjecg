package entities;

import org.joml.Vector3f;

import models.RawModel;
import render.Loader;

public class SheepTest extends Entity{
	

	public SheepTest(RawModel model, Vector3f position, String asset, float rotX, float rotY, float rotZ,
			float scale, boolean isMap, Loader loader) {
		super(model, asset, position, rotX, rotY, rotZ, scale, isMap, loader);
	}

	@Override
	public void update(Camera camera) {
		Vector3f movement = new Vector3f(0,0,0);movement.add(getPosition());
		movement.x+=.1f;
		setPosition(movement);

		if(getPosition().x<-62.5)
			setPosition(new Vector3f(-62.5f,getPosition().y,getPosition().z));
		if(getPosition().x>62.5)
			setPosition(new Vector3f(62.5f,getPosition().y,getPosition().z));
		if(getPosition().y<-62.5)
			setPosition(new Vector3f(getPosition().x,-62.5f,getPosition().z));
		if(getPosition().y>62.5)
			setPosition(new Vector3f(getPosition().x,62.5f,getPosition().z));
		
	}

}
