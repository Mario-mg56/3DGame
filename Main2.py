import pygame as pg
from Objects import *
from Util import *
from Screen import *
from Cam import *
from GameManager import GameManager

pg.init()
clock = pg.time.Clock() #Control de FPS

gm = GameManager()

gm.cam = Cam(Point(0,0,0), 20, gm)
gm.screen = Screen(gm)

cube = Cube(30,0,0,20)

gm.addPoints(cube.getPoints())

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