package uvg.edu.gt;

/**
 * Almacena el resultado de una medición de rendimiento de un algoritmo de sort.
 */
public class BenchmarkResult {

    private final String algorithmName;
    private final int arraySize;
    private final long timeNanos;
    private final boolean preSorted;

    public BenchmarkResult(String algorithmName, int arraySize, long timeNanos, boolean preSorted) {
        this.algorithmName = algorithmName;
        this.arraySize = arraySize;
        this.timeNanos = timeNanos;
        this.preSorted = preSorted;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public int getArraySize() {
        return arraySize;
    }

    public long getTimeNanos() {
        return timeNanos;
    }

    public double getTimeMillis() {
        return timeNanos / 1_000_000.0;
    }

    public boolean isPreSorted() {
        return preSorted;
    }

    @Override
    public String toString() {
        return String.format("%-12s | n=%-5d | %10.3f ms | %s",
                algorithmName, arraySize, getTimeMillis(),
                preSorted ? "pre-ordenado" : "desordenado");
    }
}
