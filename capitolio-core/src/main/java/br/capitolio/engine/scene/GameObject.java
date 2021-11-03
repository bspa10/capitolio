package br.capitolio.engine.scene;

import br.capitolio.engine.graphics.Mesh;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GameObject {
    private final List<GameObject> children = new ArrayList<>();
    public void addObject(GameObject go) {
        children.add(go);
    }
    public List<GameObject> getChildren() {
        return children;
    }

    private final Transform transform = new Transform();
    private Mesh mesh;

}
