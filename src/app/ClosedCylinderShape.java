// module "Computergrafik Grundlagen", authors Hartmut Schirmacher + Henrik Tramberend
// Berliner Hochschule für Technik
// contact hschirmacher@bht-berlin.de

package app;

import static cgg_tools.Mat4x4.identity;

import cgg_tools.Mat4x4;
import cgg_tools.Vec3;

/**
 * Represents a cylinder around the Y-axis, with caps on both ends
 */
public class ClosedCylinderShape implements Shape {

    /**
     * group to hold the open cylinder plus two end caps
     */
    private GroupShape shapes;

    /**
     * Constructs a closed cylinder shape centered around the Y axis
     *
     * @param radius       radius of the cylinder
     * @param y_min        lowest Y coordinate for the cylinder
     * @param y_max        highest Y coordinate for the cylinder
     * @param material     material for the curved hull of the cylinder
     * @param cap_material material for the end caps at the bottom and top
     */
    public ClosedCylinderShape(double radius, double y_min, double y_max, Material material, Material cap_material) {

        var cylinder = new OpenCylinderShape(radius, y_min, y_max, material);
        var top_disc = new DiscShape(new Vec3(0, y_max, 0), radius, cap_material); // normal points up
        var bottom_disc = new DiscShape(new Vec3(0, -y_min, 0), radius, cap_material); // will be flipped!
        var rotate = Mat4x4.rotate(Vec3.nxAxis, 180);
        var bottom_group = new GroupShape(rotate); // flipped, negates Y coord!
        bottom_group.add(bottom_disc);
        shapes = new GroupShape(identity());
        shapes.add(top_disc);
        shapes.add(cylinder);
        shapes.add(bottom_group);
    }

    @Override
    public Hit intersect(Ray ray) {
        return shapes.intersect(ray);
    }

}
