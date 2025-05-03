package math.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import math.util.*;

public class Matrix implements Operable<Matrix> {
    public double[][] matrix;

    public Matrix(double[][] datos){
        this.matrix = datos;
    }
    public Matrix(int cols, int rows, ArrayList<Double> datos) {
        matrix = new double[rows][cols];
        setupear_matrix(datos);
    }
    public Matrix(int cols, int rows, double[] datos) {
        matrix = new double[rows][cols];
        ArrayList<Double> new_datos = new ArrayList<>();
        for (double d : datos) {
            new_datos.add(d);
        }
        setupear_matrix(new_datos);
    }
    public void setupear_matrix(ArrayList<Double> datos){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                this.matrix[i][j] = datos.remove(0);
            }
        }
    }
    public Matrix add(Matrix matrix){
        ArrayList<Double> datos = new ArrayList<>();
        for (int i = 0; i < this.matrix.length; i++) {
            datos.add(this.toDatos().get(i) + matrix.toDatos().get(i));
        }
        return new Matrix(this.matrix.length,this.matrix[0].length,datos);
    }
    public Matrix subtract(Matrix matrix){
        ArrayList<Double> datos = new ArrayList<>();
        for (int i = 0; i < this.matrix.length; i++) {
            datos.add(this.toDatos().get(i) - matrix.toDatos().get(i));
        }
        return new Matrix(this.matrix.length,this.matrix[0].length,datos);
    }

    public Matrix divide(Matrix other) {
        return null;
    }
    public void divideS(Matrix other) {
    }
    public Matrix multiply(Matrix other) {
        return null;
    }
    public void multiplyS(Matrix other) {
    }
    public void addS(Matrix other) {
    }
    public void subtractS(Matrix other) {
    }

    public ArrayList<Double> toDatos(){
        ArrayList<Double> datos = new ArrayList<>();
        for (double[] d : matrix) {
            for (double dd : d) {
                datos.add(dd);
            }
        }
        return datos;
    }
    public Point toPoint() {
        ArrayList<Double> datos = toDatos();
        return new Point(datos.get(0),datos.get(1),datos.get(2));
    }
    public Vector toVector() {
        ArrayList<Double> datos = toDatos();
        return new Vector(datos.get(0),datos.get(1),datos.get(2));
    }
    public double[][] getData() {
        return matrix;
    }

    @Override
    public String toString() {
        String res = "";
        ArrayList<Double> datos = toDatos();
        for (double d : datos) {
            res += d + "\t";
        }
        return res;
    }
    public Matrix rotar(double angulo_X,double angulo_Y,double angulo_Z){
        Matrix m = UtilMatrix.multiplicarMatrices(UtilMatrix.getRotationMatrix(Math.toRadians(angulo_X), Math.toRadians(angulo_Y), Math.toRadians(angulo_Z)),this);
        return m;
    }
}

