package graphics;

import managers.GameManager;
import math.components.Point2;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Renderer {
    private long window;
    GameManager gm;

    public Renderer(int width, int height) {
        GameManager gm = GameManager.getInstance();

        GLFWErrorCallback.createPrint(System.err).set(); //Imprime los errores que puedan ocurrir al usar GLFW *1

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

        //glOrtho(0, 800, 600, 0, -1, 1); //Posiciona (0, 0) en la esquina superior izquierda
        glOrtho(-400, 400, -300, 300, -1, 1); //Posiciona (0, 0) en el centro de la ventana
    }

    public void update() {
            Draw.fill(Color.BLACK);
            draw();
            glfwSwapBuffers(window); //Mostrar frame
            glfwPollEvents(); //Procesar eventos
    }

    private void draw(){
        for (Point2 p : gm.info2d) {
            Draw.drawPoint(p, Color.BLUE);
        }

    }

    public void clean() { //Libera los recursos
        // Libera todos los callbacks asociados a esa ventana (teclado, mouse, scroll, etc.)
        glfwFreeCallbacks(window);
        // Destruye la ventana GLFW (la borra de la pantalla y libera su memoria)
        glfwDestroyWindow(window);
        // Libera todo lo que GLFW haya reservado a nivel global
        glfwTerminate();
        //Libera la memoria del callback *1
        glfwSetErrorCallback(null).free();
    }

    public long getWindow() {return window;}
}
