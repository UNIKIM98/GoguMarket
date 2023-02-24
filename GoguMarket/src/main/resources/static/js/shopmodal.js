// Get the modal
var shopmodal = document.getElementById("shopModal");

//Get the button that opens the modal
var modaltr = document.getElementById("modalTr");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal
modaltr.onclick = function() {
  shopmodal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  shopmodal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == shopmodal) {
    shopmodal.style.display = "none";
  }
}

