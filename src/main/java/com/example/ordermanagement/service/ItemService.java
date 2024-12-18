package com.example.ordermanagement.service;

import com.example.ordermanagement.model.dto.ItemDto;
import com.example.ordermanagement.model.entity.Item;
import com.example.ordermanagement.repository.ItemRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService (
        final ItemRepository itemRepository
    ) {
        this.itemRepository = itemRepository;
    }

    public Item createItem(final ItemDto item) {
        Item itemEntity = Item.builder()
            .name(item.getName())
            .build();
        return itemRepository.save(itemEntity);
    }

    public Optional<Item> updateItem(final Long itemId, final ItemDto item) {
        return this.getItemById(itemId)
            .map(itemEntity -> {
                itemEntity.setName(item.getName());
                return itemRepository.saveAndFlush(itemEntity);
            });
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<Item> getItemById(final Long itemId) {
        return itemRepository.findById(itemId);
    }

    public boolean deleteItem(final Long itemId) {
        Optional<Item> item = this.getItemById(itemId);

        if (item.isPresent()) {
            itemRepository.delete(item.get());
            return true;
        }

        return false;
    }

    public Item getOrCreateItem(final ItemDto itemDto) {
        if(ObjectUtils.isEmpty(itemDto))
            return null;

        if(ObjectUtils.isEmpty(itemDto.getId()))
            return this.createItem(itemDto);

        return this.getItemById(itemDto.getId())
            .orElseGet(() -> this.createItem(itemDto));
    }
}
