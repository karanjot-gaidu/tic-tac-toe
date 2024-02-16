# w23-csci2020u-project-team06
| Collaborators |
| --- |
|Amandeep Saroa|
|Brock Davidge|
|Karan Gaidu|
|Varun Gagwani|
|Xuan Zheng|

# Project Information
- This is a web-based implementation of the popular game Tic Tac Toe. 
The game allows two players to play against each other on a 3x3 board. 
Players take turns to mark their symbol (X or O) on the board, and the first player to get three symbols in a row (horizontally, vertically, or diagonally) wins the game. 
If no player is able to achieve this, the game is a draw.

- The project is implemented using HTML, CSS, and JavaScript on the front-end, and uses a Node.js back-end with a RESTful API to handle game logic and persistence. 
The back-end is connected to a MongoDB database to store game data.

- The front-end provides a simple and intuitive user interface for players to start a new game or join an existing game by entering a game ID. 
Once both players have joined the game, they can take turns marking their symbol on the board until the game is won or drawn. 
The front-end also provides feedback to the players on the game status, such as whose turn it is, whether the game is won or drawn, and who the winner is.

- The back-end handles game logic and persistence by exposing a RESTful API that provides endpoints for creating a new game, joining an existing game, making a move on the board, and retrieving game data. 
The back-end is designed to handle multiple games at once, with each game having a unique ID and state that is stored in the database.

- Overall, this project provides a fun and engaging way for players to play the classic game of Tic Tac Toe with each other online. 
The implementation is simple and lightweight, making it easy to deploy and maintain, and the use of a RESTful API and database allows for scalability and flexibility in handling multiple games and players.

# How to Run the Game
To run the game, follow these steps:
1. Ensure that you have Java and Maven installed on your system.
2. Download or clone the project from the GitHub repository https://github.com/OntarioTech-CS-program/w23-csci2020u-project-team06.git.
3. Open IntelliJ IDEA and navigate to the project directory in your terminal.
4. Once the build is complete, run the TicTacToeApplication.java file.
![Test Webpage](/src/main/resources/static/images/build_complete.jpg)
5. Open a web browser and go to the following URL: http://localhost:8080 to start the game.
![Test Webpage](/src/main/resources/static/images/game_page.jpg)

# How to Play the Game
Once you have started the game, follow these steps to play:
1. Enter your name or user ID and click the "Create a New Game" button.
2. The game board will be displayed with empty squares.
![Test Webpage](/src/main/resources/static/images/createNewGame.jpg)
3. Open a new web browser and go to the following URL: http://localhost:8080.
4. Enter the second player's username and click the "Connect to Random Game" button to start the game.
![Test Webpage](/src/main/resources/static/images/connectRandomGame.jpg)
5. The first player will be prompted to make a move by clicking on an empty square on the board.
![Test Webpage](/src/main/resources/static/images/firstPlayerMove.jpg)
6. After the first player has made their move, the second player will be prompted to make a move.
![Test Webpage](/src/main/resources/static/images/secondPlayerMove.jpg)
7. Players will take turns making moves until one player wins or the board is filled with no winner.
![Test Webpage](/src/main/resources/static/images/noWins.jpg)
8. If a player wins, the game will display a message declaring the winner and offer the option to play again.
![Test Webpage](/src/main/resources/static/images/aPlayerWins.jpg)

# Improvements
- Refactored the code by extracting the calculation process into a separate function to eliminate code duplication and improve code readability.
- Filtered out non-alphabetic words from the training and testing files to ensure that only relevant words are used to train and test the model, thus improving the accuracy of the detection.
- Improved the user experience by designing a more intuitive and user-friendly interface that displays the accuracy and precision of the detection, making it easier for users to understand and interpret the results.


# Video Demo
[![Video](https://img.youtube.com/vi/WZLi76jSpbg/0.jpg)](https://youtu.be/WZLi76jSpbg)

# References:
- Spring Boot
- Maven
- Bootstrap
- jQuery
- https://codepen.io/marxcom/pen/LWQXRX
