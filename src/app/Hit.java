package app;

import cgg_tools.Vec2;
import cgg_tools.Vec3;

public record Hit(double t, Vec3 hitpoint, Vec2 uv, Vec3 normal, Material material) {
}