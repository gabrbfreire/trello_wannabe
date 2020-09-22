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

        return cardRepository.selectCardsByListId(boardId);
    }

    public void createCard(String title, Integer listId, User user, Integer boardId, Integer cardIndex){

        Card newCard = new Card();
        newCard.setCard_title(title);
        newCard.setList_list_id(listId);
        newCard.setUser_id(user.getUser_id());
        newCard.setBoard_id(boardId);
        newCard.setCard_index(cardIndex);
        cardRepository.save(newCard);
    }

    public void updateCardTitle(Integer cardId, String newTitle, User user){

        cardRepository.updateCardTitle(cardId, newTitle, user.getUser_id());
    }

    public void updateCardList(Integer cardId, Integer newListId, User user){

        cardRepository.updateCardList(cardId, newListId, user.getUser_id());
    }

    public void updateCardIndex(Integer cardId, Integer cardIndex, User user){

        cardRepository.updateCardIndex(cardId, cardIndex, user.getUser_id());
    }

    public void deleteCard(Integer cardId, User user){

        // Checks if the user who owns the card is making the change
        // Is this needed?
        // if its need do it here or in the DB?
        Optional<Card> cardOptional = cardRepository.findById(cardId);

        if(cardOptional.isPresent()){
            Card card = cardOptional.get();
            if(card.getUser_id() == user.getUser_id()){
                cardRepository.deleteById(cardId);
            }else{
                //return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }
    }
}
