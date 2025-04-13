package graphics;

import math.components.Point2;

import java.util.ArrayList;
import org.lwjgl.opengl.*;
import static org.lwjgl.opengl.GL11.*;

public class Draw {
    public static void fill(Color color) {
        GL11.glClearColor(color.r, color.g, color.b, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    public static void drawPoint(Point2 point, Color color) {
        glColor3f(color.r, color.g, color.b);
        glBegin(GL_POINTS);
        glVertex2f(point.x, point.y);
        glEnd();
    }
    public static void drawPoint(Point2 point, Float[] rgb) {
        glColor3f(rgb[0], rgb[1], rgb[2]);
        glBegin(GL_POINTS);
        glVertex2f(point.x, point.y);
        glEnd();
    }

    public static void drawLine(Point2 start, Point2 end, Color color) {
        glColor3f(color.r, color.g, color.b);
        glBegin(GL_LINES);
        glVertex2f(start.x, start.y);
        glVertex2f(end.x, end.y);
        glEnd();
    }
    public static void drawLine(Point2 start, Point2 end, Float[] rgb) {
        glColor3f(rgb[0], rgb[1], rgb[2]);
        glBegin(GL_LINES);
        glVertex2f(start.x, start.y);
        glVertex2f(end.x, end.y);
        glEnd();
    }

    public static void drawPoly(ArrayList<Point2> points, Color color) {
        glColor3f(color.r, color.g, color.b);
        glBegin(GL_POLYGON);
        for (Point2 p : points) {glVertex2f(p.x, p.y);}
        glEnd();
    }
    public static void drawPoly(ArrayList<Point2> points, Float[] rgb) {
        glColor3f(rgb[0], rgb[1], rgb[2]);
        glBegin(GL_POLYGON);
        for (Point2 p : points) {glVertex2f(p.x, p.y);}
        glEnd();
    }
}
