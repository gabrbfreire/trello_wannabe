
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

    getLists();
    getUserEmail();
});

//SELECT
function getLists(){

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/lists",
        success:function(data) {
            buildLists(data);
            getCards(); // Creates cards only after lists are done
        }
    });
}

function getCards(){

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/cards/get",
        success:function(data) {
            console.log(data);
            buildCards(data)
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


function buildLists(data){

    //Removes all lists from page
    $(".row").empty();

    // Creates the "New Board" button
    $('.row').append(
        '<div class="col-md-3" id="new-list-button">'+
        '<div class="card mb-4 box-shadow">'+
        '<button type="button" class="btn btn-sm btn-outline-secondary" data-toggle="modal" data-target="#newBoardList">New List</button>'
    );

    // Creates all boards on page
    data.forEach( function (list, index) {

        $('#new-list-button').before(
            '<div class="col-md-3 board">' +
            '<div class="card mb-4 box-shadow">' +
            '<div class="card-body">' +
            '<p class="card-text" id="'+ data[index].list_id +'">' + data[index].list_title + '</p>' +
            '<div class="card box-shadow">' +
            '<button type="button" class="btn btn-sm btn-outline-secondary">New Card</button>'
        )
    });
}

function buildCards(data){

    data.forEach( function (card, index) {
        console.log(card.card_title)

        $('#'+ card.list_list_id +'').after(
            '<div class="d-flex justify-content-end border rounded mb-3">' +
            '<div class="card-body">'+ card.card_title +'</div>' +
            '<button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>'
        )
    });
}
