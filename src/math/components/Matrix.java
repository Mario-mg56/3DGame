package math.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import math.util.*;

public class Matrix {
    private double[][] matrix;

    public Matrix(int cols, int rows, List<Double> datos) {
        matrix = new double[rows][cols];
        if (datos != null) {
            setupearMatrix(datos);
        }
    }

    private void setupearMatrix(List<Double> datos) { // privado
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (!datos.isEmpty()) {
                    matrix[i][j] = datos.remove(0);
                }
            }
        }
    }

    public Point toPoint() {
        List<Double> resultado = new ArrayList<>();
        for (double[] fila : matrix) {
            for (double valor : fila) {
                resultado.add(valor);
            }
        }
        return new Point(resultado.get(0), resultado.get(1), resultado.get(2));
    }

    public Vector toVector() {
        List<Double> resultado = new ArrayList<>();
        for (double[] fila : matrix) {
            for (double valor : fila) {
                resultado.add(valor);
            }
        }
        return new Vector(resultado.get(0), resultado.get(1), resultado.get(2));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (double[] fila : matrix) {
            for (double valor : fila) {
                sb.append(valor).append(" | ");
            }
            sb.setLength(sb.length() - 3); // Elimina el último " | "
            sb.append("\n");
        }
        return sb.toString();
    }

    public Matrix getRotationMatrix(double ang_x, double ang_y, double ang_z) {
        double sin_x = Math.sin(ang_x), cos_x  = Math.cos(ang_x);
        double sin_y = Math.sin(ang_y), cos_y  = Math.cos(ang_y);
        double sin_z = Math.sin(ang_z), cos_z  = Math.cos(ang_z);
        //Rota en YXZ
        Double[] datos = {
                cos_y * cos_z, -sin_z * cos_y, sin_y,
                cos_x * sin_z + sin_x * sin_y * cos_z, cos_x * cos_z - sin_x * sin_y * sin_z, -sin_x * cos_y,
                sin_x * sin_z - cos_x * sin_y * cos_z, sin_x * cos_z + cos_x * sin_y * sin_z, cos_x * cos_y
        };
        List<Double> listaDatos = Arrays.asList(datos);
        return new Matrix(3, 3, listaDatos);
    }

    public double[][] getData(){return this.matrix;}

    public void redondear(int decimales) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = Math.round(matrix[i][j] * Math.pow(10, decimales)) / Math.pow(10, decimales);
            }
        }
    }

    public double[][] rotar(double anguloX, double anguloY, double anguloZ) {
        Matrix matrizRotacion = getRotationMatrix(Math.toRadians(anguloX), Math.toRadians(anguloY), Math.toRadians(anguloZ));
        this.matrix = Util.multiplicarMatrices(matrizRotacion, this.matrix);
        return this.matrix;
    }
}

