package library_project.service;

import library_project.models.Book;
import library_project.models.People;
import library_project.repository.PeopleRepository;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    //метод возвращающий ВСЕХ пользователей
    public List<People> returnAllPeople(){
        return peopleRepository.findAll();
    }

    //метод возвращающий ОДНОГО пользователя по id
    public People returnOnePeople(int id){
        return peopleRepository.findById(id).get();
    }

    //метод сохраяющий изменения ОДНОГО пользователей
    @Transactional
    public void saveChangePeople(People people,int id){
        people.setId(id);
        peopleRepository.save(people);
    }

    //метод добавляющий ОДНОГО пользователей
    @Transactional
    public void savePeople(People people){
        peopleRepository.save(people);
    }

    //метод удаляющий ОДНОГО пользователей
    @Transactional
    public void delPeople(int id){
        peopleRepository.deleteById(id);
    }

    //Метод получающий список книг у пользователя
    public List<Book> bookListUsing(int id){
        People people = peopleRepository.findById(id).get();
        List<Book> books;
        Hibernate.initialize(books = people.getBooks()); //что бы сразу подгрузились
        return books;
    }

    //Удаление из связывающей таблицы, удаление книги у человека
    @Transactional
    public void delBookOfList(int idPeople, int idBook){
        People people = peopleRepository.findById(idPeople).get();
        List<Book> books = people.getBooks();
        for (Book b: books) {
            if(b.getId() == idBook){
                books.remove(b);
            }
        }
    }


}
