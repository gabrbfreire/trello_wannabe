document.getElementById('new-team-form').addEventListener('submit', function () {
  var teamName = document.getElementById('team-name').value;
  submitTeam(teamName);
  event.preventDefault();
});

function submitTeam(teamName) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
      if (this.responseText == "") {
        window.location.href = "bugs.php";
        console.log(teamName);
      } else {
        document.getElementById("result").innerHTML = this.responseText;
      }
    }
  };
  xhttp.open("POST", "php/submit-team.php?teamName=" + teamName, true);
  xhttp.send();
}