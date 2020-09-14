function register(name, lastName, email, password, passwordRepeat) {
  let xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {

      if (this.responseText == "Saved") {
        window.location.href = 'boards';
      } else {
        document.getElementById("result").innerHTML = 'this.responseText';
      }

    }
  };
  xhttp.open("POST", "signup?email=" + email + "&password=" + password, true);
  xhttp.send();
}


document.getElementById('register-form').addEventListener('submit', function () {

  let email = document.getElementById('user-email').value;
  let password = document.getElementById('user-password').value;
  let passwordRepeat = document.getElementById('user-repeat-password').value;

  if (password === passwordRepeat) {
    register(email, password, passwordRepeat);
  } else {
    document.getElementById('result').innerHTML = "Passwords do not match";
  }

  event.preventDefault();
});