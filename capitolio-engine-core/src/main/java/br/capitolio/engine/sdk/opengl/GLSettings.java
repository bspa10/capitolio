package br.capitolio.engine.sdk.opengl;

import br.capitolio.engine.core.logging.Logger;
import br.capitolio.engine.core.logging.LoggerFactory;

public abstract class GLSettings {
    private static final Logger LOGGER = LoggerFactory.getLogger(GLSettings.class);

    private GLSettings() {}

    private static String vscode;
    public static void setVertexShaderCode(String code) {
        LOGGER.debug("Configuring Vertex ShaderProgram Code");
        vscode = code;
    }
    public static String getVertexShaderCode(){
        return vscode;
    }

    private static String fscode;
    public static void setFragmentShaderCode(String code) {
        LOGGER.debug("Configuring Fragment ShaderProgram Code");
        fscode = code;
    }
    public static String getFragmentShaderCode(){
        return fscode;
    }
}
