package shapes;

import java.util.ArrayList;
import java.util.List;

import app.Hit;
import app.Ray;
import cgg_tools.Mat4x4;
import cgg_tools.Vec3;

public class GroupShape implements Shape {
    List<Shape> shapes = new ArrayList<>();
    Mat4x4 transform;
    Mat4x4 invert;
    Mat4x4 transpose;

    public GroupShape(Mat4x4 transform) {
        this.transform = transform;
        invert = Mat4x4.invert(transform);
        transpose = Mat4x4.transpose(invert);
    }

    public void add(Shape s) {
        if (s == null)
            throw new IllegalArgumentException();
        shapes.add(s);
    }

    @Override
    public Hit intersect(Ray r) {
        Vec3 local_x0 = Mat4x4.multiplyPoint(invert, r.x0());
        Vec3 local_d = Mat4x4.multiplyDirection(invert, r.d());
        Ray local_ray = new Ray(local_x0, local_d, r.tMin(), r.tMax());
        double bestT = Double.POSITIVE_INFINITY;
        Hit bestHit = null;

        for (Shape s : shapes) {
            Hit h = s.intersect(local_ray);
            if (h == null)
                continue;
            if (bestHit == null || h.t() < bestT) {
                bestT = h.t();
                bestHit = h;
            }
        }
        if (bestHit == null)
            return null;

        Vec3 local_x = bestHit.hitpoint();
        Vec3 local_n = bestHit.normal();
        Vec3 world_x = Mat4x4.multiplyPoint(transform, local_x);
        Vec3 world_n = Vec3.normalize(Mat4x4.multiplyDirection(transpose, local_n));

        return new Hit(bestT, world_x, bestHit.uv(), world_n, bestHit.material());
    }
}
