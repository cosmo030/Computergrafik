// module "Computergrafik Grundlagen", authors Hartmut Schirmacher + Henrik Tramberend
// Berliner Hochschule für Technik
// contact hschirmacher@bht-berlin.de

package app;

import cgg_tools.Color;
import cgg_tools.Vec2;
import cgg_tools.Vec3;

public class app {

  public static void main(String[] args) {
    int width = 1280;
    int height = 720;

    // This object defines the contents of the image.
    // It must implement the cggtools.Sampler interface.
    Camera cam = new Camera(width, height, 45.0);
    Raytracer rt = new Raytracer(cam, Color.black);

    // add spheres
    rt.addSphere(new Sphere(new Vec3(1, -3, -35), 4, Color.beige));
    rt.addSphere(new Sphere(new Vec3(0, 0, -20), 1, Color.blue));
    rt.addSphere(new Sphere(new Vec3(-2.5, -1.5, -15), 1.5, Color.green));
    rt.addSphere(new Sphere(new Vec3(-3, 0, -15), 2, Color.magenta));
    rt.addSphere(new Sphere(new Vec3(5, 0, -40), 4, Color.cyan));
    rt.addSphere(new Sphere(new Vec3(8, 2, -40), 3, Color.yellow));    
    rt.addSphere(new Sphere(new Vec3(-1.8, 1.3, -10), 0.7, Color.gray));    

    // iterate over all pixel of the image
    var image = new Image(width, height);
    for (int i = 0; i != width; i++) {
      for (int j = 0; j != height; j++) {      
        /* Ray ray = cam.generate_ray(new Vec2(i, j));
        Vec3 d = ray.getDValue();
        Vec3 n = Vec3.normalize(d);
        double r = n.x();
        double g = n.y();
        double b = 0.0;
        Color color = new Color(r, g, b); */
        image.setPixel(i, j, rt.getColor(new Vec2(i,j)));
      }
    }

    // Write the image to disk.
    image.writePNG("a02_spheres");

    IntersectionTest.runAll();;
  }
}
