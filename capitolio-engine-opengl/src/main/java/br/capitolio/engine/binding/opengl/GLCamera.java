package br.capitolio.engine.binding.opengl;

import br.capitolio.engine.EngineSettings;
import br.capitolio.engine.render.frontend.camera.Camera;
import org.joml.Matrix4f;

public class GLCamera extends Camera {
    private Matrix4f projectionMatrix;


    @Override
    protected void doInit() {
        projectionMatrix = new Matrix4f().perspective(
                EngineSettings.getFieldOfView(),
                window.getAspectRatio(),
                EngineSettings.getzNear(),
                EngineSettings.getzFar()
        );
    }
}
