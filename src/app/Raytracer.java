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
        Material material = h.material();
        Vec3 to_viewer = Vec3.normalize(Vec3.negate(ray.d));
        Vec3 to_light = Vec3.normalize(new Vec3(1, 1, .5));
        Color incoming_light = Color.white;               
        Color color = material.shade(h, to_viewer, to_light, incoming_light);
        return color;
    }

}
