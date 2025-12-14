package shapes;

import app.Hit;
import app.Material;
import app.Ray;
import cgg_tools.Vec2;
import cgg_tools.Vec3;

public record BackgroundShape(Material material) implements Shape {

    @Override
    public Hit intersect(Ray r) {
        double t = r.tMax();

        Vec3 d = Vec3.normalize(r.d());
        double dx = d.x();
        double dy = d.y();
        double dz = d.z();

        double phi = Math.atan2(dz, dx);
        double u = (phi + Math.PI) / (2 * Math.PI);

        double theta = Math.asin(dy);
        double v = .5 - (theta / Math.PI);

        Vec3 x = r.point_at(t);
        Vec3 n = Vec3.negate(Vec3.normalize(r.d()));

        Hit hit = new Hit(t, x, new Vec2(u, v), n, material);
        return hit;
    }

}
