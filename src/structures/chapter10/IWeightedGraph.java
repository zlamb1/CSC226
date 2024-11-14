package structures.chapter10;

public interface IWeightedGraph<T, W> extends IGraph<T> {
    void addEdge(T fromVertex, T toVertex, W weight);
    W getWeight(T fromVertex, T toVertex);
}
