package com.kelompok3.librify.dto;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PengembalianDto {
    private Long id;
    @NotEmpty(message = "Denda is required.")
    private long denda;
    @NotEmpty(message = "Peminjaman is required.")
    private PeminjamanDto peminjaman;
    @NotEmpty(message = "Status Pengembalian is required")
    private boolean statusPengembalian;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date tanggalPengembalian;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date createdAt;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date updatedAt;
}
