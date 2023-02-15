package TVShows.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import TVShows.domain.TVShow;
import TVShows.service.TVShowService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/show")
@RequiredArgsConstructor
public class ShowController {

	private final TVShowService showService;

	
	@PostMapping("/add")
	public String addShow(@ModelAttribute TVShow tvShow, Model model) {
		showService.createShow(tvShow);
		 model.addAttribute("show", tvShow);
		return "tvshowform";
	}
	
	@GetMapping("/{id}")
	public String singleShow(@PathVariable("id") Long id, Model model) {
		TVShow tvShow = showService.findShowById(id);
		 model.addAttribute("show", tvShow);
		return "singleShow";
	}
}
