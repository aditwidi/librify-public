package com.kelompok3.librify.mapper;

import com.kelompok3.librify.dto.PeminjamanDto;
import com.kelompok3.librify.model.Peminjaman;

public class PeminjamanMapper {
    public static PeminjamanDto mapToPeminjamanDto(Peminjaman peminjaman) {
        PeminjamanDto peminjamanDto = PeminjamanDto.builder()
                .id(peminjaman.getId())
                .user(UserMapper.mapToUserDto(peminjaman.getUser()))
                .book(BookMapper.mapToBookDto(peminjaman.getBook()))
                .statusPeminjaman(peminjaman.isStatusPeminjaman())
                .createdAt(peminjaman.getCreatedAt())
                .updatedAt(peminjaman.getUpdatedAt())
                .build();
        return peminjamanDto;
    }

    public static Peminjaman mapToPeminjaman(PeminjamanDto peminjamanDto) {
        Peminjaman peminjaman = Peminjaman.builder()
                .id(peminjamanDto.getId())
                .user(UserMapper.mapToUser(peminjamanDto.getUser()))
                .book(BookMapper.mapToBook(peminjamanDto.getBook()))
                .statusPeminjaman(peminjamanDto.isStatusPeminjaman())
                .createdAt(peminjamanDto.getCreatedAt())
                .updatedAt(peminjamanDto.getUpdatedAt())
                .build();
        return peminjaman;
    }
}
