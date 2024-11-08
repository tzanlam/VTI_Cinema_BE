package cinema.service.MoreService;

import cinema.modal.entity.MoreService;
import cinema.modal.entity.constant.StatusService;
import cinema.modal.response.DTO.MoreServiceDTO;
import cinema.repository.MoreServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MoreServiceImpl implements MoreServiceService{
    @Autowired
    private MoreServiceRepository moreServiceRepository;

    @Override
    public List<MoreService> findServices() {
        return List.of((MoreService) moreServiceRepository.findAll());
    }

    @Override
    public MoreService findById(int id) {
        MoreService moreService = moreServiceRepository.findById(id).orElse(null);
        return  moreService;
    }

    @Override
    public MoreService createService(MoreServiceDTO dto) {
        MoreService moreService = new MoreService();
        moreService.setName(dto.getName());
        moreService.setImage(dto.getImage());
        moreService.setPrice(Double.parseDouble(dto.getPrice()));
        moreService.setStatus(StatusService.ACTIVE);
        moreServiceRepository.save(moreService);
        return moreService;
    }

    @Override
    public MoreService updateService(int id, MoreServiceDTO dto) {
        MoreService moreService = moreServiceRepository.findById(id).orElse(null);
        if (moreService != null) {
            moreService.setName(dto.getName());
            moreService.setImage(dto.getImage());
            moreService.setPrice(Double.parseDouble(dto.getPrice()));
            moreServiceRepository.save(moreService);
            List<StatusService> statusServices = Arrays.asList(StatusService.values());
            if (statusServices.contains(dto.getStatus())) {
                moreService.setStatus(StatusService.valueOf(dto.getStatus()));
            }
            return moreService;
        }
        return null;
    }

    @Override
    public MoreService changeStatus(int id, String status) {
        MoreService moreService = moreServiceRepository.findById(id).orElse(null);
        if (moreService != null) {
            List<StatusService> statusServices = Arrays.asList(StatusService.values());
            if (statusServices.contains(status)) {
                moreService.setStatus(StatusService.valueOf(status));
                return moreService;
            }
        }
        return null;
    }

    @Override
    public List<MoreService> findStatusActive() {
        List<MoreService> moreServices = List.of((MoreService) moreServiceRepository.findAll());
        List<MoreService> result = new ArrayList<>();
        for (MoreService moreService : moreServices) {
            if (moreService.getStatus() == StatusService.ACTIVE) {
                result.add(moreService);
            }
        }
        return result;
    }
}
