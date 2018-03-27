package entities;

import org.joml.Vector3f;

import models.RawModel;
import render.Loader;

public class Player extends Entity{
	

	public Player(RawModel model, Vector3f position, String asset, float rotX, float rotY, float rotZ,
			float scale, Loader loader) {
		super(model, asset, position, rotX, rotY, rotZ, scale, loader);
	}

}
