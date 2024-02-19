package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("54757585");

        // sada cemo sacuvati ove objekte u H2 bazi koja ce im dodeliti odgovarajuce id-jeve
        // save() vraca sacuvani objekat (sada ima dodeljeni id)
        // Sada mozemo manipulisati sa ericSaved i rodSaved tako sto im valorizujemo jos neki atribut
        // Tako izmenjeni ericSaved i rodSaved se moraju sacuvati u H2 bazi
        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);
        Author rodSaved = authorRepository.save(rod);
        Book noEJBSaved = bookRepository.save(noEJB);

        ericSaved.getBooks().add(dddSaved);      // pravimo relaciju izmedju autora eric i knjige ddd
        rodSaved.getBooks().add(noEJBSaved);

        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);

        // Assignment - new Publisher
        Publisher publisher1 = new Publisher();
        publisher1.setPublisherName("BIGZ");
        publisher1.setAddress("Bul Vojvode Misica bb");
        publisher1.setState("Serbia");
        publisher1.setCity("Belgrade");
        publisher1.setZipCode("11000");
        publisherRepository.save(publisher1);

        System.out.println("In Bootstrap");
        System.out.println("Author count: " + authorRepository.count() + " authors");
        System.out.println("Book count: " + bookRepository.count() + " books");
        System.out.println("Publisher count: " + publisherRepository.count() + " publishers");
    }
}
