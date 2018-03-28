package world;

import java.util.ArrayList;
import java.util.List;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Player;
import models.RawModel;
import render.Loader;
import render.Renderer;
import shaders.StaticShader;

public class World {
	
	private Player map;
	private RawModel model;
	
	private float scale = 32;
	
	private List<Entity> entities = new ArrayList<Entity>();
	
	private Matrix4f worldMatrix;
	
	public World(Loader loader) {
		model = loader.loadToVAO();
		map = new Player(model, new Vector3f(0,0,0), "Map", 0, 0, 0, scale, true, loader);
		Player player = new Player(model, new Vector3f(0,0,0), "Dragon", 0, 0, 0, .75f, false, loader);
		entities.add(map);
		entities.add(player);
		this.worldMatrix = new Matrix4f().setTranslation(new Vector3f(0));
		this.worldMatrix.scale(scale);
	}
	
	public void update(Camera camera) {
		for(Entity e:entities) {
			e.update(camera);
		}
	}
	
	public void render(Renderer renderer, StaticShader shader, Camera camera) {
		for(Entity e:entities) {
			renderer.render(e, shader, camera, this);
		}
	}
	
	public Matrix4f getWorldMatrix() {
		return worldMatrix;
	}
	
	public float getScale() {
		return scale;
	}
	
}
