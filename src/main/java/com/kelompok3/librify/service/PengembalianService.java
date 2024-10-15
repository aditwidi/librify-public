package com.kelompok3.librify.service;

import com.kelompok3.librify.dto.PeminjamanDto;
import com.kelompok3.librify.dto.PengembalianDto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface PengembalianService {
    public List<PengembalianDto> getPengembalians();
    public List<PengembalianDto> getPengembalians(long id);
    public PengembalianDto getPengembalian(Long PengembalianId);
    public List<PeminjamanDto> getPeminjamanUser(long id);
    public void updatePengembalian(PengembalianDto PengembalianDto);
    public void deletePengembalian(Long PengembalianId);
    public void savePengembalian(PengembalianDto pengembalianDto);
    public void savePengembalian(PeminjamanDto peminjamanDto);
    public void kembalikan(PengembalianDto pengembalianDto, Date tanggalPengembalian);
}
