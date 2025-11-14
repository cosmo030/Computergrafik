package app;

import cgg_tools.Color;
import cgg_tools.Vec3;

public record DirectionalLight(Color color, Vec3 d) implements Light {

    @Override
    public Vec3 to_light(Vec3 receiver_position) {        
        Vec3 dMinus = Vec3.negate(d);
        Vec3 dNormalized = Vec3.normalize(dMinus);
        return dNormalized;
    }

    @Override
    public double distance(Vec3 receiver_position) {        
        return Double.MAX_VALUE;
    }

    @Override
    public Color color_at(Vec3 receiver_position) {        
        return color;
    }
    
}
