package app;

import java.util.ArrayList;

import cgg_tools.Color;
import cgg_tools.Sampler;
import cgg_tools.Vec2;

public class Raytracer implements Sampler{
    Camera cam;
    ArrayList<Sphere> scene;
    Color background;

    public Raytracer(Camera cam, Color background) {
        this.cam = cam;
        this.background = background;
        scene = new ArrayList<>();        
    }

    public void addSphere(Sphere s) {
        if(s == null) throw new IllegalArgumentException();
        scene.add(s);
    }

    @Override
    public Color getColor(Vec2 p) {
        Ray ray = cam.generate_ray(p);
        double bestT = Double.POSITIVE_INFINITY;
        Hit bestHit = null;

        for(Sphere s : scene) {
            Hit h = s.intersect(ray);
            if(h!=null && h.t() < bestT) {
                bestT = h.t();
                bestHit = h;
            }
        }
        if(bestHit == null) {
            return background;
        }
        return bestHit.c();
    }
    
}
