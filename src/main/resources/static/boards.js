createBoards();

function createBoards() {
    console.log("asd")
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            if (this.responseText == "") { } else {
                let bugsJson = JSON.parse(this.response);
                //loadBugs(bugsJson);
                console.log("asd")
            }
        }
    };
    xhttp.open("POST", "boards", true);
    xhttp.send();
}


function getBoardsFromJson(bugsJson) {
    for (let len in bugsJson) {
        let id = "bugsJson." + len + ".id";
        let title = "bugsJson." + len + ".title";
        let desc = "bugsJson." + len + ".desc";
        let status = "bugsJson." + len + ".status";

        let boardList = "";

        //Determines in which list each bug goes
        switch (eval(status)) {
            case "0":
                boardList = "bug-list-open";
                break;
            case "1":
                boardList = "bug-list-in-progress";
                break;
            case "2":
                boardList = "bug-list-tested";
                break;
            case "3":
                boardList = "bug-list-reopen";
                break;
            case "4":
                boardList = "bug-list-closed";
                break;
        }

        //Creates cards for bugs
        createCard(id, title, desc, boardList, bugsJson);
    }
}

//Creates cards for bugs
function createButton(id, title, desc, boardList, bugsJson) {
    let card = document.createElement("div");
    card.setAttribute("class", "card mb-1 bug-card");
    card.setAttribute("id", eval(id));
    document.getElementById(boardList).appendChild(card);

    let cardBody = document.createElement("div");
    cardBody.setAttribute("class", "card-body");
    card.appendChild(cardBody);

    let cardTitle = document.createElement("h5");
    cardTitle.setAttribute("class", "card-title");
    cardTitle.innerHTML = eval(title);
    cardBody.appendChild(cardTitle);

    let cardText = document.createElement("p");
    cardText.setAttribute("class", "card-text m-0 mw-1");
    cardText.innerHTML = eval(desc);
    cardBody.appendChild(cardText);

    let cardButtonDelete = document.createElement("button");
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

    let cardButtonAlter = document.createElement("button");
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