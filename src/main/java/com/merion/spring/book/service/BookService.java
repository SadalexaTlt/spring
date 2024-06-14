package com.merion.spring.book.service;

import com.merion.spring.book.entity.BookEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class BookService {
    static List<BookEntity> bookStorage = new ArrayList<>();

    public BookService(){
        fillStorage();
    }

    public void fillStorage(){
        Random random = new Random();
        for(int i=0;i < 100; i++){
            BookEntity book = new BookEntity();
            book.setId(i);
            book.setTitle("Book #" + random.nextInt(100,999));
            book.setDescription("Lorem Ipsum - это текст-\"рыба\", часто используемый в печати и вэб-дизайне. Lorem Ipsum является стандартной \"рыбой\" для текстов на латинице с начала XVI века. В то время некий безымянный печатник создал большую коллекцию размеров и форм шрифтов, используя Lorem Ipsum для распечатки образцов. Lorem Ipsum не только успешно пережил без заметных изменений пять веков, но и перешагнул в электронный дизайн. Его популяризации в новое время послужили публикация листов Letraset с образцами Lorem Ipsum в 60-х годах и, в более недавнее время, программы электронной вёрстки типа Aldus PageMaker, в шаблонах которых используется Lorem Ipsum.");
            bookStorage.add(book);
        }
    }
    public List<BookEntity> all(){
        return bookStorage;
    }

    public Optional<BookEntity> byId (Integer id){
        return bookStorage.stream().filter( (book -> book.getId().equals(id))).findFirst();
    }

    public BookEntity create (String title, String description){
        BookEntity book = new BookEntity();
        book.setId(bookStorage.size());
        book.setTitle(title);
        book.setDescription(description);
        bookStorage.add(book);
        return book;
    }
    public Optional<BookEntity> edit(BookEntity book) {
        BookEntity oldBookEntity = byId(book.getId()).orElseThrow();
        oldBookEntity.setTitle(book.getTitle());
        oldBookEntity.setDescription(book.getDescription());
        return Optional.of(oldBookEntity);
    }
    public Boolean delete(Integer id){
        Optional<BookEntity> book = byId(id);
        if(book.isEmpty()){
            return false;
        }
        bookStorage.remove(book.get());
        return true;
    }
}
