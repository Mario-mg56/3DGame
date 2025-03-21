import pygame as pg
from Objects import *
from Util import *


v1 = Vector(2,2,2)
print(v1.normalized())

gm = GameManager()

gm.add(2, 2, 2, 2)
gm.printAll()




pg.init()
screen = pg.display.set_mode((650, 650))
clock = pg.time.Clock() #Control de FPS

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