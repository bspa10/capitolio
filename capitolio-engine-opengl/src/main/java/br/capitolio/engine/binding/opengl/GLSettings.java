package br.capitolio.engine.binding.opengl;

import br.capitolio.engine.core.AbstractSettings;
import br.capitolio.engine.core.logging.Logger;
import br.capitolio.engine.core.logging.LoggerFactory;

public abstract class GLSettings extends AbstractSettings {
    private static final Logger LOGGER = LoggerFactory.getLogger(GLSettings.class);

    private GLSettings() {}

    private static String vscode;
    public static void setVertexShaderCode(String code) {
        LOGGER.debug("Configuring Vertex Shader Code");
        vscode = code;
    }
    public static String getVertexShaderCode(){
        return vscode;
    }

    private static String fscode;
    public static void setFragmentShaderCode(String code) {
        LOGGER.debug("Configuring Fragment Shader Code");
        fscode = code;
    }
    public static String getFragmentShaderCode(){
        return fscode;
    }
}
