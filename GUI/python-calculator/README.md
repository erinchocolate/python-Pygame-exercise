# Calculator

I build two calculators using different GUI module of python. 

## Table of contents

- [Overview](#overview)
  - [The challenge](#the-challenge)
  - [Screenshot](#screenshot)
- [My process](#my-process)
  - [Built with](#built-with)
  - [What I learned](#what-i-learned)

## Overview

### The challenge

Users should be able to:

- Enter the number
- Select the operation
- See the result

### Screenshot


### ![screenshot](https://github.com/erinchocolate/build-my-own-x/blob/master/GUI/python-calculator/screenshot.png)


## My process

### Built with

- Python 3.10
- PyQt5
- Qt Designer
- PySimpleGUI

### What I learned

Calculator Logic

```python
def num_press(self, key_number):
	self.temp_nums.append(key_number)
	temp_string = ''.join(self.temp_nums)
	if self.fin_nums:
		self.result_field.setText(''.join(self.fin_nums) + temp_string)
	else:
		self.result_field.setText(temp_string)

def func_press(self, operator):
	temp_string =''.join(self.temp_nums)
	self.fin_nums.append(temp_string)
	self.fin_nums.append(operator)
	self.temp_nums = []
	self.result_field.setText(''.join(self.fin_nums))

def func_result(self):
	fin_string = ''.join(self.fin_nums) + ''.join(self.temp_nums)
	result_string = eval(fin_string)
	fin_string += "="
	fin_string += str(result_string)
	self.result_field.setText(fin_string)
```
