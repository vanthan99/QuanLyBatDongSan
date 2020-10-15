package com.vanthan99.batdongsan.services.ServicesImpl;

import com.vanthan99.batdongsan.entities.Support;
import com.vanthan99.batdongsan.repositories.SupportRepository;
import com.vanthan99.batdongsan.services.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SupportServiceImpl implements SupportService {
    private SupportRepository supportRepository;
    @Autowired
    public void setSupportRepository(SupportRepository supportRepository){
        this.supportRepository = supportRepository;
    }

    @Override
    public Page<Support> findAll(Pageable pageable) {
        return supportRepository.findAll(pageable);
    }

    @Override
    public Support findById(Long id) {
        return supportRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Support support) {
        supportRepository.save(support);
    }

    @Override
    public void deleteById(Long id) {
        supportRepository.deleteById(id);
    }

    @Override
    public Page<Support> findByReasonId(Long reasonId, Pageable pageable) {
        return supportRepository.getSupportsByReason_Id(reasonId,pageable);
    }
}
