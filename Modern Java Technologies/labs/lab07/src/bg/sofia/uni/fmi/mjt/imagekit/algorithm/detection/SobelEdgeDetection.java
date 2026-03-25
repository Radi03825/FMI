package bg.sofia.uni.fmi.mjt.imagekit.algorithm.detection;

import bg.sofia.uni.fmi.mjt.imagekit.algorithm.ImageAlgorithm;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class SobelEdgeDetection implements EdgeDetectionAlgorithm {

    private static final int[][] SOBEL_X = {
        {-1, 0, 1},
        {-2, 0, 2},
        {-1, 0, 1}
    };

    private static final int[][] SOBEL_Y = {
        {-1, -2, -1},
        {0, 0, 0},
        {1, 2, 1}
    };

    private static final int MAX_MAGNITUDE = 255;

    private final ImageAlgorithm grayscaleAlgorithm;

    public SobelEdgeDetection(ImageAlgorithm grayscaleAlgorithm) {
        this.grayscaleAlgorithm = grayscaleAlgorithm;
    }

    @Override
    public BufferedImage process(BufferedImage image) {
        if (image == null) {
            throw new IllegalArgumentException("Image cannot be null");
        }

        BufferedImage grayscaleImage = grayscaleAlgorithm.process(image);

        int width = grayscaleImage.getWidth();
        int height = grayscaleImage.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 1; x < width - 1; x++) {
            for (int y = 1; y < height - 1; y++) {
                int magnitude = calculateSobelMagnitude(grayscaleImage, x, y);
                Color resultColor = new Color(magnitude, magnitude, magnitude);
                bufferedImage.setRGB(x, y, resultColor.getRGB());
            }
        }

        return bufferedImage;
    }

    private int calculateSobelMagnitude(BufferedImage image, int x, int y) {
        int gx = 0;
        int gy = 0;

        for (int kx = -1; kx <= 1; kx++) {
            for (int ky = -1; ky <= 1; ky++) {
                int rgb = image.getRGB(x + kx, y + ky);
                Color color = new Color(rgb);

                int gray = color.getRed();
                gx += gray * SOBEL_X[kx + 1][ky + 1];
                gy += gray * SOBEL_Y[kx + 1][ky + 1];
            }
        }

        int magnitude = (int) Math.sqrt(gx * gx + gy * gy);
        return Math.min(MAX_MAGNITUDE, magnitude);
    }

}
