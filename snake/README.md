# Snake

This is a snake game created by pygame.

## Table of contents

- [Overview](#overview)
  - [The challenge](#the-challenge)
  - [Screenshot](#screenshot)
- [My process](#my-process)
  - [Built with](#built-with)
  - [What I learned](#what-i-learned)

## Overview

### The challenge

Snake game has two main parts: snake and fruit:

### Fruit logic

When the snake's head collide with the fruit. The fruit changes its position randomly (not on the snake tho).

### Snake logic

##### move

- The head is moved to a new block
- The block before teh head gets to the position where the head used to be
- Each block is moved to the previous block's position

##### Eat

- When the head collide with the fruit, add one more block to the body

### Game over

- If the snake's head collide with the body
- If the snake's head collide with the screen frame

### Screenshot

![screenshot](https://github.com/erinchocolate/python-Pygame-exercise/blob/master/snake/screenshot.png)

## My process

### Built with

- Python 3.10
- Python random module
- Pygame
- Pygame vector2

### What I learned

Snake can't move against to itself, i.e if snake is moving down, then when the player presses the "Up" key, it won't change snake's movement

To create a snake and fruit, I need to create a grid on the screen and make some blocks as snake body while another block as a fruit. 

How to make a grid

```python
cell_size = 40
cell_number = 20
# initialize the screen
screen = pygame.display.set_mode((cell_size * cell_number, cell_size * cell_number))
```

How to draw snake

```python
class SNAKE:
	def __init__(self):
		self.body = [Vector2(5, 10), Vector2(6,10), Vector2(7,10)]
		self.direction = Vector2(0, 1)

	def draw_snake(self):
	# draw a rectangle for each block of the snake
	for block in self.body:
		block_rect = pygame.Rect(block.x * cell_size, block.y * cell_size, cell_size, cell_size)
```

How to move snake

```python
	def move_snake(self):
		body_copy = self.body[:-1]
	# the snake head vector position is the previous head vector add the direction vector
		body_copy.insert(0, body_copy[0] + self.direction)
		self.body = body_copy[:]
```



