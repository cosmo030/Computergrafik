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

    Mat4x4 translation = Mat4x4.move(new Vec3(1.5, .5, 5));
    Mat4x4 rotate = Mat4x4.rotate(new Vec3(-1, 1, 0), 30);
    Mat4x4 transform = Mat4x4.multiply(rotate, translation);

    Camera cam = new Camera(width, height, 70.0, transform);
    GroupShape scene = new GroupShape(Mat4x4.identity());

    // gate
    cube_gate(scene);
    // rotated cube
    cube_rotated(scene);
    // leaned on cube
    cube_lean(scene);

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
    PhongMaterial phong1 = new PhongMaterial(Color.red, Color.red, Color.black, 1);
    PhongMaterial phong2 = new PhongMaterial(Color.red, Color.red, Color.white, 20);
    PhongMaterial phong3 = new PhongMaterial(Color.red, Color.red, Color.white, 1000);
    PhongMaterial material_disc = new PhongMaterial(Color.gray, Color.gray, Color.white, 1000);
    // DiscShape ds = new DiscShape(new Vec3(0, -.5, 0), 100, material_disc);
    RectShape rs = new RectShape(new Vec3(0, -.5, 0), 10, 10, material_disc);
    scene.add(new SphereShape(new Vec3(-1, -.25, -2.5), .4, phong1));
    scene.add(new SphereShape(new Vec3(0, -.25, -2.5), .4, phong2));
    scene.add(new SphereShape(new Vec3(1, -.25, -2.5), .4, phong3));
    scene.add(rs);

    Raytracer raytracer = new Raytracer(cam, scene, list, Color.black, Color.black);
    image.sample(raytracer);
    // Write the image to disk.
    image.writePNG("a05-cuboids");
  }

  // auslagern weil vieelllll zu lang für main
  public static void cube_gate(GroupShape scene) {
    var center = new Vec3(-2.5, 0, -2.7);
    var size = new Vec3(1, 1, 1);
    var mat = new PhongMaterial(Color.green, Color.green, Color.white, 1000);
    var cuboid = new CuboidShape(center, size, mat);

    var center2 = new Vec3(-2.5, 1, -2.7);
    var size2 = new Vec3(1, 1, 1);
    var mat2 = new PhongMaterial(Color.green, Color.green, Color.white, 1000);
    var cuboid2 = new CuboidShape(center2, size2, mat2);

    var center3 = new Vec3(-1.5, 1, -2.7);
    var size3 = new Vec3(1, 1, 1);
    var mat3 = new PhongMaterial(Color.green, Color.green, Color.white, 1000);
    var cuboid3 = new CuboidShape(center3, size3, mat3);

    var center4 = new Vec3(-.5, 1, -2.7);
    var size4 = new Vec3(1, 1, 1);
    var mat4 = new PhongMaterial(Color.green, Color.green, Color.white, 1000);
    var cuboid4 = new CuboidShape(center4, size4, mat4);

    var center5 = new Vec3(.5, 1, -2.7);
    var size5 = new Vec3(1, 1, 1);
    var mat5 = new PhongMaterial(Color.green, Color.green, Color.white, 1000);
    var cuboid5 = new CuboidShape(center5, size5, mat5);

    var center6 = new Vec3(1.5, 1, -2.7);
    var size6 = new Vec3(1, 1, 1);
    var mat6 = new PhongMaterial(Color.green, Color.green, Color.white, 1000);
    var cuboid6 = new CuboidShape(center6, size6, mat6);

    var center7 = new Vec3(2.3, 1, -2.7);
    var size7 = new Vec3(1, 1, 1);
    var mat7 = new PhongMaterial(Color.green, Color.green, Color.white, 1000);
    var cuboid7 = new CuboidShape(center7, size7, mat7);

    var center8 = new Vec3(2.3, 0, -2.7);
    var size8 = new Vec3(1, 1, 1);
    var mat8 = new PhongMaterial(Color.green, Color.green, Color.white, 1000);
    var cuboid8 = new CuboidShape(center8, size8, mat8);

    scene.add(cuboid);
    scene.add(cuboid2);
    scene.add(cuboid3);
    scene.add(cuboid4);
    scene.add(cuboid5);
    scene.add(cuboid6);
    scene.add(cuboid7);
    scene.add(cuboid8);
  }

  static void cube_lean(GroupShape scene) {
    var center = new Vec3(2.36, -.25, 0);
    var size = new Vec3(.5, .5, .5);
    var mat = new PhongMaterial(Color.beige, Color.beige, Color.white, 1000);
    var cuboid = new CuboidShape(center, size, mat);

    var center2 = new Vec3(2, -2.2, 0);
    var size2 = new Vec3(.5, .5, .5);
    var mat2 = new PhongMaterial(Color.cyan, Color.cyan, Color.white, 1000);
    var cuboid2 = new CuboidShape(center2, size2, mat2);

    Mat4x4 rot = Mat4x4.rotate(new Vec3(0, 0, 1), 45);
    GroupShape tilted = new GroupShape(rot);
    tilted.add(cuboid2);
    scene.add(tilted);
    scene.add(cuboid);
  }

  static void cube_rotated(GroupShape scene) {
    var center = new Vec3(.4, .3, .5);
    var size = new Vec3(.6, .6, .6);
    var mat = new PhongMaterial(Color.cyan, Color.cyan, Color.white, 1000);
    var cuboid = new CuboidShape(center, size, mat);

    Mat4x4 rot = Mat4x4.rotate(new Vec3(1, 0, 1), 45);
    GroupShape tilted = new GroupShape(rot);
    tilted.add(cuboid);
    scene.add(tilted);
  }
}
