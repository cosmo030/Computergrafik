package app;

import cgg_tools.Vec3;
import cgg_tools.Color;
import cgg_tools.Util;

public record PointLight(Vec3 position, Color color) implements Light {

    @Override
    public Vec3 to_light(Vec3 receiver_position) {        
        Vec3 s = Vec3.normalize(Vec3.subtract(position, receiver_position));
        return s;
    }

    @Override
    public double distance(Vec3 receiver_position) {        
        double s = Vec3.distance(position, receiver_position);
        return s;
    }

    @Override
    public Color color_at(Vec3 receiver_position) {        
        double d = distance(receiver_position);
        if(d < Util.EPSILON) d = Util.EPSILON;
        double att = 1 / (d*d);
        Color incoming = Color.multiply(att, color);
        return Color.multiply(incoming, 8);
    }
}