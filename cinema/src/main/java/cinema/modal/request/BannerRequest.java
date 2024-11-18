package cinema.modal.request;

import cinema.modal.entity.Banner;
import lombok.Data;

@Data
public class BannerRequest {
    private String imageUrl;
    private String title;
    private String description;

    public Banner asBanner() {
        Banner banner = new Banner();
        banner.setImageUrl(imageUrl);
        banner.setTitle(title);
        banner.setDescription(description);
        banner.setActive(false);
        return banner;
    }

    public Banner updateBanner(Banner banner) {
        banner.setImageUrl(imageUrl);
        banner.setTitle(title);
        banner.setDescription(description);
        banner.setActive(false);
        return banner;
    }
}
