from Util import *

class Object:
    def __init__(self, x, y, z): #Las coordenadas x, y, z corresponden al centro del objeto
        self.x = x
        self.y = y
        self.z = z

    def __str__(self):
        return f"x:{self.x} y: {self.y} z: {self.z}"

class Cube(Object):
    def __init__(self, x, y, z, edgeLength):
        super().__init__(x, y, z)
        self.edgeLength = edgeLength #Lado del cubo

    #--Vértices--
    # Cuanto más positivo sea el vertice en "y" antes irá alfabéticamente
    # Cuando más negativo sea el vértice en "x" y "z" antes irán alfabeticamente
    # (prioridades: y>z>x) (mirar vertices.png)


    def getA(self):
        return Point(self.x-(self.edgeLength/2), self.y+(self.edgeLength/2), self.z-(self.edgeLength/2))
    def getB(self):
        return Point(self.x+(self.edgeLength/2), self.y+(self.edgeLength/2), self.z-(self.edgeLength/2))
    def getC(self):
        return Point(self.x-(self.edgeLength/2), self.y+(self.edgeLength/2), self.z+(self.edgeLength/2))
    def getD(self):
        return Point(self.x+(self.edgeLength/2), self.y+(self.edgeLength/2), self.z+(self.edgeLength/2))
    def getE(self):
        return Point(self.x-(self.edgeLength/2), self.y-(self.edgeLength/2), self.z-(self.edgeLength/2))
    def getF(self):
        return Point(self.x+(self.edgeLength/2), self.y-(self.edgeLength/2), self.z-(self.edgeLength/2))
    def getG(self):
        return Point(self.x-(self.edgeLength/2), self.y-(self.edgeLength/2), self.z+(self.edgeLength/2))
    def getH(self):
        return Point(self.x+(self.edgeLength/2), self.y-(self.edgeLength/2), self.z+(self.edgeLength/2))
    
    def getPoints(self):
        return [self.getA(), self.getB(), self.getC(), self.getD(), self.getE(), self.getF(), self.getG(), self.getH()]
    

    #Aristas

    def getAB(self):
        return Util.createVector(self.getA(), self.getB())
    def getAC(self):
        return Util.createVector(self.getA(), self.getC())
    def getAD(self):
        return Util.createVector(self.getA(), self.getD())
    def getBD(self):
        return Util.createVector(self.getB(), self.getD())
    def getBF(self):
        return Util.createVector(self.getB(), self.getF())
    def getCD(self):
        return Util.createVector(self.getC(), self.getD())
    def getCG(self):
        return Util.createVector(self.getC(), self.getG())
    def getGE(self):
        return Util.createVector(self.getG(), self.getE())
    def getGH(self):
        return Util.createVector(self.getG(), self.getH())
    def getEF(self):
        return Util.createVector(self.getE(), self.getF())
    def getFH(self):
        return Util.createVector(self.getF(), self.getH())
    
    #Caras (Desde el punto de visto del eje de coordenadas)

    def getTop(self):
        return Plane(self.getA, self.getB, self.getC)
    def getBottom(self):
        return Plane(self.getE, self.getF, self.getG)
    def getLeft(self):
        return Plane(self.getC, self.getA, self.getE)
    def getRight(self):
        return Plane(self.getB, self.getD, self.getF)
    def getFront(self):
        return Plane(self.getC, self.getD, self.getG)
    def get(self):
        return Plane(self.getA, self.getB, self.getE)


class GameManager:
    def __init__(self):
        self.objs = set()
    
    def search(self, x, y, z):
        for i in self.objs:
            if (i.x == x and i.y == y and i.z == z):
                return i
        return None
    
    def add(self, x, y, z, edge = None):
        if self.search(x, y, z) is not None:
            print("Error hay otro objeto en esa posición")
            return
        else:
            obj = Cube(x, y, z, edge) 
        self.objs.add(obj)
        return obj

    def remove(self, x, y, z):
        self.objs.remove(self.search(x, y, z))

    def move(self, obj, x, y, z):
        if self.search(x, y, z) is not None:
            print("Error hay otro objeto en el destino")
            return
        obj.x = x
        obj.y = y
        obj.z = z
    
    def printAll(self):
        for i in self.objs:
            print(i)
    
