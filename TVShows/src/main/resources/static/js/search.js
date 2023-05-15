$(document).ready(function() {
  $('#search').on('input', function() {
    var keyword = $(this).val();
    if (keyword.length >= 3) {
      $.ajax({
        type: 'GET',
        url: '/search/' + keyword,
        headers: {
          "Accept": "application/json"
        },
        async: true,
        success: function(data) {
          console.log('Response:', data);
          var html = '';
          $.each(data, function(index, show) {
           html += '<div><a href="/show/' + show.id + '">' + show.name + '</a></div>';
          });
          if (html !== '') {
            $('#search-results').html(html);
            $('#search-results').show();
          } else {
            $('#search-results').html('');
            $('#search-results').hide();
          }
        },
error: function(xhr, status, error) {
  console.log('Error:', error);
  console.log('Status:', status);
  console.log('XHR:', xhr);
      var errorMessage = '<div class="error">An error occurred while searching.</div>';
      $('#search-results').html(errorMessage);
}
      });
    } else {
      $('#search-results').html('');
      $('#search-results').hide();
    }
  });
});

// Hide the dropdown when clicking outside of it
$(document).on('click', function(e) {
  if ($(e.target).closest('#search').length === 0) {
    $('#search-results').hide();
  } else {
    $('#search-results').toggle();
  }
});
// html += '<a href="/show/' + show.id + '">' + show.name + '</a>';

$(function() {
    $('a.page-scroll').bind('click', function(event) {
        var $anchor = $(this);
        $('html, body').stop().animate({
            scrollTop: $($anchor.attr('href')).offset().top
        }, 1500, 'easeInOutExpo');
        event.preventDefault();
    });
});

// Closes the Responsive Menu on Menu Item Click
$('.navbar-collapse ul li a').click(function() {
    $('.navbar-toggle:visible').click();
});