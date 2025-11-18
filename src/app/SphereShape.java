package app;

import cgg_tools.Vec3;
import cgg_tools.Util;

public record SphereShape(Vec3 center, double radius, Material material) implements Shape {

    public Hit intersect(Ray r) {
        Vec3 v = Vec3.subtract(r.x0(), center);
        Vec3 d = r.d();
        double a = Vec3.dot(d, d);
        double b = 2 * Vec3.dot(v, d);
        double c = Vec3.dot(v, v) - (radius * radius);
        double disc = b * b - 4 * a * c;
        double eps = Util.EPSILON; // use elipson for 3 possibilities

        if (disc < -eps)
            return null;
        if (disc > eps) {
            double t1 = (-b - Math.sqrt(disc)) / (2 * a);
            double t2 = (-b + Math.sqrt(disc)) / (2 * a);
            if (r.is_valid(t1)) {
                Vec3 x1 = r.point_at(t1);
                Vec3 n1 = Vec3.normalize(Vec3.subtract(x1, center));
                Hit hit = new Hit(t1, x1, n1, material);
                return hit;
            }
            if (r.is_valid(t2)) {
                Vec3 x2 = r.point_at(t2);
                Vec3 n2 = Vec3.normalize(Vec3.subtract(x2, center));
                Hit hit = new Hit(t2, x2, n2, material);
                return hit;
            }
        }
        if (disc <= eps) {
            double t = -b / (2 * a);
            if (r.is_valid(t)) {
                Vec3 x = r.point_at(t);
                Vec3 n = Vec3.normalize(Vec3.subtract(x, center));
                Hit hit = new Hit(t, x, n, material);
                return hit;
            }
        }
        return null;
    }
}
