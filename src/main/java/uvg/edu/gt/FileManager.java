package uvg.edu.gt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Maneja la lectura y escritura de arreglos de enteros en archivos de texto.
 * Cada número se almacena en una línea separada.
 */
public class FileManager {

    /**
     * Escribe un arreglo de enteros en un archivo, un número por línea.
     *
     * @param array    arreglo de enteros a escribir
     * @param filePath ruta del archivo de destino
     * @throws IOException si ocurre un error de escritura
     */
    public void writeArrayToFile(int[] array, String filePath) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(filePath))) {
            for (int value : array) {
                writer.write(Integer.toString(value));
                writer.newLine();
            }
        }
    }

    /**
     * Lee un arreglo de enteros desde un archivo (un número por línea).
     *
     * @param filePath ruta del archivo a leer
     * @return arreglo de enteros leídos
     * @throws IOException si ocurre un error de lectura
     */
    public int[] readArrayFromFile(String filePath) throws IOException {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Path.of(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    numbers.add(Integer.parseInt(line));
                }
            }
        }

        int[] result = new int[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            result[i] = numbers.get(i);
        }
        return result;
    }
}
