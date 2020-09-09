package com.example.trello_wannabe.service;

import com.example.trello_wannabe.entity.Card;
import com.example.trello_wannabe.entity.User;
import com.example.trello_wannabe.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    public List<Card> getCards(Integer boardId){
        return cardRepository.selectCardsByBoardId(boardId);
    }

    public void createCard(String title, Integer boardId, User user){
        Card newCard = new Card();
        newCard.setCard_title(title);
        newCard.setBoards_board_id(boardId);
        newCard.setBoards_user_id_user(user.getUser_id());
        cardRepository.save(newCard);
    }

    public void updateCard(Integer cardId, String title){
        Optional<Card> cardData = cardRepository.findById(cardId);
        Card card = cardData.get();
        card.setCard_title(title);
        cardRepository.save(card);
    }

    public void deleteCard(Integer cardId){
        cardRepository.deleteById(cardId);
    }
}
