package br.capitolio.engine.scene;

import lombok.Getter;
import org.joml.Vector3f;

@Getter
public final class Transform {

    private final Vector3f position = new Vector3f();
    private final Vector3f rotation = new Vector3f();
    private final float scale = 0.0f;

}
