document.addEventListener('DOMContentLoaded', () => {
  // Register form submit event handler for forms with the id 'custom-submission-id'
  const forms = document.querySelectorAll('form.custom-submission-class');
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
            console.error('There has been a problem while contacting the server:', error);
          });
      });
    });
  }
});
