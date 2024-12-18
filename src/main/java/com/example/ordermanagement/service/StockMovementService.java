package com.example.ordermanagement.service;

import com.example.ordermanagement.model.dto.StockMovementDto;
import com.example.ordermanagement.model.entity.StockMovement;
import com.example.ordermanagement.repository.StockMovementRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockMovementService {

    private final StockMovementRepository stockMovementRepository;
    private final ItemService itemService;

    @Autowired
    public StockMovementService(
        final StockMovementRepository stockMovementRepository,
        final ItemService itemService
    ) {
        this.stockMovementRepository = stockMovementRepository;
        this.itemService = itemService;
    }

    public StockMovement createStockMovement(final StockMovementDto stockMovementDto) {
        StockMovement stockMovement = StockMovement.builder()
            .creationDate(stockMovementDto.getCreationDate())
            .item(itemService.getOrCreateItem(stockMovementDto.getItem()))
            .quantity(stockMovementDto.getQuantity())
            .build();

        return stockMovementRepository.save(stockMovement);
    }

    public Optional<StockMovement> updateStockMovement(
        final Long stockMovementId,
        final StockMovementDto stockMovementDto
    ) {
        return this.getStockMovementById(stockMovementId)
            .map(stockMovementEntity -> {
                stockMovementEntity.setCreationDate(stockMovementDto.getCreationDate());
                stockMovementEntity.setItem(itemService.getOrCreateItem(stockMovementDto.getItem()));
                stockMovementEntity.setQuantity(stockMovementDto.getQuantity());
                return stockMovementRepository.saveAndFlush(stockMovementEntity);
            });
    }

    public Optional<StockMovement> getStockMovementById(final Long stockMovementId) {
        return stockMovementRepository.findById(stockMovementId);
    }

    public List<StockMovement> getAllStockMovements() {
        return stockMovementRepository.findAll();
    }

    public boolean deleteStockMovement(final Long stockMovementId) {
        Optional<StockMovement> stockMovement = this.getStockMovementById(stockMovementId);

        if (stockMovement.isPresent()) {
            stockMovementRepository.delete(stockMovement.get());
            return true;
        }

        return false;
    }
}
