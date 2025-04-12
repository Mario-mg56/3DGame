package testing;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class GfxTest {
    public long window;

    public void run() {
        init();
        loop();

        // Limpieza
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void init() {
        GLFWErrorCallback.createPrint(System.err).set(); //Imprime los errores que puedan ocurrir al usar GLFW

        if (!glfwInit()) //Inicializa la librería
            throw new IllegalStateException("No se pudo inicializar GLFW");

        //Crea una ventana de las dimensiones y título especificados
        window = glfwCreateWindow(800, 600, "Tittle", NULL, NULL);

        if (window == NULL)
            throw new RuntimeException("No se pudo crear la ventana");

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1); // VSync
        glfwShowWindow(window);

        GL.createCapabilities(); // Habilita OpenGL
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
        glColor3f(1.0f, 0.0f, 0.0f); // Rojo: R=1, G=0, B=0

        glBegin(GL_POLYGON); // Dibujar un cuadrado
        glVertex2f(100, 100); // Vértice inferior izquierdo
        glVertex2f(200, 100); // Vértice inferior derecho
        glVertex2f(200, 200); // Vértice superior derecho
        glVertex2f(100, 200); // Vértice superior izquierdo
        glEnd();
    }

    public static void main(String[] args) {
        new GfxTest().run();
    }
}
