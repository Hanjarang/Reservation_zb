package com.example.reservation.controller;

import com.example.reservation.model.Store;
import com.example.reservation.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping("/register")
    public ResponseEntity<Store> registerStore(@RequestBody Store store) {
        Store registeredStore = storeService.registerStore(store);
        return ResponseEntity.ok(registeredStore);
    }

    @PutMapping("/update")
    public ResponseEntity<Store> updateStore(@RequestBody Store store) {
        Store updatedStore = storeService.updateStore(store);
        return ResponseEntity.ok(updatedStore);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<Store>> getAllStores() {
        List<Store> stores = storeService.getAllStores();
        return ResponseEntity.ok(stores);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable Long id) {
        Store store = storeService.findById(id);
        if (store != null) {
            return ResponseEntity.ok(store);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Store>> findByName(@RequestParam String name) {
        List<Store> stores = storeService.findByName(name);
        return ResponseEntity.ok(stores);
    }
}
