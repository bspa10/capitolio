package br.capitolio.engine.gameplay;

import br.capitolio.engine.render.backend.mesh.Mesh;
import lombok.Getter;
import lombok.Setter;
import org.joml.Math;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;

public class GameObject {

    private final List<GameObject> children = new ArrayList<>();
    private final Transform transform = new Transform();
    private final Matrix4f world = new Matrix4f();
    private Mesh mesh;

    public Transform getTransform() {
        return transform;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }

    public void addObject(GameObject go) {
        children.add(go);
    }
    public List<GameObject> getChildren() {
        return children;
    }

    public Matrix4f getWorldMatrix() {
        return world
                .identity()
                .translate(transform.getPosition())
                .rotateX(Math.toRadians(transform.getRotation().x))
                .rotateY(Math.toRadians(transform.getRotation().y))
                .rotateZ(Math.toRadians(transform.getRotation().z))
                .scale(transform.getScale());
    }
}
