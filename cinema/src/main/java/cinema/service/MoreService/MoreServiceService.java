package cinema.service.MoreService;

import cinema.modal.entity.MoreService;
import cinema.modal.response.DTO.MoreServiceDTO;

import java.util.List;

public interface MoreServiceService {
    List<MoreService> findServices();
    MoreService findById(int id);
    MoreService createService(MoreServiceDTO dto);
    MoreService updateService(int id, MoreServiceDTO dto);
    MoreService changeStatus(int id, String status);
    List<MoreService> findStatusActive();
}
