import pygame as pg
from Util import *

class Screen:
    def __init__(self):
        self.screen = pg.display.set_mode((650, 650))
    
    def setScreen(self, x, y):
        self.screen = pg.display.set_mode((x, y))

    def draw(self, p:Point2D, color=(255, 255, 255)):
        pg.draw.circle(self.screen, color, (p.x, p.y), 2)

    def drawLine(self, p1:Point2D, p2:Point2D, color=(255, 255, 255)):
        pg.draw.line(self.screen, color, (p1.x, p2.y), (p2.z, p2.y)) 
