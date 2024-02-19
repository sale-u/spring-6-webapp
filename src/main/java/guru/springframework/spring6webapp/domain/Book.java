package guru.springframework.spring6webapp.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // H2 database nema autoincrement, pa umesto IDENTITY stavljamo AUTO
    private Long id;
    private String title;
    private String isbn;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
