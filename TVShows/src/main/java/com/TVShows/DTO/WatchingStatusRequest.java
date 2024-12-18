package com.TVShows.DTO;

import com.TVShows.enums.ViewerStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WatchingStatusRequest {
	private Integer showId;
	private ViewerStatus status;
}
