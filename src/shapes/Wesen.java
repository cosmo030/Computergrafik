package shapes;

import app.Hit;
import app.Joints;
import app.Materials;
import app.Ray;
import app.Sizes;
import cgg_tools.Mat4x4;
import cgg_tools.Vec3;

public class Wesen implements Shape {
    private GroupShape shapes;

    public Wesen(Mat4x4 transform, Sizes sizes, Joints joints, Materials materials) {
        shapes = new GroupShape(transform);

        Vec3 torso_size = sizes.torso();
        double torso_height = torso_size.y();
        double lift = torso_height / 2.0;
        Mat4x4 torso_transform = Mat4x4.move(new Vec3(0, lift, 0));
        GroupShape torso_node = new GroupShape(torso_transform);

        Vec3 half = new Vec3(torso_size.x(), torso_size.y(), torso_size.z());
        Vec3 center = new Vec3(0, 0, 0);
        double radius = torso_size.x() / 2.0;
        double y_min = -torso_height / 2.0;
        double y_max = torso_height / 2.0;
        ClosedCylinderShape torso_skin = new ClosedCylinderShape(radius, y_min, y_max, materials.torso(),
                materials.torso());
        torso_node.add(torso_skin);

        shapes.add(torso_node);

        double shoulderX = torso_size.x() / 2.0;
        double shoulderY = torso_size.y() / 2.0 * 0.9;
        double shoulderZ = 0.0;
        Mat4x4 translate_shoulder = Mat4x4.move(new Vec3(shoulderX, shoulderY, shoulderZ));
        Mat4x4 rotate_shoulder = Mat4x4.rotate(Vec3.nzAxis, joints.shoulder());
        Mat4x4 transform_shoulder = Mat4x4.multiply(translate_shoulder, rotate_shoulder);
        GroupShape shoulder_joint = new GroupShape(transform_shoulder);
        torso_node.add(shoulder_joint);

        Vec3 upper = sizes.upper_arm();
        Vec3 upperHalf = new Vec3(upper.x(), upper.y(), upper.z());
        Vec3 upperCenter = new Vec3(0.0, -upper.y() / 2.0, 0.0);
        CuboidShape upperArm = new CuboidShape(upperCenter, upperHalf, materials.arm());
        shoulder_joint.add(upperArm);

        double upperLen = sizes.upper_arm().y();
        Mat4x4 translate_elbow = Mat4x4.move(new Vec3(0, -upperLen, 0));
        Mat4x4 rotate_elbow = Mat4x4.rotate(Vec3.nzAxis, joints.elbow());
        Mat4x4 transform_elbow = Mat4x4.multiply(translate_elbow, rotate_elbow);
        GroupShape elbow_joint = new GroupShape(transform_elbow);
        shoulder_joint.add(elbow_joint);

        Vec3 lower = sizes.forearm();
        Vec3 lowerHalf = new Vec3(lower.x(), lower.y(), lower.z());
        Vec3 lowerCenter = new Vec3(0, -lower.y() / 2, 0);

        CuboidShape lowerArm = new CuboidShape(lowerCenter, lowerHalf, materials.arm());
        elbow_joint.add(lowerArm);

        double lowerLen = lower.y();
        Mat4x4 translate_wrist = Mat4x4.move(new Vec3(0, -lowerLen, 0));
        Mat4x4 rotate_wrist = Mat4x4.rotate(Vec3.nzAxis, joints.wrist());
        Mat4x4 transform_wrist = Mat4x4.multiply(translate_wrist, rotate_wrist);
        GroupShape wrist_joint = new GroupShape(transform_wrist);
        elbow_joint.add(wrist_joint);

        double handRadius = sizes.hand_radius() / 2;
        Vec3 handCenter = new Vec3(0, 0, 0);

        SphereShape hand = new SphereShape(handCenter, handRadius, materials.joint());
        wrist_joint.add(hand);

    }

    @Override
    public Hit intersect(Ray r) {
        return shapes.intersect(r);
    }

}