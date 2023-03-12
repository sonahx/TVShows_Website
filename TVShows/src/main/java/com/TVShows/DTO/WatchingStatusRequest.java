package com.TVShows.DTO;

import com.TVShows.domain.ViewerStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class WatchingStatusRequest {

	private Long showId;
	private ViewerStatus status;
}
