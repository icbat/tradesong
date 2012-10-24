import os

import cocos
from cocos import layer, tiles

class Map (cocos.layer.Layer):
	_filename = ""
	def __init__(self, filename="/../assets/maps/town.tmx"):
		super(Map, self).__init__()
		_filename = os.getcwd() + filename
		self = tiles.load(_filename)

if __name__ == "__main__":
	global scroller
	from cocos.director import director
	director.init(width=800, height=600, do_not_scale=True, resizable=True)	
	director.set_show_FPS(True)
	
	#Unsure what this does exactly but let's try it
	scroller = layer.ScrollingManager()

	grass_layer = tiles.load(os.getcwd() + "/../assets/maps/town.tmx")['GroundLayer']
	house_base_layer = tiles.load(os.getcwd() + "/../assets/maps/town.tmx")['HouseBaseLayer']
	house_detail_layer = tiles.load(os.getcwd() + "/../assets/maps/town.tmx")['HouseDetailLayer']
	scroller.add(grass_layer, 0)
	scroller.add(house_base_layer, 1)
	scroller.add(house_detail_layer, 2)
	main_scene = cocos.scene.Scene(scroller)
	
	director.run (main_scene)