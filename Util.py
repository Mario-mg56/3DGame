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
