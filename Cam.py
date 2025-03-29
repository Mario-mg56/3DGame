from Util import *
from GameManager import GameManager

class Cam:
    def __init__(self, centro, d, gm:GameManager):
        self.centro = centro
        self.d = d
        self.puntoDeLaCamara = Point(centro.x+self.d, centro.y, centro.z)
        self.gm = gm
        #Estoy asumiendo que cuando se inicializa por primera vez la cam el jugador mira hacia adelante
        # self.axisX2D = Rect(Point(self.puntoDeLaCamara.x, self.puntoDeLaCamara.y+1, self.puntoDeLaCamara.z), self.puntoDeLaCamara)
        # self.axisY2D = Rect(Point(self.puntoDeLaCamara.x, self.puntoDeLaCamara.y+1, self.puntoDeLaCamara.z).rotar(0, -90, 0), self.puntoDeLaCamara)
        self.actualizar_plano()
        self.info3Dto2D()

    def update(self):
        self.info3Dto2D()

    def rotarCamara(self, angulo_x, angulo_y):
        puntoCamaraCentrado = self.puntoDeLaCamara - self.centro
        puntoCamaraCentrado.rotar(angulo_x, angulo_y, 0)
        self.puntoDeLaCamara = puntoCamaraCentrado + self.centro

        #Actualizar ejes del plano
        self.axisX2D.rotate(angulo_x, angulo_y, 0)
        self.axisY2D.rotate(angulo_x, angulo_y, 0)

        self.actualizar_plano() #Actualizar posición plano cámara

        

    def actualizar_plano(self):
        self.vectorNormal= Util.Vector.createVector(self.centro, self.puntoDeLaCamara)
        self.planoCam = PlaneNor(self.vectorNormal,self.puntoDeLaCamara)
        #Conseguir el vector que se refiere al eje x local
        vectorALaCamara = Util.Vector.createVector(self.centro,self.puntoDeLaCamara)
        self.vector_X_local = Util.Vector.producto_vectorial(vectorALaCamara,Util.Vector.getDown())
        self.vector_Y_local = Util.Vector.producto_vectorial(vectorALaCamara,self.vector_X_local)

    # def watchMario(self, point:Point):
    #     ray = Rect(self.centro, point)
    #     cutPoint = Util.interseccion_recta_plano(ray, self.planoCam)
    #     #Buscar los puntos de corte con los ejes 2D para encontrar las coordenadas 2D
    #     pInX = Util.interseccion_recta_recta(Rect(cutPoint, self.axisY2D.vDir), self.axisX2D)
    #     pInY = Util.interseccion_recta_recta(Rect(cutPoint, self.axisX2D.vDir), self.axisY2D)
    #     #Coordenadas en 2d
    #     x2d = Util.Vector.createVector(cutPoint, pInX).getMod()
    #     y2d = Util.Vector.createVector(cutPoint, pInY).getMod()

    def info3Dto2D(self):
        for i in self.gm.info3d:
            p = self.watchDiego(i)
            # print(p)
            self.gm.info2d.add(p)
    
    def watchDiego(self, point:Point):
        print("Punto",point.name)
        ray = Rect(self.centro,point)
        # print("vectoralpunto",ray.vDir)
        cutPoint = Util.interseccion_recta_plano(ray, self.planoCam)
        
        #Conseguir el vector del centro al punto creo q se puede mejorar
        vectorAlPunto = Util.Vector.createVector(cutPoint,self.puntoDeLaCamara)
        #COnseguir la distancia
        distancia = vectorAlPunto.getMod()
        # print("xlocal:",self.vector_X_local,"punto corte:",cutPoint,"punto_camara:",self.puntoDeLaCamara)
        #Conseguir el angulo entre anmbos vector los devuelve en angulos
        
        # print("xlocal:",self.vector_X_local,"vectoralpunto:", vectorAlPunto)
        [signx,signy,angulo] = Util.Vector.getCuadrante_y_angulo(vectorAlPunto,self.vector_X_local,self.vector_Y_local)
        print("angulo:",math.degrees(angulo), "distancia:",distancia)
        x_local = distancia*math.cos(angulo)
        y_local = distancia*math.sin(angulo)*signy
        # print("x:",x_local,"y:",y_local)
        return Point2D(x_local,y_local)
    