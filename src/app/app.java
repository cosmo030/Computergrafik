// module "Computergrafik Grundlagen", authors Hartmut Schirmacher + Henrik Tramberend
// Berliner Hochschule für Technik
// contact hschirmacher@bht-berlin.de

package app;

import java.util.ArrayList;
import java.util.List;

import cgg_tools.Color;
import cgg_tools.Mat4x4;
import cgg_tools.Vec3;
import samplers.ConstantColor;
import samplers.Raytracer;
import samplers.TransformSampler;
import shapes.ClosedCylinderShape;
import shapes.GroupShape;
import shapes.OpenCylinderShape;
import shapes.RectShape;
import shapes.SphereShape;
import cgg_tools.ImageTexture;

public class app {

  public static void main(String[] args) {
    int width = 1280;
    int height = 720;

    // This object defines the contents of the image.
    // It must implement the cggtools.Sampler interface.
    // iterate over all pixel of the image
    // scene
    var image = new Image(width, height);

    Mat4x4 translation = Mat4x4.move(new Vec3(4, 5, 18));
    Mat4x4 rotate = Mat4x4.rotate(new Vec3(-1, 1, 0), 30);
    Mat4x4 transform = Mat4x4.multiply(rotate, translation);

    Camera cam = new Camera(width, height, 70.0, transform);
    GroupShape scene = new GroupShape(Mat4x4.identity());

    PointLight lightsource1 = new PointLight(new Vec3(1, 10, -1), Color.white);
    PointLight lightsource2 = new PointLight(new Vec3(3, 10, 1), Color.white);
    PointLight lightsource3 = new PointLight(new Vec3(4, 10, 1), Color.white);
    PointLight lightsource4 = new PointLight(new Vec3(10, 10, -5), Color.white);
    PointLight lightsource5 = new PointLight(new Vec3(4, 10, 5), Color.white);
    PointLight lightsource6 = new PointLight(new Vec3(0, 1, 20), Color.white);
    PointLight lightsource7 = new PointLight(new Vec3(15, 1, 20), Color.white);
    List<Light> list = new ArrayList<>();
    list.add(lightsource1);
    list.add(lightsource2);
    list.add(lightsource3);
    list.add(lightsource4);
    list.add(lightsource5);
    list.add(lightsource6);
    list.add(lightsource7);

    Mat4x4 translation_tex = Mat4x4.move(new Vec3(0, 0, 0));
    Mat4x4 rotate_tex = Mat4x4.rotate(new Vec3(0, 0, 1), 0);
    Mat4x4 scale_tex = Mat4x4.scale(10, 10, 10);
    Mat4x4 transform_tex = Mat4x4.multiply(rotate_tex, translation_tex, scale_tex);

    Mat4x4 translation_tex2 = Mat4x4.move(new Vec3(0, 0, 0));
    Mat4x4 rotate_tex2 = Mat4x4.rotate(new Vec3(1, 1, 0), 45);
    Mat4x4 scale_tex2 = Mat4x4.scale(5, 5, 5);
    Mat4x4 transform_tex2 = Mat4x4.multiply(rotate_tex2, translation_tex2, scale_tex2);

    Mat4x4 translation_tex3 = Mat4x4.move(new Vec3(0, 0, 0));
    Mat4x4 rotate_tex3 = Mat4x4.rotate(new Vec3(0, 0, 1), -35);
    Mat4x4 scale_tex3 = Mat4x4.scale(6, 6, 6);
    Mat4x4 transform_tex3 = Mat4x4.multiply(rotate_tex3, translation_tex3, scale_tex3);

    var base = new ImageTexture("src/textures/grass.jpg");
    var finalSampler = new TransformSampler(transform_tex, base);

    var col_discs2 = new ImageTexture("src/../images/a01-discs.png");
    var finalSampler2 = new TransformSampler(transform_tex2, col_discs2);

    var col_discs3 = new ImageTexture("src/textures/A_red.png");
    var finalSampler3 = new TransformSampler(transform_tex3, col_discs3);

    PhongMaterial phong_tex = new PhongMaterial(ConstantColor.CC(Color.gray), finalSampler,
        ConstantColor.CC((Color.gray), 0), 2000);

    PhongMaterial phong_tex2 = new PhongMaterial(ConstantColor.CC(Color.gray), finalSampler2,
        ConstantColor.CC((Color.gray), 0), 2000);

    PhongMaterial phong_tex3 = new PhongMaterial(ConstantColor.CC(Color.gray), finalSampler3,
        ConstantColor.CC((Color.gray), 0), 2000);

    RectShape rs = new RectShape(new Vec3(-.5, 0, .5), 100, 100, phong_tex);

    SphereShape sphere1 = new SphereShape(new Vec3(10, 2, -4), 3, phong_tex2);
    OpenCylinderShape openCyl1 = new OpenCylinderShape(3, 0, 5, phong_tex3);
    ClosedCylinderShape cylinder1 = new ClosedCylinderShape(3, 0, 5, phong_tex3, phong_tex3);

    scene.add(rs);
    scene.add(sphere1);
    scene.add(openCyl1);
    scene.add(cylinder1);

    Raytracer raytracer = new Raytracer(cam, scene, list, Color.black, Color.black);
    image.sample(raytracer);
    // Write the image to disk.
    image.writePNG("a07-transformed-image");
  }
}