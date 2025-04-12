package graphics;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class Screen {
    int width, height;
    long Screen;

    public Screen() {
        width = 960;
        height = 540;
        Screen = GLFW.glfwCreateWindow(width, height, "", 0, 0);
    }
}
