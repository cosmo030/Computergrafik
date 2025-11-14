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

    DiscShape ds = new DiscShape(new Vec3(0, -0.5, 0), 100, Color.yellow);
    BackgroundShape bs = new BackgroundShape(Color.multiply(0.7, Color.white));
    scene.add(new SphereShape(new Vec3(-1, -.25, -2.5), .7, Color.red));
    scene.add(new SphereShape(new Vec3(0, -.25, -2.5), .5, Color.green));
    scene.add(new SphereShape(new Vec3(1, -.25, -2.5), .7, Color.blue));
    scene.add(ds);
    scene.add(bs);

    DirectionalLight testLight = new DirectionalLight(Color.white, new Vec3(-1,-1,-.5));
    System.out.println(testLight.to_light(null));

    Raytracer raytracer = new Raytracer(cam, scene, Color.black);
    image.sample(raytracer);
    // Write the image to disk.
    image.writePNG("a04-dummy");
  }
}
