
package app;

import cgg_tools.Color;
import cgg_tools.ImageWriter;

public class Image implements cgg_tools.Image {
    int width, height;
    double[] data;

    // TODO create a double array for storage of the RGB Pixels
    public Image(int width, int height) {
        this.width = width;
        this.height = height;
        data = new double[width * height * 3];
    }

    // TODO store the RGB values of the pixel (i,j) in the image
    @Override
    public void setPixel(int i, int j, Color color) {
        if(i < 0 || i >= width || j < 0 || j >= height) return;
        int index = j * width + i;
        int rgb = index * 3;
        data[rgb + 0] = color.r();            
        data[rgb + 1] = color.g();            
        data[rgb + 2] = color.b();            
    }

    // TODO use ImageWriter.writePNG to implement this
    public void writePNG(String name) {
        System.out.format("Implement function `app.Image.writePng` to actually write image `%s`\n", name);
        ImageWriter.writePNG(name, data, width, height);
    }

    // TODO return the RGB color of a given pixel (i,j)
    @Override
    public Color getPixel(int i, int j) {
        return Color.black;
    }

    // TODO return the image width 
    @Override
    public int width() {
        return width;
    }

    // TODO return the image height 
    @Override
    public int height() {
        return height;
    }

}
