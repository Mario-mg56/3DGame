from Util import *
from Cam import Cam
import math

# pA = Point(2,2,2)
# pB = Point(4,4,4)
# pC = Point(3,3,2)

centro = Point(0, 0, 0)

cam = Cam(centro, 20)

print(cam.puntoDeLaCamara)

cam.rotarCamara(20, 30)

print(cam.puntoDeLaCamara)