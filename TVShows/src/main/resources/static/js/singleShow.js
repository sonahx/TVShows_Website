document.addEventListener('DOMContentLoaded', () => {
	// Register form submit event handler for every form in your page
	const forms = document.querySelectorAll('form');
	if (forms) {
		forms.forEach(f => {
			let action = f.action;
			f.addEventListener('submit', (event) => {
				// prevent default behavior, i.e., form submission
				event.preventDefault();
				// create a FormData object and add the form data to it
				const formData = new FormData(f);
				// perform server side request using the FormData object as the request body
				fetch(action, {
					method: 'POST',
					body: formData
				})
					.then((response) => {
						if (!response.ok) {
							throw new Error('Network response was not ok');
						}
						console.log('Request successfully completed');
					})
					.catch((error) => {
						console.error('There has been a problem while contacting server:', error);
					});
			});
		});
	}
});

var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal
btn.onclick = function() {
  modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}

document.getElementById("viewerStatus").addEventListener("change", function() {
    const form = document.getElementById("viewerStatusForm");
    const formData = new FormData(form);
    const action = form.getAttribute("action");
    fetch(action, {
        method: "POST",
        body: formData,
        headers: {
            "Accept": "application/json"
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Network response was not ok");
        }
        console.log("Request successfully completed");
    })
    .catch(error => {
        console.error("There has been a problem while contacting server:", error);
    });
});

const form = document.getElementById('commentButton').addEventListener("click", function(event) {
  const textarea = document.querySelector('textarea[name="text"]');
  if (textarea.value.trim() !== '') {
    setTimeout(() => {
      location.reload(true);
    }, 1000);
  }
});