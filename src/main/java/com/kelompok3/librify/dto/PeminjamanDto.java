package com.kelompok3.librify.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeminjamanDto {
    private Long id;
    @NotEmpty(message = "User is required.")
    private UserDto user;
    @NotEmpty(message = "Book is required.")
    private BookDto book;
    @NotEmpty(message = "Status Peminjaman is required")
    private boolean statusPeminjaman;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date createdAt;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date updatedAt;
}