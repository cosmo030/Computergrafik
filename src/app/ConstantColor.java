package app;

import cgg_tools.Color;
import cgg_tools.ConstantColorSampler;
import cgg_tools.Sampler;
import cgg_tools.Vec2;

public record ConstantColor(Color c) implements Sampler {

    public static Sampler CC(Color c) {
        return new ConstantColorSampler(c);
    }

    public static Sampler CC(Color c, double w) {
        return new ConstantColorSampler(Color.multiply(c, w));
    }

    @Override
    public Color getColor(Vec2 p) {
        return c;
    }

}
