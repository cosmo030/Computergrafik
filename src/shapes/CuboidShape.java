package shapes;

import app.Hit;
import app.Material;
import app.Ray;
import cgg_tools.Mat4x4;
import cgg_tools.Vec3;

public class CuboidShape implements Shape {
    GroupShape root;
    Vec3 center;
    Vec3 size;
    Material mat;

    public CuboidShape(Vec3 center, Vec3 size, Material mat) {
        this.center = center;
        this.size = size;
        this.mat = mat;
        Mat4x4 matrix = Mat4x4.move(center).multiply(Mat4x4.scale(size));
        root = new GroupShape(matrix);
        cuboid();
    }

    public void cuboid() {
        RectShape base = new RectShape(new Vec3(0, 0, 0), 1, 1, mat);

        Vec3 xAxis = new Vec3(1, 0, 0);
        Vec3 zAxis = new Vec3(0, 0, 1);

        GroupShape top = new GroupShape(Mat4x4.move(new Vec3(0, .5, 0)));
        top.add(base);
        root.add(top);

        GroupShape bottom = new GroupShape(Mat4x4.move(new Vec3(0, -.5, 0)).multiply(Mat4x4.rotate(xAxis, 180)));
        bottom.add(base);
        root.add(bottom);

        GroupShape front = new GroupShape(Mat4x4.move(new Vec3(0, 0, .5)).multiply(Mat4x4.rotate(xAxis, 90)));
        front.add(base);
        root.add(front);

        GroupShape back = new GroupShape(Mat4x4.move(new Vec3(0, 0, -.5)).multiply(Mat4x4.rotate(xAxis, -90)));
        back.add(base);
        root.add(back);

        GroupShape left = new GroupShape(Mat4x4.move(new Vec3(-.5, 0, 0)).multiply(Mat4x4.rotate(zAxis, 90)));
        left.add(base);
        root.add(left);

        GroupShape right = new GroupShape(Mat4x4.move(new Vec3(.5, 0, 0)).multiply(Mat4x4.rotate(zAxis, -90)));
        right.add(base);
        root.add(right);
    }

    @Override
    public Hit intersect(Ray r) {
        return root.intersect(r);
    }

}
