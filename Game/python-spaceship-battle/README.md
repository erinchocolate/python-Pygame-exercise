This is my first time to use pygame to create a game. I followed the tutorial created by [Tech With Tim](https://www.youtube.com/watch?v=jO6qQDNa2UY).

## what makes the game
### game initialization
##### Screen
- color
- background
- game title
- middle line
- frame per second

##### Spaceship& Bullets
- color
- ship speed
- bullet speed
- max numbers of bullets
- ship size
- bullet size
- custom events

##### load game assets
- spaceship image
- background image
- fonts
- sound

### Game helper functions
##### draw_window()
- backgound
- health text
- spaceships
- bullets
- display.update()

##### ship movement
- use a/d/w/s to control the yellow ship 
- use left/right/up/down to control the red ship
- ship moving speed is set in the game initialization
- make sure each ship moves within the half side of the screen and doesn't move across the middle line

##### handle bullets
- when bullets hit the ship or move off the screen, remove the bullet from bullet list
- trigger the hit event(custom event) using colliderect()

##### draw_winner()
- winner text
- display.update()
- time.delay()

### Game main function
1. create rectangles for ships
2. initialize bullets list
3. initialize health
4. set clock
5. set while loop:
- check if player clicks quit - if so, quit the game
- check keys for yellow ship movement
- check keys for red ship movement
- check hit event and update health text
- display winning text if any ship's health is 0 or below and break the loop

The document below is for my personal reference for what I use in this project include modules, functions and etc.

## os 
[os](https://docs.python.org/3/library/os.html) provides a portable way of using operating system dependent functionality. In this game, I mainly use [os.path.join](https://docs.python.org/3/library/os.path.html#module-os.path) to load game assets:

Join one or more path components intelligently. The return value is the concatenation of _path_ and any members of _*paths_ with exactly one directory separator following each non-empty part except the last, meaning that the result will only end in a separator if the last part is empty. If a component is an absolute path, all previous components are thrown away and joining continues from the absolute path component.

example:
`os.path.join('Assets', 'Grenade+1.mp3')`

## pygame
Install:
`python3 -m pip install -U pygame --user`

### modules
##### [pygame](https://www.pygame.org/docs/ref/pygame.html)
The pygame package represents the top-level package for others to use. Pygame itself is broken into many submodules, but this does not affect programs that use pygame.

Functions I use |Definition
--------------|------------
pygame.init() |initialize all imported pygame modules
pygame.quit() |uninitialize all pygame modules

##### [pygame.display](https://www.pygame.org/docs/ref/display.html)
This module offers control over the pygame display. Pygame has a single display Surface that is either contained in a window or runs full screen. Once you create the display you treat it as a regular Surface. Changes are not immediately visible onscreen; you must choose one of the two flipping functions to update the actual display.

Functions I use |Definition
--------------|------------
pygame.display.set_mode |Initialize a window or screen for display
pygame.display.set_caption |Set the current window caption
pygame.display.update|Update portions of the screen for software displays

##### [pygame.Surface](https://www.pygame.org/docs/ref/surface.html?highlight=blit#pygame.Surface.blit)
pygame object for representing images

Functions I use |Definition
--------------|------------
pygame.blit()|draw one image onto another

###### how to use blit()
blit(source, dest, area=None, special_flags=0) -> Rect

The draw can be positioned with the dest argument. The dest argument can either be a pair of coordinates representing the position of the upper left corner of the blit or a Rect, where the upper left corner of the rectangle will be used as the position for the blit. The size of the destination rectangle does not effect the blit.


##### [pygame.Rect](https://www.pygame.org/docs/ref/rect.html)
Pygame uses Rect objects to store and manipulate rectangular areas. A Rect can be created from a combination of left, top, width, and height values. Rects can also be created from python objects that are already a Rect or have an attribute named "rect".

Functions I use |Definition
--------------|------------
pygame.Rect.colliderect|test if two rectangles overlap

##### [pygame.image](https://www.pygame.org/docs/ref/image.html)
pygame module for image transfer

Functions I use |Definition
--------------|------------
pygame.iamge.load|load new image from a file (or file-like object)

##### [pygame.transform](https://www.pygame.org/docs/ref/transform.html)
pygame module to transform surfaces

Functions I use |Definition
--------------|------------
pygame.transform.scale|resize to new resolution
pygame.transform.rotate|rotate an image

##### [pygame.draw](https://www.pygame.org/docs/ref/draw.html)
Draw several simple shapes to a surface.

Functions I use |Definition
--------------|------------
pygame.draw.rect|draw a rectangle

##### [pygame.event](https://www.pygame.org/docs/ref/event.html)
pygame module for interacting with events and queues

Functions I use |Definition
--------------|------------
pygame.event.get|get events from the queue
pygame.event.post|place a new event on the queue
pygame.event.Event|create a new event object
pygame.event.EventType|pygame object for representing events

##### [pygame.font](https://www.pygame.org/docs/ref/font.html)
pygame module for loading and rendering fonts

Functions I use |Definition
--------------|------------
pygame.font.init()|initialize the font module
pygame.font.Font.render()|draw text on a new Surface

You can load fonts from the system by using the `pygame.font.SysFont()` function. 

Pygame comes with a builtin default font. This can always be accessed by passing None as the font name.

##### [pygame.mixer]()
pygame module for loading and playing sounds

Functions I use |Definition
--------------|------------
pygame.mixer.init()|initialize the mixer module
pygame.mixer.Sound()|Create a new Sound object from a file or buffer object
pygame.mixer.music.play()|Start the playback of the music stream

##### [pygame.key](https://www.pygame.org/docs/ref/key.html#module-pygame.key)
pygame module to work with the keyboard

Functions I use |Definition
--------------|------------
pygame.key.get_pressed()|get the state of all keyboard buttons
