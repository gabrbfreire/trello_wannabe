document.getElementById('login-form').addEventListener('submit', function () {
  var name = document.getElementById('user-email').value;
  var password = document.getElementById('user-password').value;
  login(name, password);
  event.preventDefault();
});


function login(name, password) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {

      if (this.responseText == "") {
        window.location.href = 'bugs.php';
      } else {
        document.getElementById("result").innerHTML = this.responseText;
      }

    }
  };
  xhttp.open("POST", "php/login.php?name=" + name + "&password=" + password, true);
  xhttp.send();
}