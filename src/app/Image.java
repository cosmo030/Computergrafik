
package app;

import cgg_tools.Color;
import cgg_tools.ImageWriter;
import cgg_tools.Sampler;
import cgg_tools.Vec2;

public class Image implements cgg_tools.Image {
    int width, height;
    double[] data;

    // create a double array for storage of the RGB Pixels
    public Image(int width, int height) {
        this.width = width;
        this.height = height;
        data = new double[width * height * 3];
    }

    // store the RGB values of the pixel (i,j) in the image
    @Override
    public void setPixel(int i, int j, Color color) {
        if (i < 0 || i >= width || j < 0 || j >= height)
            return;
        int index = j * width + i;
        int rgb = index * 3;
        data[rgb + 0] = color.r();
        data[rgb + 1] = color.g();
        data[rgb + 2] = color.b();
    }

    public void writePNG(String name) {
        System.out.format("Implement function `app.Image.writePng` to actually write image `%s`\n", name);
        ImageWriter.writePNG(name, data, width, height);
    }

    public void sample(Sampler s) {
        for (int i = 0; i != width; i++) {
            for (int j = 0; j != height; j++) {
                this.setPixel(i, j, s.getColor(new Vec2(i, j)));
            }
        }
    }

    // return the RGB color of a given pixel (i,j)
    @Override
    public Color getPixel(int i, int j) {
        if (i < 0 || i >= width || j < 0 || j >= height)
            return Color.black;
        int index = j * width + i;
        int rgb = index * 3;
        double r = data[rgb];
        double g = data[rgb + 1];
        double b = data[rgb + 2];
        Color color = new Color(r, g, b);
        return color;
    }

    @Override
    public int width() {
        return width;
    }

    @Override
    public int height() {
        return height;
    }

}
