from Util import *

class Cam:
    def __init__(self, centro, d):
        self.centro = centro
        self.d = d
        self.puntoDeLaCamara = Point(centro.x+self.d, centro.y, centro.z)
        #Estoy asumiendo que cuando se inicializa por primera vez la cam el jugador mira hacia adelante
        self.axisX2D = Rect(Point(self.puntoDeLaCamara.x, self.puntoDeLaCamara.y+1, self.puntoDeLaCamara.z), self.puntoDeLaCamara)
        self.axisY2D = Rect(Point(self.puntoDeLaCamara.x, self.puntoDeLaCamara.y+1, self.puntoDeLaCamara.z).rotar(0, -90, 0), self.puntoDeLaCamara)
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

    def watchMario(self, point:Point):
        ray = Rect(self.centro, point)
        cutPoint = Util.interseccion_recta_plano(ray, self.vectorNormal)
        #Buscar los puntos de corte con los ejes 2D para encontrar las coordenadas 2D
        pInX = Util.interseccion_recta_recta(Rect(cutPoint, self.axisY2D.vDir), self.axisX2D)
        pInY = Util.interseccion_recta_recta(Rect(cutPoint, self.axisX2D.vDir), self.axisY2D)
        x2d = Util.Vector.createVector(cutPoint, pInX).getMod()
        y2d = Util.Vector.createVector(cutPoint, pInY).getMod()
    
    def watchDiego(self, point:Point):
        ray = Rect(self.centro, point)
        cutPoint = Util.interseccion_recta_plano(ray, self.vectorNormal)
        #Conseguir el vector que se refiere al eje x local
        Vector_X_local = Util.Vector.producto_cruzado(cutPoint.toVector(),Util.Vector.getDown())
        #Conseguir el vector del centro al punto creo q se puede mejorar
        vectorAlPunto = Util.Vector.createVector(cutPoint,self.puntoDeLaCamara)
        #COnseguir la distancia
        distancia = vectorAlPunto.getMod()
        #COnseguir el angulo entre anmbos vector los devuelve en angulos
        angulo = Util.Vector.angulo_entre_vectores(Vector_X_local,vectorAlPunto)
        x_local = distancia*math.cos(angulo)
        y_local = distancia*math.sin(angulo)
        return x_local,y_local