package br.capitolio.engine.resources.image;

import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public abstract class PNGLoader {

    private PNGLoader(){}

    @SneakyThrows
    public static ByteBuffer load(InputStream stream) {
        final var bi = ImageIO.read(stream);
        final var width = bi.getWidth();
        final var height = bi.getHeight();

        final var raw = bi.getRGB(0, 0, width, height, null, 0, width);
        final var buffer = ByteBuffer.allocateDirect(width * height * 4).order(ByteOrder.nativeOrder());

        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                int pixel = raw[i * width + j];
                buffer.put((byte) ((pixel >> 16) & 0xFF));  //RED
                buffer.put((byte) ((pixel >> 8) & 0xFF));   //GREEN
                buffer.put((byte) ((pixel) & 0xFF));        //BLUE
                buffer.put((byte) ((pixel >> 24) & 0xFF));  //ALPHA
            }
        }

        buffer.flip();
        buffer.get(new byte[buffer.capacity()]);

        return buffer;
    }

}
