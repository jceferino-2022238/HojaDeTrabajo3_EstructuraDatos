package uvg.edu.gt;

/**
 * Implementación del algoritmo Radix Sort.
 * Algoritmo de ordenamiento no comparativo que ordena enteros procesando
 * dígito por dígito usando Counting Sort como subrutina.
 * 
 * NOTA: Este algoritmo solo funciona con Integer, no con tipos genéricos.
 * 
 * Complejidad temporal:
 * - Mejor caso: O(d * n) donde d es el número de dígitos
 * - Caso promedio: O(d * n)
 * - Peor caso: O(d * n)
 * - Espacio: O(n + k) donde k es el rango de dígitos (0-9)
 */
public class RadixSort extends SortAlgorithm<Integer> {

    @Override
    public void sort(Integer[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        // Encontrar el valor máximo para determinar el número de dígitos
        int max = getMax(array);

        // Aplicar counting sort para cada dígito
        // exp es 10^i donde i es la posición del dígito actual
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(array, exp);
        }
    }

    private int getMax(Integer[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    private void countingSortByDigit(Integer[] array, int exp) {
        int n = array.length;
        Integer[] output = new Integer[n];
        int[] count = new int[10]; // Dígitos del 0 al 9

        // Contar ocurrencias de cada dígito
        for (int i = 0; i < n; i++) {
            int digit = (array[i] / exp) % 10;
            count[digit]++;
        }

        // Cambiar count[i] para que contenga la posición actual
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Construir el arreglo de salida
        for (int i = n - 1; i >= 0; i--) {
            int digit = (array[i] / exp) % 10;
            output[count[digit] - 1] = array[i];
            count[digit]--;
        }

        // Copiar el arreglo de salida al arreglo original
        System.arraycopy(output, 0, array, 0, n);
    }

    @Override
    public String getName() {
        return "RadixSort";
    }
}