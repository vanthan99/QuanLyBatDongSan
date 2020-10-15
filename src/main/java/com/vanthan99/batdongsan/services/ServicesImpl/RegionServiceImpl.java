package com.vanthan99.batdongsan.services.ServicesImpl;

import com.vanthan99.batdongsan.entities.Region;
import com.vanthan99.batdongsan.repositories.ReasonRepository;
import com.vanthan99.batdongsan.repositories.RegionRepository;
import com.vanthan99.batdongsan.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RegionServiceImpl implements RegionService {
    private RegionRepository regionRepository;

    @Autowired
    public void setRegionRepository(RegionRepository regionRepository){
        this.regionRepository = regionRepository;
    }

    @Override
    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    @Override
    public Page<Region> findAll(Pageable pageable) {
        return regionRepository.findAll(pageable);
    }

    @Override
    public Region findById(Long id) {
        return regionRepository.findById(id).orElse(null);
    }

    @Override
    public boolean save(Region region) {
        Region availableRegion = this.findByName(region.getName());
        if (availableRegion != null){
            return false;
        }
        regionRepository.save(region);
        return true;
    }

    @Override
    public void deleteById(Long id) {
        regionRepository.deleteById(id);
    }

    @Override
    public Region findByName(String inputName) {
        return regionRepository.findByName(inputName);
    }
}
