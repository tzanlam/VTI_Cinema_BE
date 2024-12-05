package cinema.service.MoreService;

import cinema.modal.entity.MoreService;
import cinema.modal.request.MoreServiceRequest;
import cinema.modal.response.DTO.MoreServiceDTO;

import java.util.List;
import java.util.Map;

public interface MoreServiceService {
    List<MoreService> findServices();
    MoreService findById(int id);
    MoreService createService(MoreServiceRequest request);
    MoreService updateService(int id, MoreServiceRequest request);
    MoreService changeStatus(int id, String status);
    List<MoreService> findStatusActive();
}
