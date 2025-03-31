import pygame as pg
from Objects import *
from Util import *
from Screen import *
from Cam import *
from GameManager import GameManager
from InputManager import *
from Entity import Entity

pg.init()
clock = pg.time.Clock() #Control de FPS

gm = GameManager()

gm.InputManager = InputManager(gm)
gm.player = Entity(Point(0, 0, 0), gm, 3, "Player")
gm.cam = Cam(400, gm)
gm.screen = Screen(gm)


# cube = Cube(50,0,0,200)

# gm.addPoints(cube.getPoints())

# gm.addPoint(Point(7,4,2))


heightSquare = 7
gm.addPoint(Point(401,1,0,"CAM"))

gm.addPoint(Point(40, heightSquare, -10,"A"))  # A
gm.addPoint(Point(60, heightSquare, -10))  # B
gm.addPoint(Point(40, heightSquare, 10))   # C
gm.addPoint(Point(60, heightSquare, 10))   # D
gm.addPoint(Point(40, heightSquare-20, -10)) # E
gm.addPoint(Point(60, heightSquare-20, -10)) # F
gm.addPoint(Point(40, heightSquare-20, 10))  # G
gm.addPoint(Point(60, heightSquare-20, 10))  # H




# Bucle del juego
running = True
while running:
    for event in pg.event.get():
        if event.type == pg.QUIT:
            running = False

    gm.update()


    clock.tick(60)  # Control de FPS
    pg.display.flip()  # Actualizar la pantalla

pg.quit()