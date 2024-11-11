package aed;

public interface ColaDePrioridad<T> {
    public T proximo();
    public void encolar(T element);
    public T desencolar();
    public T eliminar(int position);
}
