package uvg.edu.gt;

/**
 * Implementación del algoritmo de ordenamiento Gnome Sort.
 * 
 * Complejidad temporal:
 * - Mejor caso: O(n) cuando el arreglo está ordenado
 * - Caso promedio: O(n²)
 * - Peor caso: O(n²)
 */
public class GnomeSort<T extends Comparable<T>> extends SortAlgorithm<T> {

    @Override
    public void sort(T[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        int index = 0;
        while (index < array.length) {
            if (index == 0) {
                index++;
            } else if (array[index].compareTo(array[index - 1]) >= 0) {
                index++;
            } else {
                swap(array, index, index - 1);
                index--;
            }
        }
    }

    @Override
    public String getName() {
        return "GnomeSort";
    }
}