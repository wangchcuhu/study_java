package Operator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @program: Java_study
 * @description:
 * @author: liu yan
 * @create: 2021-12-06 16:57
 */
public class Test {
    BufferedImage bufferedImage;

    {
        try {
            bufferedImage = ImageIO.read(Files.newInputStream(Paths.get("D:")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
