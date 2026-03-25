package bg.sofia.uni.fmi.mjt.imagekit.filesystem;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LocalFileSystemImageManager implements FileSystemImageManager {

    public LocalFileSystemImageManager() {
    }

    @Override
    public BufferedImage loadImage(File imageFile) throws IOException {
        if (imageFile == null) {
            throw new IllegalArgumentException("The file is null");
        } else if (!imageFile.exists()) {
            throw new IOException("The file does not exist");
        } else if (!imageFile.isFile()) {
            throw new IOException("The file is not a regular file");
        }

        String name = imageFile.getName();
        if (!name.endsWith(".jpg") && !name.endsWith(".png") && !name.endsWith(".bmp")) {
            throw new IOException("File format is not supported");
        }

        BufferedImage image = ImageIO.read(imageFile);

        if (image == null) {
            throw new IOException("Failed to read the image");
        }

        return image;
    }

    @Override
    public List<BufferedImage> loadImagesFromDirectory(File imagesDirectory) throws IOException {
        if (imagesDirectory == null) {
            throw new IllegalArgumentException("The directory is null");
        } else if (!imagesDirectory.exists()) {
            throw new IOException("The directory does not exist");
        } else if (!imagesDirectory.isDirectory()) {
            throw new IOException("The directory is not a directory");
        }

        List<BufferedImage> images = new ArrayList<>();

        File[] files = imagesDirectory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    images.addAll(loadImagesFromDirectory(file));
                } else if (file.isFile()) {
                    images.add(loadImage(file));
                }
            }
        }

        return images;
    }

    @Override
    public void saveImage(BufferedImage image, File imageFile) throws IOException {
        if (image == null) {
            throw new IllegalArgumentException("The image is null");
        } else if (imageFile == null) {
            throw new IllegalArgumentException("The file is null");
        } else if (imageFile.exists()) {
            throw new IOException("The file already exists");
        } else if (!imageFile.getParentFile().exists()) {
            throw new IOException("The parent directory does not exist");
        }

        String name = imageFile.getName();
        String format;

        if (name.endsWith(".jpg")) {
            format = "jpg";
        } else if (name.endsWith(".png")) {
            format = "png";
        } else if (name.endsWith(".bmp")) {
            format = "bmp";
        } else {
            throw new IOException("File format is not supported");
        }

        ImageIO.write(image, format, imageFile);
    }
    
}
