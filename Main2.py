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


cube = Cube(40,0,0,20)

gm.addPoints(cube.getPoints())

# gm.addPoint(Point(7,4,2))


heightSquare = 7
# gm.addPoint(Point(401,1,0,"CAM"))




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