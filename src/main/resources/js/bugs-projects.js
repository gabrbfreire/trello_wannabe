//Loads project anchors
function loadProjects() {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
      if (this.responseText == "") { } else {

        var projectsJson = JSON.parse(this.response);
        createProjectsAnchors(projectsJson);

      }
    }
  };
  xhttp.open("POST", "php/select-projects.php?teamId=" + teamId, true);
  xhttp.send();
}

function createProjectsAnchors(projectsJson) {
  destroyAnchors();
  destroyBugLists();

  var projects = [];
  for (var len in projectsJson) {
    var id = "projectsJson." + len + ".id";
    var title = "projectsJson." + len + ".title";
    var team = "projectsJson." + len + ".team";
    projects.push(eval(id));

    var projectAnchor = document.createElement("a");
    projectAnchor.setAttribute("class", "align-middle mr-5 project-anchors");
    projectAnchor.setAttribute("id", eval(id) + "-project");
    projectAnchor.setAttribute("href", "#");
    projectAnchor.innerHTML = eval(title);
    document.getElementById("projects-col").appendChild(projectAnchor);

    //Checks if project is already loaded
    document.getElementById(eval(id) + "-project").addEventListener('click', function () {
      if (projectId != this.id.split("-")[0]) {
        projectId = this.id.split("-")[0];
        destroyBugLists();
        createBugs();
      }
    });
  }
  clickFirstAnchor(projects);
}


function destroyBugLists() {
  $('.bug-card').remove();
}

function destroyAnchors() {
  $('.project-anchors').remove();
}

//Loads first project
function clickFirstAnchor(projects) {
  projectId = projects[0];
  createBugs();
}




//Create new project
document.getElementById('add-project-form').addEventListener('submit', function () {
  var projectName = document.getElementById('project-name').value;
  createProject(projectName);
  event.preventDefault();
});

function createProject(projectName) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
      if (this.responseText == "") {
        window.location.href = "bugs.php";
      }
    }
  };
  xhttp.open("POST", "php/submit-project.php?projectName=" + projectName + "&teamId=" + teamId, true);
  xhttp.send();
}