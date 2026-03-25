package bg.sofia.uni.fmi.mjt.imagekit.algorithm.grayscale;

import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LuminosityGrayscaleTest {

    @Test
    public void testProcessNullImage() {
        LuminosityGrayscale algorithm = new LuminosityGrayscale();
        assertThrows(IllegalArgumentException.class, () -> algorithm.process(null), "Image cannot be null");
    }

    @Test
    public void testProcessImage() {
        LuminosityGrayscale algorithm = new LuminosityGrayscale();

        BufferedImage inputImage = new BufferedImage(2, 2, BufferedImage.TYPE_INT_RGB);
        inputImage.setRGB(0, 0, new Color(255, 0, 0).getRGB()); // Red
        inputImage.setRGB(1, 0, new Color(0, 255, 0).getRGB()); // Green
        inputImage.setRGB(0, 1, new Color(0, 0, 255).getRGB()); // Blue
        inputImage.setRGB(1, 1, new Color(255, 255, 255).getRGB()); // White

        BufferedImage outputImage = algorithm.process(inputImage);

        assertNotNull(outputImage, "Output image should not be null");
        assertEquals(2, outputImage.getWidth(), "Output image width should be 2");
        assertEquals(2, outputImage.getHeight(), "Output image height should be 2");

        int expectedRedLuminosity = (int) (0.21 * 255 + 0.72 * 0 + 0.07 * 0);
        int expectedGreenLuminosity = (int) (0.21 * 0 + 0.72 * 255 + 0.07 * 0);
        int expectedBlueLuminosity = (int) (0.21 * 0 + 0.72 * 0 + 0.07 * 255);
        int expectedWhiteLuminosity = (int) (0.21 * 255 + 0.72 * 255 + 0.07 * 255);

        assertEquals(new Color(expectedRedLuminosity, expectedRedLuminosity, expectedRedLuminosity).getRGB(), outputImage.getRGB(0, 0));
        assertEquals(new Color(expectedGreenLuminosity, expectedGreenLuminosity, expectedGreenLuminosity).getRGB(), outputImage.getRGB(1, 0));
        assertEquals(new Color(expectedBlueLuminosity, expectedBlueLuminosity, expectedBlueLuminosity).getRGB(), outputImage.getRGB(0, 1));
        assertEquals(new Color(expectedWhiteLuminosity, expectedWhiteLuminosity, expectedWhiteLuminosity).getRGB(), outputImage.getRGB(1, 1));
    }
    
}
