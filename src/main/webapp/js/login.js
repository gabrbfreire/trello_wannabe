$(document).ready(function () {
  $.ajax({
    type: "GET",
    url: "logout"
  })
});

$('#login-form').on('submit', function (e) {
  let email = $('#user-email').val();
  let password = $('#user-password').val();
  login(email, password);
  e.preventDefault();
});

function login(email, password) {
  $(document).ready(function () {
    $.ajax({
      type: "POST",
      url: "login",
      data: {email: email, password: password},
      success:function() {
        window.location.href = 'boards'
      },
      error: function (){
        $('#result').text('The username or password is incorrect');
      }
    })
  });
}


