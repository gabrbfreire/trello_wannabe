function login(email, password) {
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
}


document.getElementById('login-form').addEventListener('submit', function () {
  let email = document.getElementById('user-email').value;
  let password = document.getElementById('user-password').value;
  login(email, password);
  event.preventDefault();
});