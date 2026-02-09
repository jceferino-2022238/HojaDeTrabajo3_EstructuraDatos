package uvg.edu.gt;

/**
 * Implementación del algoritmo Quick Sort.
 * Algoritmo de ordenamiento basado en divide y conquista.
 * Selecciona un pivote y particiona el arreglo en elementos menores y mayores al pivote.
 * 
 * Complejidad temporal:
 * - Mejor caso: O(n log n)
 * - Caso promedio: O(n log n)
 * - Peor caso: O(n²) cuando el arreglo está ordenado o en orden inverso
 * - Espacio: O(log n) por la recursión
 */
public class QuickSort<T extends Comparable<T>> extends SortAlgorithm<T> {

    @Override
    public void sort(T[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(T[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private int partition(T[] array, int low, int high) {
        T pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j].compareTo(pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, high);
        return i + 1;
    }

    @Override
    public String getName() {
        return "QuickSort";
    }
}