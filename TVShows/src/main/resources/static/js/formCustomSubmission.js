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

const form = document.getElementById('commentButton').addEventListener("click", function(event) {
  const textarea = document.querySelector('textarea[name="text"]');
  if (textarea.value.trim() !== '') {
    setTimeout(() => {
      location.reload(true);
    }, 1000);
  }
});

// STATUS CHANGING
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

// SEASON TRACKING
const trackingButtons = document.querySelectorAll(".trackingButton");

trackingButtons.forEach((button) => {
  button.addEventListener("click", function () {
    const seasonBlock = button.closest(".season-block");
    const progressValue = seasonBlock.querySelector(".progressValue");
    const episodeMaxValue = seasonBlock.querySelector("#episodeMaxValue");

    let currentValue = progressValue ? parseInt(progressValue.textContent) : 0;
    let maxEpisodeCount = episodeMaxValue ? parseInt(episodeMaxValue.value) : 0; // Define maxEpisodeCount here

    if (button.id === 'increment') {
      currentValue = Math.min(currentValue + 1, maxEpisodeCount);
    } else if (button.id === 'decrement') {
      currentValue = Math.max(currentValue - 1, 0);
    }

    if (progressValue) {
      progressValue.textContent = currentValue + " / ";
    }

    updateButtonState(seasonBlock, currentValue, maxEpisodeCount);
  });
});

document.addEventListener("DOMContentLoaded", function() {
  const seasonBlocks = document.querySelectorAll(".season-block");

  seasonBlocks.forEach((seasonBlock) => {
    const progressValue = seasonBlock.querySelector(".progressValue");
    const episodeMaxValue = seasonBlock.querySelector("#episodeMaxValue");
    const currentValue = progressValue ? parseInt(progressValue.textContent) : 0;
    const maxEpisodeCount = episodeMaxValue ? parseInt(episodeMaxValue.value) : 0;
    updateButtonState(seasonBlock, currentValue, maxEpisodeCount);
  });
});

function updateButtonState(seasonBlock, currentValue, maxEpisodeCount) {
  const incrementButton = seasonBlock.querySelector("#increment");
  const decrementButton = seasonBlock.querySelector("#decrement");

  if (currentValue === 0) {
    incrementButton.classList.remove('hidden');
    decrementButton.classList.add('hidden');
  } else if (currentValue === maxEpisodeCount) {
    incrementButton.classList.add('hidden');
    decrementButton.classList.remove('hidden');
  } else {
    incrementButton.classList.remove('hidden');
    decrementButton.classList.remove('hidden');
  }
}