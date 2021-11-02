package br.capitolio.binding.opengl;

import br.capitolio.engine.renderer.RenderManager;
import org.lwjgl.opengl.GL11;

public class GLRenderManager extends RenderManager {

    @Override
    protected void doClear() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    @Override
    protected void doCleanup() {
    }
}
