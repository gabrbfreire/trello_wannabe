function login(email, password) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {

      if (this.responseText == "Valid") {
        window.location.href = 'main';
      } else {
        document.getElementById("result").innerHTML = this.responseText;
      }

    }
  };
  xhttp.open("POST", "login?email=" + email + "&password=" + password, true);
  xhttp.send();
}


document.getElementById('login-form').addEventListener('submit', function () {
  var email = document.getElementById('user-email').value;
  var password = document.getElementById('user-password').value;
  login(email, password);
  event.preventDefault();
});