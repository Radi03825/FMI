package bg.sofia.uni.fmi.mjt.imagekit.filesystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LocalFileSystemImageManagerTest {

    private LocalFileSystemImageManager manager;
    private File mockFile;
    private BufferedImage mockImage;

    @BeforeEach
    public void setUp() {
        manager = new LocalFileSystemImageManager();
        mockFile = mock(File.class);
        mockImage = mock(BufferedImage.class);
    }

    @Test
    public void testLoadImageWithNullFile() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            manager.loadImage(null);
        });
        assertEquals("The file is null", exception.getMessage());
    }

    @Test
    public void testLoadImageWithNonExistentFile() throws IOException {
        when(mockFile.exists()).thenReturn(false);
        IOException exception = assertThrows(IOException.class, () -> {
            manager.loadImage(mockFile);
        });
        assertEquals("The file does not exist", exception.getMessage());
    }

    @Test
    public void testLoadImageWithNonRegularFile() throws IOException {
        when(mockFile.exists()).thenReturn(true);
        when(mockFile.isFile()).thenReturn(false);
        IOException exception = assertThrows(IOException.class, () -> {
            manager.loadImage(mockFile);
        });
        assertEquals("The file is not a regular file", exception.getMessage());
    }

    @Test
    public void testLoadImageWithUnsupportedFormat() throws IOException {
        when(mockFile.exists()).thenReturn(true);
        when(mockFile.isFile()).thenReturn(true);
        when(mockFile.getName()).thenReturn("image.gif");
        IOException exception = assertThrows(IOException.class, () -> {
            manager.loadImage(mockFile);
        });
        assertEquals("File format is not supported", exception.getMessage());
    }

    @Test
    public void testLoadImageWithValidFile() throws IOException {
        when(mockFile.exists()).thenReturn(true);
        when(mockFile.isFile()).thenReturn(true);
        when(mockFile.getName()).thenReturn("image.jpg");
        when(ImageIO.read(mockFile)).thenReturn(mockImage);

        BufferedImage result = manager.loadImage(mockFile);
        assertEquals(mockImage, result);
    }

    @Test
    public void testLoadImagesFromDirectoryWithNullDirectory() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            manager.loadImagesFromDirectory(null);
        });
        assertEquals("The directory is null", exception.getMessage());
    }

    @Test
    public void testLoadImagesFromDirectoryWithNonExistentDirectory() throws IOException {
        when(mockFile.exists()).thenReturn(false);
        IOException exception = assertThrows(IOException.class, () -> {
            manager.loadImagesFromDirectory(mockFile);
        });
        assertEquals("The directory does not exist", exception.getMessage());
    }

    @Test
    public void testLoadImagesFromDirectoryWithNonDirectory() throws IOException {
        when(mockFile.exists()).thenReturn(true);
        when(mockFile.isDirectory()).thenReturn(false);
        IOException exception = assertThrows(IOException.class, () -> {
            manager.loadImagesFromDirectory(mockFile);
        });
        assertEquals("The directory is not a directory", exception.getMessage());
    }

    @Test
    public void testLoadImagesFromDirectoryWithValidDirectory() throws IOException {
        File mockDirectory = mock(File.class);
        when(mockDirectory.exists()).thenReturn(true);
        when(mockDirectory.isDirectory()).thenReturn(true);

        File mockImageFile = mock(File.class);
        when(mockImageFile.exists()).thenReturn(true);
        when(mockImageFile.isFile()).thenReturn(true);
        when(mockImageFile.getName()).thenReturn("image.jpg");

        when(mockDirectory.listFiles()).thenReturn(new File[]{mockImageFile});

        BufferedImage mockImage = mock(BufferedImage.class);
        mockStatic(ImageIO.class);
        when(ImageIO.read(mockImageFile)).thenReturn(mockImage);

        assertEquals(1, manager.loadImagesFromDirectory(mockDirectory).size(),
            "Valid directory with one valid image should load successfully");
    }

    @Test
    public void testSaveImageWithNullImage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            manager.saveImage(null, mockFile);
        });
        assertEquals("The image is null", exception.getMessage());
    }

    @Test
    public void testSaveImageWithNullFile() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            manager.saveImage(mockImage, null);
        });
        assertEquals("The file is null", exception.getMessage());
    }

    @Test
    public void testSaveImageWithExistingFile() throws IOException {
        when(mockFile.exists()).thenReturn(true);
        IOException exception = assertThrows(IOException.class, () -> {
            manager.saveImage(mockImage, mockFile);
        });
        assertEquals("The file already exists", exception.getMessage());
    }

    @Test
    public void testSaveImageWithNonExistentParentDirectory() throws IOException {
        when(mockFile.exists()).thenReturn(false);
        when(mockFile.getParentFile()).thenReturn(mock(File.class));
        when(mockFile.getParentFile().exists()).thenReturn(false);
        IOException exception = assertThrows(IOException.class, () -> {
            manager.saveImage(mockImage, mockFile);
        });
        assertEquals("The parent directory does not exist", exception.getMessage());
    }

    @Test
    public void testSaveImageWithUnsupportedFormat() throws IOException {
        when(mockFile.exists()).thenReturn(false);
        when(mockFile.getParentFile()).thenReturn(mock(File.class));
        when(mockFile.getParentFile().exists()).thenReturn(true);
        when(mockFile.getName()).thenReturn("image.gif");
        IOException exception = assertThrows(IOException.class, () -> {
            manager.saveImage(mockImage, mockFile);
        });
        assertEquals("File format is not supported", exception.getMessage());
    }

    @Test
    public void testSaveImageWithValidFile() throws IOException {
        when(mockFile.exists()).thenReturn(false);
        when(mockFile.getParentFile()).thenReturn(mock(File.class));
        when(mockFile.getParentFile().exists()).thenReturn(true);
        when(mockFile.getName()).thenReturn("image.jpg");

        manager.saveImage(mockImage, mockFile);
        verify(ImageIO.class);
        ImageIO.write(mockImage, "jpg", mockFile);
    }
    
}
