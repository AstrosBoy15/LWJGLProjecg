package utils;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import entities.Camera;
import world.World;

public class Maths {

	public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float scale) {
		
		Matrix4f matrix = new Matrix4f();
		matrix.translate(translation);
		matrix.rotate((float) Math.toRadians(rx), 1.0f, 0.0f, 0.0f);
		matrix.rotate((float) Math.toRadians(ry), 0.0f, 1.0f, 0.0f);
		matrix.rotate((float) Math.toRadians(rz), 0.0f, 0.0f, 1.0f);
		matrix.scale(scale);
		
		return matrix;
		
	}
	
	public static Matrix4f createTransformationMatrix(Vector3f translation, Camera camera, World world, float rx, float ry, float rz, Vector3f scale) {
		
		Matrix4f matrix = camera.getProjection();
		matrix.mul(world.getWorldMatrix());
		matrix.translate(translation);
		matrix.rotate((float) Math.toRadians(rx), 1.0f, 0.0f, 0.0f);
		matrix.rotate((float) Math.toRadians(ry), 0.0f, 1.0f, 0.0f);
		matrix.rotate((float) Math.toRadians(rz), 0.0f, 0.0f, 1.0f);
		matrix.scale(scale);
		
		return matrix;
		
	}
	
	public static Matrix4f createViewMatrix(Camera camera) {
		Matrix4f viewMatrix = new Matrix4f();
		viewMatrix.identity();
		viewMatrix.rotate((float) Math.toRadians(camera.getPitch()), new Vector3f(1, 0, 0), viewMatrix);
		viewMatrix.rotate((float) Math.toRadians(camera.getYaw()), new Vector3f(0, 1, 0), viewMatrix);
		Vector3f cameraPos = camera.getPosition();
		Vector3f negativeCameraPos = new Vector3f(-cameraPos.x,-cameraPos.y,-cameraPos.z);
		viewMatrix.translate(negativeCameraPos, viewMatrix);
		return viewMatrix;
	}
	
}
