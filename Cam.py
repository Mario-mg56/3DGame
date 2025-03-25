from Util import *

class Cam:
    def __init__(self, centro, d):
        self.centro = centro
        self.d = d
        self.puntoDeLaCamara = Point(centro.x+self.d, centro.y, centro.z)
        self.actualizar_plano()

    def rotarCamara(self, angulo_x, angulo_y):
        puntoCamaraCentrado = self.puntoDeLaCamara - self.centro
        puntoCamaraCentrado.rotar(angulo_x, angulo_y, 0)
        self.puntoDeLaCamara = puntoCamaraCentrado + self.centro
        self.actualizar_plano()

    def actualizar_plano(self):
        self.vectorNormal= Util.Vector.createVector(self.centro, self.puntoDeLaCamara)
        self.planoCam = PlaneNor(self.vectorNormal,self.puntoDeLaCamara)

