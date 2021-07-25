# Smart-Space-Division
This program tests if the smart space division algorithm is faster than basic collision detection.
The test simulates 10000 circles with random velocities in a closed room. 
An elastic collision response is used to calculate the resulting velocities after a collision.
It uses the swing library to display the circles on a GUI with a button to change detection mode and a timer that shows the time
used to calculate 1 frame.

&nbsp;
&nbsp;

## The algorithms
### Basic
This algorithm is made up of a double for loop. 
This loops over every possible object pair and checks if they overlap.
If they overlap the collision response is calculated.

&nbsp;

### Smart Space Division
#### Division
This algorithm first divides the objects along the x-axis by calculating the median of all x positions.
All objects to the left of the median are added to the left space and the objects to the right to the right space.
When objects overlap the median, they are added to the both the spaces.
The left and right space are then divided along the y-axis in the same manner.
When the max depth is reached or a further division is useless, the division is stopped.
A division is useless when 1 or 2 of the resulting spaces contains the same amount of objects as the parent space. 
When the division is complete, a tree like structure has been formed.

#### Collision resolution
The program walks through the tree structure and uses the basic algorithm to detect collisions between the objects in a space.
Objects only have to be tested with other objects of the same space because of the way the spaces are divided.
This is the reason why this algorithm is faster than the basic algorithm.

&nbsp;
&nbsp;

## Preview
An example of the program using the basic algorithm.

<img src="https://github.com/Matts-vdp/Smart-Space-Division/blob/main/preview/ssd-basic.png" width="400">

An example of the program using the smart space division algorithm.

<img src="https://github.com/Matts-vdp/Smart-Space-Division/blob/main/preview/ssd-smart.png" width="400">

&nbsp;
&nbsp;

## Conclusion
While the basic algorithm is good enough for a low amount of objects, the difference is clearly visible when simulating the 10000 objects in the test. 
As seen in the preview images, the basic algorithm takes about 322 ms to calculate 1 frame while the smart algorithm takes only about 5ms.
While the time varies a tiny bit between different frames, the gap between the performance of the basic and smart algorithm stays large.

&nbsp;
&nbsp;

## Disclaimer
### Basic algorithm
The basic algorithm can be optimised so pairs that contain the same objects in a different order then a previously tested shot are not tested.
This could lead to a speed improvement of about 2 times.
While this helps, it is not enough to come close to the speed increase provided by the smart space division algorithm.

### Object amount
When simulating low amounts of objects, the overhead the smart space division provides can slow down the calculations.
While this slow down is very minor, it is something to keep in mind when the target is to simulate a small amount of objects.

### Max depth
The max depth of the space algorithm has a big impact on the resulting calculation time.
For every amount of objects, there will be an optimal max depth to ensure the maximum calculation speed.
If a smaller max depth is used then the groups in the resulting spaces will be to large which causes the basic algorithm to take a long time.
When using a max depth that is to large, the overhead of the further space divisions takes more time than it would take to calculate the divided groups with the basic algorithm.


&nbsp;
&nbsp;

## Usage
You can run the java source code by starting the main routine in the Controller class.
You can also download the latest release in the releases tab.

