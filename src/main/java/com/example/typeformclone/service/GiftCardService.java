package com.example.typeformclone.service;

import com.example.typeformclone.model.GiftCard;
import com.example.typeformclone.model.User;
import com.example.typeformclone.repository.GiftCardRepository;
import com.example.typeformclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GiftCardService {

    @Autowired
    private GiftCardRepository giftCardRepository;

    @Autowired
    private UserRepository userRepository;

    public GiftCard convertPointsToGiftCard(Long userId, int points) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        if (user.getPoints() < points) {
            throw new RuntimeException("Insufficient points");
        }
        user.setPoints(user.getPoints() - points);
        userRepository.save(user);

        GiftCard giftCard = new GiftCard();
        giftCard.setCode(UUID.randomUUID().toString());
        giftCard.setValue(points);
        giftCard.setUser(user);

        return giftCardRepository.save(giftCard);
    }

    public List<GiftCard> getGiftCardsByUser(Long userId) {
        return giftCardRepository.findByUserId(userId);
    }
}
