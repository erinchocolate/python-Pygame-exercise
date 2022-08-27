import random
from sys import exit

import pygame


class Player(pygame.sprite.Sprite):
    def __init__(self):
        super().__init__()
        # import walking images
        player_walk_1 = pygame.image.load('graphics/Player/player_walk_1.png').convert_alpha()
        player_walk_2 = pygame.image.load('graphics/Player/player_walk_2.png').convert_alpha()
        # import jumpping image
        self.player_jump = pygame.image.load('graphics/Player/jump.png').convert_alpha()

        # walking animation
        self.player_walk = [player_walk_1, player_walk_2]
        self.player_index = 0
        # create a player image based on index
        self.image = self.player_walk[self.player_index]
        # create a rectangle based on the image
        self.rect = self.image.get_rect(midbottom = (80, 300))

        # set gravity to make jumps more real
        self.gravity = 0

        # set sound effect for jump
        # self.jump_sound = pygame.mixer.Sound('audio/jump.mp3')
        # self.jump_sound.set_volume(0.5)

    def player_input(self):
        keys = pygame.key.get_pressed()
        # if SPACE is pressed, the player will jump and sound effect will play
        if keys[pygame.K_SPACE] and self.rect.bottom >= 300:
            self.gravity = -20
            #self.jump_sound.play()

    def apply_gravity(self):
        # the higher the player jumps, the faster it falls back to the ground 
        self.gravity += 1
        self.rect.y += self.gravity
        # when player falls below ground, makes player stand on the gound again
        if self.rect.bottom >= 300:
            self.rect.bottom = 300

    def animation_state(self):
        # display the jump image when player jumps
        if self.rect.bottom < 300:
            self.image = self.player_jump
        else:
        # change index between 0 and 1
            self.player_index += 0.1
            if self.player_index >= 2:
                self.player_index = 0
        # display walking image based on the index
            self.image = self.player_walk[int(self.player_index)]

    def update(self):
        # update player input to control the sprite
        self.player_input()
        self.apply_gravity()
        self.animation_state()


class Obstacle(pygame.sprite.Sprite):
    def __init__(self, type):
        super().__init__()
        # load image for specific types
        # create a list with two images
        # set the y position of image, fly is 210(fly in the sky) and snial is 300(on the ground)
        if type == 'fly':
            fly_1 = pygame.image.load('graphics/Fly/Fly1.png').convert_alpha()
            fly_2 = pygame.image.load('graphics/Fly/Fly2.png').convert_alpha()
            self.frames = [fly_1, fly_2]
            y_pos = 210
        else:
            snail_1 = pygame.image.load('graphics/snail/snail1.png').convert_alpha()
            snail_2 = pygame.image.load('graphics/snail/snail2.png').convert_alpha()
            self.frames = [snail_1, snail_2]
            y_pos = 300
        # use animation index to decide which image to use in each frame  
        self.animation_index = 0
        self.image = self.frames[self.animation_index]
        # create a rectangle based on the image 
        self.rect = self.image.get_rect(midbottom = (random.randint(900, 1100), y_pos))

        def animation_state(self):
            # change index between 0 and 1 to decide which image to use
            self.animation_index += 0.1
            if self.animation_index >= 2:
                self.animation_index = 0
            self.image = self.frames[int(self.animation_index)]

        def destroy(self):
            # if the obstacle moves out of the screen, delete it
            if self.rect.x <= -100:
                self.kill()

        def update(self):
            # update obstacle movement, animation and delete
            self.animation_state()
            self.rect.x -= 6
            self.destroy()



def display_score():
    # convert game time to score by dividing 1000
    score = int(pygame.time.get_ticks()/1000) - start_time
    # display score 
    score_surface = text_font.render(f'Score: {score}', False, (64, 64, 64))
    score_rect = score_surface.get_rect(center = (400,50))
    screen.blit(score_surface, score_rect)
    return score

def collision_sprite():
    # when obstacle collide with player, delete all sprites in the obstacle group and return False
    if pygame.sprite.spritecollide(player.sprite, obstacle_group, False):
        obstacle_group.empty()
        return False
    else:
        return True

# pygame initiation
pygame.init()
# set game window size
screen = pygame.display.set_mode((800, 400))
# set game name
pygame.display.set_caption("Pixel Runner")
# set game font
text_font = pygame.font.Font('font/Pixeltype.ttf', 50)
# set game music
#bg_music = pygame.mixer.Sound('audio/music.wav')
#bg_music.play(loops = -1)

clock = pygame.time.Clock()
start_time = 0
game_active = False
score = 0

# set game background
sky_surface = pygame.image.load('graphics/Sky.png').convert_alpha()
ground_surface = pygame.image.load('graphics/ground.png').convert_alpha()

# set player image for intro screen 
player_stand = pygame.image.load('graphics/Player/player_stand.png').convert_alpha()
player_stand = pygame.transform.rotozoom(player_stand, 0, 2)
player_stand_rect = player_stand.get_rect(center = (400, 200))

# set game title
title_surface = text_font.render("Pixel Runner", False, (111, 196, 169)).convert_alpha()
title_rect = title_surface.get_rect(center =(400, 80))

# set game instruction
instruction = text_font.render("Press SPACE to Start", False, (111, 196, 169)).convert_alpha()
ins_rect = instruction.get_rect(center = (400, 330))

# set timer to create obstacles
obstacle_timer = pygame.USEREVENT + 1
pygame.time.set_timer(obstacle_timer, 2000)

# sprite groups
player = pygame.sprite.GroupSingle()
player.add(Player())

obstacle_group = pygame.sprite.Group()

while True:
    for event in pygame.event.get():
    # if player presses exit, quit the game
        if event.type == pygame.QUIT:
            pygame.quit()
            exit()

        if game_active:
    # create an obstacle sprite
            if event.type == obstacle_timer:
                obstacle_group.add(Obstacle(random.choice(['fly','snail','snail','snail'])))

        else:
            if event.type == pygame.KEYDOWN and event.key == pygame.K_SPACE:
                game_active = True
                start_time = int(pygame.time.get_ticks()/1000)


    if game_active:
        # display game backgound
        screen.blit(sky_surface,(0,0))
        screen.blit(ground_surface,(0, 300))
        # display score
        score = display_score()
        # display player and its update
        player.draw(screen)
        player.update()
        # display obstacle and its update
        obstacle_group.draw(screen)
        obstacle_group.update()
        # if collision happens, game over
        game_active = collision_sprite

    else:
        # display gameover screen
        screen.fill((94,129,162))
        screen.blit(player_stand, player_stand_rect)
        # create score message and rectangle
        score_message = text_font.render(f'Your score:{score}', False,(111, 196, 169))
        score_message_rect = score_message.get_rect(center = (400, 350))
        # display game title
        screen.blit(title_surface, title_rect)
        # display the game instruction if the score is 0(intro screen)
        if score == 0:
            screen.blit(instruction, ins_rect)
        else:
        # display the final score 
            screen.blit(score_message, score_message_rect)
    pygame.display.update()
    clock.tick(60)
