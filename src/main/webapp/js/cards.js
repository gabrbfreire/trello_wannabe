
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
            buildCards(data);
            setCreateUpdateDeleteEvent();
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
        '<button type="button" class="btn btn-sm btn-outline-secondary" data-toggle="modal" data-target="#createList">New List</button>'
    );

    // Creates all boards on page
    data.forEach( function (list, index) {

        $('#new-list-button').before(
            '<div class="col-md-3 board">' +
            '<div class="card mb-4 box-shadow">' +
            '<div class="card-body">' +
            '<p class="card-text" id="'+ data[index].list_id +'">' + data[index].list_title + '</p>' +
            '<div class="card box-shadow">' +
            '<button type="button" class="btn btn-sm btn-outline-secondary create-card-button" data-toggle="modal" data-target="#createCardModal">New Card</button>'
        )
    });
}

function buildCards(data){


    data.forEach( function (card, index) {

        $('#'+ card.list_list_id +'').after(
            '<div class="d-flex justify-content-end border rounded mb-3">' +
            '<div class="card-body" id="card-text-'+data[index].card_id+'">'+ card.card_title +'</div>' +
            '<div class="dropdown show">'+
            '<a class="btn btn-secondary bg-light border-0 dropdown-white" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-ellipsis-h text-body"></i></a>'+
            '<div class="dropdown-menu" aria-labelledby="dropdownMenuLink" id="'+data[index].card_id+'">'+
            '<a class="dropdown-item update-button" data-toggle="modal" data-target="#updateCardModal" href="#">Edit</a>'+
            '<a class="dropdown-item delete-button" href="#">Delete</a>'
        )
    });
}



function createNewCard(){
    let cardTitle = $('#card-title').val();

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/cards?title=macaco&listId=3",
        data: {title: cardTitle},
        success:function() {
            getBoards();
        }
    });
}

let listId, cardId;
$('#create-card-modal-button').on('click', function (){
    cardTitle = $('#card-title').val();

    console.log(cardTitle)
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/cards",
        data: {title: cardTitle, listId: listId},
        success:function() {
            getLists();
        }
    });
});

$('#update-card-modal-button').on('click', function (){
    let newTitle = $('#card-new-title').val();

    $.ajax({
        type: "PUT",
        url: "http://localhost:8080/cards/updateTitle",
        data: {cardId: cardId, newTitle: newTitle},
        success:function() {
            getLists();
        }
    });
});

function setCreateUpdateDeleteEvent() {
    let cardTitle;

    // CREATE
    $('.create-card-button').on('click', function (){
        listId = $(this).parent().siblings('.card-text').attr('id');
    });


    // DELETE
    $('.delete-button').on('click', function (){
        cardId = $(this).closest('div').attr('id');

        $.ajax({
            type: "DELETE",
            url: "http://localhost:8080/cards",
            data: {cardId: cardId},
            success:function() {
                getLists();
            }
        });
    });

    // UPDATE
    $('.update-button').on('click', function () {
        cardId = $(this).closest('div').attr('id');
        cardTitle = $('#card-text-' + cardId).html();

        // Shows current name on the update input
        $('#card-new-title').val(cardTitle);
    });
}