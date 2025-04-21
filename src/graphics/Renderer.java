package graphics;

import math.components.Point;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Renderer {
    private long window;

    public Renderer(int width, int height) {
        GLFWErrorCallback.createPrint(System.err).set(); //Imprime los errores que puedan ocurrir al usar GLFW

        if (!glfwInit()) //Inicializa la librería
            throw new IllegalStateException("No se pudo inicializar GLFW");

        //Crea una ventana de las dimensiones y título especificados
        this.window = glfwCreateWindow(width, height, "Title", NULL, NULL);

        if (window == NULL)
            throw new RuntimeException("No se pudo crear la ventana");

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1); // VSync
        glfwShowWindow(window);
        GL.createCapabilities(); // Habilita OpenGL
    }

    public void start() {
        loop();
        // Limpieza
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void loop() {
        //glOrtho(0, 800, 600, 0, -1, 1); //Posiciona (0, 0) en la esquina superior izquierda
        glOrtho(-400, 400, -300, 300, -1, 1); //Posiciona (0, 0) en el centro de la ventana

        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            draw();

            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }

    public void draw(){

    }

    public long getWindow() {return window;}
}
