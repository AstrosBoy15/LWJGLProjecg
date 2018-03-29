package world;

import org.joml.Matrix4f;
import org.joml.Vector2f;

import entities.Camera;
import models.RawModel;
import models.TexturedModel;
import render.Loader;
import render.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

public class MapRenderer {

	private Matrix4f transform = new Matrix4f();
	
	private float scale;
	
	private World world;
	private RawModel model;
	private ModelTexture texture;
	private TexturedModel texturedModel;

	public MapRenderer(RawModel model, float scale, World world, Loader loader, String asset){
		this.world = world;
		this.model = model;
		this.scale = scale;
		texture = new ModelTexture(loader.loadTexture(asset));
		texturedModel = new TexturedModel(model, texture);
	}

	public void renderMap(Renderer renderer, StaticShader shader, Camera cam) {

	//	Vector2f position = new Vector2f((world.getWidth()*world.getScale())-world.getScale(), (-world.getHeight()*world.getScale())+world.getScale()),
	//			scale = new Vector2f((world.getWidth()*world.getScale()), (world.getWidth()*world.getScale()));
		
	//	transform.identity().translate(position.x, position.y, 0).scale(scale.x, scale.y, 1);
	//	shader.loadTransformationMatrix(cam.getProjection().mul(transform));
		renderer.render(this, shader, cam, world);
		
	}
	
	public RawModel getRawModel() {
		return model;
	}
	
	public TexturedModel getModel() {
		return texturedModel;
	}
	
	public float getScale() {
		return scale;
	}
	
}
