package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started in Bootstrap");

        Author eric = new Author("Eric", "Clapton");
        Book ericBook = new Book("A book", "12312122512");

        Publisher publisher = new Publisher();
        publisher.setName("Ithakki");
        publisher.setAddress("whatever");

        publisherRepository.save(publisher);

        eric.getBooks().add(ericBook);
        ericBook.getAuthors().add((eric));

        ericBook.setPublisher(publisher);
        publisher.getBooks().add(ericBook);

        authorRepository.save(eric);
        bookRepository.save(ericBook);
        publisherRepository.save(publisher);


        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "129512312512");

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher);


        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of Publishers: "+publisherRepository.count());
        System.out.println("Authors: "+authorRepository.count());
        System.out.println("Publisher books: "+publisher.getBooks().size());
    }
}
