from Util import *
from Cam import Cam
import math

# pA = Point(2,2,2)
# pB = Point(4,4,4)
# pC = Point(3,3,2)

# centro = Point(0, 0, 0)

# cam = Cam(centro, 20)

# print(cam.puntoDeLaCamara)

# cam.rotarCamara(20, 30)

# print(cam.puntoDeLaCamara)

vector1 = Vector(1,2,3)
vector2 = Vector(2,1,1)
angulo = Util.Vector.angulo_entre_vectores(vector1, vector2)
angulo_en_grados = math.degrees(angulo)
print(angulo_en_grados)
