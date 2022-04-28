![screenshot](https://github.com/erinchocolate/build-my-own-x/blob/master/Game/python-turtle-racing/screenshot.png)

This document is for my personal reference on what I use in this project include modules, functions and etc.

## [Turtle](https://docs.python.org/3/library/turtle.html)
Turtle can draw intricate shapes using programs that repeat simple moves.

##### Turtle motion
Functions I use|Definition
------------ | ------------
turtle.forward(distance)|Move the turtle forward by the specified _distance_, in the direction the turtle is headed
turtle.left(angle)|Turn turtle left by _angle_ units
turtle.speed()|Set the turtle’s speed to an integer value in the range 0..10. If no argument is given, return current speed
turtle.position()|Return the turtle’s current location (x,y)
turtle.penup()|Pull the pen up – no drawing when moving
turtle.pendown()|Pull the pen down – drawing when moving
turtle.color()|Return or set pencolor and fillcolor
turtle.shape()|Set turtle shape to shape with given _name_ or, if name is not given, return name of current shape
turtle.setpos()|set turtle location(x,y)

##### Turtle screen
Functions I use|Definition
------------ | ------------
turtle.setup()|Set the size and position of the main window
turtle.title()|Set title of turtle window to _titlestring_

##### Other
Functions I use|Definition
------------ | ------------
random.shuffle()|Shuffle the sequence _x_ in place
string.index()|Return the lowest index in the string where substring _sub_ is found within the slice `s[start:end]`. Raise ValueError when the substring is not found
string.isdigit()|Return `True` if all characters in the string are digits and there is at least one character, `False` otherwise


