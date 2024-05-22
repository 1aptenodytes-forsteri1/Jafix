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
    public List<Batch> getBatchesByCoffeeHouse(@RequestBody CoffeeHouse coffeeHouse){
        return batchRepository.getBatchesByCoffeeHouse(coffeeHouse.id());
    }
    @PostMapping
    public void addBatch(@RequestBody Batch batch){
        batchRepository.addBatch(batch);
    }
}
