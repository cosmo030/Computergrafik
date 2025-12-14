package samplers;

import java.util.ArrayList;
import java.util.List;
//import java.util.ListIterator;

import app.DiscModel2D;
import cgg_tools.Color;
import cgg_tools.RandomUtil;
import cgg_tools.Sampler;
import cgg_tools.Vec2;

public class ColoredDiscs implements Sampler {
    Color background;
    int width;
    int height;
    int count;
    double radiusMin;
    double radiusMax;
    List<DiscModel2D> discs;

    public ColoredDiscs(int width, int height, int count, double radiusMin, double radiusMax, Color background) {
        if (count < 0 || radiusMin > radiusMax || width <= 0 || height <= 0) {
            throw new IllegalArgumentException("rules: 'count >= 0 && radiusMin <= radiusMax' !!!");
        }
        this.count = count;
        this.width = width;
        this.height = height;
        this.radiusMin = radiusMin;
        this.radiusMax = radiusMax;
        this.background = background;
        discs = new ArrayList<>(count);

        addDiscs();
    }

    public void addDiscs() {
        discs.clear();

        for (int c = 0; c < count; c++) {
            double tmpRadius = RandomUtil.random();
            double radius = radiusMin + tmpRadius * (radiusMax - radiusMin);

            double tmpX = RandomUtil.random();
            double tmpY = RandomUtil.random();
            double x = tmpX * width;
            double y = tmpY * height;
            Color color = RandomUtil.randomColor();

            Vec2 center = new Vec2(x, y);
            DiscModel2D disc = new DiscModel2D(center, radius, color);
            discs.add(disc);
        }
    }

    @Override
    public Color getColor(Vec2 p) {
        DiscModel2D best = null;
        double smallestRadius = Double.POSITIVE_INFINITY;
        for (int index = 0; index < discs.size(); index++) {
            DiscModel2D disc = discs.get(index);
            if (disc.coversPoint(p) && disc.r() < smallestRadius) {
                best = disc;
                smallestRadius = disc.r();
            }
        }
        if (best != null)
            return best.color();
        return background;
    }
}
