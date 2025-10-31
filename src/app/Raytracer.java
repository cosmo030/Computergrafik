package app;

import java.util.ArrayList;

import cgg_tools.Color;
import cgg_tools.Sampler;
import cgg_tools.Vec2;
import cgg_tools.Vec3;

public class Raytracer implements Sampler {
    Camera cam;
    ArrayList<Shape> scene;
    Color background;

    public Raytracer(Camera cam, Color background) {
        this.cam = cam;
        this.background = background;
        scene = new ArrayList<>();
    }

    public void addSphere(Shape s) {
        if (s == null)
            throw new IllegalArgumentException();
        scene.add(s);
    }

    static Color shade(Vec3 normal, Color color) {
        Vec3 lightDir = Vec3.normalize(new Vec3(1, 1, 0.5));
        double cos_angle = Math.max(0, Vec3.dot(lightDir, normal));
        Color ambient = Color.multiply(0.1, color);
        Color diffuse = Color.multiply(0.9 * cos_angle, color);
        return Color.add(ambient, diffuse);
    }

    @Override
    public Color getColor(Vec2 p) {
        Ray ray = cam.generate_ray(p);
        double bestT = Double.POSITIVE_INFINITY;
        Hit bestHit = null;

        for (Shape s : scene) {
            Hit h = s.intersect(ray);
            if (h != null && h.t() < bestT) {
                bestT = h.t();
                bestHit = h;
            }
        }
        if (bestHit == null) {
            return background;
        }
        return shade(bestHit.n(), bestHit.c());
    }

}
