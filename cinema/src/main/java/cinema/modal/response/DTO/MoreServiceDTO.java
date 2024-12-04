package cinema.modal.response.DTO;

import cinema.modal.entity.MoreService;
import lombok.Data;

@Data
public class MoreServiceDTO {
    private String service_id;
    private String service_name;
    private String service_image;
    private String service_description;
    private String service_price;
    private String service_status;

    public MoreServiceDTO(MoreService moreService) {
        this.service_id = String.valueOf(moreService.getId());
        this.service_name = String.valueOf(moreService.getId());
        this.service_image = moreService.getImage();
        this.service_description = moreService.getDescription();
        this.service_price = String.valueOf(moreService.getPrice());
        this.service_status = String.valueOf(moreService.getStatus());
    }
}
