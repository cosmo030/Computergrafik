// module "Computergrafik Grundlagen", authors Hartmut Schirmacher + Henrik Tramberend
// Berliner Hochschule für Technik
// contact hschirmacher@bht-berlin.de

package app;

import java.util.ArrayList;
import java.util.List;

import cgg_tools.Color;
import cgg_tools.Mat4x4;
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

    Mat4x4 translation = Mat4x4.move(new Vec3(-.5, 2, 12));
    Mat4x4 rotate = Mat4x4.rotate(new Vec3(-1, 0, 0), 15);
    Mat4x4 transform = Mat4x4.multiply(rotate, translation);

    Camera cam = new Camera(width, height, 70.0, transform);
    GroupShape scene = new GroupShape(Mat4x4.identity());

    PointLight lightsource1 = new PointLight(new Vec3(2, 4, -1), Color.white);
    PointLight lightsource2 = new PointLight(new Vec3(2, 4, 1), Color.white);
    PointLight lightsource3 = new PointLight(new Vec3(4, 4, 1), Color.white);
    PointLight lightsource4 = new PointLight(new Vec3(10, 10, -5), Color.white);
    PointLight lightsource5 = new PointLight(new Vec3(4, 4, 5), Color.white);
    List<Light> list = new ArrayList<>();
    list.add(lightsource1);
    list.add(lightsource2);
    list.add(lightsource3);
    list.add(lightsource4);
    list.add(lightsource5);
    PhongMaterial phongUVDebug = new PhongMaterial(new UVDebugSampler(), new UVDebugSampler(),
        ConstantColor.CC((Color.gray), 0), 2000);
    PhongMaterial material_disc = new PhongMaterial(ConstantColor.CC(Color.gray), ConstantColor.CC(Color.gray),
        ConstantColor.CC((Color.white), 20), 1000);
    RectShape rs = new RectShape(new Vec3(-.5, 0, .5), 7, 7, phongUVDebug);
    DiscShape ds = new DiscShape(new Vec3(-.5, 0, -7.5), 4, phongUVDebug);

    scene.add(rs);
    scene.add(ds);

    Raytracer raytracer = new Raytracer(cam, scene, list, Color.black, Color.black);
    image.sample(raytracer);
    // Write the image to disk.
    image.writePNG("a07-uv-debug");
  }
}