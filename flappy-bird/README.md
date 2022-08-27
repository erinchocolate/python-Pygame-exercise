# Flappy Bird

This is Flappy Bird game made by pygame. 

## Table of contents

- [Overview](#overview)
  - [The challenge](#the-challenge)
  - [Screenshot](#screenshot)
- [My process](#my-process)
  - [Built with](#built-with)
  - [What I learned](#what-i-learned)
  - [Continued development](#continued-development)

## Overview

### The challenge

Player should be able to:

- Use 'Space' to control the bird movement
- See their score

### Screenshot

![screenshot](https://github.com/erinchocolate/python-Pygame-exercise/blob/master/flappy-bird/screenshot.png)


## My process

### Built with

- Python 3.10
- Pygame

### What I learned

How to make floor animation

- Create two floor rects
- The first rect's x postion moves by deducting certain distance
- When one moves out of the screen, display the other one

```python
def draw_floor():
	screen.blit(floor_surface,(floor_x_pos + 576, 900))
	screen.blit(floor_surface,(floor_x_pos, 900))

While game true:
# floor movement
	floor_x_pos -= 2
	draw_floor()
# when the floor moves out of screen, reset its x pos
	if floor_x_pos < -576:
		floor_x_pos = 0
```
How to make bird flip animation

- Import different images for animation
- create a list including all images
- create a new rect based on a different image
- change index of the list to change image

```python
bird_downflap = pygame.transform.scale2x(pygame.image.load('assets/bluebird-downflap.png').convert_alpha())
bird_midflap = pygame.transform.scale2x(pygame.image.load('assets/bluebird-midflap.png').convert_alpha())
bird_upflap = pygame.transform.scale2x(pygame.image.load('assets/bluebird-upflap.png').convert_alpha())
bird_frames = [bird_downflap, bird_midflap, bird_upflap]
bird_index = 0
bird_surface = bird_frames[bird_index]

def bird_animation():
	new_bird = bird_frames[bird_index]
	new_bird_rect = new_bird.get_rect(center = (100, bird_rect.centery))
	return new_bird, new_bird_rect

While game true:
	if event.type == bird_timer:
		if bird_index < 2:
			bird_index += 1
		else:
			bird_index = 0
			bird_surface, bird_rect = bird_animation()
```

How to apply gravity to bird's falling

```python
gravity = 0.28
bird_movement = 0

while game true:
	bird_movement += gravity
	bird_rect.centery += bird_movement
```

### Continued development

I want to study NEAT to implement AI to play this game.
