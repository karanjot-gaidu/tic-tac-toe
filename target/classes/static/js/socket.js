const url = 'http://localhost:8080';
let stompClient;
let gameId;
let playerType;

// allows for connecting to socket given a specific game id
function connectToSocket(gameId) {

    console.log("connecting to the game");
    let socket = new SockJS(url + "/gameplay");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("connected to the frame: " + frame);
        stompClient.subscribe("/topic/game-progress/" + gameId, function (response) {
            let data = JSON.parse(response.body);
            console.log(data);
            displayResponse(data);
        })
    })
}

// initialize creation of a new game
function createGame() {
    let login = document.getElementById("login").value;
    if (login == null || login === '') {
        alert("Please Enter Login");
    } else {
        $.ajax({
            url: url + "/game/start",
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                "login": login
            }),
            success: function (data) {
                gameId = data.gameId;
                playerType = 'X';
                reset();
                connectToSocket(gameId);
                alert("Your created a game. Game id is: " + data.gameId);
                gameOn = true;
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}

// connect to a random function
function connectToRandom() {
    let login = document.getElementById("login").value;
    if (login == null || login === '') {
        alert("Please Enter Username");
    } else {
        $.ajax({
            url: url + "/game/connect/random",
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                "login": login
            }),
            success: function (data) {
                gameId = data.gameId;
                playerType = 'O';
                reset();
                connectToSocket(gameId);
                alert("Congrats you're playing with: " + data.player1.login);
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}

// connect to a specific game
// this method is working will. However if you use it linked a specific game, then
// you will lose the connection with localhost:8080/gameplay (error code 500)
function connectToSpecificGame() {
    let login = document.getElementById("login").value;
    if (login == null || login === '') {
        alert("Please Enter Username");
    } else {
        let gameId = document.getElementById("game_id").value;
        if (gameId == null || gameId === '') {
            alert("Please enter game id");
        } else {
            $.ajax({
                url: url + "/game/connect",
                type: 'POST',
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify({
                    "player": {
                        "login": login
                    },
                    "gameId": gameId
                }),
                success: function (data) {
                    gameId = data.gameId;
                    playerType = 'O';
                    reset();
                    connectToSocket(data.gameId);
                    alert("Congrats you're playing with: " + data.player1.login);
                },
                error: function (error) {
                    console.log(error);
                }
            });
        }
    }
}