package com.in.sms.service;

import com.in.sms.model.Parents;

import java.util.List;

public interface ParentsService {
    Parents saveParents(Parents parents);
    List<Parents> getAllParents();
    Parents getParentsById(Long id);
    Parents updateParents(Long id, Parents updatedParents);
    void deleteParents(Long id);
}