package com.example.ordermanagement.resource;

import com.example.ordermanagement.model.dto.ItemDto;
import com.example.ordermanagement.model.entity.Item;
import com.example.ordermanagement.service.ItemService;

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
@RequestMapping("/api/items")
public class ItemResource {

    private final ItemService itemService;

    @Autowired
    public ItemResource(
        final ItemService itemService
    ) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<Item> createItem(
        @RequestBody
        final ItemDto itemDto
    ) {
        Item item = itemService.createItem(itemDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    @PutMapping("{itemId}")
    public ResponseEntity<Item> updateItem(
        @PathVariable
        final Long itemId,
        @RequestBody
        final ItemDto item
    ) {
        return itemService.updateItem(itemId, item)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> allItems = itemService.getAllItems();
        return ResponseEntity.ok(allItems);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Item> getItemById(
        @PathVariable
        final Long itemId
    ) {
        return itemService.getItemById(itemId)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteItem(
        @PathVariable
        final Long itemId
    ) {
        return itemService.deleteItem(itemId)
            ? ResponseEntity.ok().build()
            : ResponseEntity.notFound().build();
    }
}
