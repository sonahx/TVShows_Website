package com.TVShows.config;

import com.TVShows.domain.*;
import com.TVShows.service.ShowCommentService;
import com.TVShows.service.TVShowService;
import com.TVShows.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserService userService;
    private final TVShowService showService;
    private final ShowCommentService commentService;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) {
        // creating two users with different roles;
        User user1 = User.builder()
                .id(1L)
                .name("A Random User")
                .email("test@gmail.com")
                .password(encoder.encode("pass"))
                .roles(Role.USER).build();

        User user2 = User.builder()
                .id(2L)
                .name("Administrator account")
                .email("admin@gmail.com")
                .password(encoder.encode("pass"))
                .roles(Role.ADMINISTRATOR).build();

        userService.createUser(user1);
        userService.createUser(user2);


        //creating multipe tv Shows
        TVShow show1 = new TVShow(
                "Game of Thrones",
                "2011",
                Genre.DRAMA,
                "David Benioff, D. B. Weiss",
                "an American fantasy drama television series created by David Benioff and D. B. Weiss for HBO." +
                        " It is an adaptation of A Song of Ice and Fire, a series of fantasy novels by George R. R. Martin," +
                        " the first of which is A Game of Thrones. The show was shot in the United Kingdom, Canada, Croatia, Iceland," +
                        " Malta, Morocco, and Spain. It premiered on HBO in the United States on April 17, 2011, and concluded on May 19, 2019," +
                        " with 73 episodes broadcast over eight seasons",
                "https://flxt.tmsimg.com/assets/p8553063_b_v13_ax.jpg",
                null,
                ShowStatus.FINISHED,
                "60 minutes",
                "Peter Dinklage, Lena Headey, Nikolaj Coster-Waldau, Emilia Clarke, others");

        TVShow show2 = new TVShow(
                "Breaking bad",
                "2008",
                Genre.DRAMA,
                "Vince Gilligan",
                "A high school chemistry teacher dying of cancer teams with a former student to secure his family's future by manufacturing and selling crystal meth.",
                "https://m.media-amazon.com/images/M/MV5BN2VjOTkwMjgtYWEyMy00MzNmLTllMjktNDI1ZmRhYTAwYTg1XkEyXkFqcGdeQXVyNjAwNDUxODI@._V1_FMjpg_UX1000_.jpg",
                null,
                ShowStatus.FINISHED,
                "60 minutes",
                "Bryan Cranston, Aaron Paul, Anna Gunn, others");

        TVShow show3 = new TVShow(
                "True Detective",
                "2014",
                Genre.DRAMA,
                "Nic Pizzolatto",
                "True Detective is an American anthology crime drama television series created and written by Nic Pizzolatto." +
                        " The series, broadcast by the premium cable network HBO in the United States," +
                        " premiered on January 12, 2014. Each season of the series is structured as a self-contained narrative," +
                        " employing new cast ensembles, and following various sets of characters and settings",
                "https://flxt.tmsimg.com/assets/p10291550_b_v13_aj.jpg",
                null,
                ShowStatus.FINISHED,
                "60 minutes",
                "Matthew McConaughey, Woody Harrelson, Michelle Monaghan, others");

        TVShow show4 = new TVShow(
                "Twin Peaks",
                "1990",
                Genre.DRAMA,
                "David Lynch",
                "The series follows an investigation, headed by FBI Special Agent Dale Cooper (Kyle MacLachlan) " +
                        "and local Sheriff Harry S. Truman (Michael Ontkean), into the murder of homecoming queen Laura Palmer " +
                        "(Sheryl Lee) in the fictional town of Twin Peaks",
                "https://m.media-amazon.com/images/M/MV5BMTExNzk2NjcxNTNeQTJeQWpwZ15BbWU4MDcxOTczOTIx._V1_.jpg",
                null,
                ShowStatus.FINISHED,
                "60 minutes",
                "Kyle MacLachlan, Michael Ontkean, Mädchen Amick, others");

        showService.createShow(show1);
        showService.createShow(show2);
        showService.createShow(show3);
        showService.createShow(show4);

        //adding few comments to shows
        ShowComment comment1 = new ShowComment("Some text..", user1, show1, LocalDateTime.now());
        ShowComment comment2 = new ShowComment("Bigtexts5-9135ui-1a,lf[", user1, show1, LocalDateTime.now());
        ShowComment comment3 = new ShowComment("\"But I must explain to you how all this mistaken idea of denouncing" +
                " pleasure and praising pain was born and I will give you a complete account of the system, and expound " +
                "the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one " +
                "rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know " +
                "how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there " +
                "anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because " +
                "occasionally circumstances occur in which toil and pain can procure him some great pleasure. " +
                "To take a trivial example, which of us ever undertakes laborious physical exercise, except to " +
                "obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy " +
                "a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?",
                user1, show1, LocalDateTime.now());

        commentService.save(comment1);
        commentService.save(comment2);
        commentService.save(comment3);
    }
}