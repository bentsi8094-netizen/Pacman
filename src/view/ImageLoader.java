package view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImageLoader {
    private static Map<String, BufferedImage> images = new HashMap<>();

    public static void loadAllResources() {
        try {
            put("wall", "src/image/woll.png");
            put("grass", "src/image/grass.png");
            put("iron", "src/image/airon.png");

            put("pacman_up_open", "src/image/openUp.png");
            put("pacman_up_close", "src/image/closeUp.png");
            put("pacman_down_open", "src/image/openDown.png");
            put("pacman_down_close", "src/image/closeDown.png");
            put("pacman_left_open", "src/image/openLeft.png");
            put("pacman_left_close", "src/image/closeLeft.png");
            put("pacman_right_open", "src/image/openRight.png");
            put("pacman_right_close", "src/image/closeRight.png");

            put("blinky_up", "src/image/blinkyUp.png");
            put("blinky_down", "src/image/blinkyDown.png");
            put("blinky_left", "src/image/blinkyLeft.png");
            put("blinky_right", "src/image/blinkyRight.png");

            put("pinky_up", "src/image/pinkyUp.png");
            put("pinky_down", "src/image/pinkyDown.png");
            put("pinky_left", "src/image/pinkyLeft.png");
            put("pinky_right", "src/image/pinkyRight.png");

            put("inky_up", "src/image/inkyUp.png");
            put("inky_down", "src/image/inkyDown.png");
            put("inky_left", "src/image/inkyLeft.png");
            put("inky_right", "src/image/inkyRight.png");

            put("clyde_up", "src/image/clydeUp.png");
            put("clyde_down", "src/image/clydeDown.png");
            put("clyde_left", "src/image/clydeLeft.png");
            put("clyde_right", "src/image/clydeRight.png");

        } catch (IOException e) {
            System.err.println("Error: Image files missing!");
            e.printStackTrace();
        }
    }

    private static void put(String key, String path) throws IOException {
        File file = new File(path);
        if(file.exists()) {
            images.put(key, ImageIO.read(file));
        } else {
            System.err.println("Missing file: " + path);
        }
    }

    public static BufferedImage get(String key) {
        return images.get(key);
    }
}