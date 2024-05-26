package com.example.demo.Controllers;

import com.example.demo.Models.Purchase;
import com.example.demo.Repositories.PurchaseRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    private final PurchaseRepository purchaseRepository;
    PurchaseController(PurchaseRepository purchaseRepository){
        this.purchaseRepository = purchaseRepository;
    }
    @PostMapping
    public void makePurchase(@RequestBody Purchase purchase){
        purchaseRepository.makePurchase(purchase);
    }
    @GetMapping
    public List<Purchase> getActivePurchases(){
        return purchaseRepository.getActivePurchases();
    }
    @PatchMapping
    public void completePurchase(@RequestBody Purchase purchase){
        purchaseRepository.completePurchase(purchase.getId());
    }
}
