package cinema.modal.response.DTO;

import cinema.modal.entity.MoreService;
import lombok.Data;

@Data
public class MoreServiceDTO {
    private String service_id;
    private String service_name;
    private String service_image;
    private String description;
    private String price;
    private String service_status;

    public MoreServiceDTO(MoreService moreService) {
        this.service_name = String.valueOf(moreService.getId());
        this.service_image = moreService.getImage();
        this.description = moreService.getDecription();
        this.price = String.valueOf(moreService.getPrice());
        this.service_status = String.valueOf(moreService.getStatus());
    }
}
