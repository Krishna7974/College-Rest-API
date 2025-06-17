package com.in.sms.service.serviceImpl;

import com.in.sms.model.Parents;
import com.in.sms.repository.ParentsRepository;
import com.in.sms.service.serviceInterfaces.ParentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentsServiceImpl implements ParentsService {

    @Autowired
    private ParentsRepository parentsRepository;

    @Override
    public Parents saveParents(Parents parents) {
        return parentsRepository.save(parents);
    }

    @Override
    public List<Parents> getAllParents() {
        return parentsRepository.findAll();
    }

    @Override
    public Parents getParentsById(Long id) {
        return parentsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parent not found with id: " + id));
    }

    @Override
    public Parents updateParents(Long id, Parents updatedParents) {
        Parents existing = getParentsById(id);
        existing.setFatherName(updatedParents.getFatherName());
        existing.setMotherName(updatedParents.getMotherName());
        existing.setSiblings(updatedParents.getSiblings());
        return parentsRepository.save(existing);
    }

    @Override
    public void deleteParents(Long id) {
        parentsRepository.deleteById(id);
    }
}
