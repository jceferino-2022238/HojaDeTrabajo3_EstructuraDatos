package uvg.edu.gt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Orquesta la ejecución de benchmarks para los algoritmos de sort.
 * Ejecuta cada algoritmo con distintos tamaños de arreglo y recopila los resultados.
 *
 * Estructura y flujo general definidos; la lógica de medición detallada
 * y el profiling se implementarán más adelante.
 */
public class SortBenchmark {

    private final List<SortAlgorithm<Integer>> algorithms;
    private final int[] testSizes;
    private final List<BenchmarkResult> results;

    /**
     * Tamaños de prueba por defecto según la hoja de trabajo:
     * desde 10 hasta 3000.
     */
    private static final int[] DEFAULT_SIZES = {10, 100, 500, 1000, 1200, 1500, 1800, 2000, 2500, 3000};

    public SortBenchmark() {
        this.algorithms = new ArrayList<>();
        this.testSizes = DEFAULT_SIZES;
        this.results = new ArrayList<>();
    }

    public SortBenchmark(int[] customSizes) {
        this.algorithms = new ArrayList<>();
        this.testSizes = customSizes;
        this.results = new ArrayList<>();
    }

    /**
     * Registra un algoritmo de sort para ser evaluado.
     *
     * @param algorithm el algoritmo a registrar
     */
    public void addAlgorithm(SortAlgorithm<Integer> algorithm) {
        algorithms.add(algorithm);
    }

    /**
     * Ejecuta todos los benchmarks: cada algoritmo con cada tamaño,
     * tanto con datos desordenados como con datos pre-ordenados.
     *
     * @param dataGenerator generador de datos aleatorios
     */
    public void runAll(DataGenerator dataGenerator) {
        for (SortAlgorithm<Integer> algorithm : algorithms) {
            for (int size : testSizes) {
                // Escenario promedio: datos desordenados
                int[] rawData = dataGenerator.generateRandomArray(size, 10000);
                Integer[] unsortedData = DataGenerator.toIntegerArray(rawData);
                BenchmarkResult unsortedResult = runSingle(algorithm, unsortedData, false);
                results.add(unsortedResult);

                // Escenario mejor caso: datos ya ordenados
                Arrays.sort(rawData);
                Integer[] sortedData = DataGenerator.toIntegerArray(rawData);
                BenchmarkResult sortedResult = runSingle(algorithm, sortedData, true);
                results.add(sortedResult);
            }
        }
    }

    /**
     * Ejecuta un único benchmark: mide el tiempo que tarda el algoritmo en ordenar el arreglo.
     *
     * @param algorithm  el algoritmo a medir
     * @param data       el arreglo a ordenar (se clona para no alterar el original)
     * @param preSorted  indica si los datos están pre-ordenados
     * @return resultado de la medición
     */
    private BenchmarkResult runSingle(SortAlgorithm<Integer> algorithm, Integer[] data, boolean preSorted) {
        Integer[] copy = data.clone();

        long startTime = System.nanoTime();
        algorithm.sort(copy);
        long endTime = System.nanoTime();

        long elapsed = endTime - startTime;
        return new BenchmarkResult(algorithm.getName(), data.length, elapsed, preSorted);
    }

    /**
     * Retorna todos los resultados recopilados.
     *
     * @return lista de resultados
     */
    public List<BenchmarkResult> getResults() {
        return results;
    }

    /**
     * Imprime todos los resultados en consola.
     */
    public void printResults() {
        System.out.println("=".repeat(65));
        System.out.println("RESULTADOS DE BENCHMARK");
        System.out.println("=".repeat(65));
        for (BenchmarkResult result : results) {
            System.out.println(result);
        }
        System.out.println("=".repeat(65));
    }

    /**
     * Retorna los tamaños de prueba configurados.
     */
    public int[] getTestSizes() {
        return testSizes;
    }

    /**
     * Retorna la lista de algoritmos registrados.
     */
    public List<SortAlgorithm<Integer>> getAlgorithms() {
        return algorithms;
    }
}
