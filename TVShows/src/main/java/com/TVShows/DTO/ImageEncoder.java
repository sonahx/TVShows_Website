package com.TVShows.DTO;

import com.TVShows.domain.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Getter
@Setter
public class ImageEncoder {

    private MultipartFile image;

    public User encodeImage(User user) throws IOException {
        user.setImage(Base64.getEncoder().encodeToString(this.image.getBytes()));
        return user;
    }
}
