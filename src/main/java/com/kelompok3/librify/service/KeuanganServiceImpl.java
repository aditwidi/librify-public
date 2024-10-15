package com.kelompok3.librify.service;

import com.kelompok3.librify.dto.KeuanganDto;
import com.kelompok3.librify.dto.PengembalianDto;
import com.kelompok3.librify.mapper.KeuanganMapper;
import com.kelompok3.librify.model.Keuangan;
import com.kelompok3.librify.repository.KeuanganRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class KeuanganServiceImpl implements KeuanganService{
    @Autowired
    private KeuanganRepository keuanganRepository;

    @Override
    public List<KeuanganDto> getKeuangans() {
        List<Keuangan> keuangans = keuanganRepository.findAll();
        List<KeuanganDto> keuanganDtos = keuangans.stream()
                .map((keuangan) -> (KeuanganMapper.mapToKeuanganDto(keuangan)))
                .collect(Collectors.toList());
        return keuanganDtos;
    }

    @Override
    public long getTotalPemasukan() {
        return keuanganRepository.findAll().stream()
                .filter(keuangan -> keuangan.getJenis() > 0)
                .mapToLong(Keuangan::getNominalArus)
                .sum();
    }

    @Override
    public long getTotalPengeluaran() {
        return keuanganRepository.findAll().stream()
                .filter(keuangan -> keuangan.getJenis() < 0)
                .mapToLong(Keuangan::getNominalArus)
                .sum();
    }

    public long getTotal(){
        return keuanganRepository.findAll().stream()
                .mapToLong(keuangan -> keuangan.getNominalArus() * keuangan.getJenis())
                .sum();
    }

    @Override
    public Page<Keuangan> findPaginated(Pageable pageable) {
        return keuanganRepository.findAll(pageable);
    }

    @Override
    public int countKeuangans() {
        return getKeuangans().size();
    }

    @Override
    public KeuanganDto getKeuangan(Long keuanganId) {
        Optional<Keuangan> keuangan = keuanganRepository.findById(keuanganId);
        if (keuangan.isPresent()) {
            return KeuanganMapper.mapToKeuanganDto(keuangan.get());
        } else {
            return null;
        }
    }
    @Override
    public void updateKeuangan(KeuanganDto keuanganDto) {
        Keuangan keuangan = KeuanganMapper.mapToKeuangan(keuanganDto);
        keuanganRepository.save(keuangan);
    }
    @Override
    public void deleteKeuangan(Long keuanganId) {
        keuanganRepository.deleteById(keuanganId);
    }

    @Override
    public void saveKeuangan(KeuanganDto keuanganDto) {
        Keuangan keuangan = KeuanganMapper.mapToKeuangan(keuanganDto);
        keuanganRepository.save(keuangan);
    }

    @Override
    public void saveKeuangan(PengembalianDto pengembalianDto) {
        KeuanganDto keuanganDto = new KeuanganDto();
        keuanganDto.setJenis(1);
        keuanganDto.setNominalArus(pengembalianDto.getDenda());
        keuanganDto.setDeskripsi("Denda");
        saveKeuangan(keuanganDto);
    }

    @Override
    public List<Long> getPendapatanBulanan() {
        List<Keuangan> keuanganList = keuanganRepository.findAll().stream()
                .filter(keuangan -> keuangan.getJenis() > 0)
                .toList();

        List<Long> monthlyIncome = new ArrayList<Long>(Collections.nCopies(12, 0L));

        for (Keuangan keuangan : keuanganList) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(keuangan.getCreatedAt());
            int month = cal.get(Calendar.MONTH);
            monthlyIncome.set(month, monthlyIncome.get(month) + keuangan.getNominalArus());
        }
        return monthlyIncome;
    }

    @Override
    public List<Long> getPengeluaranBulanan() {
        List<Keuangan> keuanganList = keuanganRepository.findAll().stream()
                .filter(keuangan -> keuangan.getJenis() < 0)
                .collect(Collectors.toList());

        List<Long> monthlyExpense = new ArrayList<Long>(Collections.nCopies(12, 0L));

        for (Keuangan keuangan : keuanganList) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(keuangan.getCreatedAt());
            int month = cal.get(Calendar.MONTH);
            monthlyExpense.set(month, monthlyExpense.get(month) + keuangan.getNominalArus());
        }
        return monthlyExpense;
    }
}
