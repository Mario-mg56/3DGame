class Point:
    def __init__(self, x, y, z):
        self.x = x
        self.y = y
        self.z = z
        
    def __sub__(self,punto: Point):
        return Vector(self.x-punto.x,self.y-punto.y,self.z-punto.z)


class Rect:
    def __init__(self, puntoA: Point, puntoB: Point):
        self.vDir = puntoA-puntoB
        #(x = x1 + vDir.x*t), (y = y1 + vDir.y*t), (z = z1 + vDir.z*t)
        self.x = puntoA.x
        self.y = puntoA.y
        self.z = puntoA.z
    
    
    def getPoint(self, t=0):
        x = self.x + vdir.x*t
        y = self.y + vdir.y*t
        z = self.z + vdir.z*t
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
        
    
        


class Vector:
    def __init__(self, x, y, z):
        self.x = x
        self.y = y
        self.z = z
    
    def getMod(self): #Devuelve el módulo del vector
        return ((self.fin.x-self.inicio.x)**2 + (self.fin.y-self.inicio.y)**2 + (self.fin.z-self.inicio.z)**2)**0.5
    
    def normalized(self):
        x_normalized = self.x/self.getMod()
        y_normalized = self.y/self.getMod()
        z_normalized = self.z/self.getMod()
        return Vector(x_normalized,y_normalized,z_normalized) 
    
