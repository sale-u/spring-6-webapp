package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
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
        // Naravno, tako izmenjeni ericSaved i rodSaved se moraju sacuvati u H2 bazi
        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);
        Author rodSaved = authorRepository.save(rod);
        Book noEJBSaved = bookRepository.save(noEJB);

        ericSaved.getBooks().add(dddSaved);      // pravimo relaciju izmedju autora eric i knjige ddd
        rodSaved.getBooks().add(noEJBSaved);

        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author count: " + authorRepository.count() + " authors");
        System.out.println("Book count: " + bookRepository.count() + " books");
    }
}