package bg.sofia.uni.fmi.mjt.imagekit.algorithm.detection;

import bg.sofia.uni.fmi.mjt.imagekit.algorithm.ImageAlgorithm;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SobelEdgeDetectionTest {

    private ImageAlgorithm imageAlgorithm = mock(ImageAlgorithm.class);
    private SobelEdgeDetection sobelEdgeDetection = new SobelEdgeDetection(imageAlgorithm);

    @Test
    public void testProcessNullImage() {
        assertThrows(IllegalArgumentException.class, () -> sobelEdgeDetection.process(null), "Image cannot be null");
    }

    @Test
    public void testProcessImage() {
        BufferedImage inputImage = new BufferedImage(3, 3, BufferedImage.TYPE_INT_RGB);
        inputImage.setRGB(0, 0, new Color(255, 255, 255).getRGB());
        inputImage.setRGB(1, 0, new Color(255, 255, 255).getRGB());
        inputImage.setRGB(2, 0, new Color(255, 255, 255).getRGB());
        inputImage.setRGB(0, 1, new Color(255, 255, 255).getRGB());
        inputImage.setRGB(1, 1, new Color(0, 0, 0).getRGB());
        inputImage.setRGB(2, 1, new Color(255, 255, 255).getRGB());
        inputImage.setRGB(0, 2, new Color(255, 255, 255).getRGB());
        inputImage.setRGB(1, 2, new Color(255, 255, 255).getRGB());
        inputImage.setRGB(2, 2, new Color(255, 255, 255).getRGB());

        when(imageAlgorithm.process(any(BufferedImage.class))).thenReturn(inputImage);

        SobelEdgeDetection algorithm = new SobelEdgeDetection(imageAlgorithm);
        BufferedImage outputImage = algorithm.process(inputImage);

        assertNotNull(outputImage, "Output image should not be null");
        assertEquals(3, outputImage.getWidth(), "Output image width should be 3");
        assertEquals(3, outputImage.getHeight(), "Output image height should be 3");

        int expectedMagnitude = 255;
        int rgb = outputImage.getRGB(1, 1);
        assertEquals(new Color(expectedMagnitude, expectedMagnitude, expectedMagnitude).getRGB(), outputImage.getRGB(1, 1));
    }
    
}
