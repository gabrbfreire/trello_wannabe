var updateId = 0;
var teamId = 0;
var projectId = 0;

//SELECT
//Loads bugs from DB
window.addEventListener("load", function () {
  loadTeams();
  loadProjects();
});



function createBugs() {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
      if (this.responseText == "") { } else {
        var bugsJson = JSON.parse(this.response);
        loadBugs(bugsJson);
      }
    }
  };
  xhttp.open("POST", "php/select-bugs.php?projectId=" + projectId, true);
  xhttp.send();
}


function loadBugs(bugsJson) {
  for (var len in bugsJson) {
    var id = "bugsJson." + len + ".id";
    var title = "bugsJson." + len + ".title";
    var desc = "bugsJson." + len + ".desc";
    var status = "bugsJson." + len + ".status";

    var bugList = "";

    //Determines in which list each bug goes
    switch (eval(status)) {
      case "0":
        bugList = "bug-list-open";
        break;
      case "1":
        bugList = "bug-list-in-progress";
        break;
      case "2":
        bugList = "bug-list-tested";
        break;
      case "3":
        bugList = "bug-list-reopen";
        break;
      case "4":
        bugList = "bug-list-closed";
        break;
    }

    //Creates cards for bugs
    createCard(id, title, desc, bugList, bugsJson);
  }
}

//Creates cards for bugs
function createCard(id, title, desc, bugList, bugsJson) {
  var card = document.createElement("div");
  card.setAttribute("class", "card mb-1 bug-card");
  card.setAttribute("id", eval(id));
  document.getElementById(bugList).appendChild(card);

  var cardBody = document.createElement("div");
  cardBody.setAttribute("class", "card-body");
  card.appendChild(cardBody);

  var cardTitle = document.createElement("h5");
  cardTitle.setAttribute("class", "card-title");
  cardTitle.innerHTML = eval(title);
  cardBody.appendChild(cardTitle);

  var cardText = document.createElement("p");
  cardText.setAttribute("class", "card-text m-0 mw-1");
  cardText.innerHTML = eval(desc);
  cardBody.appendChild(cardText);

  var cardButtonDelete = document.createElement("button");
  cardButtonDelete.setAttribute(
    "class",
    "btn btn-primary-outline float-right p-0"
  );
  cardButtonDelete.setAttribute("id", eval(id) + "-delete");
  cardButtonDelete.innerHTML = "X";
  cardTitle.appendChild(cardButtonDelete);

  //Deletes when x is clicked
  document
    .getElementById(eval(id) + "-delete")
    .addEventListener("click", function () {
      console.log(this.id.split("-")[0]); //Get id number before "-"
      deleteBug(this.id.split("-")[0]);
    });

  var cardButtonAlter = document.createElement("button");
  cardButtonAlter.setAttribute(
    "class",
    "btn btn-primary-outline float-right p-0"
  );
  cardButtonAlter.setAttribute("id", eval(id) + "-update");
  cardButtonAlter.setAttribute("data-toggle", "modal");
  cardButtonAlter.setAttribute("data-target", "#updateBugModal");
  //data-toggle="modal" data-target="#updateBugModal"
  cardButtonAlter.innerHTML = "...";
  cardBody.appendChild(cardButtonAlter);

  //when "update" is clicked
  document.getElementById(eval(id) + "-update").addEventListener("click", function () {
    updateId = this.id.split("-")[0]; //Get id number before "-"
    document.getElementById("bug-title-update").value = eval(title);
    document.getElementById("bug-desc-update").value = eval(desc);
  });
}



//INSERT
//Inserts new bug in DB
document.getElementById("new-bug-form").addEventListener("submit", function () {
  var bugTitle = document.getElementById("bug-title").value;
  var bugDesc = document.getElementById("bug-desc").value;
  submitNewBug(bugTitle, bugDesc, projectId);
  event.preventDefault();
});

function submitNewBug(bugTitle, bugDesc, projectId) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
      if (this.responseText == "") {
        window.location.href = "bugs.php";
      } else {
        document.getElementById("result").innerHTML = this.responseText;
      }
    }
  };
  xhttp.open(
    "POST",
    "php/submit-bug.php?bugTitle=" +
    bugTitle +
    "&bugDesc=" +
    bugDesc +
    "&projectId=" +
    projectId,
    true
  );
  xhttp.send();
}



//DELETE
function deleteBug(id) {
  console.log(id);
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
      if (this.responseText == "") {
        window.location.href = "bugs.php";
      } else {
        document.getElementById("result").innerHTML = this.responseText;
      }
    }
  };
  xhttp.open("POST", "php/delete-bug.php?id=" + id);
  xhttp.send();
}



//UPDATE
document.getElementById("update-bug-form").addEventListener("submit", function () {
  console.log(document.getElementById("bug-title-update").value);
  var title = document.getElementById("bug-title-update").value
  var desc = document.getElementById("bug-desc-update").value
  updateBug(updateId, title, desc);
  event.preventDefault();
});


function updateBug(id, title, desc) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
      if (this.responseText == "") {
        window.location.href = "bugs.php";
      }
    }
  };
  xhttp.open("POST", "php/update-bug.php?bugId=" + id + "&bugTitle=" + title + "&bugDesc=" + desc, true);
  xhttp.send();
}



//LOGOUT
document.getElementById('logout-button').addEventListener('click', logout);

function logout() {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
      if (this.responseText == "") {
        window.location.href = 'index.html';
      }
    }
  };
  xhttp.open("POST", "php/logout.php", true);
  xhttp.send();
}