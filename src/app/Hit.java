package app;

import cgg_tools.Vec3;

public record Hit(double t, Vec3 x, Vec3 n, Material material) {}