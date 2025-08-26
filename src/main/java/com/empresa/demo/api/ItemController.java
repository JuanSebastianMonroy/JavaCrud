package com.empresa.demo.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private final Map<Long, Item> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    @GetMapping
    public List<Item> list() { return new ArrayList<>(store.values()); }

    @GetMapping("/{id}")
    public ResponseEntity<Item> get(@PathVariable Long id) {
        Item it = store.get(id);
        return it != null ? ResponseEntity.ok(it) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Item create(@RequestBody Item dto) {
        long id = seq.getAndIncrement();
        Item it = new Item(id, dto.getNombre(), dto.isHecho());
        store.put(id, it);
        return it;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> update(@PathVariable Long id, @RequestBody Item dto) {
        if (!store.containsKey(id)) return ResponseEntity.notFound().build();
        Item it = new Item(id, dto.getNombre(), dto.isHecho());
        store.put(id, it);
        return ResponseEntity.ok(it);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return store.remove(id) != null ? ResponseEntity.noContent().build()
                                        : ResponseEntity.notFound().build();
    }
}
