function register(name, lastName, email, password, passwordRepeat) {
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
  xhttp.open("POST", "php/register.php?name=" +
    name + "&lastName=" + lastName + "&email=" + email + "&password=" + password, true);
  xhttp.send();
}


document.getElementById('register-form').addEventListener('submit', function () {
  var name = document.getElementById('user-name').value;
  var lastName = document.getElementById('user-last-name').value;
  var email = document.getElementById('user-email').value;
  var password = document.getElementById('user-password').value;
  var passwordRepeat = document.getElementById('user-repeat-password').value;

  if (password === passwordRepeat) {
    register(name, lastName, email, password, passwordRepeat);
  } else {
    document.getElementById('result').innerHTML = "Passwords do not match";
  }

  event.preventDefault();
});