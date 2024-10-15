var ratingStars = document.getElementById("rating-stars");
var ratingInput = document.getElementById("rating");
var commentTextarea = document.getElementById("comment");
var submitButton = document.getElementById("submit-btn");

var stars = ratingStars.getElementsByTagName("i");

for (var i = 0; i < stars.length; i++) {
  stars[i].addEventListener("mouseover", function () {
    var index = parseInt(this.getAttribute("data-index"));

    for (var j = 0; j < stars.length; j++) {
      if (j <= index) {
        stars[j].classList.add("active");
      } else {
        stars[j].classList.remove("active");
      }
    }
  });

  stars[i].addEventListener("click", function () {
    var index = parseInt(this.getAttribute("data-index"));

    for (var j = 0; j < stars.length; j++) {
      if (j <= index) {
        stars[j].classList.add("active");
      } else {
        stars[j].classList.remove("active");
      }
    }

    // Set rating value to the hidden input field
    ratingInput.value = index + 1;

    // Change star color to yellow
    for (var j = 0; j < stars.length; j++) {
      if (j <= index) {
        stars[j].classList.add("yellow");
      } else {
        stars[j].classList.remove("yellow");
      }
    }
  });

  stars[i].addEventListener("mouseout", function () {
    var index = parseInt(this.getAttribute("data-index"));

    for (var j = 0; j < stars.length; j++) {
      stars[j].classList.remove("active");
    }
  });
}