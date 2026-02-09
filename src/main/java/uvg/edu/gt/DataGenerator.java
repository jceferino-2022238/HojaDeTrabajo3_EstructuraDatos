package uvg.edu.gt;

import java.util.Random;

/**
 * Genera arreglos de números enteros aleatorios.
 */
public class DataGenerator {

    private final Random random;

    public DataGenerator() {
        this.random = new Random();
    }

    public DataGenerator(long seed) {
        this.random = new Random(seed);
    }

    /**
     * Genera un arreglo de enteros aleatorios.
     *
     * @param size  cantidad de números a generar (máximo 3000)
     * @param bound límite superior exclusivo para los valores generados
     * @return arreglo de enteros aleatorios
     */
    public int[] generateRandomArray(int size, int bound) {
        if (size < 1 || size > 3000) {
            throw new IllegalArgumentException("El tamaño debe estar entre 1 y 3000, recibido: " + size);
        }
        if (bound < 1) {
            throw new IllegalArgumentException("El límite debe ser mayor a 0");
        }

        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(bound);
        }
        return array;
    }

    /**
     * Convierte un arreglo de int primitivo a Integer[] para uso con SortAlgorithm.
     *
     * @param array arreglo de int primitivos
     * @return arreglo de Integer
     */
    public static Integer[] toIntegerArray(int[] array) {
        Integer[] result = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }
}
