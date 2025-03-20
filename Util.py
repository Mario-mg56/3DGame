class Point:
    def __init__(self, x, y, z):
        self.x = x
        self.y = y
        self.z = z

class Vector:
    def __init__(self, x, y, z):
        self.x = x
        self.y = y
        self.z = z
    
    def getMod(self): #Devuelve el módulo del vector
        return ((self.fin.x-self.inicio.x)**2 + (self.fin.y-self.inicio.y)**2 + (self.fin.z-self.inicio.z)**2)**0.5

class Plane:
    def __init__(self, pA, pB, pC): # plano a partir de 3 puntos en el plano
        self.A = ((pB.y-pA.y)*(pC.z-pA.z)-(pB.z-pA.z)*(pC.y-pA.y))
        self.B = ((pB.z-pA.z)*(pC.x-pA.x)-(pB.x-pA.x)*(pC.z-pA.z))
        self.C = ((pB.x-pA.x)*(pC.y-pA.y)-(pB.y-pA.y)*(pC.x-pA.x))
        self.D = -(self.A*pA.x+self.B*pA.y+self.C*pA.z)

class Util:
    def createVector(point0, pointf):
        return Vector(point0.x - pointf.x, point0.y - pointf.y, point0.z - pointf.z)