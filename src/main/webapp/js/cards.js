
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

    getCards();
    getLists();
    getUserEmail();
});

//SELECT
function getCards(){

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/cards/get",
        success:function(data) {
            console.log(data);
            //buildBoards(data);
        }
    });
}

function getLists(){
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/lists",
        success:function(data) {
            console.log(data);
            //buildBoards(data);
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

