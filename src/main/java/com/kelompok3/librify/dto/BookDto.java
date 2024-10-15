package com.kelompok3.librify.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Long id;
    @NotEmpty(message = "Name is required.")
    private String name;
    @NotEmpty(message = "Kategori is required.")
    private String kategori;
    @NotEmpty(message = "Pengarang is required.")
    private String pengarang;
    @NotEmpty(message = "Penerbit is required")
    private String penerbit;
    @NotEmpty(message = "Tahun Terbit is required.")
    private LocalDate tahunTerbit;
    @NotEmpty(message = "Lokasi Rak is required.")
    private String lokasiRak;
    @NotEmpty(message = "Stok is required.")
    private int stok;
    @NotEmpty(message = "Jumlah dipinjam is required.")
    private int jmlDipinjam;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date createdAt;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date updatedAt;
}
