package structures.chapter5;

public class Point implements IComparable<Point> {
    protected final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point o) {
        if (x != o.x) {
            return -x + o.x;
        } else {
            return y - o.y;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Point p)) return false;
        return x == p.x && y == p.y;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
