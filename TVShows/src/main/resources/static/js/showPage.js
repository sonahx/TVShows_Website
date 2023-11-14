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

//PERSONAL SCORE CHANGING
document.getElementById("personalScore").addEventListener("change", function() {
    const form = document.getElementById("viewerPersonalScore");
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

//EXPAND SEASONS BUTTON
function toggleSeasons() {
  var extraSeasons = document.querySelectorAll('.hidden-season');
  extraSeasons.forEach(function (season) {
    if (season.style.display === 'none' || season.style.display === '') {
      season.style.display = 'flex';
    } else {
      season.style.display = 'none';
    }
  });

  // Toggle the button text and arrow symbol based on the data-expanded attribute
  var expandButton = document.getElementById('expand-button');
  var isExpanded = expandButton.getAttribute('data-expanded') === 'true';

  var buttonText = isExpanded ? 'Expand' : 'Collapse';
  var arrowSymbol = isExpanded ? '&#8595;' : '&#8593;';

  expandButton.querySelector('span.text').textContent = buttonText;
  expandButton.querySelector('span.arrow').innerHTML = arrowSymbol;

  expandButton.setAttribute('data-expanded', isExpanded ? 'false' : 'true');
}
