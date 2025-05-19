package graphics;

import math.components.Point2;

import java.util.ArrayList;
import org.lwjgl.opengl.*;
import static org.lwjgl.opengl.GL11.*;

public class Draw {
    public static void fill(Color color) {
        GL11.glClearColor(color.r/255, color.g/255, color.b/255, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    public static void drawPoint(Point2 point, Color color) {
        glPointSize(5);
        glColor3f(color.r/255, color.g/255, color.b/255);
        glBegin(GL_POINTS);
            glVertex2f(point.x, point.y);
        glEnd();
        glColor3f(1f, 1f, 1f); //Restablezco el siguiente color a un color neutral
    }
    public static void drawPoint(Point2 point, Float[] rgb) {
        glPointSize(5);
        glColor3f(rgb[0]/255, rgb[1]/255, rgb[2]/255);
        glBegin(GL_POINTS);
            glVertex2f(point.x, point.y);
        glEnd();
        glColor3f(1f, 1f, 1f); //Restablezco el siguiente color a un color neutral
    }

    public static void drawLine(Point2 start, Point2 end, Color color) {
        glLineWidth(5);
        glColor3f(color.r/255, color.g/255, color.b/255);
        glBegin(GL_LINES);
            glVertex2f(start.x, start.y);
            glVertex2f(end.x, end.y);
        glEnd();
        glColor3f(1f, 1f, 1f); //Restablezco el siguiente color a un color neutral
    }
    public static void drawLine(Point2 start, Point2 end, Float[] rgb) {
        glLineWidth(5);
        glColor3f(rgb[0]/255, rgb[1]/255, rgb[2]/255);
        glBegin(GL_LINES);
            glVertex2f(start.x, start.y);
            glVertex2f(end.x, end.y);
        glEnd();
        glColor3f(1f, 1f, 1f); //Restablezco el siguiente color a un color neutral
    }

    //Importante puntos en sentido antihorario empezando por arriba a la izq
    public static void drawPoly(ArrayList<Point2> points, Color color) {
        glColor3f(color.r/255, color.g/255, color.b/255);
        glBegin(GL_POLYGON);
            for (Point2 p : points) {glVertex2f(p.x, p.y);}
        glEnd();
        glColor3f(1f, 1f, 1f); //Restablezco el siguiente color a un color neutral
    }
    public static void drawPoly(ArrayList<Point2> points, Float[] rgb) {
        glColor3f(rgb[0]/255, rgb[1]/255, rgb[2]/255);
        glBegin(GL_POLYGON);
            for (Point2 p : points) {glVertex2f(p.x, p.y);}
        glEnd();
        glColor3f(1f, 1f, 1f); //Restablezco el siguiente color a un color neutral
    }
}
