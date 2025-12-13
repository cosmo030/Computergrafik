package app;

import cgg_tools.Color;
import cgg_tools.Sampler;
import cgg_tools.Vec3;

public record PhongMaterial(Sampler k_ambient, Sampler k_diffuse, Sampler k_specular, double shiny)
        implements Material {

    @Override
    public Color ambient(Hit hit, Color ambient_light) {
        Color ambient_result = Color.multiply(k_ambient.getColor(hit.uv()), ambient_light);
        return ambient_result;
    }

    @Override
    public Color shade(Hit hit, Vec3 to_viewer, Vec3 to_light, Color incoming_light) {
        Vec3 n = Vec3.normalize(hit.normal());
        Vec3 s = Vec3.normalize(to_light);
        Vec3 v = Vec3.normalize(to_viewer);
        Vec3 r = Vec3.reflect(s, n);

        // diffuse
        double ns = Vec3.dot(n, s);
        // pink debugging
        if (ns < 0)
            return Color.black;
        double l_cos = Math.max(0, ns);
        Color diffuse_result = Color.multiply(k_diffuse.getColor(hit.uv()), incoming_light);
        Color l_diffuse = Color.multiply(diffuse_result, l_cos);

        // specular
        double rv = Vec3.dot(r, v);
        double s_cos = Math.max(0, rv);
        double s_scalar = Math.pow(s_cos, shiny);
        Color specular_result = Color.multiply(k_specular.getColor(hit.uv()), incoming_light);
        Color l_specular = Color.multiply(specular_result, s_scalar);

        Color result = Color.add(l_diffuse, l_specular);

        return result;
    }

}
