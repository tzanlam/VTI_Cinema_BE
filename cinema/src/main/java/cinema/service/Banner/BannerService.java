package cinema.service.Banner;

import cinema.modal.entity.Banner;
import cinema.modal.request.BannerRequest;

import java.util.List;

public interface BannerService {
    List<Banner> findBanners();
    List<Banner> findBannersIsActive();
    Banner findBannerById(int id);
    Banner createBanner(BannerRequest request);
    Banner updateBanner(int id, BannerRequest request);
}
