package com.netspi.interview.service;

import com.netspi.interview.controller.dto.request.CreateItemRequestDto;
import com.netspi.interview.controller.dto.request.UpdateItemRequestDto;
import com.netspi.interview.model.Item;
import com.netspi.interview.model.Status;
import com.netspi.interview.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class ItemService {

    private final EntityManager entityManager;

    private final ItemRepository itemRepository;

    public ItemService(EntityManager entityManager, ItemRepository itemRepository) {
        this.entityManager = entityManager;
        this.itemRepository = itemRepository;
    }

    public Item getItemById(Long id) {
        return this.itemRepository.findById(id).orElse(null);
    }

    @Transactional
    public Item updateItemById(Long id, UpdateItemRequestDto updateItemRequestDto) {
        this.entityManager.createNativeQuery(String.format("update item set status=%d where id=%d", updateItemRequestDto.getStatusOrder(), id)).executeUpdate();
        return this.getItemById(id);
    }

    public List<Item> getAllItems() {
        return this.itemRepository.findAllByOrderByStatus();
    }

    @Transactional
    public void deleteAllItems() {
        this.itemRepository.deleteAll();
    }

    @Transactional
    public void createItem(CreateItemRequestDto createItemRequestDto) {
        int defaultStatus = Status.NEW.ordinal();
        String name = createItemRequestDto.getName();
        this.entityManager.createNativeQuery(String.format("insert into item (name, status) values ('%s', %d)", name, defaultStatus)).executeUpdate();
    }
}
