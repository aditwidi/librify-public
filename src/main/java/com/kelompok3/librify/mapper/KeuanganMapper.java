package com.kelompok3.librify.mapper;

import com.kelompok3.librify.dto.KeuanganDto;
import com.kelompok3.librify.model.Keuangan;

import java.util.List;
import java.util.stream.Collectors;

public class KeuanganMapper {
    public static KeuanganDto mapToKeuanganDto(Keuangan keuangan) {
        KeuanganDto keuanganDto = KeuanganDto.builder()
                .id(keuangan.getId())
                .nominalArus(keuangan.getNominalArus())
                .jenis(keuangan.getJenis())
                .deskripsi(keuangan.getDeskripsi())
                .build();
        return keuanganDto;
    }

    public static Keuangan mapToKeuangan(KeuanganDto keuanganDto) {
        Keuangan keuangan = Keuangan.builder()
                .id(keuanganDto.getId())
                .nominalArus(keuanganDto.getNominalArus())
                .jenis(keuanganDto.getJenis())
                .deskripsi(keuanganDto.getDeskripsi())
                .build();
        return keuangan;
    }
}
