package structures.chapter10;

import structures.chapter4.IQueue;

public interface IGraph<T> {
    boolean isEmpty();
    void addVertex(T vertex);
    boolean hasVertex(T vertex);
    void addEdge(T fromVertex, T toVertex);
    IQueue<T> getAdjacentVertices(T vertex);
    void clearMarks();
    void markVertex(T vertex);
    boolean isMarked(T vertex);
    T getUnmarked();
}
