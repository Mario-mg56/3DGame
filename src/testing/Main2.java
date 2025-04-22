package testing;

import managers.GameManager;
import math.components.Point;
import math.components.Point2;
import math.util.Utilities;
import objects.*;

public class Main2 {
    public static void main(String[] args) {
        GameManager gm = GameManager.getInstance();
        gm.play();
    }
}
