//sortable.js options and updates on card changes
var open = document.getElementById('bug-list-open');
var sortable = Sortable.create(open, {
  group: 'bugs',
  onAdd: function (evt) {
    //evt.clone.id   Gets new card ID
    changeStatus(evt.clone.id, 0);
  },
  onEnd: function (evt) {
    console.log(evt.newIndex);
    changeIndex(evt.clone.id, evt.newIndex);
  },
  animation: 150
});

var inProgress = document.getElementById('bug-list-in-progress');
var sortable = Sortable.create(inProgress, {
  group: 'bugs',
  onAdd: function (evt) {
    changeStatus(evt.clone.id, 1);
  },
  onEnd: function (evt) {
    console.log(evt.newIndex);
    changeIndex(evt.clone.id, evt.newIndex);
  },
  animation: 150
});

var tested = document.getElementById('bug-list-tested');
var sortable = Sortable.create(tested, {
  group: 'bugs',
  onAdd: function (evt) {
    changeStatus(evt.clone.id, 2);
  },
  onEnd: function (evt) {
    console.log(evt.newIndex);
    changeIndex(evt.clone.id, evt.newIndex);
  },
  animation: 150
});

var reopen = document.getElementById('bug-list-reopen');
var sortable = Sortable.create(reopen, {
  group: 'bugs',
  onAdd: function (evt) {
    changeStatus(evt.clone.id, 3);
  },
  onEnd: function (evt) {
    console.log(evt.newIndex);
    changeIndex(evt.clone.id, evt.newIndex);
  },
  animation: 150
});

var closd = document.getElementById('bug-list-closed');
var sortable = Sortable.create(closd, {
  group: 'bugs',
  onAdd: function (evt) {
    changeStatus(evt.clone.id, 4);
  },
  onEnd: function (evt) {
    console.log(evt.newIndex);
    changeIndex(evt.clone.id, evt.newIndex);
  },
  animation: 150
});


//Changes card status on DB when user drops a card on another list
function changeStatus(id, newStatus) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {

    }
  };
  xhttp.open("POST", "php/update-bug-status.php?id=" + id + "&newStatus=" + newStatus, true);
  xhttp.send();
}

function changeIndex(id, index) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {

    }
  };
  xhttp.open("POST", "php/update-bug-index.php?id=" + id + "&newIndex=" + index, true);
  xhttp.send();
}