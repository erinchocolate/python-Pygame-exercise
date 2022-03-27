import curses
from curses import wrapper
import time
import random

def start_screen(stdscr):
    """
    initialize the screen with the game title and instruction. 
    Ask for user input to start the game.
    """
    # clear screen
    stdscr.clear()
    # show the title and instruction
    stdscr.addstr("Welcome to the Typing Speed Test!")
    stdscr.addstr("\nPress any key to begin!")
    # refresh screen
    stdscr.refresh()
    # ask for user input
    stdscr.getkey()

def display_text(stdscr, target, current, wpm = 0):
    """
    Compare the letter typed by user with the given text. Display red if the letter typed wrong, green otherwise.
    
    Parameters:
    target - the sentence for typing from the given text.txt.
    current - the sentence the user types
    wpm - word per minute counter

    """
    
    stdscr.addstr(target)
    stdscr.addstr(2,0, f"WPM: {wpm}")
 
    # display the letter in green if user types the right letter      
    for i, char in enumerate(current):
        correct_char = target[i]
        color = curses.color_pair(1)
    # display the letter in red if user types the wrong letter
        if char != correct_char:
            color = curses.color_pair(2)

        try:
            stdscr.addstr(0, i, char, color)
        except curses.error:
            pass

def load_text():
    """
    Read the contents of the given file.  The text file contents multiple lines of sentences,
    and return a random line of the file.

    Returns:
    a random chosen line from the file
    """
    with open("text.txt", "r") as f:
        lines = f.readlines()
        return random.choice(lines).strip()

def wpm_test(stdscr):
    """
    Load the text file for typing. 
    Calculate the typing speed.
    """
    target_text = load_text()
    current_text = []
    wpm = 0
    start_time = time.time()
    stdscr.nodelay(True)
    
    while True:
        time_elapsed = max(time.time() - start_time, 1)
        # calculate how many words typed in a minute. Assume the average word has 5 letters
        wpm = round(len(current_text) / (time_elapsed / 60)/5)
        
        stdscr.clear()
        
        display_text(stdscr, target_text, current_text,wpm)
        
        stdscr.refresh()
        # if user types all the letter, breaks the loop 
        if "".join(current_text) == target_text:
            stdscr.nodelay(False)
            break 
        
        try:
            key = stdscr.getkey()
        except:
            continue
        # if user press ESC, exit the game
        if ord(key) == 27:
            break
        # if user press backspace, delete the previous letter typed
        if key in ("KEY_BACKSPACE", "\b", "\x7f"):
            if len(current_text) > 0:
                current_text.pop()
        # if user types more letters than given text, the new letter won't be shown
        elif len(current_text) < len(target_text):
            current_text.append(key)  
         
def main(stdscr):
    
    # create 3 color pairs
    curses.init_pair(1, curses.COLOR_GREEN, curses.COLOR_BLACK)
    curses.init_pair(2, curses.COLOR_RED, curses.COLOR_BLACK)
    curses.init_pair(3, curses.COLOR_WHITE, curses.COLOR_BLACK)
    
    # start game   
    start_screen(stdscr)
    
    # game continues until the user press ESC
    while True:
        wpm_test(stdscr)
    
    # after user types all the letters, ask for input to continue or exit the game
        stdscr.addstr(3,0, "You completed the text! Press any key to continue...")
    # get user input
        key = stdscr.getkey()
        if ord(key) == 27:
            break
 
    
wrapper(main)
