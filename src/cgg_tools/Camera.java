package cgg_tools;

import app.Ray;

public class Camera {
    int width;
    int height;
    double alphaDeg; // degrees
    double alphaRad; // radians
    double zPlane;

    public Camera(int w, int h, double alphaDeg) {
        if (w <= 0 || h <= 0 || (alphaDeg <= 0 || alphaDeg >= 180)) {
            throw new IllegalArgumentException("use valid values.");
        }
        this.width = w;
        this.height = h;
        this.alphaDeg = alphaDeg;
        alphaRad = Math.toRadians(alphaDeg);
        zPlane = -((double) width / 2) / (Math.tan(alphaRad / 2));
    }

    public Ray generate_ray(Vec2 pos) {
        double i = pos.u();
        double j = pos.v();
        double calcX = -(width / 2.0) + (i + 0.5);
        double calcY = (height / 2.0) - (j + 0.5);
        Vec3 d = new Vec3(calcX, calcY, zPlane);
        Vec3 x0 = new Vec3(0, 0, 0);
        double tMin = 0.0;
        double tMax = Double.POSITIVE_INFINITY;
        Ray ray = new Ray(x0, d, tMin, tMax);
        return ray;
    }
}