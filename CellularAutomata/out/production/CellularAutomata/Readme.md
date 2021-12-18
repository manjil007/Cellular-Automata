<h3> Description </h3>
This program creates a visual representation of cellular automata 
animation for different dimension and different stages. The user 
can create a cellular automata with one dimension two stages, two 
dimension two stages (Conway's game of life), and two dimension 
eight stages (Langton's loop). The program uses JavaFx to visualize
the animation along with Generation classes (different for one 
dimension and two dimension) to create a new generation and update
the current generation. </br> 
An Elementary Cellular Automata and Conway's Game of life is a sequence of symbol with symbols "0" 
and "1" where "0" means dead and "1" means live. A langton's loop
consists of eight stages which are represented by different color.
For an elementary cellular automata, the program considers the 
current cell itself, and the two adjacent cells to the left and
right as its neighbors. </br>
In Conway's game of life, the program considers the all the surrounding cells which are represented by
their position from the current cell i.e. East, North, South, West,
NorthWest, NorthEast, SouthEast, and SouthWest. 
</br> The rule for langton's loop is different as its state is determined by its 
current state, and its neighbor's state with its rotational symmetry of
North, East, South and West. The Generation classes of
each cellular automata class uses evolve to create a new 
generation by using the rule mentioned above. 

<h3> How to use it </h3>
1. The program will ask user to choose the dimension followed by stage.</br>

<h5> Elementary CA </h5> </br>
2. In elementary CA user will have option to enter the input or select the file user wants to run. </br>
3. If user selects to enter the input, user will have to enter the integer for rule and its initial state in binary string. </br>
4. If user selects to run the animation from file, user will be provided with number of option to select the file he/she wants to run. </br>
5. Output will be displayed in a different window. 

<h5> Conway's Game of life </h5> </br>
6. In Conway's game of life user will have option to enter the input or select the file user wants to run. </br>
7. If user selects to enter the input, user will have to enter the behavior and initial state. 
8. If user selects to run the animation from file, user will be provided with number of option to select the file he/she wants to run. </br>
9. Output will be displayed in a different window. </br> 

<h5> The Langton's loop </h5> </br>
10. Output will be displyed in a different window after the stages in selected. 

