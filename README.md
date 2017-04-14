# SlidingPuzzles
  This project solves sliding block puzzles. These puzzles consist of a number of rectangular blocks in a tray. The goal is to slide the pieces without lifting any out of the tray until a certain configuration is achieved.

### Team Member
  - Bruk Argaw
  - Omid Khosravani


  ![Alt Text](https://github.com/trxw/SlidingPuzzles/blob/master/assets/sliding_blocks.jpg)

### Example Input
4 3     // << tray size (height width)
0 0 1 0 // << orange
0 2 1 2 // << green
2 0 3 0 // << blue
0 1 0 1 // << pink

  * What that looks like as a block...

  ![Alt Text](https://github.com/trxw/SlidingPuzzles/blob/master/assets/start_ex_blocks.png)

### Example Goal / output
~~~
0 2 1 2 // << orange
0 2 1 2 // << green
2 1 3 1 // << blue
3 2 3 2 // << pink
~~~

  * What that looks like as a block...

  ![Alt Text](https://github.com/trxw/SlidingPuzzles/blob/master/assets/end_ex_blocks.png)


# Tools
  - Java
  - eclipse :- IDE
  - Git
  - Github

## Features
  Give in a starting configuration and goal configuration the SlidingPuzzles puzzle will return one of the path that must be taken in order to solve the puzzle in the shortest amount of steps.

## Directions To Use SlidingPuzzles


## Future Directions
  The current implementation uses a berth first algorithm that find the shortest path to the solution but does not solve the hard puzzles fast enough, thus we will implement the solver using a depth first approach. This will allow us to avoid looking for the shortest path and instead solve it faster but not be guarantee that the solution found will be the fastest.

## Acknowledgments
  This project was taken from the cs61bl course from UC Berkeley. You can find the orginal project description here [cs61bl](http://www.cs61bl.org/su15/materials/proj/proj3/proj3.html)
