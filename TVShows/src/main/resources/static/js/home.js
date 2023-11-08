var elements = $('.modal-overlay, .news-modal');

$('#modal-button').click(function(){
    elements.addClass('active');
});

$('.close-modal').click(function(){
    elements.removeClass('active');
});

  function showNewsModal() {
    // Get the news modal element
    var modal = document.getElementById("newsModal");

    // Show the news modal
    modal.style.display = "block";
  }