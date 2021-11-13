package br.capitolio.engine.gameplay;

import br.capitolio.engine.core.render.backend.mesh.Mesh;
import br.capitolio.tools.reflection.Reflections;
import org.joml.Math;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class GameObject {
    private final Transform transform = new Transform();

    private final List<Component> components = new ArrayList<>();
    private final List<GameObject> children = new ArrayList<>();
    private final Matrix4f world = new Matrix4f();
    private final Matrix4f modelViewMatrix = new Matrix4f();
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

    public void addComponent(Class<? extends Component> component) {
        components.add(Reflections.Classes.newInstance(component, this));
    }
    public Optional<Component> getComponent(Class<? extends Component> component) {
        for (var entry : components)
            if (entry.getClass().isAssignableFrom(component))
                return Optional.of(entry);

        return Optional.empty();
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

    public Matrix4f getModelViewMatrix(Matrix4f viewMatrix) {
        final var rotation = transform.getRotation();
        modelViewMatrix
                .identity()
                .translate(transform.getPosition()).
                rotateX(Math.toRadians(-rotation.x)).
                rotateY(Math.toRadians(-rotation.y)).
                rotateZ(Math.toRadians(-rotation.z)).
                scale(transform.getScale());

        return new Matrix4f(viewMatrix).mul(modelViewMatrix);
    }

    public void update() {
        components.forEach(Component::onUpdate);
    }
}
