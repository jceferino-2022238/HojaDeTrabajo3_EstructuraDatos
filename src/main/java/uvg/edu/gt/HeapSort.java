package uvg.edu.gt;

/**
 * Implementación del algoritmo Heap Sort.
 * Utiliza una estructura de heap (montículo) binario para ordenar el arreglo.
 * Construye un max-heap y extrae repetidamente el máximo elemento.
 * 
 * Complejidad temporal:
 * - Mejor caso: O(n log n)
 * - Caso promedio: O(n log n)
 * - Peor caso: O(n log n)
 * - Espacio: O(1) - ordenamiento in-place
 */
public class HeapSort<T extends Comparable<T>> extends SortAlgorithm<T> {

    @Override
    public void sort(T[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        int n = array.length;

        // Construir heap (reorganizar el arreglo)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        // Extraer elementos del heap uno por uno
        for (int i = n - 1; i > 0; i--) {
            // Mover la raíz actual al final
            swap(array, 0, i);

            // Llamar heapify en el heap reducido
            heapify(array, i, 0);
        }
    }

    /**
     * Convierte un subárbol con raíz en el nodo i en un heap.
     * 
     * @param array arreglo a heapificar
     * @param heapSize tamaño del heap
     * @param i índice de la raíz del subárbol
     */
    private void heapify(T[] array, int heapSize, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // Si el hijo izquierdo es mayor que la raíz
        if (left < heapSize && array[left].compareTo(array[largest]) > 0) {
            largest = left;
        }

        // Si el hijo derecho es mayor que el más grande hasta ahora
        if (right < heapSize && array[right].compareTo(array[largest]) > 0) {
            largest = right;
        }

        // Si el más grande no es la raíz
        if (largest != i) {
            swap(array, i, largest);

            // Recursivamente heapificar el subárbol afectado
            heapify(array, heapSize, largest);
        }
    }

    @Override
    public String getName() {
        return "HeapSort";
    }
}