import pygame as pg
from Objects import *
from Util import *
from Screen import *
from Cam import *
from GameManager import GameManager

pg.init()
clock = pg.time.Clock() #Control de FPS

gm = GameManager()

gm.cam = Cam(Point(0,0,0), 5, gm)
gm.screen = Screen(gm)

# cube = Cube(50,0,0,200)

# gm.addPoints(cube.getPoints())

# gm.addPoint(Point(7,4,2))
gm.addPoint(Point(40, 10, -10))  # A
gm.addPoint(Point(60, 10, -10))  # B
gm.addPoint(Point(40, 10, 10))   # C
gm.addPoint(Point(60, 10, 10))   # D
gm.addPoint(Point(40, -10, -10)) # E
gm.addPoint(Point(60, -10, -10)) # F
gm.addPoint(Point(40, -10, 10))  # G
gm.addPoint(Point(60, -10, 10))  # H


gm.update()


#Bucle del juego
running = True
while running:
    for event in pg.event.get():
        if event.type == pg.QUIT:
            running = False
        elif event.type == pg.KEYDOWN:
            if event.key == pg.K_UP:
                pass
            elif event.key == pg.K_DOWN:
                pass
            elif event.key == pg.K_LEFT:
                pass
            elif event.key == pg.K_RIGHT:
                pass
            elif event.key == pg.K_SPACE:
                pass
    

    clock.tick(60) #Control de FPS
    pg.display.flip() #Actualizar la pantalla

pg.quit()