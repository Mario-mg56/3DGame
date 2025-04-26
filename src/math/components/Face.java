package math.components;

import math.components.Triangule;

import java.awt.*;

public class Face extends Triangule{
    private TexturePaint texture;
    public Face(Point p1, Point p2, Point p3,TexturePaint texture) {
        super(p1, p2, p3);
        this.texture = texture;
    }
}
