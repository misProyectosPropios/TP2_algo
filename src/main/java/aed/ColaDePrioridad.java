package aed;

public interface ColaDePrioridad<T> {
    public T proximo();
    public void Encolar(T element);
    public T desencolar();
    public T quitar(int position);
}