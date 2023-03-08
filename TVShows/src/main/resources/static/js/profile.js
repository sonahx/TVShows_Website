// Get all the tab links and content areas
const tabLinks = document.querySelectorAll('.nav-link');
const tabContents = document.querySelectorAll('.tab-pane');

// Add a click event listener to each tab link
tabLinks.forEach((tabLink) => {
  tabLink.addEventListener('click', (event) => {
    // Prevent the default link behavior
    event.preventDefault();

    // Hide all tab contents
    tabContents.forEach((tabContent) => {
      tabContent.classList.remove('show', 'active');
    });

    // Show the clicked tab content
    const tabId = event.target.getAttribute('href');
    const tabContent = document.querySelector(tabId);
    if (tabContent) {
      tabContent.classList.add('show', 'active');
    } else {
      console.error(`Could not find tab content with ID ${tabId}`);
    }
  });
});