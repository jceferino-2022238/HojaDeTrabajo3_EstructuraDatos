package uvg.edu.gt;

/**
 * Implementación del algoritmo Merge Sort.
 * Algoritmo de ordenamiento basado en divide y conquista.
 * Divide el arreglo en mitades, las ordena recursivamente y luego las combina.
 * 
 * Complejidad temporal:
 * - Mejor caso: O(n log n)
 * - Caso promedio: O(n log n)
 * - Peor caso: O(n log n)
 * - Espacio: O(n)
 */
public class MergeSort<T extends Comparable<T>> extends SortAlgorithm<T> {

    @Override
    public void sort(T[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        mergeSort(array, 0, array.length - 1);
    }

    private void mergeSort(T[] array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            
            merge(array, left, mid, right);
        }
    }

    @SuppressWarnings("unchecked")
    private void merge(T[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Crear arreglos temporales
        T[] leftArray = (T[]) new Comparable[n1];
        T[] rightArray = (T[]) new Comparable[n2];

        // Copiar datos a arreglos temporales
        System.arraycopy(array, left, leftArray, 0, n1);
        System.arraycopy(array, mid + 1, rightArray, 0, n2);

        // Combinar los arreglos temporales
        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i].compareTo(rightArray[j]) <= 0) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Copiar elementos restantes de leftArray
        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        // Copiar elementos restantes de rightArray
        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    @Override
    public String getName() {
        return "MergeSort";
    }
}