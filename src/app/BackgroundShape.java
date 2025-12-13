package app;

import cgg_tools.Vec2;
import cgg_tools.Vec3;

public record BackgroundShape(Material material) implements Shape {

    @Override
    public Hit intersect(Ray r) {
        double t = r.tMax();
        Vec3 n = Vec3.negate(Vec3.normalize(r.d()));
        Vec3 x = r.point_at(t);
        Hit hit = new Hit(t, x, new Vec2(0, 0), n, material);
        return hit;
    }

}
