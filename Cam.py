from Util import *

class Cam:
    def __init__(self, centro, d):
        self.centro = centro
        self.d = d
        self.puntoDeLaCamara = Point(centro.x+self.d, centro.y, centro.z)
        #Estoy asumiendo que cuando se inicializa por primera vez la cam el jugador mira hacia adelante
        self.axisX2D = Rect(Point(self.puntoDeLaCamara.x, self.puntoDeLaCamara.y+1), self.puntoDeLaCamara)
        self.axisY2D = Rect(Point(self.puntoDeLaCamara.x, self.puntoDeLaCamara.y+1).rotar(90, 0, 0), self.puntoDeLaCamara)
        self.actualizar_plano()

    def rotarCamara(self, angulo_x, angulo_y):
        puntoCamaraCentrado = self.puntoDeLaCamara - self.centro
        puntoCamaraCentrado.rotar(angulo_x, angulo_y, 0)
        self.puntoDeLaCamara = puntoCamaraCentrado + self.centro

        #Actualizar ejes del plano
        self.axisX2D.rotate(angulo_x, angulo_y, 0)
        self.axisY2D.rotate(angulo_x, angulo_y, 0)

        self.actualizar_plano()

    def actualizar_plano(self):
        self.vectorNormal= Util.Vector.createVector(self.centro, self.puntoDeLaCamara)
        self.planoCam = PlaneNor(self.vectorNormal,self.puntoDeLaCamara)

    def watch(self, point:Point):
        ray = Rect(self.centro, point)
        cutPoint = Util.interseccion_recta_plano(ray, self.vectorNormal)
        #Buscar los puntos de corte con los ejes 2D para encontrar las coordenadas 2D
        Rect(cutPoint, self.axisX2D)