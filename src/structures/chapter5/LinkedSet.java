package structures.chapter5;

public class LinkedSet<T> extends LinkedCollection<T> implements ISet<T> {
    @Override
    public boolean add(T element) {
        if (super.contains(element)) {
            return false;
        }
        return super.add(element);
    }

    @Override
    public ISet<T> union(ISet<T> set) {
        ISet<T> unionSet = new LinkedSet<>();
        for (T el : this) {
            unionSet.add(el);
        }
        for (T el : set) {
            unionSet.add(el);
        }
        return unionSet;
    }

    @Override
    public ISet<T> intersection(ISet<T> set) {
        ISet<T> intersectionSet = new LinkedSet<>();
        for (T el : this) {
            if (set.contains(el)) {
                intersectionSet.add(el);
            }
        }
        return intersectionSet;
    }

    @Override
    public ISet<T> difference(ISet<T> set) {
        ISet<T> differenceSet = new LinkedSet<>();
        for (T el : this) {
            if (!set.contains(el)) {
                differenceSet.add(el);
            }
        }
        return differenceSet;
    }

    @Override
    public ISet<T> symmetricDifference(ISet<T> set) {
        ISet<T> symmetricDifferenceSet = new LinkedSet<>();
        for (T el : this) {
            if (!set.contains(el)) {
                symmetricDifferenceSet.add(el);
            }
        }
        for (T el : set) {
            if (!this.contains(el)) {
                symmetricDifferenceSet.add(el);
            }
        }
        return symmetricDifferenceSet;
    }

    @Override
    public boolean isSubset(ISet<T> set) {
        for (T el : this) {
            if (!set.contains(el)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isSuperset(ISet<T> set) {
        for (T el : set) {
            if (!this.contains(el)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isDisjoint(ISet<T> set) {
        for (T el : this) {
            if (set.contains(el)) {
                return false;
            }
        }
        return true;
    }
}
