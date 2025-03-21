import math

class Point:
    def __init__(self, x, y, z):
        self.x = x
        self.y = y
        self.z = z

    def __sub__(self, punto):
        return Vector(self.x-punto.x,self.y-punto.y,self.z-punto.z)

class Vector:
    def __init__(self, x, y, z):
        self.x = x
        self.y = y
        self.z = z
    
    def getMod(self): #Devuelve el módulo del vector
        return ((self.fin.x-self.inicio.x)**2 + (self.fin.y-self.inicio.y)**2 + (self.fin.z-self.inicio.z)**2)**0.5
    
    def normalize(self):
        x_normalized = self.x/self.getMod()
        y_normalized = self.y/self.getMod()
        z_normalized = self.z/self.getMod()
        return Vector(x_normalized,y_normalized,z_normalized) 

class Matrix:
    def __init__(self, x=0, y=0, z=0):
        self.matrix = [[[0 for _ in range(x)] for _ in range(y)] for _ in range(z)]

    def redondear(self, decimales=5):
        return [[round(elemento, decimales) for elemento in fila] for fila in self.matrix]
    
    def rotar(self, v, angulo_X=0, angulo_Y=0, angulo_Z=0):
        matriz_giro_X = [[1, 0, 0], [0, math.cos(angulo_X), -math.sin(angulo_X)], [0, math.sin(angulo_X), math.cos(angulo_X)]]
        matriz_giro_Y = [[math.cos(angulo_Y), 0, math.sin(angulo_Y)], [0, 1, 0], [-math.sin(angulo_Y), 0, math.cos(angulo_Y)]]
        matriz_giro_Z = [[math.cos(angulo_Z), -math.sin(angulo_Z), 0], [math.sin(angulo_Z), math.cos(angulo_Z), 0], [0, 0, 1]]

        matriz_giro_X = self.redondear_matriz(matriz_giro_X, 10)
        matriz_giro_Y = self.redondear_matriz(matriz_giro_Y, 10)
        matriz_giro_Z = self.redondear_matriz(matriz_giro_Z, 10)
        matriz_rotada = self.multiplicar_matrices(matriz_giro_Z, self.multiplicar_matrices(matriz_giro_Y, matriz_giro_X))
        r = self.multiplicar_matrices(matriz_rotada,v)
        return r

class Rect:
    def __init__(self, puntoA: Point, puntoB: Point):
        self.vDir = puntoA-puntoB
        #Ec paramétrica: (x = x1 + vDir.x*t), (y = y1 + vDir.y*t), (z = z1 + vDir.z*t)
        self.x = puntoA.x
        self.y = puntoA.y
        self.z = puntoA.z
    
    def getPoint(self, t=0):
        x = self.x + self.vdir.x*t
        y = self.y + self.vdir.y*t
        z = self.z + self.vdir.z*t
        return Point(x,y,z)
    
    
    def isContent(self, punto: Point):
        t1, t2, t3 = None, None, None
        
        if self.vDir.x != 0:
            t1 = (punto.x - self.x) / self.vDir.x
        elif self.x != punto.x:
            return False

        if self.vDir.y != 0:
            t2 = (punto.y - self.y) / self.vDir.y
        elif self.y != punto.y:
            return False

        if self.vDir.z != 0:
            t3 = (punto.z - self.z) / self.vDir.z
        elif self.z != punto.z:
            return False
        
        t = [t1,t2,t3]
        tArray = []
        for i in t : 
            if i is not None : 
                tArray.append(i)
        
        return len(set(tArray))==1


class Plane:
    def __init__(self, pA, pB, pC): # plano a partir de 3 puntos en el plano
        self.A = ((pB.y-pA.y)*(pC.z-pA.z)-(pB.z-pA.z)*(pC.y-pA.y))
        self.B = ((pB.z-pA.z)*(pC.x-pA.x)-(pB.x-pA.x)*(pC.z-pA.z))
        self.C = ((pB.x-pA.x)*(pC.y-pA.y)-(pB.y-pA.y)*(pC.x-pA.x))
        self.D = -(self.A*pA.x+self.B*pA.y+self.C*pA.z)

class Util:
    def createVector(point0, pointf):
        return Vector(point0.x - pointf.x, point0.y - pointf.y, point0.z - pointf.z)
    
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