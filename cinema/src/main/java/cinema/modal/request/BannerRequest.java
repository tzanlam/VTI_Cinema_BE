package cinema.modal.request;

import cinema.modal.entity.Banner;
import cinema.modal.entity.constant.StatusBanner;
import lombok.Data;

@Data
public class BannerRequest {
    private String imageUrl;
    private String title;
    private String description;
    private int movieId;

    public Banner asBanner() {
        Banner banner = new Banner();
        populateBanner(banner);
        return banner;
    }

    public Banner updateBanner(Banner banner) {
        populateBanner(banner);
        return banner;
    }

    private void populateBanner(Banner banner) {
        banner.setImageUrl(imageUrl);
        banner.setTitle(title);
        banner.setDescription(description);
        banner.setStatus(StatusBanner.INACTIVE);
    }
}
