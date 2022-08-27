import random
import sys

import pygame
from pygame.transform import rotate


def draw_floor():
    """
    display two floors for animation, when one floor moves out of the screen, display the other
    """
    screen.blit(floor_surface,(floor_x_pos + 576, 900))
    screen.blit(floor_surface,(floor_x_pos, 900))

def create_pipe():
    random_pipe_pos = random.choice(pipe_height)
    bottom_pipe = pipe_surface.get_rect(midtop = (700, random_pipe_pos))
    top_pipe = pipe_surface.get_rect(midbottom = (700, random_pipe_pos - 300))
    return bottom_pipe, top_pipe

def draw_pipe(pipe_list):
    if pipe_list:
        for pipe_rect in pipe_list:
            pipe_rect.x -= 10
            if pipe_rect.bottom > 1024:
                screen.blit(pipe_surface, pipe_rect)
            else:
                flip_pipe = pygame.transform.flip(pipe_surface, False, True)
                screen.blit(flip_pipe, pipe_rect)

def check_collision(pipes):
    for pipe in pipes:
         if bird_rect.colliderect(pipe):
             can_score = True
             death_sound.play()
             return False
    if bird_rect.top <= 100 or bird_rect.bottom >= 900:
        can_score = True
        return False
    return True

def rotate_bird(bird):
    new_bird = pygame.transform.rotozoom(bird, -bird_movement * 3, 1)
    return new_bird

def bird_animation():
    new_bird = bird_frames[bird_index]
    new_bird_rect = new_bird.get_rect(center = (100, bird_rect.centery))
    return new_bird, new_bird_rect

def score_display(game_state):
    if game_state == "main_game":
        score_surface = font.render(str(score), True, (255, 255, 255))
        score_rect = score_surface.get_rect(center = (288, 100))
        screen.blit(score_surface, score_rect)

    if game_state == "game_over":
        score_surface = font.render(f'Score: {score}' ,True,(255,255,255))
        score_rect = score_surface.get_rect(center = (288,100))
        screen.blit(score_surface,score_rect)

        high_score_surface = font.render(f'High score: {high_score}',True,(255,255,255))
        high_score_rect = high_score_surface.get_rect(center = (288,850))
        screen.blit(high_score_surface,high_score_rect)

def pipe_score_check():
    global score, can_score
    if pipe_list:
        for pipe in pipe_list:
            if 95 < pipe.centerx < 105 and can_score:
                score += 1
                score_sound.play()
                can_score = False
            if pipe.centerx < 0:
                can_score = True

def update_score(score, high_score):
    if score > high_score:
        high_score = score
    return high_score
# initaialize the pygame
pygame.init()

# set game screen
screen = pygame.display.set_mode((576, 1024))

# set game title
pygame.display.set_caption('Flappy Bird')

# set game font
font = pygame.font.Font("assets/04B_19.TTF", 40)

# set game sound
flap_sound = pygame.mixer.Sound('sound/sfx_wing.wav')
death_sound = pygame.mixer.Sound('sound/sfx_hit.wav')
score_sound = pygame.mixer.Sound('sound/sfx_point.wav')

# set clock
clock = pygame.time.Clock()

# set game state
game_active = True

# set background
bg_surface = pygame.image.load('assets/background-day.png').convert_alpha()
bg_surface = pygame.transform.rotozoom(bg_surface, 0, 2)
game_over_surface = pygame.transform.scale2x(pygame.image.load('assets/message.png').convert_alpha())
game_over_rect = game_over_surface.get_rect(center = (288, 512))

# set floor
floor_surface = pygame.image.load('assets/base.png').convert_alpha()
floor_surface = pygame.transform.rotozoom(floor_surface, 0, 2)
floor_x_pos = 0

# set bird
bird_downflap = pygame.transform.scale2x(pygame.image.load('assets/bluebird-downflap.png').convert_alpha())
bird_midflap = pygame.transform.scale2x(pygame.image.load('assets/bluebird-midflap.png').convert_alpha())
bird_upflap = pygame.transform.scale2x(pygame.image.load('assets/bluebird-upflap.png').convert_alpha())
bird_frames = [bird_downflap, bird_midflap, bird_upflap]
bird_index = 0
bird_surface = bird_frames[bird_index]
bird_rect = bird_surface.get_rect(center = (100, 512))

bird_timer = pygame.USEREVENT + 1
pygame.time.set_timer(bird_timer, 200)

# set pipe
pipe_surface = pygame.image.load('assets/pipe-green.png').convert_alpha()
pipe_surface = pygame.transform.rotozoom(pipe_surface, 0, 2)
pipe_list = []
pipe_height = [400, 600, 800]
# game variables
gravity = 0.28
bird_movement = 0
score = 0
high_score = 0
can_score = True

# Timer
pipe_timer = pygame.USEREVENT + 1
pygame.time.set_timer(pipe_timer, 1200)


while True:
    for event in pygame.event.get():
        # when player press exit, quit the game
        if event.type == pygame.QUIT:
            pygame.quit()
            sys.exit()
        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_SPACE and game_active:
                flap_sound.play()
                bird_movement = 0
                bird_movement -= 8
            if event.key == pygame.K_SPACE and game_active == False:
                game_active = True
                pipe_list.clear()
                bird_rect.center = (100, 512)
                bird_movement = 0
                score = 0
        if event.type == pipe_timer:
            pipe_list.extend(create_pipe())
        if event.type == bird_timer:
            if bird_index < 2:
                bird_index += 1
            else:
                bird_index = 0
            bird_surface, bird_rect = bird_animation()
    screen.blit(bg_surface,(0,0))

    if game_active:
        # bird
        bird_movement += gravity
        rotated_bird = rotate_bird(bird_surface)
        bird_rect.centery += bird_movement
        screen.blit(rotated_bird,bird_rect)
        game_active = check_collision(pipe_list)
        # pipe
        draw_pipe(pipe_list)
        # score
        pipe_score_check()
        score_display('main_game')
    else:
        screen.blit(game_over_surface, game_over_rect)
        high_score = update_score(score, high_score)
        score_display('game_over')
    # floor animation
    floor_x_pos -= 2
    draw_floor()
    # when the floor moves out of screen, reset its x pos
    if floor_x_pos < -576:
        floor_x_pos = 0
    pygame.display.update()
    clock.tick(60)
