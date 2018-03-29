package world;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Player;
import entities.SheepTest;
import models.RawModel;
import render.Loader;
import render.Renderer;
import shaders.StaticShader;

public class World {
	
	private RawModel model;
	
	private MapRenderer mapRenderer;
	
	private float scale = 32;
	
	private int width, height;
	
	private List<Entity> entities = new ArrayList<Entity>();
	
	private Matrix4f worldMatrix;
	
	public World(Loader loader) {
		model = loader.loadToVAO();
		Player player = new Player(model, new Vector3f(0,0,0), "Dragon", 0, 0, 0, .75f, false, loader);
		entities.add(player);
		SheepTest sheep = new SheepTest(model, new Vector3f(0,0,0), "Sheep", 0, 0, 0, .75f, false, loader);
		entities.add(sheep);
		worldMatrix = new Matrix4f().setTranslation(new Vector3f(0));
		worldMatrix.scale(scale);
		File mapImage = new File("res/Map.png");
		BufferedImage image = null;
		try {
			image = ImageIO.read(mapImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		height = image.getHeight();
		width = image.getWidth();
		mapRenderer = new MapRenderer(model, scale, this, loader, "Map");
	}
	
	public void update(Camera camera) {
		for(Entity e:entities) {
			e.update(camera);
		}
	}
	
	public void render(Renderer renderer, StaticShader shader, Camera camera) {
		mapRenderer.renderMap(renderer, shader, camera);
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
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
}
