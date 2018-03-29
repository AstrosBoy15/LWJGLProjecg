package render;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import entities.Camera;
import entities.Entity;
import shaders.StaticShader;
import utils.Maths;
import world.MapRenderer;
import world.World;

public class Renderer {
	
	private static final float FOV = 70;
	private static final float NEAR_PLANE = .1f;
	private static final float FAR_PLANE = 1000;
	
	private Matrix4f projectionMatrix;
	
	public Renderer(StaticShader shader) {
		createProjectionMatrix();
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}

	public void prepare() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glClearColor(1, 0, 0, 1);
	}
	
	public void render(Entity entity, StaticShader shader, Camera camera, World world) {
		GL30.glBindVertexArray(entity.getModel().getRawModel().getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		Vector3f scale = new Vector3f(entity.getScale()*4, entity.getScale()*4, 1);
		//Matrix4f target = camera.getProjection();
		//target.mul(world.getWorldMatrix());
		//target.translate(entity.getPosition());
		//target.scale(new Vector3f(entity.getScale()*4, entity.getScale()*4, 1));
		//shader.loadTransformationMatrix(target);
		//System.out.println(target);
		Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity.getPosition(), camera, world, entity.getRotX(), entity.getRotY(), 
			entity.getRotZ(), scale);
		shader.loadTransformationMatrix(transformationMatrix);
		//System.out.println(transformationMatrix);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, entity.getModel().getTexture().getID());
		GL11.glDrawElements(GL11.GL_TRIANGLES, entity.getModel().getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
	}
	
	public void render(MapRenderer renderer, StaticShader shader, Camera camera, World world) {
		GL30.glBindVertexArray(renderer.getModel().getRawModel().getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		Matrix4f target = camera.getProjection();
		target.mul(world.getWorldMatrix());
		target.translate(new Vector3f(0,0,0));
		target.scale(new Vector3f(renderer.getScale()*4, renderer.getScale()*4, 1));
		shader.loadTransformationMatrix(target);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, renderer.getModel().getTexture().getID());
		GL11.glDrawElements(GL11.GL_TRIANGLES, renderer.getModel().getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
	}
	
	private void createProjectionMatrix() {
		float aspectRatio = DisplayManager.getWidth() / DisplayManager.getHeight();
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
		float x_scale = y_scale / aspectRatio;
		float frustum_length = FAR_PLANE - NEAR_PLANE;
	 
		projectionMatrix = new Matrix4f();
		projectionMatrix._m00(x_scale);
		projectionMatrix._m11(y_scale);
		projectionMatrix._m22(-((FAR_PLANE + NEAR_PLANE) / frustum_length));
		projectionMatrix._m23(-1);
		projectionMatrix._m32(-((2 * NEAR_PLANE * FAR_PLANE) / frustum_length));
		projectionMatrix._m33(0);
	}
	
	public Matrix4f getProjectionMatrix() {
		return projectionMatrix;
	}
	
}
