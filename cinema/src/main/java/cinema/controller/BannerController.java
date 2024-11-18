package cinema.controller;

import cinema.modal.entity.Banner;
import cinema.modal.request.BannerRequest;
import cinema.modal.response.DTO.BannerDTO;
import cinema.service.Banner.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @GetMapping("find")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER', 'USER')")
    public ResponseEntity<?> findBanner() {
        try{
            List<Banner> banners = bannerService.findBanners();
            List<BannerDTO> dtos = banners.stream()
                    .map(BannerDTO::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtos);
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }


    @GetMapping("/findActive")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<?> findActiveBanner() {
        try{
            List<Banner> banners = bannerService.findBanners();
            List<BannerDTO> dtos = banners.stream()
                    .map(BannerDTO::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtos);
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }
    @GetMapping("/findId/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER', 'USER')")
    public ResponseEntity<?> findBannerId(@PathVariable int id) {
        try{
            return ResponseEntity.ok(new BannerDTO(bannerService.findBannerById(id)));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @PostMapping("create")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> createBanner(@RequestBody BannerRequest request) {
        try {
            return ResponseEntity.ok(new BannerDTO(bannerService.createBanner(request)));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateBanner(@PathVariable int id, @RequestBody BannerRequest request) {
        try{
            return ResponseEntity.ok(new BannerDTO(bannerService.updateBanner(id, request)));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @GetMapping("/setActive/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> setActive(@PathVariable int id) {
        try {
            return ResponseEntity.ok(new BannerDTO(bannerService.setActive(id)));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }
}
