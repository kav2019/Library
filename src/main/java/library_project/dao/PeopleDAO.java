package library_project.dao;

import library_project.models.Book;
import library_project.models.People;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class PeopleDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public PeopleDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    //метод возвращающий ВСЕХ пользователей
    @Transactional
    public List<People> returnAllPeople(){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from People p", People.class).getResultList();
    }

    //метод возвращающий ОДНОГО пользователя по id
    @Transactional
    public People returnOnePeople(int id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(People.class, id);
    }

    //метод сохраяющий изменения ОДНОГО пользователей
    @Transactional
    public void saveChangePeople(People people,int id){
        Session session = sessionFactory.getCurrentSession();
        People peopleFromDB = session.get(People.class, id);
        peopleFromDB.setName(people.getName());
        peopleFromDB.setYearBorn(people.getYearBorn());
    }

    //метод добавляющий ОДНОГО пользователей
    @Transactional
    public void savePeople(People people){
        Session session = sessionFactory.getCurrentSession();
        People peopleForDB = new People(people.getName(), people.getYearBorn());
        session.save(peopleForDB);
    }

    //метод удаляющий ОДНОГО пользователей
    @Transactional
    public void delPeople(int id){
        Session session = sessionFactory.getCurrentSession();
        People people = session.get(People.class, id);
        session.remove(people);
    }

    // //Метод получающий список книг у пользователя
    @Transactional
    public List<Book> bookListUsing(int id){
        Session session = sessionFactory.getCurrentSession();
        People people = session.get(People.class, id);
        List<Book> books;
        Hibernate.initialize(books = people.getBooks()); //что бы сразу подгрузились
        return books;
    }

    //Удаление из связывающей таблицы, удаление книги у человека
    @Transactional
    public void delBookOfList(int idPeople, int idBook){
        Session session = sessionFactory.getCurrentSession();
        People people = session.get(People.class, idPeople);
        List<Book> books = people.getBooks();
        for (Book b: books) {
            if(b.getId() == idBook){
                books.remove(b);
            }
        }
    }








}
