package cinema.modal.response.DTO;

import cinema.modal.entity.Banner;
import lombok.Data;

@Data
public class BannerDTO {
    private String banner_id;
    private String image_url;
    private String banner_title;
    private String banner_description;
    private String banner_movie_name;
    private String banner_isActive;

    public BannerDTO(Banner banner) {
        this.banner_id = String.valueOf(banner.getId());
        this.banner_title = banner.getTitle();
        this.banner_description = banner.getDescription();
        this.banner_movie_name = banner.getMovie().getName();
        this.image_url = banner.getImageUrl();
    }
}
