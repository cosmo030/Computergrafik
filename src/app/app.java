// module "Computergrafik Grundlagen", authors Hartmut Schirmacher + Henrik Tramberend
// Berliner Hochschule für Technik
// contact hschirmacher@bht-berlin.de

package app;

import cgg_tools.Color;
import cgg_tools.Vec3;

public class app {

  public static void main(String[] args) {
    int width = 1280;
    int height = 720;

    // This object defines the contents of the image.
    // It must implement the cggtools.Sampler interface.
    // iterate over all pixel of the image
    // scene
    var image = new Image(width, height);
    Camera cam = new Camera(width, height, 70.0);
    GroupShape scene = new GroupShape();

    DiscShape ds = new DiscShape(new Vec3(0, -0.5, 0), 100, Color.beige);
    BackgroundShape bs = new BackgroundShape(Color.multiply(0.7, Color.cyan));
    scene.add(new SphereShape(new Vec3(-2.3, -.25, -3.5), .3, Color.red));
    scene.add(new SphereShape(new Vec3(-1.5, .0, -3.0), .4, Color.green));
    scene.add(new SphereShape(new Vec3(-.8, .0, -3.0), .45, Color.blue));
    scene.add(new SphereShape(new Vec3(-.2, -.25, -2.5), .5, Color.red));
    scene.add(new SphereShape(new Vec3(.5, -.5, -2.0), .6, Color.green));
    scene.add(new SphereShape(new Vec3(1.1, -.75, -1.5), .5, Color.blue));
    scene.add(ds);
    scene.add(bs);

    Raytracer raytracer = new Raytracer(cam, scene, Color.black);
    image.sample(raytracer);
    // Write the image to disk.
    image.writePNG("a03-own-scene");
  }
}
