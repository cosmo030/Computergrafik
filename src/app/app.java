// module "Computergrafik Grundlagen", authors Hartmut Schirmacher + Henrik Tramberend
// Berliner Hochschule für Technik
// contact hschirmacher@bht-berlin.de

package app;

import cgg_tools.Color;
import cgg_tools.Vec2;
import cgg_tools.ConstantColorSampler;
import cgg_tools.ColoredDiscs;

public class app {

  public static void main(String[] args) {
    int width = 1280;
    int height = 720;

    // This object defines the contents of the image.
    // It must implement the cggtools.Sampler interface.
    // var obj = new ConstantColorSampler(Color.beige);
    var obj = new ColoredDiscs(width, height, 50, 10, 100, Color.black);

    // iterate over all pixel of the image
    var image = new Image(width, height);
    for (int i = 0; i != width; i++) {
      for (int j = 0; j != height; j++) {
        image.setPixel(i, j, obj.getColor(new Vec2(i, j)));
      }
    }

    // Write the image to disk.
    image.writePNG("a01-discs-2");
  }
}
