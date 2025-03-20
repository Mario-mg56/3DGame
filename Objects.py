from Util import Point, Vector

class Object:
    def __init__(self, x, y, z): #Las coordenadas x, y, z corresponden al centro del objeto
        self.x = x
        self.y = y
        self.z = z

    def __str__(self):
        return f"x:{self.x} y: {self.y} z: {self.z}"

class Cube(Object):
    def __init__(self, x, y, z, edge):
        super().__init__(x, y, z)
        self.edge = edge #Lado del cubo

        
    #--Vértices--
    # Cuanto más positivo sea el vertice en "y" antes irá alfabéticamente
    # Cuando más negativo sea el vértice en "x" y "z" antes irán alfabeticamente
    # (prioridades: y>z>x) (mirar vertices.png)
    
    def getA(self):
        return Point(self.x-(self.edge/2), self.y+(self.edge/2), self.z-(self.edge/2))
    def getB(self):
        return Point(self.x+(self.edge/2), self.y+(self.edge/2), self.z-(self.edge/2))
    def getC(self):
        return Point(self.x-(self.edge/2), self.y+(self.edge/2), self.z+(self.edge/2))
    def getD(self):
        return Point(self.x+(self.edge/2), self.y+(self.edge/2), self.z+(self.edge/2))
    def getE(self):
        return Point(self.x-(self.edge/2), self.y-(self.edge/2), self.z-(self.edge/2))
    def getF(self):
        return Point(self.x+(self.edge/2), self.y-(self.edge/2), self.z-(self.edge/2))
    def getG(self):
        return Point(self.x-(self.edge/2), self.y-(self.edge/2), self.z+(self.edge/2))
    def getH(self):
        return Point(self.x+(self.edge/2), self.y-(self.edge/2), self.z+(self.edge/2))

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
        if self.search(x, y, z) != -1:
            print("Error hay otro objeto en el destino")
            return
        obj.x = x
        obj.y = y
        obj.z = z
    
    def printAll(self):
        for i in self.objs:
            print(i)
    
