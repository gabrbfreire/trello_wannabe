$("#logout").on("click", logout);

function logout() {
    $(document).ready(function () {
        $.ajax({
            type: "GET",
            url: "logout",
            success: window.location.href = 'login'
        })
    });
}

$(document).ready(function () {

    let el = document.getElementById("lists-sortable");

    new Sortable(el, {
        group: 'lists',
        animation: 150,
        onUpdate: function (evt){
            let listsIdsOrder = [];

            for(let i=0;i<evt.from.getElementsByClassName('col-md-3').length-1;i++){

                try{
                    evt.from.getElementsByClassName('col-md-3')[i].setAttribute('id', 'list-'+i);
                    listsIdsOrder.push(evt.from.getElementsByClassName('col-md-3')[i].childNodes[0].childNodes[0].childNodes[1].id);
                }catch (e){
                    getLists();
                }
            }
            updateListIndex(listsIdsOrder);
            listsIdsOrder = [];
        },
    });

    getLists();
    getUserEmail();
});

//SELECT
function getLists(){

    $.ajax({
        type: "GET",
        url: "lists",
        success:function(data) {
            buildLists(data);
            getCards(); // Creates cards only after lists are done
        }
    });
}

function getCards(){

    $.ajax({
        type: "GET",
        url: "cards/get",
        success:function(data) {
            buildCards(data);
            setCreateUpdateDeleteEvent();
        }
    });
}

function getUserEmail(){

    $.ajax({
        type: "GET",
        url: "boards/getEmail",
        success:function(data) {
            $('#email').text(data);
        }
    });
}

function buildLists(data) {

    data = _.sortBy(data, "list_index");

    //Removes all lists from page
    $(".row").empty();

    // Creates the "New Board" button
    $('.row').append(
        '<div class="col-md-3" id="new-list-button">' +
        '<div class="card mb-4 box-shadow">' +
        '<button type="button" class="btn btn-sm btn-outline-secondary create-list" data-toggle="modal" data-target="#createList">New List</button>'
    );


    // Creates all lists on page
    data.forEach(function (list, index) {

        $('#new-list-button').before(
            '<div class="col-md-3 board" id="list-' + data[index].list_index + '">' +
            '<div class="card mb-4 box-shadow">' +
            '<div class="card-body">'+
            '<button type="button" class="close delete-list">' +
            '<i class="fas fa-times"></i>' +
            '</button>' +
            '<p class="card-text" id="' + data[index].list_id + '">' + data[index].list_title + '</p>' +
            '<div id="card-list-'+data[index].list_id+'" > </div>'+
            '<div class="card box-shadow">' +
            '<button type="button" class="btn btn-sm btn-outline-secondary create-card-button" data-toggle="modal" data-target="#createCardModal">New Card</button>');

        let el = document.getElementById("card-list-" + data[index].list_id);

        new Sortable(el, {
            group: 'cards', // set both lists to same group
            animation: 150,

            // Update cards index event
            onUpdate: function (evt){
                let cardsIdsOrder = [];

                for(let i=0;i<evt.from.getElementsByClassName('d-flex').length;i++){

                    evt.from.getElementsByClassName('d-flex')[i].setAttribute('id', 'list-'+ list.list_id +'-card-index-'+i);
                    cardsIdsOrder.push(evt.from.getElementsByClassName('d-flex')[i].childNodes[0].id.replace('card-text-',''));
                }
                updateCardsIndex(cardsIdsOrder);
                cardsIdsOrder = [];
            },
            onAdd: function (evt) {
                let cardsIdsOrder = [];

                for(let i=0;i<evt.to.getElementsByClassName('d-flex').length;i++){

                    evt.to.getElementsByClassName('d-flex')[i].setAttribute('id', 'list-'+ list.list_id +'-card-index-'+i);
                    cardsIdsOrder.push(evt.to.getElementsByClassName('d-flex')[i].childNodes[0].id.replace('card-text-',''));
                }
                let itemId = evt.item.id;
                let cardId = $('#'+ itemId).children()[0].id.replace('card-text-','');
                updateCardsList(cardId, list.list_id)
                updateCardsIndex(cardsIdsOrder);
                cardsIdsOrder = [];
            }
        });
    });
}


function buildCards(data){

    data = _.sortBy(data, "card_index");

    data.forEach( function (card, index) {
        $('#'+"card-list-"+ card.list_list_id +'').append(
            '<div class="d-flex justify-content-end border rounded mb-3" id="list-'+ card.list_list_id +'-card-index-'+card.card_index+'">' +
            '<div class="card-body" id="card-text-'+card.card_id+'">'+ card.card_title +'</div>' +
            '<div class="dropdown show">'+
            '<a class="btn btn-secondary bg-light border-0 dropdown-white" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-ellipsis-h text-body"></i></a>'+
            '<div class="dropdown-menu" aria-labelledby="dropdownMenuLink" id="'+data[index].card_id+'">'+
            '<a class="dropdown-item update-button" data-toggle="modal" data-target="#updateCardModal" href="#">Edit</a>'+
            '<a class="dropdown-item delete-button" href="#">Delete</a>'
        )
    });
}


$('#createList').on('shown.bs.modal', function (){
    $('#list-title').focus();
});

$('#createCardModal').on('shown.bs.modal', function (){
    $('#card-title').focus();
});

$('#updateCardModal').on('shown.bs.modal', function (){
    $('#card-new-title').focus();
});


// MODAL EVENTS
let listId, cardId, cardIndex;

// CREATE card
$('#create-card-modal-form').on('submit', function (e){
    e.preventDefault();
    $('#createCardModal').modal('toggle');
    let cardTitle = $('#card-title').val();

    $.ajax({
        type: "POST",
        url: "cards",
        data: {title: cardTitle, listId: listId, cardIndex: cardIndex},
        success:function() {
            getLists();
            $('#card-title').val('');
        }
    });
});

// UPDATE card
$('#update-card-modal-form').on('submit', function (e){
    e.preventDefault();
    $('#updateCardModal').modal('toggle');
    let newTitle = $('#card-new-title').val();

    $.ajax({
        type: "PUT",
        url: "cards/updateTitle",
        data: {cardId: cardId, newTitle: newTitle},
        success:function() {
            getLists();
        }
    });
});

// UPDATE Cards index
function updateCardsIndex(cardsIdsOrder){
    cardsIdsOrder.forEach(function (data, index){
        $.ajax({
            type: "PUT",
            url: "cards/updateIndex",
            data: {cardId: data, newIndex: index}
        });
    })
}

// UPDATE Cards list
function updateCardsList(cardId, listId){
    $.ajax({
        type: "PUT",
        url: "/cards/updateList",
        data: {cardId: cardId, newList: listId},
    });
}

//CREATE lists
$('#create-list-modal-form').on('submit', function (e){
    e.preventDefault();
    $('#createList').modal('toggle');
    let listTitle = $('#list-title').val();
    let listIndex;

    try {
        listIndex = $('#lists-sortable').children('.board').attr('id').replace('list-','');
        listIndex++;
        console.log(listIndex);
    }catch (e){
        listIndex = 0;
    }

    $.ajax({
        type: "POST",
        url: "lists",
        data: {index: listIndex, title: listTitle},
        success:function() {
            getLists();
            $('#list-title').val('');
        }
    });
});

// UPDATE Lists index
function updateListIndex(listsIdsOrder){
    listsIdsOrder.forEach(function (data, index) {
        $.ajax({
            type: "PUT",
            url: "/lists/updateIndex",
            data: {listId: data, newIndex: index}
        })
    });
}


// LIST EVENTS
function setCreateUpdateDeleteEvent() {

    let cardTitle;

    // CREATE
    $('.create-card-button').on('click', function (){
        listId = $(this).parent().siblings('.card-text').attr('id');
        try {
            cardIndex = $('#card-list-'+listId).children().last().attr('id');
            cardIndex = cardIndex.replace('list-'+ listId +'-card-index-', '');
            cardIndex++;
        }catch (e){
            cardIndex = 0;
        }
    });


    // DELETE
    $('.delete-button').on('click', function (){
        cardId = $(this).closest('div').attr('id');

        $.ajax({
            type: "DELETE",
            url: "cards",
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


    // DELETE lists
    $('.delete-list').on('click', function (){
        listId = $(this).siblings('.card-text').attr('id');

        $.ajax({
            type: "DELETE",
            url: "lists",
            data: {listId: listId},
            success:function() {
                getLists();
            }
        });
    });
}