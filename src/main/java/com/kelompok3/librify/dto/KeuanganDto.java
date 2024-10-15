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
public class KeuanganDto {
    private Long id;
    @NotEmpty(message = "Nominal is required.")
    private long nominalArus;
    @NotEmpty(message = "Jenis is required.")
    private int jenis;
    @NotEmpty(message = "Deskripsi is required.")
    private String deskripsi;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date createdAt;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date updatedAt;
}
