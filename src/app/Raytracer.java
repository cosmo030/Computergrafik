package app;

import java.util.List;

import cgg_tools.Color;
import cgg_tools.Sampler;
import cgg_tools.Util;
import cgg_tools.Vec2;
import cgg_tools.Vec3;

public record Raytracer(Camera cam, GroupShape scene, List<Light> lights, Color ambient_light, Color background) implements Sampler {

    @Override
    public Color getColor(Vec2 p) {
        Ray ray = cam.generate_ray(p);
        Hit h = scene.intersect(ray);
        if (h == null)
            return background;
        Material material = h.material();
        Vec3 to_viewer = Vec3.normalize(Vec3.negate(ray.d()));
        //Vec3 to_light = Vec3.normalize(new Vec3(1, 1, .5));
        Color ambient = material.ambient(h, ambient_light);
        Vec3 hit_pos = h.x();

        for(Light light : lights) {
            Vec3 to_light_vec = Vec3.normalize(light.to_light(hit_pos));
            double eps = Util.EPSILON;
            Vec3 shadow_origin_calc = Vec3.multiply(to_light_vec, eps);
            Vec3 shadow_origin = Vec3.add(shadow_origin_calc, hit_pos);
            double max_dist = light.distance(hit_pos);
            Ray shadow_ray = new Ray(shadow_origin, to_light_vec, eps, max_dist);
            Hit shadow_hit = scene.intersect(shadow_ray);            

            if(shadow_hit == null || shadow_hit.t() >= max_dist) {
                Color incoming_light = light.color_at(hit_pos);
                Color contrib = material.shade(h, to_viewer, to_light_vec, incoming_light);            
                ambient = Color.add(ambient, contrib);
            }

        }

        return ambient;
    }

}
