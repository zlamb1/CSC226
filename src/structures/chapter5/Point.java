package structures.chapter5;

public class Point implements IComparable<Point> {
    int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point o) {
        if(this.x != o.x) {
            return -this.x + o.x;
        }
        else {
            return this.y - o.y;
        }
    }

    public boolean equals(Object obj) {
        if( this == obj ) return true;
        if(!(obj instanceof Point p)) return false;
        return this.x == p.x && this.y == p.y;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
