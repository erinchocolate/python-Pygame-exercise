# Pixel Runner

This game is created using pygame. The goal for the game is simple. The player uses jump to aviod obstacles like flies and snails and try to last longer to get higher scores.

## Table of contents

- [Overview](#overview)
  - [The challenge](#the-challenge)
  - [Screenshot](#screenshot)
- [My process](#my-process)
  - [Built with](#built-with)
  - [What I learned](#what-i-learned)

## Overview

### The challenge

Users should be able to:

- Use space to control the sprite's jump 
- See their scores

### Screenshot

![screenshot](C:\Users\Erins Desktop\Downloads\build-my-own-x\Game\python-flappy-bird\screenshot.png)


## My process

### Built with

- Python 3.10
- Python random module
- Pygame

### What I learned

I use [sprite](https://www.pygame.org/docs/ref/sprite.html) class to create player and obstacles, which makes it easy to control the movement and animation of game sprites.

The benefits of using sprite class:

- combines a surface and a rect
- can contain more(sounds, animations, behaviour etc)
- can target multiple sprites via groups

How to make animation:

```python
self.animation_index = 0
self.image = self.frames[self.animation_index]

def animation(self):
	self.animation_index += 0.1
	if self.animation_index >= len(self.frames):
		self.animation_index = 0
		self.image = self.frames[int(self.animation_index)]
```
