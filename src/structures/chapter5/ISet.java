package structures.chapter5;

public interface ISet<T> extends ICollection<T> {
    ISet<T> makeSet();

    default ISet<T> union(ISet<T> set) {
        ISet<T> unionSet = makeSet();
        for (T el : this) {
            unionSet.add(el);
        }
        for (T el : set) {
            unionSet.add(el);
        }
        return unionSet;
    }

    default ISet<T> intersection(ISet<T> set) {
        ISet<T> intersectionSet = makeSet();
        for (T el : this) {
            if (set.contains(el)) {
                intersectionSet.add(el);
            }
        }
        return intersectionSet;
    }

    default ISet<T> difference(ISet<T> set) {
        ISet<T> differenceSet = makeSet();
        for (T el : this) {
            if (!set.contains(el)) {
                differenceSet.add(el);
            }
        }
        return differenceSet;
    }

    default ISet<T> symmetricDifference(ISet<T> set) {
        ISet<T> symmetricDifferenceSet = makeSet();
        for (T el : this) {
            if (!set.contains(el)) {
                symmetricDifferenceSet.add(el);
            }
        }
        for (T el : set) {
            if (!contains(el)) {
                symmetricDifferenceSet.add(el);
            }
        }
        return symmetricDifferenceSet;
    }

    default boolean isSubset(ISet<T> set) {
        for (T el : this) {
            if (!set.contains(el)) {
                return false;
            }
        }
        return true;
    }

    default boolean isSuperset(ISet<T> set) {
        for (T el : set) {
            if (!contains(el)) {
                return false;
            }
        }
        return true;
    }

    default boolean isDisjoint(ISet<T> set) {
        for (T el : this) {
            if (set.contains(el)) {
                return false;
            }
        }
        return true;
    }
}
