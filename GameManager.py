import pygame as pg
from Objects import *
from Util import *
from Screen import *
from Cam import *
from InputManager import InputManager

class GameManager:

    def __init__(self):
        self.info2d = set()
        self.info3d = set()
        self.objs = set()
        self.InputManager = None
        self.player = None
        self.cam = None
        self.screen = None
        reloadScreen = True

    #_instance = None  # Variable de clase para almacenar la instancia única

    # def __new__(cls):
    #     if cls._instance is None:
    #         cls._instance = super(GameManager, cls).__new__(cls)
    #         cls._instance.objs = set()
    #         cls._instance.info2d = set()
    #         cls._instance.info3d = set()
    #         cls._instance.cam = Cam(Point(0, 0, 0), 20)
    #         cls._instance.screen = Screen()
    #     return cls._instance

    # @classmethod
    # def getInstance(cls):
    #     if(cls._instance is None):
    #         cls._instance = GameManager()
    #     return cls._instance
    
    def update(self):
        self.info2d = set()
        # if self.reloadScreen:
        self.cam.update()
            # self.reloadScreen = False
        self.screen.update()
        self.InputManager.update()

    def search(self, x, y, z):
        for i in self.objs:
            if (i.x == x and i.y == y and i.z == z):
                return i
        return None
    
    def add(self, x, y, z, edge = None):
        if self.search(x, y, z) is not None:
            print("Error hay otro objeto en esa posición")
            return
        else:
            obj = Cube(x, y, z, edge) 
        self.objs.add(obj)
        return obj

    def addPoint(self, point):
        self.info3d.add(point)

    def addPoints(self, points):
        for p in points:
            self.info3d.add(p)

    def remove(self, x, y, z):
        self.objs.remove(self.search(x, y, z))

    def move(self, obj, x, y, z):
        if self.search(x, y, z) is not None:
            print("Error hay otro objeto en el destino")
            return
        obj.x = x
        obj.y = y
        obj.z = z
    
    def printAll(self):
        for i in self.objs:
            print(i)