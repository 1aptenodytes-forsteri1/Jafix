package com.example.demo.Controllers;

import com.example.demo.Models.Batch;
import com.example.demo.Models.CoffeeHouse;
import com.example.demo.Repositories.BatchRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/batches")
public class BatchController {
    private final BatchRepository batchRepository;
    BatchController(BatchRepository batchRepository){
        this.batchRepository = batchRepository;
    }
    @GetMapping
    public List<Batch> getBatchesByCoffeeHouse(@RequestParam Integer coffeeHouseId){
        return batchRepository.getBatchesByCoffeeHouse(coffeeHouseId);
    }
    @PostMapping
    public void addBatch(@RequestBody Batch batch){
        batch.setAmount(10000);
        batchRepository.addBatch(batch);
    }
}
