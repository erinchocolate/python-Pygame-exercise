## What makes the game
Snake game has two main parts: snake and fruit.

To create a snake and fruit, I need to create a grid on the screen and make some blocks as snake body while another block as a fruit.

I use [vector2](https://www.pygame.org/docs/ref/math.html) to display and store block position.

### Fruit logic
When the snake's head collide with the fruit. The fruit changes its position randomly (not on the snake tho).

### Snake logic
##### move
- The head is moved to a new block
- The block before teh head gets to the position where the head used to be
- Each block is moved to the previous block's position

##### Eat
- When the head collide with the fruit, add one more block to the body

### Game over
- If the snake's head collide with the body
- If the snake's head collide with the screen frame