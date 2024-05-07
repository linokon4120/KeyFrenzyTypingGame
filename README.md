# CSCI 205 - Software Engineering and Design
Bucknell University
Lewisburg, PA
### Course Info
Instructor: Lily Romano, Joshua Stough
Semester: Spring 2024
## Team Information
>1. Scrum Master: Ellyn Ngo - Sophomore - Business Analytics and Computer Science
>2. Developer: Rahul - Sophomore - Computer Science Major; Economics Minor
>3. Developer: Holiness - Sophomore - Computer Science and Engineering 
>4. Product Owner: Hannah - Sophomore - Computer Science


## Project Information
In this project, we created an arcade game application called KeyFrenzy, with the main objective of helping the user increase typing speed, responsiveness and accuracy in the English language as they proceed within the game. 

The main task of the player is to defend the main objective (main character) from being attacked by another objective (the ghost). To do so, the player must input a string of characters that is of the English language that matches the one prompted on top of the attacking objective, and if the string is inputted before the attacking objective reaches the defended objective, the attacker shall be eliminated. The movement of the attackers is characterised by the x-y axis with the defended objective located at the intersection of both axes. The player would be given a set of chances called “lives” to attempt to defend the main character from being attacked. The specifics of the game is to be determined as it is being developed and tested, including final graphics, character appearances and behaviour, and player-related numerical technicalities.

The game would have multiple levels, with every level going up the complexity of the provided strings and the number of strings would increase accordingly, and the input time for the player would be decreased accordingly. Customisation would be provided for the main character in the form of naming. Background maps would be altered within every level to increase in-game dynamics. If all the levels are passed, the player will win. If the player lost in only one of the levels, they would have to restart the entire game. The level would be discrete, and the changes would be displayed instantaneously as the user progresses to the next level.
*Write a few sentences about your project. This can be done later, as you
may not quite know all the details yet.*

Welcome to our KeyFrenzy Game. it is a dynamic typing game using Java Fx to improve typing skills and reaction time as well.
* The game's core revolves around a player typing words correctly to "destroy" on-screen ghosts, each labeled with words that appear randomly and increase in difficulty as the player advances through levels. 
* The gameplay environment is set up using a GridPane within a VBox layout, where animated ghosts traverse the screen, challenging the player to type the associated words before the ghosts reach the center of the screen. 
* Ghost movements and interactions are managed by an AnimationTimer and PathTransition, ensuring smooth and responsive gameplay. The game's architecture follows the MVC (Model-View-Controller) pattern, with the KeyFrenzyGame Contoller class and ghost animation  class handling the UI components and user interactions, while game logic and state are managed in separate controller and model classes. 
* Additionally, a custom WordDictionary class loads words from a file into a map, categorized by word length, to dynamically adjust the game's difficulty based on the player's level. 
* Player progress, including score and health, is continuously updated and displayed, with the game's state (paused, ongoing, or ended) controlled via UI elements like buttons. 
* The game concludes either when the player runs out of health or successfully completes all levels, with a transition to a game over screen managed through JavaFX's FXMLLoader.


## Package structure
1. The ghosts Package handles all animation of the ghosts on the screen which includes 
* The GhostAnimation which handle the movement of the ghost in the game
* The Ghost file that initiate the actual ghosts in the screen
2. The typing mechanism package includes the different ways the words would be handled in the game from a random dictionary that will be used in the game. 
3. Lastly the GameController sets up the entire game and runs the entire program. This package includes classes that control how the ghost actually moves and every component of the game. 
* Controls what screen is being shown based on the events that are happening such as a welcome screen and a GameOver Screen.
* This is the most important package. 


## Third-party Libraries
* JavaFX, CSS

## How to run it
Simply run the GameMain file in the Game Controller package and enjoy the game!

## Demo video
Link to Bucknell Media Space:
https://mediaspace.bucknell.edu/media/CSCI+Final+Proj+1/1_d1a646dh