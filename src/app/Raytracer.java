package app;

import cgg_tools.Color;
import cgg_tools.Sampler;
import cgg_tools.Vec2;
import cgg_tools.Vec3;

public class Raytracer implements Sampler {
    Camera cam;
    GroupShape scene;
    Color background;

    public Raytracer(Camera cam, GroupShape scene, Color background) {
        this.cam = cam;
        this.background = background;
        this.scene = scene;
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
        Hit h = scene.intersect(ray);
        if (h == null)
            return background;
        if (Double.isInfinite(h.t()))
            return shade(h.n(), h.c());
        Vec3 n = h.n();
        Color c = h.c();
        return shade(n, c);
    }

}
