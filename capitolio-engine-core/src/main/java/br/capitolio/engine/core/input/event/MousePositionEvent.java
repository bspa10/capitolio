package br.capitolio.engine.core.input.event;

import br.capitolio.engine.core.event.AbstractEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.joml.Vector2d;

@Getter
@RequiredArgsConstructor
public final class MousePositionEvent extends AbstractEvent {

    private final Vector2d position;

}
