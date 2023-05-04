const form = document.getElementById('commentButton').addEventListener("click", function(event) {
  const textarea = document.querySelector('textarea[name="text"]');
  if (textarea.value.trim() !== '') {
    setTimeout(() => {
      location.reload(true);
    }, 1000);
  }
});