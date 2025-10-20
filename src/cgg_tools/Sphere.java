package cgg_tools;

public record Sphere(Vec3 center, double radius, Color color) {

    public Hit intersect(Ray r) {
        Color cHit = this.color;
        Vec3 v = Vec3.subtract(r.x0, center);
        Vec3 d = r.d;
        double a = Vec3.dot(d, d);
        double b = 2 * Vec3.dot(v, d);
        double c = Vec3.dot(v, v) - (radius * radius);
        double disc = b * b - 4 * a * c;
        if (disc == 1) {
            double t = -b / (2 * a);
        }
        if (disc > 0) {
            double t1 = (-b - Math.sqrt(disc) / (2 * a));
            double t2 = (-b + Math.sqrt(disc) / (2 * a));
            if (r.is_valid(t1)) {
                Hit hit = new Hit(t1, v, d, cHit);
                return hit;
            }
        }
        return null;
    }
}
