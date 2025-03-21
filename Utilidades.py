class Util:
    def redondear_matriz(matriz, decimales=5):
        return [[round(elemento, decimales) for elemento in fila] for fila in matriz]
    def multiplicar_matrices(matriz_A, matriz_B):
        filas_A = len(matriz_A)
        columnas_A = len(matriz_A[0])
        columnas_B = len(matriz_B[0])

        matriz_C = [[0 for _ in range(columnas_B)] for _ in range(filas_A)]
        for i in range(filas_A):
            for j in range(columnas_B):
                for k in range(columnas_A): 
                    matriz_C[i][j] += matriz_A[i][k] * matriz_B[k][j] 
        return matriz_C
    def rotacion (v,angulo_X=0,angulo_Y=0,angulo_Z=0):
        matriz_giro_x = [[1, 0, 0], [0, math.cos(angulo_X), -math.sin(angulo_X)], [0, math.sin(angulo_X), math.cos(angulo_X)]]
        matriz_giro_Y = [[math.cos(angulo_Y), 0, math.sin(angulo_Y)], [0, 1, 0], [-math.sin(angulo_Y), 0, math.cos(angulo_Y)]]
        matriz_giro_Z = [[math.cos(angulo_Z), -math.sin(angulo_Z), 0], [math.sin(angulo_Z), math.cos(angulo_Z), 0], [0, 0, 1]]



        matriz_giro_x = redondear_matriz(matriz_giro_x, 10)
        matriz_giro_Y = redondear_matriz(matriz_giro_Y, 10)
        matriz_giro_Z = redondear_matriz(matriz_giro_Z, 10)
        matriz_rotada = multiplicar_matrices(matriz_giro_Z, multiplicar_matrices(matriz_giro_Y, matriz_giro_x))
        r = multiplicar_matrices(matriz_rotada,v)
        return r
