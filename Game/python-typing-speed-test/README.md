This document is for my personal reference on what I use in the project includes modules, functions and etc

## [Curses](https://docs.python.org/3/library/curses.html#curses.window.nodelay)
The curses library supplies a terminal-independent screen-painting and keyboard-handling facility for text-based terminals.

##### wrapper()
wrapper initializes curses module and call the main function passing the initial curses object - stdscr

Functions I use | Definition
------------ | ------------
stdscr.clear()|Clear screen
stdscr.addstr()|displays a string at the current cursor location
stdscr.refresh()|update the screen
stdscr.getkey()|waits for the user input and converts the integer to a string
stdscr.getch()|refreshes the screen and then waits for the user input
stdscr.nodelay(flag)|if flag is True, getch() will be non-blocking
curses.init_color|Change the definition of a color, taking the number of the color to be changed followed by three RGB values
curses.init_pair(n,f,b)|changes the definition of color pair _n_, to foreground color f and background color b
curses.color_pair|Return the attribute value for displaying text in the specified color pair

## [time](https://docs.python.org/3/library/time.html)
Functions I use | Definition
------------ | ------------
time.sleep(secs)|Suspend execution of the calling thread for the given number of seconds
time.time()|Return the time in seconds since the [epoch](https://docs.python.org/3/library/time.html#epoch) as a floating point number.

## Built-in functions
Functions I use | Definition
------------ | ------------
enumerate(iterable, start=0)|Return an enumerate object
ord(c)|Given a string representing one Unicode character, return an integer representing the Unicode code point of that character
open()|Open file and return a corresponding file object



## What makes this project
### Helper function
##### start_screen
- stdscr.clear() - clear screen
- stdscr.addstr() - add title and instructions
- stdscr.refresh() - refresh screen
- stdscr.getkey() - ask for user input

##### display_text
- stdscr.addstr()
	- show the target text for users to type
	- show the word per minute counter
- check user input and target text
	- display user input text in green if typing right
	- display user input text in red if typing wrong

##### load_text
- open file
- read lines
- return randomly chosen lines

##### wpm_test
- calculate word per minute - (length of typing text/5)/(time/60)
- check user input 
	- If ESC, break the loop
	- if BACKSPACE, delete the previous typing letter



