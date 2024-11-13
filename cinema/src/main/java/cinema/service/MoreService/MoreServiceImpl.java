package cinema.service.MoreService;

import cinema.modal.entity.MoreService;
import cinema.modal.entity.constant.StatusService;
import cinema.modal.request.MoreServiceRequest;
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
        List<MoreService> moreServices = moreServiceRepository.findAll();
        return moreServices;
    }

    @Override
    public MoreService findById(int id) {
        MoreService moreService = moreServiceRepository.findById(id).orElse(null);
        return  moreService;
    }

    @Override
    public MoreService createService(MoreServiceRequest request) {
        MoreService moreService = new MoreService();
        moreService.setName(request.getName());
        moreService.setImage(request.getImage());
        moreService.setDecription(request.getDecription());
        moreService.setPrice(request.getPrice());
        moreService.setStatus(StatusService.ACTIVE);
        moreServiceRepository.save(moreService);
        return moreService;
    }

    @Override
    public MoreService updateService(int id, MoreServiceRequest request) {
        MoreService moreService = moreServiceRepository.findById(id).orElse(null);
        if (moreService != null) {
            moreService.setName(request.getName());
            moreService.setImage(request.getImage());
            moreService.setDecription(request.getDecription());
            moreService.setPrice(request.getPrice());
            moreServiceRepository.save(moreService);
            List<StatusService> statusServices = Arrays.asList(StatusService.values());
            StatusService service = StatusService.valueOf(request.getStatus());
            if (statusServices.contains(service)) {
                moreService.setStatus(StatusService.valueOf(request.getStatus()));
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
            StatusService service = StatusService.valueOf(status);
            if (statusServices.contains(service)) {
                moreService.setStatus(StatusService.valueOf(status));
                return moreService;
            }
        }
        return null;
    }

    @Override
    public List<MoreService> findStatusActive() {
        List<MoreService> moreServices = moreServiceRepository.findAll();
        List<MoreService> result = new ArrayList<>();
        for (MoreService moreService : moreServices) {
            if (moreService.getStatus() == StatusService.ACTIVE) {
                result.add(moreService);
            }
        }
        return result;
    }
}
