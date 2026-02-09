package uvg.edu.gt;

/**
 * Clase abstracta base para todos los algoritmos de ordenamiento.
 * Cada algoritmo concreto (GnomeSort, MergeSort, etc.) debe extender esta clase.
 *
 * @param <T> tipo de elementos a ordenar, debe implementar Comparable
 */
public abstract class SortAlgorithm<T extends Comparable<T>> {

    /**
     * Ordena el arreglo in-place en orden ascendente.
     *
     * @param array el arreglo a ordenar
     */
    public abstract void sort(T[] array);

    /**
     * Retorna el nombre del algoritmo de ordenamiento.
     *
     * @return nombre del algoritmo
     */
    public abstract String getName();

    /**
     * Intercambia dos elementos en el arreglo.
     * Método utilitario disponible para las subclases.
     *
     * @param array el arreglo
     * @param i     índice del primer elemento
     * @param j     índice del segundo elemento
     */
    protected void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
