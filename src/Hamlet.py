import cocos

class Hamlet(cocos.layer.Layer):
    def __init__(self):
		super(Hamlet, self).__init__()
		
		label = cocos.text.Label('Hello world!',
			font_name = 'Times New Roman',
			font_size = 32,
			anchor_x = 'center',
			anchor_y = 'center')
		label.position = 320,240
		self.add(label)
		
if __name__ == "__main__":
	cocos.director.director.init()
	
	Hamlet_layer = Hamlet()
	
	main_scene = cocos.scene.Scene(Hamlet_layer)
	
	cocos.director.director.run (main_scene)