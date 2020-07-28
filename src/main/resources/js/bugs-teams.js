function loadTeams() {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
      if (this.responseText == "") { } else {

        var teamsJson = JSON.parse(this.response);
        createTeamsButtons(teamsJson);

      }
    }
  };
  xhttp.open("POST", "php/select-teams.php", true);
  xhttp.send();
}

function createTeamsButtons(teamsJson) {
  var teams = [];
  for (var len in teamsJson) {
    var id = "teamsJson." + len + ".id";
    var name = "teamsJson." + len + ".name";
    teams.push(eval(id));

    var teamButton = document.createElement("button");
    teamButton.setAttribute("class", "btn btn-primary mr-4");
    teamButton.setAttribute("id", eval(id) + "-team");
    teamButton.innerHTML = eval(name);
    document.getElementById("teams-buttons").appendChild(teamButton);

    document.getElementById(eval(id) + "-team").addEventListener('click', function () {
      if (teamId != this.id.split("-")[0]) {
        teamId = this.id.split("-")[0];
        console.log(teamId);
        loadProjects();
      }
    });
  }

  teamId = teams[0];
  loadProjects();
}
