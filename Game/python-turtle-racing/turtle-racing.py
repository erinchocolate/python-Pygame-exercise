import random
import time
import turtle

# set screen size
WIDTH, HEIGHT = 500, 500
# set color scheme
COLORS = ["red", "green", "blue", "orange", "yellow", "black", "pink", "cyan", "brown", "purple"]

def get_number_of_racers():
    """
    Ask for user input on turtle numbers.
    Check user input is valid or not. Return a number entered by user between 2 to 10.
    """
    racers= 0
    while True:
        # ask for input
        racers = input("Enter the number of racers (2 - 10): ")
        # check whether user input is a number, if not, ask for another input
        if racers.isdigit():
            racers = int(racers)
        else:
            print("Input is not numeric... Try again!")
            continue
        # check whether input is between 2 to 10, if not, ask for another input
        if 2 <= racers <= 10:
            return racers
        else:
            print("Number not in range 2 - 10. Try again!")

def race(colors):
    """
    Create turtles and let them move forward. Return the first one to pass the check point
    """
    turtles = create_turtles(colors)

    while True:
        for racer in turtles:
            # each turtle move random distance forward 
            distance = random.randrange(1, 20)
            racer.forward(distance)
            # if the turtle pass the check point
            x, y = racer.pos()
            if y >= HEIGHT//2 - 10:# check point
            # return the color of winning turtle
                return colors[turtles.index(racer)]

def create_turtles(color):
    """
    Return a list of turtles with different colors
    """
    turtles = []
    # spacing between each turtle
    spacingx = WIDTH // (len(colors) + 1)
    for i, color in enumerate(colors):
        racer = turtle.Turtle()
        racer.color(color)
        racer.shape("turtle")
        racer.left(90)
        racer.penup()
    # turtle's starting position
        racer.setpos(-WIDTH//2 + (i + 1) * spacingx, -HEIGHT//2 + 20)
        racer.pendown()
        turtles.append(racer)
    return turtles

def init_turtle():
    """
    initialize the screen size and title
    """
    screen = turtle.Screen()
    screen.setup(WIDTH, HEIGHT)
    screen.title("Turtle Racing!")

racers = get_number_of_racers()
init_turtle()
# create different color orders
random.shuffle(COLORS)
colors = COLORS[:racers]
# display the winner color
winner = race(colors)
print("The winner is the turtle with color: ", winner)
time.sleep(5)
