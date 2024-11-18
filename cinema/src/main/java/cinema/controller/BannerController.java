package cinema.controller;

import cinema.modal.request.BannerRequest;
import cinema.service.Banner.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
            return ResponseEntity.ok(bannerService.findBanners());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }


    @GetMapping("/findActive")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<?> findActiveBanner() {
        try{
            return ResponseEntity.ok(bannerService.findBannersIsActive());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }
    @GetMapping("/findId/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER', 'USER')")
    public ResponseEntity<?> findBannerId(@PathVariable int id) {
        try{
            return ResponseEntity.ok(bannerService.findBannerById(id));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @PostMapping("create")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> createBanner(@RequestBody BannerRequest request) {
        try {
            return ResponseEntity.ok(bannerService.createBanner(request));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateBanner(@PathVariable int id, @RequestBody BannerRequest request) {
        try{
            return ResponseEntity.ok(bannerService.updateBanner(id, request));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @GetMapping("/setActive/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> setActive(@PathVariable int id) {
        try {
            return ResponseEntity.ok(bannerService.setActive(id));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }
}
