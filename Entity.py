from Util import *
from InputManager import Input
from GameManager import GameManager
from Cam import Cam

class Entity:
    def __init__(self, position:Point, gm:GameManager, dp=3, id=""):
        self.id = id
        self.position = position
        self.dp = dp #Modulo del vector de desplazamiento
        self.gm = gm

    def move(self, input:Input):
        camVector = self.gm.cam.vectorALaCamara.normalize() #Vector que apunta a la camara
        camVector.y = 0
        camVector *= self.dp
        # perpendicularidad izquierda (-y, x) derecha (y, -x) (2D)
        if input == None:
            return
        elif input == Input.UP:
            print("Estoy subiendo")
            self.position += camVector
            self.gm.cam.puntoDeLaCamara += camVector
        elif input == Input.DOWN:
            self.position -= camVector
            self.gm.cam.puntoDeLaCamara -= camVector
        elif input == Input.LEFT:
            self.position += Vector(camVector.z*-1, 0, camVector.x)
            self.gm.cam.puntoDeLaCamara += Vector(camVector.z*-1, 0, camVector.x)
        elif input == Input.RIGHT:
            self.position += Vector(camVector.z, 0, camVector.x*-1)
            self.gm.cam.puntoDeLaCamara += Vector(camVector.z, 0, camVector.x*-1)
        elif input == Input.LEFTUP:
            self.position += camVector.rotar(angulo_y=-45)
            self.gm.cam.puntoDeLaCamara += camVector
        elif input == Input.LEFTDOWN:
            self.position += camVector.rotar(angulo_y=-135)
            self.gm.cam.puntoDeLaCamara += camVector
        elif input == Input.RIGHTUP:
            self.position += camVector.rotar(angulo_y=45)
            self.gm.cam.puntoDeLaCamara += camVector
        elif input == Input.RIGHTDOWN:
            self.position += camVector.rotar(angulo_y=135)
            self.gm.cam.puntoDeLaCamara += camVector
        
        print("New position: ", self.position.x, self.position.y, self.position.z)

    def teleport(self, target:Point):
        self.position= target