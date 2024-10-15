package com.kelompok3.librify.mapper;

import com.kelompok3.librify.dto.BookDto;
import com.kelompok3.librify.model.Book;

import java.util.List;
import java.util.stream.Collectors;

public class BookMapper {
    public static BookDto mapToBookDto(Book book) {
        BookDto bookDto = BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .kategori(book.getKategori())
                .pengarang(book.getPengarang())
                .penerbit(book.getPenerbit())
                .tahunTerbit(book.getTahunTerbit())
                .lokasiRak(book.getLokasiRak())
                .stok(book.getStok())
                .jmlDipinjam(book.getJmlDipinjam())
                .createdAt(book.getCreatedAt())
                .updatedAt(book.getUpdatedAt())
                .build();
        return bookDto;
    }

    public static List<BookDto> mapToBookDtoList(List<Book> books) {
        return books.stream()
                .map(BookMapper::mapToBookDto)
                .collect(Collectors.toList());
    }

    public static Book mapToBook(BookDto bookDto) {
        Book book = Book.builder()
                .id(bookDto.getId())
                .name(bookDto.getName())
                .kategori(bookDto.getKategori())
                .pengarang(bookDto.getPengarang())
                .penerbit(bookDto.getPenerbit())
                .tahunTerbit(bookDto.getTahunTerbit())
                .lokasiRak(bookDto.getLokasiRak())
                .stok(bookDto.getStok())
                .jmlDipinjam(bookDto.getJmlDipinjam())
                .createdAt(bookDto.getCreatedAt())
                .updatedAt(bookDto.getUpdatedAt())
                .build();
        return book;
    }
}
