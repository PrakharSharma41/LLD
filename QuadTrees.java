import java.util.ArrayList;
import java.util.List;

class Point {
    double x, y;
    
    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

class Quadtree {
    private static final int CAPACITY = 4; // Max points before subdivision
    private double x, y, width, height;
    private List<Point> points;
    private Quadtree nw, ne, sw, se;
    private boolean divided;

    Quadtree(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.points = new ArrayList<>();
        this.divided = false;
    }
    boolean insert(Point p) {
        // Check if the point is inside the boundary
        if (!contains(p)) return false;
    
        // If capacity is not reached, add to current node
        if (points.size() < CAPACITY) {
            points.add(p);
            return true;
        }
    
        // Subdivide if not already divided
        if (!divided) subdivide();
    
        // Recursively insert into one of the quadrants
        return (nw.insert(p) || ne.insert(p) || sw.insert(p) || se.insert(p));
    }
    
    private boolean contains(Point p) {
        return (p.x >= x && p.x < x + width && p.y >= y && p.y < y + height);
    }
    
    private void subdivide() {
        double halfW = width / 2, halfH = height / 2;
        nw = new Quadtree(x, y, halfW, halfH);
        ne = new Quadtree(x + halfW, y, halfW, halfH);
        sw = new Quadtree(x, y + halfH, halfW, halfH);
        se = new Quadtree(x + halfW, y + halfH, halfW, halfH);
        divided = true;
    }
    List<Point> query(double qx, double qy, double qwidth, double qheight, List<Point> found) {
        if (found == null) found = new ArrayList<>();
    
        // If the query does not intersect this node, return
        if (!intersects(qx, qy, qwidth, qheight)) return found;
    
        // Add points within query range
        for (Point p : points) {
            if (p.x >= qx && p.x < qx + qwidth && p.y >= qy && p.y < qy + qheight) {
                found.add(p);
            }
        }
    
        // Recurse if there are children
        if (divided) {
            nw.query(qx, qy, qwidth, qheight, found);
            ne.query(qx, qy, qwidth, qheight, found);
            sw.query(qx, qy, qwidth, qheight, found);
            se.query(qx, qy, qwidth, qheight, found);
        }
    
        return found;
    }
    
    private boolean intersects(double qx, double qy, double qwidth, double qheight) {
        return !(qx > x + width || qx + qwidth < x || qy > y + height || qy + qheight < y);
    }
        
}
