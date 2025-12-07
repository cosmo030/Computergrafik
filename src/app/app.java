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
    PhongMaterial phong1 = new PhongMaterial(Color.darkgreen, Color.darkgreen, Color.white, 4);
    PhongMaterial phong2 = new PhongMaterial(Color.green, Color.green, Color.white, 20);
    PhongMaterial phong3 = new PhongMaterial(Color.cyan, Color.cyan, Color.white, 1000);
    PhongMaterial phong4 = new PhongMaterial(Color.darkmagenta, Color.darkmagenta, Color.white, 4);
    PhongMaterial phong5 = new PhongMaterial(Color.brightermagenta, Color.brightermagenta, Color.white, 20);
    PhongMaterial phong6 = new PhongMaterial(Color.yellow, Color.yellow, Color.white, 1000);
    PhongMaterial material_disc = new PhongMaterial(Color.gray, Color.gray, Color.white, 1000);
    RectShape rs = new RectShape(new Vec3(0, -.5, 0), 10, 10, material_disc);

    Sizes sizes = new Sizes(new Vec3(.4, .6, .2), new Vec3(.1, .4, .1), new Vec3(.1, .3, .1), .15);
    Joints joints = new Joints(-50, 60, -100);
    Materials mats = new Materials(phong1, phong2, phong3);
    Mat4x4 world_move = Mat4x4.move(new Vec3(0, 0, 0));
    Mat4x4 world_rotate = Mat4x4.rotate(new Vec3(0, 1, 0), 45);
    Mat4x4 world = Mat4x4.multiply(world_move, world_rotate);
    Wesen w1 = new Wesen(world, sizes, joints, mats);

    Sizes sizes2 = new Sizes(new Vec3(.4, .6, .2), new Vec3(.1, .4, .1), new Vec3(.1, .3, .1), .15);
    Joints joints2 = new Joints(-120, -50, -100);
    Materials mats2 = new Materials(phong1, phong2, phong3);
    Mat4x4 world_move2 = Mat4x4.move(new Vec3(0, 0, -1.5));
    Mat4x4 world_rotate2 = Mat4x4.rotate(new Vec3(0, 1, 0), 45);
    Mat4x4 world2 = Mat4x4.multiply(world_move2, world_rotate2);
    Wesen w2 = new Wesen(world2, sizes2, joints2, mats2);

    Sizes sizes3 = new Sizes(new Vec3(.3, .6, .1), new Vec3(.08, .3, .08), new Vec3(.07, .25, .07), .12);
    Joints joints3 = new Joints(-50, -50, -1);
    Materials mats3 = new Materials(phong4, phong5, phong6);
    Mat4x4 world_move3 = Mat4x4.move(new Vec3(2, 0, -1.5));
    Mat4x4 world_rotate3 = Mat4x4.rotate(new Vec3(0, 1, 0), 340);
    Mat4x4 world3 = Mat4x4.multiply(world_move3, world_rotate3);
    Wesen w3 = new Wesen(world3, sizes3, joints3, mats3);

    scene.add(w1);
    scene.add(w2);
    scene.add(w3);
    scene.add(rs);

    Raytracer raytracer = new Raytracer(cam, scene, list, Color.black, Color.black);
    image.sample(raytracer);
    // Write the image to disk.
    image.writePNG("a06-jointythings");
  }
}