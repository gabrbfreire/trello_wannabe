package com.example.trello_wannabe.service;

import com.example.trello_wannabe.entity.Card;
import com.example.trello_wannabe.entity.User;
import com.example.trello_wannabe.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    public List<Card> getCards(Integer boardId, User user){

        return cardRepository.selectCardsByBoardId(boardId, user.getUser_id());
    }

    public void createCard(String title, Integer boardId, User user){

        cardRepository.createCard(title, boardId, user.getUser_id());
    }

    public void updateCardTitle(Integer cardId, String newTitle, User user){

        cardRepository.updateCardTitle(cardId, newTitle, user.getUser_id());
    }

    public void updateCardBoard(Integer cardId, Integer newBoardId, User user){

        cardRepository.updateCardBoard(cardId, newBoardId, user.getUser_id());
    }

    public void deleteCard(Integer cardId, User user){

        cardRepository.deleteById(cardId);
    }
}
