package app;

import cgg_tools.Util;
import cgg_tools.Vec2;
import cgg_tools.Vec3;

public record RectShape(Vec3 p, double width, double depth, Material material) implements Shape {

    @Override
    public Hit intersect(Ray r) {
        double p_y = p.y();
        double x0 = r.x0().y();
        double d_y = r.d().y();
        if (Math.abs(d_y) <= Util.EPSILON)
            return null;
        double t = (p_y - x0) / d_y;
        if (!r.is_valid(t))
            return null;

        Vec3 x = r.point_at(t);
        double d_x = x.x() - p.x();
        double d_z = x.z() - p.z();
        double half_width = width / 2;
        double half_depth = depth / 2;
        if (Math.abs(d_x) > half_width || Math.abs(d_z) > half_depth)
            return null;

        double u = (d_x + half_width) / width;
        double v_raw = (d_z + half_depth) / depth;

        double v = 1 - v_raw;
        Vec2 uv = new Vec2(u, v);

        Vec3 nNew = new Vec3(0, 1, 0);

        Hit hit = new Hit(t, x, uv, nNew, material);

        return hit;
    }

}
