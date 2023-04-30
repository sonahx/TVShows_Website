package com.TVShows.config;

import com.TVShows.domain.ShowComment;
import com.TVShows.domain.TVShow;
import com.TVShows.domain.User;
import com.TVShows.enums.Genre;
import com.TVShows.enums.Role;
import com.TVShows.enums.ShowStatus;
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
                .roles(Role.USER)
                .enabled(true)
                .build();

        User user2 = User.builder()
                .id(2L)
                .name("Administrator account")
                .email("admin@gmail.com")
                .password(encoder.encode("pass"))
                .roles(Role.ADMINISTRATOR)
                .enabled(true)
                .build();

        userService.createUser(user1);
        userService.createUser(user2);

        //creating multiple tv Shows
        TVShow show1 = new TVShow();
        show1.setName("Game of Thrones - Season 1");
        show1.setEpisodesNumber(10);
        show1.setReleaseDate("2011");
        show1.setGenre(Genre.DRAMA);
        show1.setDirectors("David Benioff, D. B. Weiss");
        show1.setNextEpisode(null);
        show1.setStatus(ShowStatus.FINISHED);
        show1.setEpisodeDuration("60 minutes");
        show1.setActors("Peter Dinklage, Lena Headey, Nikolaj Coster-Waldau, Emilia Clarke, others");
        show1.setImageUrl("https://flxt.tmsimg.com/assets/p8553063_b_v13_ax.jpg");
        show1.setTrailerUrl("https://www.youtube.com/embed/KPLWWIOCOOQ");
        show1.setDescription("an American fantasy drama television series created by David Benioff and D. B. Weiss for HBO." +
                " It is an adaptation of A Song of Ice and Fire, a series of fantasy novels by George R. R. Martin," +
                " the first of which is A Game of Thrones. The show was shot in the United Kingdom, Canada, Croatia, Iceland," +
                " Malta, Morocco, and Spain. It premiered on HBO in the United States on April 17, 2011, and concluded on May 19, 2019," +
                " with 73 episodes broadcast over eight seasons");

        TVShow show2 = new TVShow();
        show2.setName("Breaking bad - Season 1");
        show2.setEpisodesNumber(9);
        show2.setReleaseDate("2008");
        show2.setGenre(Genre.DRAMA);
        show2.setDirectors("Vince Gilligan");
        show2.setNextEpisode(null);
        show2.setStatus(ShowStatus.FINISHED);
        show2.setEpisodeDuration("60 minutes");
        show2.setActors("Bryan Cranston, Aaron Paul, Anna Gunn, others");
        show2.setImageUrl("https://www.themoviedb.org/t/p/w500/ggFHVNu6YYI5L9pCfOacjizRGt.jpg");
        show2.setTrailerUrl("https://www.youtube.com/embed/HhesaQXLuRY");
        show2.setDescription("A high school chemistry teacher dying of cancer teams with a former student to secure his " +
                "family's future by manufacturing and selling crystal meth.");

        TVShow show3 = new TVShow();
        show3.setName("True Detective - Season 1");
        show3.setEpisodesNumber(8);
        show3.setReleaseDate("2014");
        show3.setGenre(Genre.DRAMA);
        show3.setDirectors("Nic Pizzolatto");
        show3.setNextEpisode(null);
        show3.setStatus(ShowStatus.FINISHED);
        show3.setEpisodeDuration("60 minutes");
        show3.setActors("Matthew McConaughey, Woody Harrelson, Michelle Monaghan, others");
        show3.setImageUrl("https://flxt.tmsimg.com/assets/p10291550_b_v13_aj.jpg");
        show3.setTrailerUrl("https://www.youtube.com/embed/fVQUcaO4AvE");
        show3.setDescription("True Detective is an American anthology crime drama television series created and written by Nic Pizzolatto." +
                " The series, broadcast by the premium cable network HBO in the United States," +
                " premiered on January 12, 2014. Each season of the series is structured as a self-contained narrative," +
                " employing new cast ensembles, and following various sets of characters and settings");

        TVShow show4 = new TVShow();
        show4.setName("Twin Peaks - Season 1");
        show4.setEpisodesNumber(20);
        show4.setReleaseDate("1990");
        show4.setGenre(Genre.DRAMA);
        show4.setDirectors("David Lynch");
        show4.setNextEpisode(null);
        show4.setStatus(ShowStatus.FINISHED);
        show4.setEpisodeDuration("60 minutes");
        show4.setActors("Kyle MacLachlan, Michael Ontkean, Mädchen Amick, others");
        show4.setImageUrl("https://m.media-amazon.com/images/M/MV5BMTExNzk2NjcxNTNeQTJeQWpwZ15BbWU4MDcxOTczOTIx._V1_.jpg");
        show4.setTrailerUrl("https://www.youtube.com/embed/Zwn9ou_nf-I");
        show4.setDescription("The series follows an investigation, headed by FBI Special Agent Dale Cooper (Kyle MacLachlan)" +
                "and local Sheriff Harry S. Truman (Michael Ontkean), into the murder of homecoming queen Laura Palmer" +
                "(Sheryl Lee) in the fictional town of Twin Peaks");

        TVShow show5 = new TVShow();
        show5.setName("Game of Thrones - Season 2");
        show5.setEpisodesNumber(10);
        show5.setReleaseDate("2012");
        show5.setGenre(Genre.DRAMA);
        show5.setDirectors("David Benioff, D. B. Weiss");
        show5.setNextEpisode(null);
        show5.setStatus(ShowStatus.FINISHED);
        show5.setEpisodeDuration("60 minutes");
        show5.setActors("Peter Dinklage, Lena Headey, Nikolaj Coster-Waldau, Emilia Clarke, others");
        show5.setImageUrl("https://m.media-amazon.com/images/M/MV5BYTRiNDQwYzAtMzVlZS00NTI5LWJjYjUtMzkwNTUzMWMxZTllXkEyXkFqcGdeQXVyNDIzMzcwNjc@._V1_.jpg");
        show5.setTrailerUrl("https://www.youtube.com/embed/KPLWWIOCOOQ");
        show5.setDescription("an American fantasy drama television series created by David Benioff and D. B. Weiss for HBO." +
                " It is an adaptation of A Song of Ice and Fire, a series of fantasy novels by George R. R. Martin," +
                " the first of which is A Game of Thrones. The show was shot in the United Kingdom, Canada, Croatia, Iceland," +
                " Malta, Morocco, and Spain. It premiered on HBO in the United States on April 17, 2011, and concluded on May 19, 2019," +
                " with 73 episodes broadcast over eight seasons");

        TVShow show6 = new TVShow();
        show6.setName("Game of Thrones - Season 3");
        show6.setEpisodesNumber(10);
        show6.setReleaseDate("2013");
        show6.setGenre(Genre.DRAMA);
        show6.setDirectors("David Benioff, D. B. Weiss");
        show6.setNextEpisode(null);
        show6.setStatus(ShowStatus.FINISHED);
        show6.setEpisodeDuration("60 minutes");
        show6.setActors("Peter Dinklage, Lena Headey, Nikolaj Coster-Waldau, Emilia Clarke, others");
        show6.setImageUrl("https://upload.wikimedia.org/wikipedia/en/1/1d/Game_of_Thrones_Season_3.jpg");
        show6.setTrailerUrl("https://www.youtube.com/embed/KPLWWIOCOOQ");
        show6.setDescription("an American fantasy drama television series created by David Benioff and D. B. Weiss for HBO." +
                " It is an adaptation of A Song of Ice and Fire, a series of fantasy novels by George R. R. Martin," +
                " the first of which is A Game of Thrones. The show was shot in the United Kingdom, Canada, Croatia, Iceland," +
                " Malta, Morocco, and Spain. It premiered on HBO in the United States on April 17, 2011, and concluded on May 19, 2019," +
                " with 73 episodes broadcast over eight seasons");

        TVShow show7 = new TVShow();
        show7.setName("Game of Thrones - Season 4");
        show7.setEpisodesNumber(10);
        show7.setReleaseDate("2014");
        show7.setGenre(Genre.DRAMA);
        show7.setDirectors("David Benioff, D. B. Weiss");
        show7.setNextEpisode(null);
        show7.setStatus(ShowStatus.FINISHED);
        show7.setEpisodeDuration("60 minutes");
        show7.setActors("Peter Dinklage, Lena Headey, Nikolaj Coster-Waldau, Emilia Clarke, others");
        show7.setImageUrl("https://cdns-images.dzcdn.net/images/cover/318302146bfcda17cfdbd7dbff2f7b2c/500x500.jpg");
        show7.setTrailerUrl("https://www.youtube.com/embed/KPLWWIOCOOQ");
        show7.setDescription("an American fantasy drama television series created by David Benioff and D. B. Weiss for HBO." +
                " It is an adaptation of A Song of Ice and Fire, a series of fantasy novels by George R. R. Martin," +
                " the first of which is A Game of Thrones. The show was shot in the United Kingdom, Canada, Croatia, Iceland," +
                " Malta, Morocco, and Spain. It premiered on HBO in the United States on April 17, 2011, and concluded on May 19, 2019," +
                " with 73 episodes broadcast over eight seasons");

        TVShow show8 = new TVShow();
        show8.setName("Game of Thrones - Season 5");
        show8.setEpisodesNumber(10);
        show8.setReleaseDate("2014");
        show8.setGenre(Genre.DRAMA);
        show8.setDirectors("David Benioff, D. B. Weiss");
        show8.setNextEpisode(null);
        show8.setStatus(ShowStatus.FINISHED);
        show8.setEpisodeDuration("60 minutes");
        show8.setActors("Peter Dinklage, Lena Headey, Nikolaj Coster-Waldau, Emilia Clarke, others");
        show8.setImageUrl("https://upload.wikimedia.org/wikipedia/en/5/59/Game_of_Thrones_Season_5.png");
        show8.setTrailerUrl("https://www.youtube.com/embed/KPLWWIOCOOQ");
        show8.setDescription("an American fantasy drama television series created by David Benioff and D. B. Weiss for HBO." +
                " It is an adaptation of A Song of Ice and Fire, a series of fantasy novels by George R. R. Martin," +
                " the first of which is A Game of Thrones. The show was shot in the United Kingdom, Canada, Croatia, Iceland," +
                " Malta, Morocco, and Spain. It premiered on HBO in the United States on April 17, 2011, and concluded on May 19, 2019," +
                " with 73 episodes broadcast over eight seasons");

        TVShow show9 = new TVShow();
        show9.setName("Game of Thrones - Season 6");
        show9.setEpisodesNumber(10);
        show9.setReleaseDate("2014");
        show9.setGenre(Genre.DRAMA);
        show9.setDirectors("David Benioff, D. B. Weiss");
        show9.setNextEpisode(null);
        show9.setStatus(ShowStatus.FINISHED);
        show9.setEpisodeDuration("60 minutes");
        show9.setActors("Peter Dinklage, Lena Headey, Nikolaj Coster-Waldau, Emilia Clarke, others");
        show9.setImageUrl("https://upload.wikimedia.org/wikipedia/en/d/d1/Game_of_Thrones_Season_6.jpeg");
        show9.setTrailerUrl("https://www.youtube.com/embed/KPLWWIOCOOQ");
        show9.setDescription("an American fantasy drama television series created by David Benioff and D. B. Weiss for HBO." +
                " It is an adaptation of A Song of Ice and Fire, a series of fantasy novels by George R. R. Martin," +
                " the first of which is A Game of Thrones. The show was shot in the United Kingdom, Canada, Croatia, Iceland," +
                " Malta, Morocco, and Spain. It premiered on HBO in the United States on April 17, 2011, and concluded on May 19, 2019," +
                " with 73 episodes broadcast over eight seasons");

        TVShow show10 = new TVShow();
        show10.setName("Game of Thrones - Season 7");
        show10.setEpisodesNumber(10);
        show10.setReleaseDate("2014");
        show10.setGenre(Genre.DRAMA);
        show10.setDirectors("David Benioff, D. B. Weiss");
        show10.setNextEpisode(null);
        show10.setStatus(ShowStatus.FINISHED);
        show10.setEpisodeDuration("60 minutes");
        show10.setActors("Peter Dinklage, Lena Headey, Nikolaj Coster-Waldau, Emilia Clarke, others");
        show10.setImageUrl("https://upload.wikimedia.org/wikipedia/en/9/92/Game_of_Thrones_Season_7.png");
        show10.setTrailerUrl("https://www.youtube.com/embed/KPLWWIOCOOQ");
        show10.setDescription("an American fantasy drama television series created by David Benioff and D. B. Weiss for HBO." +
                " It is an adaptation of A Song of Ice and Fire, a series of fantasy novels by George R. R. Martin," +
                " the first of which is A Game of Thrones. The show was shot in the United Kingdom, Canada, Croatia, Iceland," +
                " Malta, Morocco, and Spain. It premiered on HBO in the United States on April 17, 2011, and concluded on May 19, 2019," +
                " with 73 episodes broadcast over eight seasons");

        TVShow show11 = new TVShow();
        show11.setName("Game of Thrones - Season 8");
        show11.setEpisodesNumber(10);
        show11.setReleaseDate("2014");
        show11.setGenre(Genre.DRAMA);
        show11.setDirectors("David Benioff, D. B. Weiss");
        show11.setNextEpisode(null);
        show11.setStatus(ShowStatus.FINISHED);
        show11.setEpisodeDuration("60 minutes");
        show11.setActors("Peter Dinklage, Lena Headey, Nikolaj Coster-Waldau, Emilia Clarke, others");
        show11.setImageUrl("https://upload.wikimedia.org/wikipedia/en/e/e0/Game_of_Thrones_Season_8.png");
        show11.setTrailerUrl("https://www.youtube.com/embed/KPLWWIOCOOQ");
        show11.setDescription("an American fantasy drama television series created by David Benioff and D. B. Weiss for HBO." +
                " It is an adaptation of A Song of Ice and Fire, a series of fantasy novels by George R. R. Martin," +
                " the first of which is A Game of Thrones. The show was shot in the United Kingdom, Canada, Croatia, Iceland," +
                " Malta, Morocco, and Spain. It premiered on HBO in the United States on April 17, 2011, and concluded on May 19, 2019," +
                " with 73 episodes broadcast over eight seasons");

        TVShow show12 = new TVShow();
        show12.setName("True Detective - Season 2");
        show12.setEpisodesNumber(8);
        show12.setReleaseDate("2014");
        show12.setGenre(Genre.DRAMA);
        show12.setDirectors("Nic Pizzolatto");
        show12.setNextEpisode(null);
        show12.setStatus(ShowStatus.FINISHED);
        show12.setEpisodeDuration("60 minutes");
        show12.setActors("Matthew McConaughey, Woody Harrelson, Michelle Monaghan, others");
        show12.setImageUrl("https://upload.wikimedia.org/wikipedia/ru/thumb/b/bd/%D0%9D%D0%B0%D1%81%D1%82%D0%BE%D1%8F%D1%89%D0%B8%D0%B9_%D0%B4%D0%B5%D1%82%D0%B5%D0%BA%D1%82%D0%B8%D0%B2_%282_%D1%81%D0%B5%D0%B7%D0%BE%D0%BD%29.jpg/270px-%D0%9D%D0%B0%D1%81%D1%82%D0%BE%D1%8F%D1%89%D0%B8%D0%B9_%D0%B4%D0%B5%D1%82%D0%B5%D0%BA%D1%82%D0%B8%D0%B2_%282_%D1%81%D0%B5%D0%B7%D0%BE%D0%BD%29.jpg");
        show12.setTrailerUrl("https://www.youtube.com/embed/fVQUcaO4AvE");
        show12.setDescription("True Detective is an American anthology crime drama television series created and written by Nic Pizzolatto." +
                " The series, broadcast by the premium cable network HBO in the United States," +
                " premiered on January 12, 2014. Each season of the series is structured as a self-contained narrative," +
                " employing new cast ensembles, and following various sets of characters and settings");

        showService.createShow(show1);
        showService.createShow(show2);
        showService.createShow(show3);
        showService.createShow(show4);
        showService.createShow(show5);
        showService.createShow(show6);
        showService.createShow(show7);
        showService.createShow(show8);
        showService.createShow(show9);
        showService.createShow(show10);
        showService.createShow(show11);
        showService.createShow(show12);

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