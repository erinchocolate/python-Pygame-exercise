![screenshot](https://github.com/erinchocolate/build-my-own-x/blob/master/Game/python-space-invaders/screenshot.png)

This document is for my personal reference on what I use in this project include modules, functions and etc.

## What makes the game

### game initialization

##### screen
- background
- game title

##### load game assets
- ships images
- lasers images
- background image

### Class
##### Laser
- Init
	- position(x, y)
	- laser image
	- mask
- draw
	- laser image at position(x, y)
- move
	- speed
	- update y(move horizontally)
- off_screen
	- return true if laser's position is off the screen
- collision
	- return true if laser is overlapped with other object

 ##### Ship
 COOLDOWN - set as 30 meaning there is 0.5 seconds (FPS is 60, so 30 is half second) 
 between bullet shooting

 - init
	 - position(x, y)
	 - health points
	 - ship image
	 - laser image
	 - laser list
	 - cool down counter
 - draw
	 - ship image at position(x, y)
	 - if laser list has an element, display laser image
 - move_laser
	 - cooldown()
	 - laser.move()
	 - laser.offscreen()
	 - laser.collision()
		 - decrease health points by 10
 - cooldown
	 - if cool down counter is larger than COOLDOWN, set it as 0
	 - else if cool down counter is larger than 0, it increases by 1
 - shoot
	 - check cool down counter, if it's 0, then create a Laser object
	 - add new Laser object into laser list
	 - set cool down counter as 1
 - get_width
 - get_height

##### Player(ship)
- init
	- inherit from ship
	- yellow ship image
	- yellow laser image
	- mask
	- max health
- move_lasers
	- cooldown()
	- laser.off_screen()
	- laser.collision()
- draw
	- inherit from ship
	- healthbar
- healthbar
	- player health/max health(100)

##### Enemy(ship)
 Color map
 - init
	- inherit from ship
	- color ship image
	- color laser image
	- mask
- move
	- speed
	- update y(move horizontally)


## Helper function
##### collide
return true if one object is overlapped with the other object

##### redraw_window
- display level and lives text
- display enemy
- display player
- display lost text
- pygame.display.update()

## Game main function
1. set clock and FPS
2. set game level, lives
3. set font
4. set enemy wave
5. set speed for player, bullet and enemy
6. creat a player
7. set lost flag

while loop:
- clock.tick(FPS)
- redraw_window
- check if player is lost
- check if enemies are gone, if so increase wave and creates more enemies
- check the key player press or click and move the ship or fire
- enemy move and shoot
	- check if player collide with enemy - update the healthbar
	- check if enemy passes the checkpoint - update the lives
- player shoot

## main_menu
- display game title on screen
- check event type 
	- quit
	- start the game


## Modules
##### [pygame mask](https://www.pygame.org/docs/ref/mask.html)
pygame module for image masks. Why use mask? Mask is useful for fast pixel perfect collision detection. A mask uses 1 bit per-pixel to store which parts collide.

Functions I use | Definition
------------ | ------------
pygame.mask.from_surface|Creates a Mask from the given surface
pygame.mask.overlap|Returns the first point of intersection encountered between this mask and `other`. 

##### [random](https://docs.python.org/3/library/random.html)
This module implements pseudo-random number generators for various distributions.

Functions I use | Definition
------------ | ------------
random.randrange|return a randomly selected element from range(start, stop, step)
random.choice|return a random element from the non-empty sequence
