/* function login(email, password) {
  let xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {

      if (this.status == 200) {
        window.location.href = 'boards';
      } else {
        document.getElementById("result").innerHTML = this.responseText;
      }
    }
  };
  xhttp.open("POST", "login?email=" + email + "&password=" + password, true);
  xhttp.send();
} */


$('#login-form').on('submit', function () {
  let email = $('#user-email').value;
  let password = $('#user-password').value;
  login(email, password);
  event.preventDefault();
});

function login(email, password) {
  $(document).ready(function () {
    $.ajax({
      type: "POST",
      url: "http://localhost:8080/login",
      data: {email: email, password: password},
      success:function() {
        window.location.href = 'boards'
      },
      error: function (){
        console.log('The username or password is incorrect');
        $('#result').text('The username or password is incorrect');
      }
    })
  });
}