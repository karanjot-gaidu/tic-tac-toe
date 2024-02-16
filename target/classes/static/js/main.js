var turns = [["#", "#", "#"], ["#", "#", "#"], ["#", "#", "#"]];
var turn = "";
var gameOn = true; // Initialize gameOn flag to enable game
// declare what to do in the case of a player's turn
function playerTurn(turn, id) {
    if (gameOn) {
        var spotTaken = $("#" + id).text();
        // if move possible
        if (spotTaken === "#") {
            makeAMove(playerType, id.split("_")[0], id.split("_")[1]);
        }
        else
        {
            // if not possible to move
            alert("It's not your turn!");

        }
    }
}

// Improvement: "move lock" can be added to the method
// allows for player to move to a specific x and y coordinate given
function makeAMove(type, coordinateX, coordinateY) {
    $.ajax({
        url: url + "/game/gameplay",
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            "type": type,
            "coordinateX": coordinateX,
            "coordinateY": coordinateY,
            "gameId": gameId
        }),
        success: function (data) {
            gameOn = false;
            displayResponse(data);

        },
        error: function (error) {
            console.log(error);
        }
    })
}

// display the results based on the given data
function displayResponse(data)
{
    let board = data.board;
    for (let i = 0; i < board.length; i++) {
        for (let j = 0; j < board[i].length; j++) {
            if (board[i][j] === 1) {
                turns[i][j] = 'X'
            } else if (board[i][j] === 2) {
                turns[i][j] = 'O';
            }
            let id = i + "_" + j;
            $("#" + id).text(turns[i][j]);
        }
    }
    if (data.winner != null) {
        // alert("Winner is " + data.winner);
        gameOn = false; // disable gameOn flag after game is over
        document.getElementById("celebration").style.display = "block";
        document.getElementById("winner").style.display = "block";
        document.getElementById("winner").innerHTML = "Winner is " + data.winner;
    } else {
        gameOn = true; // enable gameOn flag for next player's turn
    }
}

// create turn based on slot and id
$(".tic").click(function () {
    var slot = $(this).attr('id');
    playerTurn(turn, slot);
});

// resets the board to default state
function reset()
{
    turns = [["#", "#", "#"], ["#", "#", "#"], ["#", "#", "#"]];
    $(".tic").text("#");
}

// initialize use of reset function
$("#reset").click(function () {
    reset();
});