var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

var span = document.getElementsByClassName("close1")[0];

// Get the <span> element that closes the modal


// When the user clicks the button, open the modal 
btn.onclick = function() {
	console.log('gd')
	modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
	modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it





var Sns = document.getElementById("mySns");


// Get the button that opens the modal
var Snsbtn = document.getElementById("clickSns");


//When the user clicks the button, open the modal
Snsbtn.onclick = function() {
	console.log('gd')
	Sns.style.display = "block";
}





window.onclick = function(event) {
	if (event.target == modal) {
		modal.style.display = "none";
	}

	if (event.target == Sns) {
		Sns.style.display = "none";
	}
}