$("#logout").on("click", logout);

function logout() {
    $(document).ready(function () {
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/logout",
            success: window.location.href = 'login'
        })
    });
}

$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/endBoardSession"
    })
    getBoards();
    getUserEmail();
});

//SELECT
function getBoards(){

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/boards/get",
        success:function(data) {
            buildBoards(data);
            setViewDeleteUpdateEvents();
        }
    });
}

function getUserEmail(){

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/boards/getEmail",
        success:function(data) {
            $('#email').text(data);
        }
    });
}

function buildBoards(data){

    // Removes all boards from page
    $( ".row" ).empty();

    // Creates the "New Board" button
    $('.row').append(
                '<div class="col-md-3" id="new-board-button">'+
                '<div class="card mb-4 box-shadow">'+
                '<button type="button" class="btn btn-sm btn-outline-secondary" data-toggle="modal" data-target="#newBoardModal">New Board</button>'
    );

    // Creates all boards on page
    data.forEach( function (board, index) {

        $('#new-board-button').before(
            '<div class="col-md-3 board">' +
            '<div class="card mb-4 box-shadow">' +
            '<div class="card-body">' +
            '<p class="card-text" id="card-text-'+data[index].board_id+'">' + data[index].board_name + '</p>' +
            '<div class="d-flex justify-content-end">' +
            '<div class="btn-group" id="'+ data[index].board_id +'">' +
            '<button type="button" class="view-button btn btn-sm btn-outline-secondary">View</button>' +
            '<button type="button" class="update-button btn btn-sm btn-outline-secondary" data-toggle="modal" data-target="#updateBoardModal">Edit</button>' +
            '<button type="button" class="delete-button btn btn-sm btn-outline-secondary" data-toggle="modal" data-target="#deleteBoardModal">Delete</button>'
        )
    });
}

//CREATE
$('#new-board-modal-button').on('click', createNewBoard);

function createNewBoard(){
    let boardName = $('#board-name').val();

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/boards/",
        data: {boardName: boardName},
        success:function() {
            getBoards();
        }
    });
}

// Creates events after the boards are created
function setViewDeleteUpdateEvents(){
    let boardId, boardName;

    //DELETE
    $('.delete-button').on('click', function (){
        boardId = $(this).closest('div').attr('id');
        boardName = $('#card-text-' + boardId).html();

        // Shows current name on the delete modal title
        $('#delete-modal-title').html(boardName);
    });

    $('#delete-modal-button').on('click', function (){
        $.ajax({
            type: "DELETE",
            url: "http://localhost:8080/boards",
            data: {boardId: boardId},
            success:function() {
                getBoards();
            }
        });
    });

    //UPDATE
    $('.update-button').on('click', function (){
        boardId = $(this).closest('div').attr('id');
        boardName = $('#card-text-' + boardId).html();

        // Shows current name on the update input
        $('#board-new-name').val(boardName);
    });

    $('#update-modal-button').on('click', function (){
        let boardNewName = $('#board-new-name').val();

        $.ajax({
            type: "PUT",
            url: "http://localhost:8080/boards",
            data: {boardId: boardId, boardNewName: boardNewName},
            success:function() {
                getBoards();
            }
        });
    });

    //VIEW
    $('.view-button').on('click', function (){
        boardId = $(this).closest('div').attr('id');

        $.ajax({
            type: "POST",
            url: "http://localhost:8080/boards/storeBoardId",
            data: {boardId: boardId},
            success:function() {
                window.location.href = 'cards'
            }
        });
    });

}

