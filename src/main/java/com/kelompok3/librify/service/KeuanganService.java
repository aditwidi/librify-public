package com.kelompok3.librify.service;

import com.kelompok3.librify.dto.KeuanganDto;
import com.kelompok3.librify.dto.PengembalianDto;
import com.kelompok3.librify.model.Keuangan;
import com.kelompok3.librify.model.Pengembalian;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface KeuanganService {
    public List<KeuanganDto> getKeuangans();
    public KeuanganDto getKeuangan(Long KeuanganId);
    public Page<Keuangan> findPaginated(Pageable pageable);
    public void updateKeuangan(KeuanganDto KeuanganDto);
    public void deleteKeuangan(Long KeuanganId);
    public void saveKeuangan(KeuanganDto KeuanganDto);
    public void saveKeuangan(PengembalianDto pengembalianDto);
    public int countKeuangans();
    public long getTotalPemasukan();
    public long getTotalPengeluaran();
    public long getTotal();
    public List<Long> getPendapatanBulanan();
    public List<Long> getPengeluaranBulanan();
}
