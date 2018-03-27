package world;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector3f;

import entities.Entity;
import entities.Player;
import models.RawModel;
import render.Loader;
import render.Renderer;
import shaders.StaticShader;

public class World {
	
	private Player player;
	private RawModel model;
	
	private float scale = 32;
	
	private List<Entity> entities = new ArrayList<Entity>();
	
	public World(Loader loader) {
		model = loader.loadToVAO();
		player = new Player(model, new Vector3f(200,200,0), "Dragon", 0, 0, 0, scale, loader);
		entities.add(player);
	}
	
	public void render(Renderer renderer, StaticShader shader) {
		for(Entity e:entities) {
			renderer.render(e, shader);
		}
	}
	
}
