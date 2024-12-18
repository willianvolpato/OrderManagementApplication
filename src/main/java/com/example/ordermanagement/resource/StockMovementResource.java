package com.example.ordermanagement.resource;

import com.example.ordermanagement.model.dto.StockMovementDto;
import com.example.ordermanagement.model.entity.StockMovement;
import com.example.ordermanagement.service.StockMovementService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stock-movements")
public class StockMovementResource {

    private final StockMovementService stockMovementService;

    @Autowired
    public StockMovementResource(
        final StockMovementService stockMovementService
    ) {
        this.stockMovementService = stockMovementService;
    }

    @PostMapping
    public ResponseEntity<StockMovement> createStockMovement(
        @RequestBody
        final StockMovementDto stockMovementDto
    ) {
        StockMovement stockMovement = stockMovementService.createStockMovement(stockMovementDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(stockMovement);
    }

    @PutMapping("{stockMovementId}")
    public ResponseEntity<StockMovement> updateStockMovement(
        @PathVariable
        final Long stockMovementId,
        @RequestBody
        final StockMovementDto stockMovementDto
    ) {
        return stockMovementService.updateStockMovement(stockMovementId, stockMovementDto)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<StockMovement>> getAllStockMovements() {
        List<StockMovement> allStockMovements = stockMovementService.getAllStockMovements();
        return ResponseEntity.ok(allStockMovements);
    }

    @GetMapping("/{stockMovementId}")
    public ResponseEntity<StockMovement> getStockMovementById(
        @PathVariable
        final Long stockMovementId
    ) {
        return stockMovementService.getStockMovementById(stockMovementId)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{stockMovementId}")
    public ResponseEntity<Void> deleteStockMovement(
        @PathVariable
        final Long stockMovementId
    ) {
        return stockMovementService.deleteStockMovement(stockMovementId)
            ? ResponseEntity.ok().build()
            : ResponseEntity.notFound().build();
    }
}
