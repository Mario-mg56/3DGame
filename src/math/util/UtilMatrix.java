package math.util;

import math.components.Matrix;

import java.util.Arrays;
import java.util.List;

public class UtilMatrix {

//    def multiplicar_matrices(matriz_A, matriz_B):
//    filas_A = len(matriz_A)
//    columnas_A = len(matriz_A[0])
//    columnas_B = len(matriz_B[0])
//
//    matriz_C = [[0 for _ in range(columnas_B)] for _ in range(filas_A)]
//            for i in range(filas_A):
//            for j in range(columnas_B):
//            for k in range(columnas_A):
//    matriz_C[i][j] += matriz_A[i][k] * matriz_B[k][j]
//            return Matrix(len(matriz_C),len(matriz_C[0]),matriz_C)

    public static Matrix multiplicarMatrices(Matrix ma, Matrix mb) {
        int filas_ma = ma.getData().length;
        int cols_ma = ma.getData()[0].length;
        int cols_mb = mb.getData()[0].length;

        double[][] mc = new double[cols_mb][filas_ma];
        for (int i = 0; i < filas_ma; i++) {
            for (int j = 0; j < cols_mb; j++) {
                for (int k = 0; k < cols_ma; k++) {
                    mc[i][j] += ma.getData()[i][k] * mb.getData()[k][j];
                }
            }
        }

        return Matrix(mc.length, mc[0].length, mc);
    }
}
