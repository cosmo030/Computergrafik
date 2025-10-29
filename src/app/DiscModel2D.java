package app;

import cgg_tools.Color;
import cgg_tools.Vec2;

public record DiscModel2D(Vec2 m, double r, Color color) {

    public boolean coversPoint(Vec2 pos) {
        Vec2 v = Vec2.subtract(pos, m);
        double squareV = Vec2.squaredLength(v);
        double squareR = r * r;
        if (squareV <= squareR)
            return true;
        return false;
    }
}
