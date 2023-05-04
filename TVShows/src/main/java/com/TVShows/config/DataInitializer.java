package com.TVShows.config;

import com.TVShows.domain.NewsArticle;
import com.TVShows.domain.ShowComment;
import com.TVShows.domain.TVShow;
import com.TVShows.domain.User;
import com.TVShows.enums.Genre;
import com.TVShows.enums.Role;
import com.TVShows.enums.ShowStatus;
import com.TVShows.service.NewsArticleService;
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
    private final NewsArticleService newsArticleService;
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
        show12.setImageUrl("https://upload.wikimedia.org/wikipedia/ru/thumb/b/bd/%D0%9D%D0%B0%D1%81%D1%82%D0%BE%D1%8F%D" +
                "1%89%D0%B8%D0%B9_%D0%B4%D0%B5%D1%82%D0%B5%D0%BA%D1%82%D0%B8%D0%B2_%282_%D1%81%D0%B5%D0%B7%D0%BE%D0%BD%29" +
                ".jpg/270px-%D0%9D%D0%B0%D1%81%D1%82%D0%BE%D1%8F%D1%89%D0%B8%D0%B9_%D0%B4%D0%B5%D1%82%D0%B5%D0%BA%D1%82%" +
                "D0%B8%D0%B2_%282_%D1%81%D0%B5%D0%B7%D0%BE%D0%BD%29.jpg");
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

        //creating few news articles
        NewsArticle article1 = new NewsArticle();
        article1.setName("Bryan Cranston Played Some Hilariously Mean Pranks on Aaron Paul During Breaking Bad");
        article1.setRelatedTo("Breaking bad");
        article1.setAuthor(user2);
        article1.setDate(LocalDateTime.now());
        article1.setImageUrl("https://image.cnbcfm.com/api/v1/image/107142579-1666970394789-gettyimages-174381559-75647685.jpeg?v=1666986612");
        article1.setText("""
                Ten years ago, Breaking Bad gave us the gift of one Jesse Pinkman, meth cook, Roomba dad, and lover of\s
                the word "bitch." But the character we now know and love five seasons later almost never made it very far
                . As Aaron Paul explained during the show's 10th-anniversary panel at San Diego Comic-Con on Thursday,\s
                the writers originally wanted to kill off his character in Season One—a fact that Bryan Cranston decided\s
                to take advantage of with some evil and hilarious pranks.

                “He came up to me the first time he did it and just gives me a really long, exaggerated hug," Paul\s
                recounted, according to Time. He’s just not letting me go [and I’m like], ‘What’s going on?' He’s like,
                 ‘Hey, man, it’s been a fun ride.’ I was like, ‘What do you mean?’ He’s like, ‘I thought you read the script,
                  right?’ [I was like,] ‘No.’ He’s like, ‘Oh,’ and then he just walks away. And I run into the production office
                  , and I’m like, ‘Where’s the script?!’ And they’re like, ‘It’s not available yet,’ and I’m like, ‘Please,
                   god, tell me.’ And then I read it and I was alive still.”

                But, being the man who won four Emmys for his deep excavation of Heisenberg's interiority, Cranston didn't
                 just let it end there. According to Time, Paul said his co-star also roped in the Breaking Bad props\s
                 department. “There was another time where they did a fitting [and said], ‘We need to measure you for the\s
                 coffin,'" he said, according to Time. "[I’m like,] ‘A coffin?’ And they’re like, ‘Oh, you don’t know about it?’"

                Of course, the show kept Jesse around until the very end. As Vince Gilligan told Variety in January, "
                the kid was too good to kill.\"""");

        NewsArticle article2 = new NewsArticle();
        article2.setName("The Succession Finale Title’s Connection to a Famous Poem Offers Tantalizing Clues");
        article2.setRelatedTo("The Succession");
        article2.setAuthor(user2);
        article2.setDate(LocalDateTime.now());
        article2.setImageUrl("https://m.media-amazon.com/images/M/MV5BYmRhNDFiYTQtYTEzMC00YjI5LThiMTItMDE4YzRlYWM1NGI4XkEyXkFqcGdeQXVyMDM2NDM2MQ@@._V1_.jpg");
        article2.setText("""
                Succession loves a good literary reference, and the recent reveal of the series finale title has given\s
                fans another allusion to interpret. Eagle-eyed listings watchers have revealed that the  title for the\s
                last episode,  set to air May 28, will be "With Open Eyes.”

                Like every other Succession season finale before it, the Season 4 finale takes its name from the poem\s
                "Dream Song 29," by John Berryman, originally published in 1964.

                Season 1 ended with "Nobody Is Ever Missing,” season 2 followed that with "This Is Not For Tears,”\s
                and season 3 used "All the Bells Say." The title phrases appear out of order and non-consecutively\s
                in the poem, but taken together offer clues to the kind of tone Succession creator Jesse Armstrong has\s
                been trying to create  over these 40-plus hours of television. ("With Open Eyes" will also apparently\s
                run an hour and half, a supersized farewell to the Roys that would mark the show’s longest episode).\s

                The Berryman invocation is a dark one. Berryman, a contemporary of Sylvia Plath, was tormented by the\s
                death of his father, who shot himself when Berryman was 12. Berryman himself would later jump to\s
                his own death off of the Washington Avenue Bridge in Minnesota in 1972.

                "To me he's the darkest of that generation of poets," Mount Holyoke English professor emeritus\s
                Christopher Benfey tells me. “There's just a kind of gothic darkness throughout his work that obviously\s
                has continued to keep him on the popular culture map.”

                GET 1 YEAR OF GQ + A FREE GQ HAT
                "Dream Song 29" is part of a cycle of poems that won Berryman the Pulitzer Prize in 1965. In it,\s
                Berryman's recurring character of Henry— a sort of "dream persona" for Berryman—thinks he may have\s
                murdered someone, but didn't. Applied to the context of Succession, there's an obvious analogy to\s
                Kendall's vehicular manslaughter incident involving the waiter at Shiv's wedding in the season 1 finale.

                While Armstrong is generally not one for explaining his thought processes, asked about "Dream Song 29"\s
                following the season 2 finale, he told Vulture that the poem "has a terrifying sense of that feeling\s
                Kendall has at the end of the last season, wondering if something could have happened. In Berryman’s\s
                poem’s case, in the end, [a death] hasn’t happened. But it has happened to Kendall."\s

                Benfey, a Succession watcher himself,  immediately picked up on the  "Nobody Is Ever Missing" parallel.
                 "I can see why somebody with a slight English major temperament might pick that line," he says.

                Benfry further speculates that while the season 1 title reference was Succession's most literal, the
                 Berryman allusions growing more abstract as the title tradition continued.

                Still, there’s reason to think "With Open Eyes" could offer clues about the  show's conclusion.\s

                In context, Berryman writes of Henry:

                "And there is another thing he has in mind

                like a grave Sienese face a thousand years

                would fail to blur the still profiled reproach of.  Ghastly,

                with open eyes, he attends, blind."

                Asked for his analysis, Benfey points to Berryman's echoes of Oedipus Rex, who blinds himself when he\s
                realizes he has killed his father and married his mother, and King Lear's Earl of Gloucester, who\s
                loses his eyes in Lear's daughter Regan's plot for power. Both sagas, obviously, involve major daddy\s
                issues. The DNA of Lear especially courses through Succession, as the ultimate story of a powerful old\s
                man’s grasping children picking  at his legacy.

                “With Open Eyes” works equally well, Benfey figures, as a nod to all the gaffes and fumbles the Roy\s
                children make as they grapple for control over Waystar Royco. "It seems to describe our kids pretty\s
                well," Benfey says. “Their eyes are wide open and they are blind.”

                Benfey, for his part, likes that Succession doesn't hit you over the head with its intellectual\s
                bonafides, instead leaving them as bonus fodder  for those in the know. "It's a show that is informed
                 by literature without showing it off," he says. “But it does tend to reward a deeper reading.”""");

        NewsArticle article3 = new NewsArticle();
        article3.setName("Severance Season 2 Gets Update From Actor After On Set Drama Reports");
        article3.setRelatedTo("Severance");
        article3.setAuthor(user2);
        article3.setDate(LocalDateTime.now());
        article3.setImageUrl("https://static1.srcdn.com/wordpress/wp-content/uploads/2023/05/severance-season-1-dylan-r-zach-cherry.jpg?q=50&fit=crop&w=680&h=400&dpr=1.5");
        article3.setText("""
                Following reports of on-set drama, Severance star Zach Cherry gives an update regarding season 2.\s
                AppleTV+'s psychological drama focuses on four employees working in the Macrodata Refinement team at\s
                Lumon Industries. Prior to their employment, each underwent a medical procedure known as Severance\s
                which uses a brain implant to separate work memories from personal life memories. Severance effectively\s
                creates two separate individuals housed within the same body.Speaking with Entertainment Tonight, Cherry\s
                gives a positive update for Severance season 2 despite reports of on-set drama. Portraying the show's\s
                Waffle party-loving Lumon Industries employee Dylan G, Cherry praised the season 2 premiere episode.\s
                Read what he said below:

                "My reaction was the same as my reaction was to the first script in season 1, which is, 'This is good!'"
                 Late last month, it was reported that Severance season 2 was experiencing significant delays.\s
                 Showrunners Dan Erickson and Mark Friedman allegedly had a falling out during the production of season
                  1 with Friedman needing to be convinced to return for the follow-up season by executive producer\s
                  Ben Stiller. Perhaps stemming from the conflict, the report states script and budget issues resulted in\s
                  recruiting House of Cards creator Beau Willimon to finish the season.

                Following the report, Stiller defended the work on Severance season 2, stating "No one’s going to the break room."\s
                The Zoolander star went on to explain the season is currently on schedule and assured viewers they are working\s
                to "make the show as good as possible." With no premiere date divulged by AppleTV+, audiences may have to
                 wait until late 2023 or early 2024 for season 2.Severance season 1 concluded with the innies seizing control\s
                 of their bodies outside the confines of Lumon Industries using the Overtime Contingency. During that time,\s
                 Mark S (Adam Scott) revealed to his sister the abuse suffered by his employers while Helly R (Britt Lower)\s
                 discovered she was Helena Eagan and publicly denounced Severance at a special event. Speaking at a Reddit AMA,\s
                 Erickson teased multiple levels of the Severance procedure while implying Helena will become the chief\s
                 antagonist for season 2. With Severance's next chapter likely months away, audiences will have to be patient\s
                 to see the consequences facing the Macrodata Refinement employees after their brief escape.""");

        NewsArticle article4 = new NewsArticle();
        article4.setName("Kevin Costner's Yellowstone Exit Confirmed After Season 5 Delay & BTS Turmoil");
        article4.setRelatedTo("Yellowstone");
        article4.setAuthor(user2);
        article4.setDate(LocalDateTime.now());
        article4.setImageUrl("https://static1.srcdn.com/wordpress/wp-content/uploads/2023/05/kevin-costner-as-john-dutton-iii-in-yellowstone-looking-pensive.jpg?q=50&fit=contain&w=1140&h=&dpr=1.5");
        article4.setText("""
                Yellowstone star Kevin Costner is officially exiting the hit series. The show, which was created by Taylor Sheridan,\s
                follows the Dutton family as they protect their land, the Yellowstone Dutton Ranch, from various interested parties.\s
                Throughout the series, Costner's character John Dutton III has had a central role as the patriarch of the family,
                 but rumors swirling around a behind-the-scenes falling-out between Costner and Sheridan have led to speculation that he
                  would not be returning after season 5, which is airing its second half later this year after a hiatus.
                                          On Wednesday, ET Online officially confirmed that Costner will not be returning after Yellowstone\s
                                          season 5. It has also not yet been confirmed when the cast and crew will reunite to shoot the\s
                                          impending final six episodes of the season. It is currently unknown how this announcement will
                                           affect the shape of the end of the season, or Yellowstone's potential for a season 6 renewal.
                                           The source of the behind-the-scenes controversy is allegedly a disagreement between Costner
                                            and Sheridan about the star's level of involvement as part of the Yellowstone cast in the\s
                                            final episodes of season 5. Costner's involvement in another Western project, Horizon, has\s
                                            reportedly delayed the return of the series. The four-hour Western epic, which Costner is\s
                                            directing, is being shot in two parts.

                                          Over the past few weeks, the signs have been increasingly pointing toward Costner severing ties\s
                                          with Yellowstone. One of the biggest signs of backstage turmoil was the fact that the Yellowstone\s
                                          panel at PaleyFest was abruptly canceled. It was also recently announced that Costner was resuming
                                           work on Horizon part two, leaving the future of the current season of Yellowstone in the air.It r
                                           emains to be seen if Yellowstone can come back from losing its biggest star. The series has a\s
                                           strong enough ensemble, including co-stars Luke Grimes, Kelly Reilly, Wes Bentley, and Cole Hauser,
                                            that it could conceivably keep going without him. However, it may come to pass that the series will\s
                                            end, leaning on its many spinoffs - which already include 1883 and 1923, with at least three more in development
                                             - to keep the story of the Dutton family going.""");

        NewsArticle article5 = new NewsArticle();
        article5.setName("\"I'll Get Around To It\": OG Game Of Thrones Star On Watching House Of The Dragon");
        article5.setRelatedTo("Game Of Thrones");
        article5.setAuthor(user2);
        article5.setDate(LocalDateTime.now());
        article5.setImageUrl("https://static1.srcdn.com/wordpress/wp-content/uploads/2023/05/paddy-considine-as-king-viserys-in-house-of-the-dragon.jpg?q=50&fit=contain&w=1140&h=&dpr=1.5" +
                "-iii-in-yellowstone-looking-pensive.jpg?q=50&fit=contain&w=1140&h=&dpr=1.5");
        article5.setText("Another original Game of Thrones star admits they still haven't watched House of the Dragon." +
                " Following GoT’s epic 8-season run, the pressure was on for the first released spinoff to recapture the" +
                " original series’ George R.R. Martin-derived fantasy magic. Thankfully, House of the Dragon successfully " +
                "made it to HBO in 2022 and quickly became a runaway hit, proving that the lore of Westeros has the legs to " +
                "carry on into the future. Unsurprisingly, HBO is now promising a barrage of GoT spinoffs, including (among others)" +
                " the recently-confirmed A Knight of the Seven Kingdoms: The Hedge Knight and a just-announced prequel centered on Aegon I Targaryen." +
                "As the world waits on House of the Dragon season 2, OG Game of Thrones stars continue to be asked if " +
                "they themselves have yet partaken of the hit spinoff. After Emilia Clarke, Nikolaj Coster-Waldau and Kit" +
                " Harington all had the question put to them, it was recently the turn of Robb Stark actor Richard Madden " +
                "to go on record about House of the Dragon. As reported by Variety from the premiere of Madden’s new " +
                "Prime Video show Citadel, the actor said he has not yet seen HotD, admitting “I’ve been very busy doing " +
                "other things, but I’ll get around to it.”" +
                "Madden at least sounds like he intends to eventually watch House of the Dragon, once his busy schedule " +
                "begins to wind down. The same can’t necessarily be said for Daenerys Targaryen actor Clarke, who also " +
                "admitted she has not watched the new show yet, but gave no indication that she ever will. As she told " +
                "Variety earlier this year:" +
                "It’s too weird. I’m so happy it’s happening. I’m so over the moon about all the awards for everyone who" +
                " made it. Miguel Sapochnik, who created it, and made it, love him. Brilliant, wonderfu. I just can’t do " +
                "it. It’s so weird. It’s so weird. It’s so strange." +
                "Another interesting House of the Dragon take was recently offered up by Jaime Lannister actor Coster-Waldau," +
                " who told EW he began watching the new show, but was forced to stop thanks to the very familiar opening titles." +
                " “It was a little strange because it was the same music and the title sequence was kind of similar,” he " +
                "said, adding “I was like, ‘Ah, this is too soon.’” Coster-Waldau did say he intends to wait awhile and " +
                "then binge-watch the whole thing." +
                "Yet another GoT star who admitted to feeling strange at the thought of watching House of the Dragon is " +
                "Jon Snow actor Harington, who told Extra that he did catch a couple of episodes, but also had to stop " +
                "because in his words, “It’s hard for me to watch because I lived in it for so long,” adding, “There’s" +
                " a pain there.” Harington did say he is slowly working through House of the Dragon, so perhaps he will " +
                "eventually have a take on the entire season. Harington will of course be back in Game of Thrones’ world " +
                "again soon himself, as he’s confirmed to return as Jon Snow in one of those many announced spinoffs.");

        newsArticleService.save(article1);
        newsArticleService.save(article2);
        newsArticleService.save(article3);
        newsArticleService.save(article4);
        newsArticleService.save(article5);
    }
}