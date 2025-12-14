package samplers;

import cgg_tools.Color;
import cgg_tools.Sampler;
import cgg_tools.Vec2;

public record ClampSampler(Sampler inner, Color border_color) implements Sampler {

    @Override
    public Color getColor(Vec2 p) {
        double u = p.u();
        double v = p.v();

        if ((0 <= u && u <= 1) && (0 <= v && v <= 1)) {
            return inner.getColor(p);
        } else {
            return border_color;
        }
    }
}
