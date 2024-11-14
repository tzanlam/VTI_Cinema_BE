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

    public MoreServiceDTO(MoreService service){
        this.service_id = String.valueOf(service.getId());
    }
}
