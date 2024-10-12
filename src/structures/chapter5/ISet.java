package structures.chapter5;

public interface ISet<T> extends ICollection<T> {
    ISet<T> union(ISet<T> set);
    ISet<T> intersection(ISet<T> set);
    ISet<T> difference(ISet<T> set);
    ISet<T> symmetricDifference(ISet<T> set);

    boolean isSubset(ISet<T> set);
    boolean isSuperset(ISet<T> set);
    boolean isDisjoint(ISet<T> set);
}
