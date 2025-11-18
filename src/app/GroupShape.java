package app;

import java.util.ArrayList;
import java.util.List;

public class GroupShape implements Shape {    
    List<Shape> shapes = new ArrayList<>();

    public void add(Shape s) {
        if (s == null)
            throw new IllegalArgumentException();
        shapes.add(s);
    }

    @Override
    public Hit intersect(Ray r) {
        double bestT = Double.POSITIVE_INFINITY;
        Hit bestHit = null;

        for (Shape s : shapes) {
            Hit h = s.intersect(r);
            if (h != null && (bestHit == null || h.t() < bestT)) {
                bestT = h.t();
                bestHit = h;
            }
        }
        return bestHit;
    }
}
