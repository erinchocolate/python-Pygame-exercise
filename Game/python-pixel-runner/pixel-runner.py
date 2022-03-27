import random
from sys import exit

import pygame

# Pygame initiation
pygame.init()
# Set up game screen 
screen = pygame.display.set_mode((800, 400))
# Set up game title
pygame.display.set_caption("Pixel Runner!")
# Set up game colors 
font_color = (111, 196, 169)
screen_color = (94, 129, 162)
# Set up game music
bg_music = pygame.mixer.Sound('audio/music.wav')
bg_music.play(loops = -1)
# Set up game font
text_font = pygame.font.Font('font/Pixeltype.ttf', 50)
# Load background image
sky_surface = pygame.image.load('graphics/Sky.png').convert_alpha()
ground_surface = pygame.image.load('graphics/ground.png').convert_alpha()
# Set up game clock
clock = pygame.time.Clock()
# Set up game variables 
start_time = 0
game_active = True
score = 0
# Set up custom event for adding obstacles 
obstacle_time = pygame.USEREVENT + 1
pygame.time.set_timer(obstacle_time, 1500)

class Player(pygame.sprite.Sprite):
    def __init__(self):
        super().__init__()
        # Load player image
        player_1 = pygame.image.load('graphics/Player/player_walk_1.png').convert_alpha()
        player_2 = pygame.image.load('graphics/Player/player_walk_2.png').convert_alpha()
        # Load player jumping image
        self.player_jump = pygame.image.load('graphics/Player/jump.png').convert_alpha()
        # Use animation index to control what player image to use 
        self.frames = [player_1, player_2]
        self.animation_index = 0
        self.image = self.frames[self.animation_index]
        # Create player rectangle
        self.rect = self.image.get_rect(midbottom = (80, 300))
        # Set up player jumping gravity
        self.gravity = 0
        # Play sound when player jumps
        self.jump_sound = pygame.mixer.Sound('audio/jump.mp3')
        self.jump_sound.set_volume(0.5)

    def player_input(self):
        """
        When the SPACE is pressed and if player is on the gound, player jumps by changing gravity
        """
        keys = pygame.key.get_pressed()
        if keys[pygame.K_SPACE] and self.rect.bottom >= 300:
            self.gravity = -20
            self.jump_sound.play()

    def apply_gravity(self):
        self.gravity += 1
        self.rect.y += self.gravity
        # Player lands on the gound
        if self.rect.bottom >= 300:
            self.rect.bottom = 300

    def animation(self):
        # When player jumps, show jump image
        if self.rect.bottom < 300:
            self.image = self.player_jump
        else:
        # When player walks, change walking images by chaning animation index
            self.animation_index += 0.1
            if self.animation_index >= len(self.frames):
                self.animation_index = 0

            self.image = self.frames[int(self.animation_index)]

    def update(self):
        self.player_input()
        self.apply_gravity()
        self.animation()


class Obstacle(pygame.sprite.Sprite):
    def __init__(self, type):
        super().__init__()
        if type == 'fly':
            fly_1 = pygame.image.load('graphics/Fly/Fly1.png').convert_alpha()
            fly_2 = pygame.image.load('graphics/Fly/Fly2.png').convert_alpha()
            self.frames = [fly_1, fly_2]
            self.pos = 210
        else:
            snail_1 = pygame.image.load('graphics/snail/snail1.png').convert_alpha()
            snail_2 = pygame.image.load('graphics/snail/snail2.png').convert_alpha()
            self.frames = [snail_1, snail_2]
            self.pos = 300

        self.animation_index = 0
        self.image = self.frames[self.animation_index]
        self.rect = self.image.get_rect(midbottom = (random.randint(900, 1100), self.pos))

    def animation(self):
        self.animation_index += 0.1
        if self.animation_index >= len(self.frames):
            self.animation_index = 0
        self.image = self.frames[int(self.animation_index)]

    def destroy(self):
        # When obstacle moves out of the screen, remove it from sprite group
        if self.rect.x <= -100:
            self.kill()

    def update(self):
        self.animation()
        self.rect.x -= 5
        self.destroy

def display_score():
    # Calulate the score by calulating the time the game lasts and dividing it by 1000
    current_time = int(pygame.time.get_ticks()/1000) - start_time
    score_surface = text_font.render(f'Score: {current_time}', False, (64, 64, 64))
    score_rect = score_surface.get_rect(center = (400,50))
    screen.blit(score_surface, score_rect)
    return current_time

def collision_sprite():
    # If player collide with the obstacle, game over
    if pygame.sprite.spritecollide(player.sprite, obstacle_group, False):
        obstacle_group.empty()
        return False
    return True

# Set up game menu/over screen
player_stand = pygame.image.load('graphics/Player/player_stand.png').convert_alpha()
player_stand = pygame.transform.rotozoom(player_stand, 0, 2)
player_stand_rect = player_stand.get_rect(center = (400, 200))
# Game menu/over screen title
title_surface = text_font.render("Pixel Runner", False, font_color).convert_alpha()
title_rect = title_surface.get_rect(center = (400, 80))
# Game menu instruction
start = text_font.render("Press SPACE to Start", False, font_color).convert_alpha()
start_rect = start.get_rect(center = (400, 330))
# Create player and obstacle group
player = pygame.sprite.GroupSingle()
player.add(Player())
obstacle_group = pygame.sprite.Group()

while True:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            pygame.quit()
            exit()

        if game_active:
            # Add obstacles when the preset custom event is trigger
            if event.type == obstacle_time:
                obstacle_group.add(Obstacle(random.choice(['fly','snail','snail'])))
        else:
            # Press SPACE to start the game
            if event.type == pygame.KEYDOWN and event.key == pygame.K_SPACE:
                game_active = True
                start_time = int(pygame.time.get_ticks()/1000)

    if game_active:
        # Draw game background
        screen.blit(sky_surface, (0,0))
        screen.blit(ground_surface, (0,300))
        score = display_score()
        # Draw player
        player.draw(screen)
        player.update()
        # Draw obstacles
        obstacle_group.draw(screen)
        obstacle_group.update()
        game_active = collision_sprite()

    else:
        # Draw game menu/over screen
        screen.fill(screen_color)
        screen.blit(player_stand, player_stand_rect)
        screen.blit(title_surface, title_rect)
        final_score = text_font.render(f'Your score:{score}', False, font_color)
        final_score_rect = final_score.get_rect(center = (400, 350))
        if final_score == 0:
            # Show instruction if game hasn't started
            screen.blit(start, start_rect)
        else:
            # Show final score if game is over
            screen.blit(final_score, final_score_rect)

    pygame.display.update()
    clock.tick(60)
