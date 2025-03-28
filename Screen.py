import pygame as pg
from Util import *
from Cam import *
from GameManager import GameManager

class Screen:
    def __init__(self, gm):
        self.width = 960
        self.height = 540
        self.screen = pg.display.set_mode((self.width, self.height))
        self.gm = gm
    
    def setScreen(self, x, y):
        self.screen = pg.display.set_mode((x, y))

    def fixCoords(self, x, y):
        if (abs(x) > (self.width/2) or abs(y) > (self.height/2)):
            return None
        return Point2D(x+self.width/2, y+self.height/2)

    def draw(self, p:Point2D, color=(255, 255, 255)):
        pg.draw.circle(self.screen, color, (p.x, p.y), 2)

    def drawLine(self, p1:Point2D, p2:Point2D, color=(255, 255, 255)):
        pg.draw.line(self.screen, color, (p1.x, p2.y), (p2.z, p2.y))
    
    def update(self):
        for p in self.gm.info2d:
            pf = self.fixCoords(p.x, p.y)
            self.draw(pf)

