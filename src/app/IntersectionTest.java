package app;

import cgg_tools.*;

public class IntersectionTest {
    static final double EPS = Util.EPSILON;

    private static boolean vecAlmostEqual(Vec3 a, Vec3 b) {
        if (Util.almostEqual(a.x(), b.x()) && Util.almostEqual(a.y(), b.y()) && Util.almostEqual(a.z(), b.z())) return true;
        return false;
    }

    private static void pass(String name) {
        System.out.println("PASS: " + name);
    }
    private static void fail(String name, String reason) {
        System.out.println("FAIL: " + name + ", " + reason);
    }

    static void expectHit(String name, Ray r, Sphere s, double tExp, Vec3 xExp, Vec3 nExp) {
        Hit h = s.intersect(r);

        if (h == null) {
            fail(name, "expected Hit, got null");
            return;
        }

        if(!Util.almostEqual(h.t(), tExp)) {
            fail(name, "t mismatch: expected ..., got ...");
            return;
        }

        if (!vecAlmostEqual(h.x(), xExp)) {
            fail(name, "point mismatch: expected ..., got ...");
            return;
        }

        if (!vecAlmostEqual(h.n(), nExp)) {
            fail(name, "normal mismatch: expected ..., got ...");
            return;
        }

        pass(name);
    }

    static void expectNoHit(String name, Ray r, Sphere s) {
        Hit h = s.intersect(r);

        if(h == null) {
            pass(name);
        } else {
            fail(name, "expected null, got t=" + h.t());
        }
    }

    public static void runAll() {
        // test T1 - hit
        Ray r = new Ray(new Vec3(0,0,0), new Vec3(0, 0, -1), 0, Double.POSITIVE_INFINITY);
        Sphere s = new Sphere(new Vec3(0, 0, -2), 1, Color.beige);
        expectHit("T1", r, s, 1.0, new Vec3(0, 0, -1), new Vec3(0, 0, 1));

        // test T2 - no hit
        Ray r2 = new Ray(new Vec3(0,0,0), new Vec3(0, 1, -1), 0, Double.POSITIVE_INFINITY);
        Sphere s2 = new Sphere(new Vec3(0, 0, -2), 1, Color.beige);
        expectNoHit("T2", r2, s2);

        // test T3 - hit
        Ray r3 = new Ray(new Vec3(0,0,0), new Vec3(0, 0, -1), 0, Double.POSITIVE_INFINITY);
        Sphere s3 = new Sphere(new Vec3(0, -1, -2), 1, Color.beige);
        expectHit("T3", r3, s3, 2.0, new Vec3(0, 0, -2), new Vec3(0, 1, 0));      
    
        // test T4 - no hit
        Ray r4 = new Ray(new Vec3(0,0,-4), new Vec3(0, 0, -1), 0, Double.POSITIVE_INFINITY);
        Sphere s4 = new Sphere(new Vec3(0, 0, -2), 1, Color.beige);
        expectNoHit("T4", r4, s4);

        // test T5 - no hit
        Ray r5 = new Ray(new Vec3(0,0,0), new Vec3(0, 0, -1), 0, 2);
        Sphere s5 = new Sphere(new Vec3(0, 0, -4), 1, Color.beige);
        expectNoHit("T5", r5, s5);
    }
}
