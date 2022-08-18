# Pomodoro Clock

## Table of contents

- [Overview](#overview)
  - [The challenge](#the-challenge)
  - [Screenshot](#screenshot)
  - [Links](#links)
- [My process](#my-process)
  - [Built with](#built-with)
  - [What I learned](#what-i-learned)

## Overview

### Use case description

- The user chooses the clock mode - Pomodoro, long break and short break
- The user can stop and restart the running timer
- The user can switch the clock mode

### Triggers

- The user presses the Start button to start the timer
- The user presses the Stop button to stop the timer
- The user changes tabs to switch the timer

### Actors

- user
- clock
- timer

### Preconditions - they must be true before a method is executed

- 3 clock modes
- 3 timers
- Start/Stop button

### Steps of execution

- When the user changes tabs, the user can see a timer with different time length
- When the user presses the Start button, the timer starts to count down and the button becomes Stop
- When the user presses the Stop button, the timer stops and the button becomes Start
- When the user changes tabs, clear the timer and jumps to the tab choosen
- After 4 complete Pomodoro timers, show the long break tab
- After each Pomodoro timer, shows the short break tab

### Screenshot

![screenshot](https://github.com/erinchocolate/build-my-own-x/blob/master/GUI/python-pomodoro-timer/screenshot.png)

## My process

### Built with

- Python 3.10
- tk module
- pyinstaller
- pygame

### What I learned

How to make python file into exe file

- `pip install pyinstaller`
- `pyinstaller --onefile -w [filename]`
- install NSIS
- compress the python file
- use NSIS "installer based on ZIP file"
