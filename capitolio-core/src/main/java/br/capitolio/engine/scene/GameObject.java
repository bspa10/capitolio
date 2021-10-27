package br.capitolio.engine.scene;

import br.capitolio.engine.graphics.Mesh;
import br.capitolio.engine.graphics.Transform;

public final class GameObject {
    private String title = "Game Object";

    private final Transform transform = new Transform();
    private Mesh mesh;

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
    public Transform getTransform() {
        return transform;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }

    public void update() {
        if (mesh.getIdentity() == null) {
            mesh.create();
        }
    }

    public void destroy() {
        if (mesh != null)
            mesh.destroy();

    }
}
