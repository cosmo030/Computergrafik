package app;

import cgg_tools.Vec2;
import cgg_tools.Vec3;
import cgg_tools.Mat4x4;

public class Camera {
    int width;
    int height;
    double alphaDeg; // degrees
    double alphaRad; // radians
    double zPlane;
    Mat4x4 view;

    public Camera(int w, int h, double alphaDeg, Mat4x4 view) {
        if (w <= 0 || h <= 0 || (alphaDeg <= 0 || alphaDeg >= 180)) {
            throw new IllegalArgumentException("use valid values.");
        }
        this.width = w;
        this.height = h;
        this.alphaDeg = alphaDeg;
        this.view = view;
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
        Vec3 point = Mat4x4.multiplyPoint(view, x0);
        Vec3 direction = Mat4x4.multiplyDirection(view, d);
        Ray ray = new Ray(point, direction, tMin, tMax);
        return ray;
    }
}