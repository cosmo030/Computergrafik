// module "Computergrafik Grundlagen", authors Hartmut Schirmacher + Henrik Tramberend
// Berliner Hochschule für Technik
// contact hschirmacher@bht-berlin.de

package app;

import java.util.ArrayList;
import java.util.List;

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

    //DirectionalLight light1 = new DirectionalLight(Color.white, new Vec3(-1,1,-.5));
    //DirectionalLight light2 = new DirectionalLight(Color.white, new Vec3(-1,-1,-.5));

    PointLight l = new PointLight(new Vec3(2, 4, -3), Color.white);    

    List<Light> list = new ArrayList<>();
    //list.add(light1);
    //list.add(light2);
    list.add(l);
    PhongMaterial phong1 = new PhongMaterial(Color.magenta, Color.magenta, Color.black, 1);
    PhongMaterial phong2 = new PhongMaterial(Color.darkred, Color.darkred, Color.white, 20);
    PhongMaterial phong3 = new PhongMaterial(Color.darkred, Color.darkred, Color.white, 1000);
    PhongMaterial material_disc = new PhongMaterial(Color.gray, Color.gray, Color.white, 1000);  
    DiscShape ds = new DiscShape(new Vec3(0, -.5, 0), 100, material_disc);  
    scene.add(new SphereShape(new Vec3(-1, -.25, -2.5), .4, phong1));
    scene.add(new SphereShape(new Vec3(0, -.25, -2.5), .4, phong2));
    scene.add(new SphereShape(new Vec3(1, -.25, -2.5), .4, phong3));
    scene.add(ds);  

    Raytracer raytracer = new Raytracer(cam, scene, list, Color.black, Color.black);
    image.sample(raytracer);
    // Write the image to disk.
    image.writePNG("a04-pointlight");
  }
}
