package com.kelompok3.librify.service;

import com.kelompok3.librify.dto.PeminjamanDto;
import com.kelompok3.librify.dto.PengembalianDto;
import com.kelompok3.librify.mapper.PeminjamanMapper;
import com.kelompok3.librify.mapper.PengembalianMapper;
import com.kelompok3.librify.model.Peminjaman;
import com.kelompok3.librify.model.Pengembalian;
import com.kelompok3.librify.repository.PengembalianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PengembalianServiceImpl implements PengembalianService{
    @Autowired
    private PengembalianRepository pengembalianRepository;
    @Autowired
    private PeminjamanService peminjamanService;
    @Autowired
    private KeuanganService keuanganService;

    @Override
    public List<PengembalianDto> getPengembalians() {
        List<Pengembalian> pengembalians = pengembalianRepository.findAll();
        List<PengembalianDto> pengembalianDtos = pengembalians.stream()
                .map((pengembalian) -> (PengembalianMapper.mapToPengembalianDto(pengembalian)))
                .collect(Collectors.toList());
        return pengembalianDtos;
    }

    @Override
    public List<PeminjamanDto> getPeminjamanUser(long id) {
        List<Pengembalian> pengembalians = pengembalianRepository.findAll();
        List<PeminjamanDto> peminjamanDtos = new ArrayList<>();
        for (Pengembalian pengembalian : pengembalians) {
            if (pengembalian.getPeminjaman().getUser().getId() == id && !pengembalian.isStatusPengembalian()) {
                PeminjamanDto peminjamanDto = PeminjamanMapper.mapToPeminjamanDto(pengembalian.getPeminjaman());
                peminjamanDtos.add(peminjamanDto);
            }
        }
        return peminjamanDtos;
    }



    public List<PengembalianDto> getPengembalians(long id) {
        List<Pengembalian> pengembalians = pengembalianRepository.findAll();
        List<PengembalianDto> pengembalianDtos = new ArrayList<>();
        for (Pengembalian pengembalian : pengembalians) {
            if (pengembalian.getPeminjaman().getUser().getId() == id) {
                PengembalianDto pengembalianDto = PengembalianMapper.mapToPengembalianDto(pengembalian);
                pengembalianDtos.add(pengembalianDto);
            }
        }
        return pengembalianDtos;
    }

    @Override
    public PengembalianDto getPengembalian(Long pengembalianId) {
        Optional<Pengembalian> pengembalian = pengembalianRepository.findById(pengembalianId);
        if (pengembalian.isPresent()) {
            return PengembalianMapper.mapToPengembalianDto(pengembalian.get());
        } else {
            return null;
        }
    }

    @Override
    public void updatePengembalian(PengembalianDto pengembalianDto) {
        Pengembalian pengembalian = PengembalianMapper.mapToPengembalian(pengembalianDto);
        pengembalianRepository.save(pengembalian);
    }

    @Override
    public void deletePengembalian(Long pengembalianId) {
        pengembalianRepository.deleteById(pengembalianId);
    }

    @Override
    public void savePengembalian(PengembalianDto pengembalianDto) {
        Pengembalian pengembalian = PengembalianMapper.mapToPengembalian(pengembalianDto);
        pengembalianRepository.save(pengembalian);
    }
    @Override
    public void savePengembalian(PeminjamanDto peminjamanDto) {
        peminjamanService.approvePeminjaman(peminjamanDto);
        PengembalianDto pengembalianDto = new PengembalianDto();
        pengembalianDto.setPeminjaman(peminjamanDto);
        Pengembalian pengembalian = PengembalianMapper.mapToPengembalian(pengembalianDto);
        pengembalianRepository.save(pengembalian);
    }

    public void kembalikan(PengembalianDto pengembalianDto, Date tanggalPengembalian){
        pengembalianDto.setStatusPengembalian(true);
        pengembalianDto.setTanggalPengembalian(tanggalPengembalian);

        LocalDate tanggalPeminjaman = pengembalianDto.getCreatedAt().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate tanggalPengembalianLocal = tanggalPengembalian.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        long denda = calculateDenda(tanggalPeminjaman, tanggalPengembalianLocal);
        pengembalianDto.setDenda(denda);

        updatePengembalian(pengembalianDto);
        if (denda > 0)
            keuanganService.saveKeuangan(pengembalianDto);
    }

    private long calculateDenda(LocalDate tanggalPeminjaman, LocalDate tanggalPengembalian) {
        long daysBetween = ChronoUnit.DAYS.between(tanggalPeminjaman, tanggalPengembalian);
        if (daysBetween <= 3) {
            return 0;
        } else {
            long dendaPerHari = 5000;
            return (daysBetween - 3) * dendaPerHari;
        }

    }
}
