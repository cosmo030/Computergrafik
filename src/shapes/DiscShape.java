package shapes;

import app.Hit;
import app.Material;
import app.Ray;
import cgg_tools.Util;
import cgg_tools.Vec2;
import cgg_tools.Vec3;

public record DiscShape(Vec3 p, double radius, Material material) implements Shape {

    @Override
    public Hit intersect(Ray r) {
        double py = p.y();
        double x0 = r.x0().y();
        double dy = r.d().y(); // y-component of ray direction
        if (Math.abs(dy) <= Util.EPSILON) // chatgpt meinte Math.abs(dy) damit ich den Betrag prüfen kann. Original nur
                                          // "dy" geschrieben.
            return null;
        double t = (py - x0) / dy;
        if (!r.is_valid(t))
            return null;

        Vec3 x = r.point_at(t);
        double dx = x.x() - p.x();
        double dz = x.z() - p.z();
        double d2 = dx * dx + dz * dz;
        if (!(d2 <= radius * radius))
            return null;
        Vec3 nNew = new Vec3(0, 1, 0);

        double u = (dx + radius) / (2 * radius);
        double v_raw = (dz + radius) / (2 * radius);
        double v = 1 - v_raw;
        Vec2 uv = new Vec2(u, v);

        Hit hit = new Hit(t, x, uv, nNew, material);
        return hit;
    }
}
