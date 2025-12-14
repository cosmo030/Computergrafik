package samplers;

import cgg_tools.Color;
import cgg_tools.Mat4x4;
import cgg_tools.Sampler;
import cgg_tools.Vec2;
import cgg_tools.Vec3;

public record TransformSampler(Mat4x4 M, Sampler inner) implements Sampler {

    @Override
    public Color getColor(Vec2 p) {
        double u = p.u();
        double v = p.v();

        Vec3 q = new Vec3(u, v, 0);
        Vec3 q2 = Mat4x4.multiplyPoint(M, q);

        Vec2 p2 = new Vec2(q2.x(), q2.y());

        return inner.getColor(p2);
    }

}