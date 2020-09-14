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

$(document).ready(
    $(document).ready(function () {
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/boards/get",
            success:function(data) {
                console.log(data);
            }
        })
    })
)

function buildBoards(data){
    console.log(data);
}