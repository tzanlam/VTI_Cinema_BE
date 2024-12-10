package cinema.service.Banner;

import cinema.modal.entity.Banner;
import cinema.modal.entity.Contact;
import cinema.modal.entity.Movie;
import cinema.modal.entity.constant.StatusMovie;
import cinema.modal.request.BannerRequest;
import cinema.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerRepository bannerRepository;

    @Override
    public List<Banner> findBanners() {
        return bannerRepository.findAll();
    }

    @Override
    public Banner findBannerById(int id) {
        return bannerRepository.findById(id).orElse(null);
    }

    @Override
    public Banner createBanner(BannerRequest request) {
        Banner banner = request.asBanner();
        bannerRepository.save(banner);
        return banner;
    }

    @Override
    public Banner updateBanner(int id, BannerRequest request) {
        Banner banner = findBannerById(id);
        if (banner != null) {
            return bannerRepository.save(request.updateBanner(banner));
        }
        return null;
    }


}
