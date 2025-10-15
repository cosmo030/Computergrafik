// module "Computergrafik Grundlagen", authors Hartmut Schirmacher + Henrik Tramberend
// Berliner Hochschule für Technik
// contact hschirmacher@bht-berlin.de

package app;

import cgg_tools.Color;
import cgg_tools.Vec2;
import cgg_tools.Vec3;
import cgg_tools.Ray;
import cgg_tools.Camera;

public class app {

  public static void main(String[] args) {
    int width = 1280;
    int height = 720;

    // This object defines the contents of the image.
    // It must implement the cggtools.Sampler interface.
    // var obj = new ConstantColorSampler(Color.beige);
    Camera cam = new Camera(width, height, 45.0);

    // iterate over all pixel of the image
    var image = new Image(width, height);
    for (int i = 0; i != width; i++) {
      for (int j = 0; j != height; j++) {
        // image.setPixel(i, j, obj.getColor(new Vec2(i, j)));
        Ray ray = cam.generate_ray(new Vec2(i, j));
        Vec3 d = ray.getDValue();
        Vec3 n = Vec3.normalize(d);
        double r = n.x();
        double g = n.y();
        double b = 0.0;
        Color color = new Color(r, g, b);
        image.setPixel(i, j, color);
      }
    }

    // Write the image to disk.
    image.writePNG("a02_cam_directions");
  }
}
