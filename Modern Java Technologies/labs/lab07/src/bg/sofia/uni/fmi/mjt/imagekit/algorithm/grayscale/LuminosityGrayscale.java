package bg.sofia.uni.fmi.mjt.imagekit.algorithm.grayscale;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class LuminosityGrayscale implements GrayscaleAlgorithm {

    private static final double RED_LUMINOSITY_COEFFICIENT = 0.21;
    private static final double GREEN_LUMINOSITY_COEFFICIENT = 0.72;
    private static final double BLUE_LUMINOSITY_COEFFICIENT = 0.07;

    public LuminosityGrayscale() {
    }

    @Override
    public BufferedImage process(BufferedImage image) {
        if (image == null) {
            throw new IllegalArgumentException("Image cannot be null");
        }

        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgb = image.getRGB(x, y);

                Color color = new Color(rgb);
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                int luminosity = (int) (RED_LUMINOSITY_COEFFICIENT * red + GREEN_LUMINOSITY_COEFFICIENT * green +
                    BLUE_LUMINOSITY_COEFFICIENT * blue);

                Color resultColor = new Color(luminosity, luminosity, luminosity);
                bufferedImage.setRGB(x, y, resultColor.getRGB());
            }
        }

        return bufferedImage;
    }
    
}
