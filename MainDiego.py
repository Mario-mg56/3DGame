from Util import *
import math

pA = Point(2,2,2)
pB = Point(4,4,4)
pC = Point(3,3,2)

planoA = Plane(pA,pB,pC)
planoA.rotar_plano(30,0,0)
print(planoA)