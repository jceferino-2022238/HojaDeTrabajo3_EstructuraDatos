package uvg.edu.gt;

import java.io.IOException;
import java.util.Arrays;

/**
 * Punto de entrada del programa.
 *
 * Flujo general:
 * 1. Genera números aleatorios y los guarda en un archivo.
 * 2. Lee los números del archivo.
 * 3. Registra los algoritmos de sort disponibles.
 * 4. Ejecuta el benchmark con datos desordenados y pre-ordenados.
 * 5. Muestra los resultados.
 */
public class Main {

    private static final String DATA_FILE = "datos.txt";
    private static final int DATA_SIZE = 3000;
    private static final int DATA_BOUND = 10000;

    public static void main(String[] args) {
        DataGenerator generator = new DataGenerator();
        FileManager fileManager = new FileManager();

        // 1. Generar datos aleatorios y guardarlos en archivo
        System.out.println("Generando " + DATA_SIZE + " números aleatorios...");
        int[] randomData = generator.generateRandomArray(DATA_SIZE, DATA_BOUND);
        try {
            fileManager.writeArrayToFile(randomData, DATA_FILE);
            System.out.println("Datos guardados en: " + DATA_FILE);
        } catch (IOException e) {
            System.err.println("Error al escribir archivo: " + e.getMessage());
            return;
        }

        // 2. Leer datos del archivo
        int[] loadedData;
        try {
            loadedData = fileManager.readArrayFromFile(DATA_FILE);
            System.out.println("Datos leídos del archivo: " + loadedData.length + " números");
        } catch (IOException e) {
            System.err.println("Error al leer archivo: " + e.getMessage());
            return;
        }

        // 3. Configurar benchmark y registrar algoritmos
        SortBenchmark benchmark = new SortBenchmark();

        // TODO: Registrar los algoritmos de sort cuando estén implementados.
        // Ejemplo de cómo se agregarán:
        //   benchmark.addAlgorithm(new GnomeSort<>());
        //   benchmark.addAlgorithm(new MergeSort<>());
        //   benchmark.addAlgorithm(new QuickSort<>());
        //   benchmark.addAlgorithm(new RadixSort());
        //   benchmark.addAlgorithm(new HeapSort<>());

        if (benchmark.getAlgorithms().isEmpty()) {
            System.out.println("\nNo hay algoritmos de sort registrados aún.");
            System.out.println("Agrega algoritmos extendiendo SortAlgorithm<T> y registrándolos con benchmark.addAlgorithm(...)");
            System.out.println("\nMostrando los primeros 20 números leídos como verificación:");
            System.out.println(Arrays.toString(Arrays.copyOf(loadedData, Math.min(20, loadedData.length))));
            return;
        }

        // 4. Ejecutar benchmarks
        System.out.println("\nEjecutando benchmarks...\n");
        benchmark.runAll(generator);

        // 5. Mostrar resultados
        benchmark.printResults();
    }
}
