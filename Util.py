import math, pygame as pg

class Point:
    def __init__(self, x, y, z):
        self.x = x
        self.y = y
        self.z = z

    def toMatrix(self):
        return Matrix(1,3, [self.x, self.y, self.z])
        
    def __sub__(self, punto):
        return Point(self.x-punto.x,self.y-punto.y,self.z-punto.z)
    
    def __add__(self, punto):
        return Point(self.x+punto.x,self.y+punto.y,self.z+punto.z)
    
    def __str__(self):
        return f"x:{self.x} y:{self.y} z:{self.z}" 
    
    def rotar(self,angulo_x,angulo_y,angulo_z):
        p = self.toMatrix().rotar(angulo_x,angulo_y,angulo_z).toPoint()
        self.x = p.x
        self.y = p.y
        self.z = p.z
    


class Point2D:
    def __init__(self, x, y):
        self.x = x
        self.y = y

    def __sub__(self, punto):
        return Vector(self.x-punto.x,self.y-punto.y, 0)

    def __str__(self):
        return f"x:{self.x} y:{self.y}" 

class Vector:
    def __init__(self, x, y, z):
        self.x = x
        self.y = y
        self.z = z
        
    def toMatrix(self):
        return Matrix(1,3, [self.x, self.y, self.z])
    
    def getMod(self): #Devuelve el módulo del vector
        return ((self.fin.x-self.inicio.x)**2 + (self.fin.y-self.inicio.y)**2 + (self.fin.z-self.inicio.z)**2)**0.5
    
    def normalize(self):
        x_normalized = self.x/self.getMod()
        y_normalized = self.y/self.getMod()
        z_normalized = self.z/self.getMod()
        return Vector(x_normalized,y_normalized,z_normalized) 

    def rotar(self, angulo_x, angulo_y, angulo_z):
        v = self.toMatrix().rotar(angulo_x,angulo_y,angulo_z).toVector()
        self.x = v.x
        self.y = v.y
        self.z = v.z
    

class Matrix:
    def __init__(self, cols,rows, datos=None):
        self.matrix = [[0 for _ in range(cols)] for _ in range(rows)]
        if(datos!=None):
            self.setupear_matrix(datos)
            
    def setupear_matrix(self, datos): #privado
        for i in range(len(self.matrix)):
            for j in range(len(self.matrix[0])):
                if(self.matrix[i][j] != None):
                    next
                self.matrix[i][j] = datos.pop(0)
    
    def toPoint(self):
        datos = self.matrix[0]
        #Fuerza bruta reescribir
        resultado = []
        for i in datos:
            for j in i:
                resultado.append(j)
        
        return Point(resultado[0],resultado[1],resultado[2])
    
    def toVector(self):
        datos = self.matrix[0]
        #Fuerza bruta reescribir
        resultado = []
        for i in datos:
            for j in i:
                resultado.append(j)
        
        return Vector(resultado[0],resultado[1],resultado[2])
    
    def __str__(self):
        return "\n".join([" | ".join(map(str, fila)) for fila in self.matrix])
    
    def redondear(self, decimales=5):
        return [[round(elemento, decimales) for elemento in fila] for fila in self.matrix]
    
    def rotar(self, angulo_X=0, angulo_Y=0, angulo_Z=0): 
        matriz_rotacion = Rotation_matrix(math.radians(angulo_X),math.radians(angulo_Y),math.radians(angulo_Z))
        # matriz_giro_X = [[1, 0, 0], [0, math.cos(angulo_X), -math.sin(angulo_X)], [0, math.sin(angulo_X), math.cos(angulo_X)]]
        # matriz_giro_Y = [[math.cos(angulo_Y), 0, math.sin(angulo_Y)], [0, 1, 0], [-math.sin(angulo_Y), 0, math.cos(angulo_Y)]]
        # matriz_giro_Z = [[math.cos(angulo_Z), -math.sin(angulo_Z), 0], [math.sin(angulo_Z), math.cos(angulo_Z), 0], [0, 0, 1]]
        # matriz_giro_X = self.redondear_matriz(matriz_giro_X, 10)
        # matriz_giro_Y = self.redondear_matriz(matriz_giro_Y, 10)
        # matriz_giro_Z = self.redondear_matriz(matriz_giro_Z, 10)
        self.matrix = Util.multiplicar_matrices(matriz_rotacion.matrix,self.matrix)
        return self.matrix
    
class Rotation_matrix(Matrix):
    def __init__(self,angulo_x,angulo_y,angulo_z):
        cosX, sinX = math.cos(angulo_x), math.sin(angulo_x)
        print(cosX,sinX,)
        cosY, sinY = math.cos(angulo_y), math.sin(angulo_y)
        cosZ, sinZ = math.cos(angulo_z), math.sin(angulo_z)
        datos=[cosY * cosZ, -sinZ * cosY, sinY,
                cosX * sinZ + sinX * sinY * cosZ, cosX * cosZ - sinX * sinY * sinZ, -sinX * cosY,
                sinX * sinZ - cosX * sinY * cosZ, sinX * cosZ + cosX * sinY * sinZ, cosX * cosY]
        super().__init__(3, 3, datos)

        
# class Perspective_matrix(Matrix):
    

# class View_matrix(Matrix):
    

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


class Plane: #Plano a partir de 3 puntos en el plano
    def __init__(self, pA:Point, pB:Point, pC:Point):
        self.A = ((pB.y-pA.y)*(pC.z-pA.z)-(pB.z-pA.z)*(pC.y-pA.y))
        self.B = ((pB.z-pA.z)*(pC.x-pA.x)-(pB.x-pA.x)*(pC.z-pA.z))
        self.C = ((pB.x-pA.x)*(pC.y-pA.y)-(pB.y-pA.y)*(pC.x-pA.x))
        self.D = -(self.A*pA.x+self.B*pA.y+self.C*pA.z)
        self.pA = pA
        self.pB = pB
        self.pC = pC
        
    def rotar_plano(self,angulo_x, angulo_y, angulo_z):
        self.pA.rotar(angulo_x, angulo_y, angulo_z)
        self.pB.rotar(angulo_x, angulo_y, angulo_z)
        self.pC.rotar(angulo_x, angulo_y, angulo_z)
        
        self.__init__(self.pA, self.pB, self.pC)
    
    def __str__(self):
        return f"Punto1 :{self.pA} Punto2:{self.pB} Punto3:{self.pC}" 

class PlaneNor: #Plano a partir de punto y vector normal al plano
    def __init__(self, v:Vector, p:Point): 
        self.A = v.x
        self.B = v.y
        self.C = v.z
        self.D = -(self.A*p.x+self.B*p.y+self.C*p.z)
        self.p = p
        self.vn = v

    def __str__(self):
        return f"Ec del plano: {self.A}*x + {self.B}*y + {self.C}*z + {self.D} = 0"

class Util:
    class Vector:
        def createVector(point0, pointf):
            return Vector(point0.x - pointf.x, point0.y - pointf.y, point0.z - pointf.z)
        def getUp():
            return Vector(0,1,0)
        def getDown():
            return Vector(0,-1,0)
        def getRight():
            return Vector(1,0,0)
        def getLeft():
            return Vector(-1,0,0)   


    def multiplicar_matrices(matriz_A, matriz_B):
        filas_A = len(matriz_A)
        columnas_A = len(matriz_A[0])
        columnas_B = len(matriz_B[0])

        matriz_C = [[0 for _ in range(columnas_B)] for _ in range(filas_A)]
        for i in range(filas_A):
            for j in range(columnas_B):
                for k in range(columnas_A): 
                    matriz_C[i][j] += matriz_A[i][k] * matriz_B[k][j] 
        return Matrix(len(matriz_C),len(matriz_C[0]),matriz_C) 
    
    def getPerspectiva(p:Point, d):
        return Point2D(p.x*d/p.z, p.y*d/(p.z))
    
    def draw(screen, p:Point2D, color=(255, 255, 255)):
        pg.draw.circle(screen, color, (p.x, p.y), 2)

    def drawLine(screen, p1:Point2D, p2:Point2D, color=(255, 255, 255)):
        pg.draw.line(screen, color, (p1.x, p2.y), (p2.z, p2.y)) 

