package com.kelompok3.librify.service;

import com.kelompok3.librify.dto.BookDto;
import com.kelompok3.librify.mapper.BookMapper;
import com.kelompok3.librify.model.Book;
import com.kelompok3.librify.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<BookDto> getBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookDto> bookDtos = books.stream()
                .map((book) -> (BookMapper.mapToBookDto(book)))
                .collect(Collectors.toList());
        return bookDtos;
    }

    @Override
    public Page<Book> findPaginated(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public List<BookDto> searchBooks(String searchTerm) {
        List<Book> books = bookRepository.findByNameContaining(searchTerm);
        return BookMapper.mapToBookDtoList(books);
    }

    @Override
    public int countBooks() {
        return getBooks().size();
    }

    @Override
    public BookDto getBook(Long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            return BookMapper.mapToBookDto(book.get());
        } else {
            return null;
        }
    }
    @Override
    public void updateBook(BookDto bookDto) {
        Book book = BookMapper.mapToBook(bookDto);
        bookRepository.save(book);
    }
    @Override
    public void deleteBook(Long bookId) {
        BookDto book = getBook(bookId);
        if (book.getJmlDipinjam() == 0)
            bookRepository.deleteById(bookId);
        else {
            book.setStok(book.getJmlDipinjam());
            updateBook(book);
        }
    }

    public void pinjam(long id){
        BookDto bookDto = getBook(id);
        bookDto.setStok(bookDto.getJmlDipinjam()+1);
        updateBook(bookDto);
    }

    @Override
    public void saveBook(BookDto bookDto) {
        Book book = BookMapper.mapToBook(bookDto);
        bookRepository.save(book);
    }


}
