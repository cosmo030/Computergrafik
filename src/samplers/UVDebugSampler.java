package samplers;

import cgg_tools.Color;
import cgg_tools.Sampler;
import cgg_tools.Vec2;

public record UVDebugSampler() implements Sampler {

    @Override
    public Color getColor(Vec2 p) {
        return new Color(p.u(), p.v(), 0);
    }

}
