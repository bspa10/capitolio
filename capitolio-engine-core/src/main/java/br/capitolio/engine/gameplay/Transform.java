package br.capitolio.engine.gameplay;

import lombok.Getter;
import lombok.Setter;
import org.joml.Vector3f;

@Getter
@Setter
public final class Transform {

    private final Vector3f position = new Vector3f();
    private final Vector3f rotation = new Vector3f();
    private float scale = 1.0f;
}
