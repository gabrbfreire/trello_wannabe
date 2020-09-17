package com.example.trello_wannabe.service;

import com.example.trello_wannabe.entity.Listt;
import com.example.trello_wannabe.repository.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListService {

    @Autowired
    ListRepository listRepository;

    public List<Listt> getLists(Integer boardId){

        return listRepository.SelectListsByBoardId(boardId);
    }

    public void createList(String title, Integer index, Integer boardId){

        Listt list = new Listt();
        list.setBoards_boards_id(boardId);
        list.setList_title(title);
        list.setList_index(index);
        listRepository.save(list);
    }

    public void updateListTitle(Integer listId, String newTitle){

        listRepository.UpdateList(listId, newTitle);
    }

    public void deleteList(Integer listId){

        listRepository.deleteById(listId);
    }
}
