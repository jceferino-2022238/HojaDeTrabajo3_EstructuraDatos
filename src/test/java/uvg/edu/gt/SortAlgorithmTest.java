package uvg.edu.gt;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;



/**
 * Tests unitarios para los algoritmos de ordenamiento (compilable en este repo).
 */
@RunWith(Parameterized.class)
public class SortAlgorithmTest {

    private static final int SMALL_SIZE = 10;
    private static final int LARGE_SIZE = 3000;
    private static final int BOUND = 10000;
    private static final long SEED = 12345L; // Semilla fija para reproducibilidad

    private SortAlgorithm<Integer> algorithm;
    private String algorithmName;

    public SortAlgorithmTest(SortAlgorithm<Integer> algorithm, String name) {
        this.algorithm = algorithm;
        this.algorithmName = name;
    }

    @Parameters(name = "{1}")
    public static Collection<Object[]> algorithms() {
        return Arrays.asList(new Object[][]{
                {new GnomeSort<Integer>(), "GnomeSort"}
        });
    }

    @Test
    public void testSmallArrayDisordered() {
        DataGenerator generator = new DataGenerator(SEED);
        int[] rawData = generator.generateRandomArray(SMALL_SIZE, BOUND);
        Integer[] data = DataGenerator.toIntegerArray(rawData);

        algorithm.sort(data);

        assertTrue(algorithmName + " falló al ordenar arreglo pequeño desordenado",
                isSorted(data));
    }

    @Test
    public void testSmallArrayOrdered() {
        DataGenerator generator = new DataGenerator(SEED);
        int[] rawData = generator.generateRandomArray(SMALL_SIZE, BOUND);
        Arrays.sort(rawData); // Pre-ordenar
        Integer[] data = DataGenerator.toIntegerArray(rawData);

        algorithm.sort(data);

        assertTrue(algorithmName + " falló al ordenar arreglo pequeño pre-ordenado",
                isSorted(data));
    }

    @Test
    public void testLargeArrayDisordered() {
        DataGenerator generator = new DataGenerator(SEED);
        int[] rawData = generator.generateRandomArray(LARGE_SIZE, BOUND);
        Integer[] data = DataGenerator.toIntegerArray(rawData);

        algorithm.sort(data);

        assertTrue(algorithmName + " falló al ordenar arreglo grande (3000) desordenado",
                isSorted(data));
        assertEquals("El tamaño del arreglo cambió", LARGE_SIZE, data.length);
    }

    @Test
    public void testLargeArrayOrdered() {
        DataGenerator generator = new DataGenerator(SEED);
        int[] rawData = generator.generateRandomArray(LARGE_SIZE, BOUND);
        Arrays.sort(rawData); // Pre-ordenar
        Integer[] data = DataGenerator.toIntegerArray(rawData);

        algorithm.sort(data);

        assertTrue(algorithmName + " falló al ordenar arreglo grande (3000) pre-ordenado",
                isSorted(data));
        assertEquals("El tamaño del arreglo cambió", LARGE_SIZE, data.length);
    }

    @Test
    public void testNullArray() {
        try {
            algorithm.sort(null);
        } catch (Exception e) {
            fail(algorithmName + " lanzó excepción con arreglo null: " + e.getMessage());
        }
    }

    @Test
    public void testEmptyArray() {
        Integer[] empty = new Integer[0];
        algorithm.sort(empty);
        assertEquals("El arreglo vacío cambió de tamaño", 0, empty.length);
    }

    @Test
    public void testSingleElement() {
        Integer[] single = {42};
        algorithm.sort(single);
        assertEquals("Valor único cambió", Integer.valueOf(42), single[0]);
    }

    @Test
    public void testDuplicateElements() {
        Integer[] data = {5, 3, 8, 3, 9, 1, 3, 5};
        algorithm.sort(data);
        assertTrue(algorithmName + " falló con elementos duplicados", isSorted(data));
    }

    @Test
    public void testAlreadySortedSmall() {
        Integer[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        algorithm.sort(data);
        assertTrue(algorithmName + " falló con arreglo ya ordenado", isSorted(data));
    }

    @Test
    public void testReverseSorted() {
        Integer[] data = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        algorithm.sort(data);
        assertTrue(algorithmName + " falló con arreglo en orden inverso", isSorted(data));
    }

    @Test
    public void testAlgorithmName() {
        assertNotNull("El nombre del algoritmo no debe ser null", algorithm.getName());
        assertFalse("El nombre del algoritmo no debe estar vacío",
                algorithm.getName().isEmpty());
    }

    private boolean isSorted(Integer[] array) {
        if (array == null || array.length <= 1) {
            return true;
        }
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
