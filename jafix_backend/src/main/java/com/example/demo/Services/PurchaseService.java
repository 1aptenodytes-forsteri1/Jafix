package com.example.demo.Services;

import com.example.demo.Models.Purchase;
import com.example.demo.Repositories.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    PurchaseService(PurchaseRepository purchaseRepository){
        this.purchaseRepository = purchaseRepository;
    }
    public void makePurchase(Purchase purchase){
        purchaseRepository.makePurchase(purchase);
    }
    public List<Purchase> getActivePurchases(){
        return purchaseRepository.getActivePurchases();
    }
    public void completePurchase(Integer purchaseId){
        purchaseRepository.completePurchase(purchaseId);
    }
}
