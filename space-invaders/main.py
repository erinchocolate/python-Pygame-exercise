import os
import random

import pygame

# Initialize the game
pygame.font.init()

# set screen and game title
WIDTH, HEIGHT = 750, 750
WIN = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("Space Invaders")

# Load game assets
# enemy ship images
RED_SPACE_SHIP = pygame.image.load(
    os.path.join("assets", "pixel_ship_red_small.png"))
BLUE_SPACE_SHIP = pygame.image.load(
    os.path.join("assets", "pixel_ship_blue_small.png"))
GREEN_SPACE_SHIP = pygame.image.load(
    os.path.join("assets", "pixel_ship_green_small.png"))

# player ship image
YELLOW_SPACE_SHIP = pygame.image.load(
    os.path.join("assets", "pixel_ship_yellow.png"))

# lasers images
RED_LASER = pygame.image.load(os.path.join("assets", "pixel_laser_red.png"))
GREEN_LASER = pygame.image.load(os.path.join("assets","pixel_laser_green.png"))
BLUE_LASER = pygame.image.load(os.path.join("assets", "pixel_laser_blue.png"))
YELLOW_LASER = pygame.image.load(os.path.join("assets", "pixel_laser_yellow.png"))

# background image
BG = pygame.transform.scale(pygame.image.load(os.path.join("assets", "background-black.png")),(WIDTH, HEIGHT))

class Laser:
    """
    The laser loaded from laser image. Both player and enemy ship can shoot the laser, which moves 
    vertically. It can collide with ships.
    """
    def __init__(self, x, y, img):
        """
        Initialize the laser with coordinate x, coordinate y and image loaded from assets.
        Create a mask from the given image.
        """
        self.x = x
        self.y = y
        self.img = img
        self.mask = pygame.mask.from_surface(self.img)

    def draw(self, window):
        """
        display laser at the position of (x, y) on the screen
        """
        window.blit(self.img, (self.x, self.y))

    def move(self, vel):
        """
        move laser at the speed of vel
        """
        self.y += vel

    def off_screen(self, height):
        """
        return True if the laser's position is out of the screen
        """
        return not(self.y <= height and self.y >= 0)

    def collision(self, obj):
        """
        return True when the laser is overlapped with another object
        """
        return collide(self, obj)

class Ship:
    """
    The ship can move and shoot lasers. There is cooldown time between shooting.
    """

    COOLDOWN = 30

    def __init__(self, x, y, health = 100):
        """
        initilize the ship with the position of coordinate x and coordinate y. It starts with 
        100 health points. The cooldown time starts with 0.
        """
        self.x = x
        self.y = y
        self.health = health
        self.ship_img = None
        self.laser_img = None
        self.lasers = []
        self.cool_down_counter = 0

    def draw(self, window):
        """
        display the ship image at the postion of (x, y) and the laser the ship shoots
        """
        window.blit(self.ship_img, (self.x, self.y))
        for laser in self.lasers:
            laser.draw(window)

    def move_lasers(self, vel, obj):
        """
        check whether the ship can shoot laser first.
        When laser is moving off the screen or hit the other ship, the laser is removed from laser
        list and the ship loses 10 health points.
        """
        self.cooldown()
        for laser in self.lasers:
            laser.move(vel)
            if laser.off_screen(HEIGHT):
                self.lasers.remove(laser)
            elif laser.collision(obj):
                obj.health -= 10
                self.lasers.remove(laser)

    def cooldown(self):
        """
        Set the counter so the ship can only shoot another laser waiting half seconds after the 
        previous laser
        """
        if self.cool_down_counter >= self.COOLDOWN:
            self.cool_down_counter = 0
        elif self.cool_down_counter > 0:
            self.cool_down_counter += 1

    def shoot(self):
        """
        The ship can shoot laser when the cool down counter is 0
        """
        if self.cool_down_counter == 0:
            laser = Laser(self.x, self.y, self.laser_img)
            self.lasers.append(laser)
            self.cool_down_counter = 1

    def get_width(self):
        """return the width of ship image"""
        return self.ship_img.get_width()

    def get_height(self):
        """return the height of ship image"""
        return self.ship_img.get_height()

class Player(Ship):
    def __init__(self, x, y, health=100):
        super().__init__(x, y, health=health)
        self.ship_img = YELLOW_SPACE_SHIP
        self.laser_img = YELLOW_LASER
        self.mask = pygame.mask.from_surface(self.ship_img)
        self.max_health = health

    def move_lasers(self, vel, objs):
        """
        After shooting the laser, if laser goes off the screen, remove it.
        if laser hits the enemy ship, remove the enemy ship and laser.
        """
        self.cooldown()
        for laser in self.lasers:
            laser.move(vel)
            if laser.off_screen(HEIGHT):
                self.lasers.remove(laser)
            else:
                for obj in objs:
                    if laser.collision(obj):
                        objs.remove(obj)
                        if laser in self.lasers:
                            self.lasers.remove(laser)

    def draw(self, window):
        """
        display the player ship and healthbar
        """
        super().draw(window)
        self.healthbar(window)

    def healthbar(self, window):
        """
        healthbar shows the percentage of the real health out of the max health
        """
        pygame.draw.rect(window, (255, 0, 0), (self.x, self.y + self.ship_img.get_height() + 10, self.ship_img.get_width(), 10))
        pygame.draw.rect(window, (0, 255, 0), (self.x, self.y + self.ship_img.get_height() + 10, self.ship_img.get_width() * (self.health/self.max_health), 10))

class Enemy(Ship):
    # set colors to choose from
    COLOR_MAP = {
        "red":(RED_SPACE_SHIP, RED_LASER),
        "blue":(BLUE_SPACE_SHIP, BLUE_LASER),
        "green":(GREEN_SPACE_SHIP, GREEN_LASER)
    }

    def __init__(self, x, y, color, health=100):
        """
        Initialize enemy ships and its laser with the color randomly choosen from color map.
        Create a mask from the given image
        """
        super().__init__(x, y, health)
        self.ship_img, self.laser_img = self.COLOR_MAP[color]
        self.mask = pygame.mask.from_surface(self.ship_img)

    def move(self, vel):
        """
        Enemy ship with speed of vel
        """
        self.y += vel

def collide(obj1, obj2):
    """
    Return True if obj1's mask encounters obj2's mask, meaning two objects overlap with each other
    """
    offset_x = obj2.x - obj1.x
    offset_y = obj2.y - obj1.y
    return obj1.mask.overlap(obj2.mask, (offset_x, offset_y)) != None

def main():
    # Initialize the game
    run = True
    FPS = 60
    # game level
    level = 0
    # player's life
    lives = 5
    clock = pygame.time.Clock()

    # font
    main_font = pygame.font.SysFont("Noto Sans", 50)
    lost_font = pygame.font.SysFont("Noto Sans", 60)

    enemies = []
    # range for enemy ships numbers
    wave_length = 5
    # enemy speed
    enemy_vel = 1

    player = Player(300, 650)
    # player speed
    player_vel = 5
    # laser speed
    laser_vel = 5

    lost = False
    lost_count = 0

    def redraw_window():
        WIN.blit(BG, (0, 0))
        #draw level and lives text
        lives_label = main_font.render(f"lives: {lives}", 1, (255,255,255))
        level_label = main_font.render(f"level: {level}", 1, (255,255,255))
        #display the level and lives
        WIN.blit(lives_label, (10, 10))
        WIN.blit(level_label,(WIDTH - level_label.get_width() -10, 10))
        #display the enemy
        for enemy in enemies:
            enemy.draw(WIN)
        #display the player
        player.draw(WIN)
        #display the lost text
        if lost:
            lost_label = lost_font.render("You Lost!", 1, (255, 255, 255))
            WIN.blit(lost_label, (WIDTH/2 - lost_label.get_width()/2, 350))
        pygame.display.update()

    while run:
        clock.tick(FPS)
        redraw_window()
        # plyaer loses when its health or lives is below 0
        if lives <= 0 or player.health <= 0:
            lost = True
            lost_count += 1
        # when player loses, we show the lost text for 3 seconds and restart the game
        if lost:
            if lost_count > FPS * 3:
                run = False
            else:
                continue

        # when player hits all enemy ships, increase the level and wave, and creates randomly choosen number
        # from the range of wave of enemy ships
        if len(enemies) == 0:
            level += 1
            wave_length += 5
            for i in range(wave_length):
                enemy = Enemy(random.randrange(50, WIDTH-100), random.randrange(-1500, -100), random.choice(["red", "blue", "green"]))
                enemies.append(enemy)

        # when player clicks X, quit the program
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                quit()

        # player uses a/d/s/w and space to control the ship and fire
        keys = pygame.key.get_pressed()
        if keys[pygame.K_a] and player.x - player_vel > 0: # left
            player.x -= player_vel
        if keys[pygame.K_d] and player.x + player_vel + player.get_width() < WIDTH: # right
            player.x += player_vel
        if keys[pygame.K_w] and player.y - player_vel > 0: # up
            player.y -= player_vel
        if keys[pygame.K_s] and player.y + player_vel + player.get_height() + 15 < HEIGHT: # down
            player.y += player_vel
        if keys[pygame.K_SPACE]:
            player.shoot()

        # enemy moves and shoots
        for enemy in enemies[:]:
            enemy.move(enemy_vel)
            enemy.move_lasers(laser_vel, player)
            if random.randrange(0, 120) == 1:
                enemy.shoot()

            # player loses 10 health points when it collides with enemies
            if collide(enemy, player):
                player.health -= 10
                enemies.remove(enemy)

            # player loses 1 live when an enemy ship goes down to the screen bottom
            if enemy.y + enemy.get_height() > HEIGHT:
                lives -= 1
                enemies.remove(enemy)

        player.move_lasers(-laser_vel, enemies)

def main_menu():
    """
    initilize the main menu. Press mouse button to start the game or press X to quit the game
    """
    title_font = pygame.font.SysFont("Noto", 70)
    run = True
    while run:
        WIN.blit(BG, (0, 0))
        title_label = title_font.render("Press the mouse to begin...", 1, (255,255,255))
        WIN.blit(title_label, (WIDTH/2 - title_label.get_width()/2, 350))
        pygame.display.update()
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                quit()
            if event.type == pygame.MOUSEBUTTONDOWN:
                main()
    pygame.quit()

main_menu()
