package cinema.modal.request;

import cinema.modal.entity.MoreService;
import lombok.Data;

@Data
public class MoreServiceRequest {
    private String name;
    private String image;
    private String description;
    private String price;

    public MoreService asMoreService() {
        MoreService moreService = new MoreService();
        populateMoreService(moreService);
        return moreService;
    }

    public MoreService updateMoreService(MoreService moreService) {
        populateMoreService(moreService);
        return moreService;
    }

    private void populateMoreService(MoreService moreService) {
        moreService.setName(name);
        moreService.setImage(image);
        moreService.setDescription(description);
        moreService.setPrice(Double.parseDouble(price));
    }
}
