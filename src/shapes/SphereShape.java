package shapes;

import cgg_tools.Vec3;
import app.Hit;
import app.Material;
import app.Ray;
import cgg_tools.Util;
import cgg_tools.Vec2;

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

                double nx = n1.x();
                double ny = n1.y();
                double nz = n1.z();
                double phi = Math.atan2(nz, nx);
                double u1 = (phi + Math.PI) / (2 * Math.PI);
                double theta = Math.asin(ny);
                double v1 = .5 - theta / Math.PI;

                Hit hit = new Hit(t1, x1, new Vec2(u1, v1), n1, material);
                return hit;
            }
            if (r.is_valid(t2)) {
                Vec3 x2 = r.point_at(t2);
                Vec3 n2 = Vec3.normalize(Vec3.subtract(x2, center));

                double nx = n2.x();
                double ny = n2.y();
                double nz = n2.z();
                double phi = Math.atan2(nz, nx);
                double u2 = (phi + Math.PI) / (2 * Math.PI);
                double theta = Math.asin(ny);
                double v2 = .5 - theta / Math.PI;

                Hit hit = new Hit(t2, x2, new Vec2(u2, v2), n2, material);
                return hit;
            }
        }
        if (disc <= eps) {
            double t = -b / (2 * a);
            if (r.is_valid(t)) {
                Vec3 x = r.point_at(t);
                Vec3 n = Vec3.normalize(Vec3.subtract(x, center));

                double nx = n.x();
                double ny = n.y();
                double nz = n.z();
                double phi = Math.atan2(nz, nx);
                double u3 = (phi + Math.PI) / (2 * Math.PI);
                double theta = Math.asin(ny);
                double v3 = .5 - theta / Math.PI;

                Hit hit = new Hit(t, x, new Vec2(u3, v3), n, material);
                return hit;
            }
        }
        return null;
    }
}
