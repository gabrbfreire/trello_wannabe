$(document).ready(function () {
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/logout"
  })
});

$('#register-form').on('submit', function (e) {
  e.preventDefault();

  let email = $('#user-email').val();
  let password = $('#user-password').val();
  let passwordRepeat = $('#user-repeat-password').val();

  if (password == passwordRepeat) {
    register(email, password, passwordRepeat);
  } else {
    document.getElementById('result').innerHTML = "Passwords do not match";
  }
});

function register(email, password, passwordRepeat) {

  $(document).ready(function () {
    $.ajax({
      type: "POST",
      url: "http://localhost:8080/signup",
      data: {email: email, password: password},
      success:function() {
        window.location.href = 'login'
      },
      error: function (){
        $('#result').text('User already exists');
      }
    })
  });
}
