from enum import Enum
import pygame as pg

class Input(Enum):
    LEFT = 0
    RIGHT = 1
    UP = 2
    DOWN = 3
    RIGHTUP = 4
    RIGHTDOWN = 5
    LEFTUP = 6
    LEFTDOWN = 7

class InputManager:
    def __init__(self, gm):
        self.input = None
        self.camInput = None
        self.gm = gm
        #Eso es vdd, pero podemos hacer que si abres el menu se pause el juego, con el inventario es otro tema  si es multijugador xd mucha fé tienes tu

    def update(self):
        if self.input is not None:
            self.gm.player.move(self.input)
        self.input = None
        self.camInput = None
        self.checkInputs()

    def checkInputs(self):
        # Obtener teclas presionadas
        keys = pg.key.get_pressed()

        # Movement Listeners
        if keys[pg.K_w]:
            if not keys[pg.K_a] and not keys[pg.K_d]:
                self.input = Input.UP
        if keys[pg.K_s]:
            if not keys[pg.K_a] and not keys[pg.K_d]:
                self.input = Input.DOWN
        if keys[pg.K_a]:
            if keys[pg.K_w] and not keys[pg.K_s]:
                self.input = Input.LEFTUP
            elif keys[pg.K_s] and not keys[pg.K_w]:
                self.input = Input.LEFTDOWN
            else:
                self.input = Input.LEFT
        if keys[pg.K_d]:
            if keys[pg.K_w] and not keys[pg.K_s]:
                self.input = Input.RIGHTUP
            elif keys[pg.K_s] and not keys[pg.K_w]:
                self.input = Input.RIGHTDOWN
            else:
                self.input = Input.RIGHT
        
        # Cam listeners
        if keys[pg.K_UP]:
            if not keys[pg.K_a] and not keys[pg.K_d]:
                self.camInput = Input.UP
        if keys[pg.K_DOWN]:
            if not keys[pg.K_a] and not keys[pg.K_d]:
                self.camInput = Input.DOWN
        if keys[pg.K_LEFT]:
            if keys[pg.K_UP] and not keys[pg.K_DOWN]:
                self.camInput = Input.LEFTUP
            elif keys[pg.K_DOWN] and not keys[pg.K_UP]:
                self.camInput = Input.LEFTDOWN
            else:
                self.camInput = Input.LEFT
        if keys[pg.K_RIGHT]:
            if keys[pg.K_UP] and not keys[pg.K_DOWN]:
                self.camInput = Input.RIGHTUP
            elif keys[pg.K_DOWN] and not keys[pg.K_UP]:
                self.camInput = Input.RIGHTDOWN
            else:
                self.camInput = Input.RIGHT
        if keys[pg.K_SPACE]:
            pass
