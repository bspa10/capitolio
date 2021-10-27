package br.capitolio.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtils {

    public static InputStream stream(String path) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    }

    public static String load(String path) {
        final var result = new StringBuilder();
        final var is = stream(path);

        if (is == null) {
            throw new RuntimeException("Não foi possível obter [%s]".formatted(path));
        }

        try (var reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Couldn't find the file at " + path);
        }

        return result.toString();
    }

}
