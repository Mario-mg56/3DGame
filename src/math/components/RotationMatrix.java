//package math.components;
//
//import java.util.List;
//import java.util.Arrays;
//
//public class RotationMatrix extends Matrix {
//    public RotationMatrix(double ang_x, double ang_y, double ang_z) {
//        double sin_x = Math.sin(ang_x), cos_x  = Math.cos(ang_x);
//        double sin_y = Math.sin(ang_y), cos_y  = Math.cos(ang_y);
//        double sin_z = Math.sin(ang_z), cos_z  = Math.cos(ang_z);
//        //Rota en YXZ
//        Double[] datos = {
//                cos_y * cos_z, -sin_z * cos_y, sin_y,
//                cos_x * sin_z + sin_x * sin_y * cos_z, cos_x * cos_z - sin_x * sin_y * sin_z, -sin_x * cos_y,
//                sin_x * sin_z - cos_x * sin_y * cos_z, sin_x * cos_z + cos_x * sin_y * sin_z, cos_x * cos_y
//        };
//        List<Double> listaDatos = Arrays.asList(datos);
//        super(3, 3, listaDatos);
//    }
//}
