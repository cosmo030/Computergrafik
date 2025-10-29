package app;

import cgg_tools.Vec3;

public class Ray {
    Vec3 x0; // punkt x0
    Vec3 d; // richtung d
    double tMin;
    double tMax;

    public Ray(Vec3 x0, Vec3 d, double tMin, double tMax) {
        this.x0 = x0;
        this.d = d;
        this.tMin = tMin;
        this.tMax = tMax;
        // this.tMax = Double.POSITIVE_INFINITY;
    }

    public Vec3 point_at(double t) {
        Vec3 t_times_d = Vec3.multiply(t, d);
        Vec3 res = Vec3.add(x0, t_times_d);
        return res;
    }

    public boolean is_valid(double t) {
        if (t < tMin || t > tMax || Double.isNaN(t)) {
            return false;
        }
        return true;
    }

    public Vec3 getDValue() {
        return d;
    }
}
