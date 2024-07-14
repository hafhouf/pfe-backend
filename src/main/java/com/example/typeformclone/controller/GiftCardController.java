package com.example.typeformclone.controller;

import com.example.typeformclone.model.GiftCard;
import com.example.typeformclone.service.GiftCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/giftcards")
public class GiftCardController {

    @Autowired
    private GiftCardService giftCardService;

    @PostMapping("/convert")
    public ResponseEntity<GiftCard> convertPointsToGiftCard(@RequestParam Long userId, @RequestParam int points) {
        return ResponseEntity.ok(giftCardService.convertPointsToGiftCard(userId, points));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<GiftCard>> getGiftCardsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(giftCardService.getGiftCardsByUser(userId));
    }
}
