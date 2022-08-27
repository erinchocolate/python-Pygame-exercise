import os
import random
import sys

import pygame
from pygame.math import Vector2


class SNAKE:
    def __init__(self):
        self.body = [Vector2(5, 10), Vector2(6,10), Vector2(7,10)]
        self.direction = Vector2(0, 1)

    def draw_snake(self):
        # draw a rectangle for each block of the snake
        for block in self.body:
            block_rect = pygame.Rect(block.x * cell_size, block.y * cell_size, cell_size, cell_size)
            pygame.draw.rect(screen,(183, 111, 122),block_rect)

    def move_snake(self):
        body_copy = self.body[:-1]
        # the snake head vector position is the previous head vector add the direction vector
        body_copy.insert(0, body_copy[0] + self.direction)
        self.body = body_copy[:]

    def add_block(self):
        body_copy = self.body[:]
        # add a new block after the snake
        new_block = Vector2(self.body[-1].x, self.body[-1].y)
        body_copy.append(new_block)
        self.body = body_copy[:]

class FRUIT:
    def __init__(self):
        self.randomize()

    def draw_fruit(self):
        # draw a rectangle for fruit
        fruit_rect = pygame.Rect(self.pos.x * cell_size,self.pos.y * cell_size,cell_size, cell_size)
        screen.blit(apple, fruit_rect)

    def randomize(self):
        self.x = random.randint(0, cell_number-1)
        self.y = random.randint(0, cell_number-1)
        self.pos = Vector2(self.x, self.y)

class MAIN:
    def __init__(self):
        self.snake = SNAKE()
        self.fruit = FRUIT()

    def update(self):
        self.snake.move_snake()
        self.check_collision()
        self.check_fail()

    def draw_elements(self):
        self.snake.draw_snake()
        self.fruit.draw_fruit()
        self.draw_score()

    def check_collision(self):
        # if the snake head at the same position of fruit, then randomize the fruit position and 
        # add a block to the snake
        if self.fruit.pos == self.snake.body[0]:
            self.fruit.randomize()
            self.snake.add_block()
        # check whether fruit appears on the snake body, if so, change the fruit position  
            for block in self.snake.body[1:]:
                if block == self.fruit.pos:
                    self.fruit.randomize()

    def check_fail(self):
        # if snake hits the screen frame, game over
        if not 0 <= self.snake.body[0].x < cell_number or not 0 <= self.snake.body[0].y < cell_number:
            self.game_over()
        # if snake hits itself, game over
        for block in self.snake.body[1:]:
            if block == self.snake.body[0]:
                self.game_over()

    def draw_score(self):
        score_text = str(len(self.snake.body) - 3)
        score_surface = game_font.render(score_text,True,(56,74,12))
        score_x = int(cell_size * cell_number - 60)
        score_y = int(cell_size * cell_number - 40)
        score_rect = score_surface.get_rect(center = (score_x, score_y))
        apple_rect = apple.get_rect(midright = (score_rect.left, score_rect.centery))

        screen.blit(score_surface, score_rect)
        screen.blit(apple, apple_rect)

    def game_over(self):
        pygame.time.delay(3000)
        pygame.quit()
        sys.exit()

pygame.init()
# initialize the grid
cell_size = 40
cell_number = 20
# initialize the screen
screen = pygame.display.set_mode((cell_size * cell_number, cell_size * cell_number))
clock = pygame.time.Clock()
apple = pygame.image.load(os.path.join('Graphics', 'apple.png')).convert_alpha()

game_font = pygame.font.Font('Font/PoetsenOne-Regular.ttf', 25)

main_game = MAIN()

SCREEN_UPDATE = pygame.USEREVENT
pygame.time.set_timer(SCREEN_UPDATE, 150)

while True:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            pygame.quit()
            sys.exit()
        if event.type == SCREEN_UPDATE:
            main_game.update()
        if event.type == pygame.KEYDOWN:
            # snake moves to the direction decided by the key the player presses
            # snake can't move against to itself, i.e if snake is moving down, then when the player 
            # presses the "Up" key, it won't change snake's movement
            if event.key == pygame.K_UP and main_game.snake.direction.y != 1:
                main_game.snake.direction = Vector2(0, -1)
            if event.key == pygame.K_DOWN and main_game.snake.direction.y != -1:
                main_game.snake.direction = Vector2(0, 1)
            if event.key == pygame.K_RIGHT and main_game.snake.direction.x != -1:
                main_game.snake.direction = Vector2(1, 0)
            if event.key == pygame.K_LEFT and main_game.snake.direction.x != 1:
                main_game.snake.direction = Vector2(-1, 0)

    screen.fill((175,215,70))
    main_game.draw_elements()
    pygame.display.update()
    clock.tick(60)
