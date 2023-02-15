package TVShows.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="TVShow")
@Data @NoArgsConstructor
public class TVShow {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String name;
	@Column
	private String releaseDate;
	@Enumerated(EnumType.STRING)
	private Genre genre;
	@Lob
	@Column
	private String description;
	@Column
	private String imageUrl;
	
	public TVShow(String name, String releaseDate, Genre genre, String description, String imageUrl) {
		this.name = name;
		this.releaseDate = releaseDate;
		this.description = description;
		this.genre = genre;
		this.imageUrl = imageUrl;
	}
		
}
