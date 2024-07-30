package com.example.reservation.service;

import com.example.reservation.model.Store;
import com.example.reservation.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public Store registerStore(Store store) {
        return storeRepository.save(store);
    }

    public Store updateStore(Store store) {
        return storeRepository.save(store);
    }

    public void deleteStore(Long storeId) {
        storeRepository.deleteById(storeId);
    }

    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    public List<Store> findByName(String name) {
        return storeRepository.findByName(name);
    }

    public Store findById(Long id) {
        return storeRepository.findById(id).orElse(null);
    }
}
