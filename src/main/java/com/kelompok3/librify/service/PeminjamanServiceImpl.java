package com.kelompok3.librify.service;

import com.kelompok3.librify.dto.PeminjamanDto;
import com.kelompok3.librify.mapper.PeminjamanMapper;
import com.kelompok3.librify.model.Peminjaman;
import com.kelompok3.librify.model.User;
import com.kelompok3.librify.repository.BookRepository;
import com.kelompok3.librify.repository.PeminjamanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.kelompok3.librify.mapper.UserMapper.mapToUserDto;

@Service
public class PeminjamanServiceImpl implements PeminjamanService{
    @Autowired
    private PeminjamanRepository peminjamanRepository;
    @Autowired
    private BookService bookService;

    @Override
    public List<PeminjamanDto> getPeminjamans() {
        List<Peminjaman> peminjamans = peminjamanRepository.findAll();
        List<PeminjamanDto> peminjamanDtos = peminjamans.stream()
                .map((peminjaman) -> (PeminjamanMapper.mapToPeminjamanDto(peminjaman)))
                .collect(Collectors.toList());
        return peminjamanDtos;
    }
    public List<PeminjamanDto> getPeminjamans(long id) {
        List<Peminjaman> peminjamans = peminjamanRepository.findAll();
        List<PeminjamanDto> peminjamanDtos = new ArrayList<>();
        for (Peminjaman peminjaman : peminjamans) {
            if (peminjaman.getUser().getId() == id) {
                PeminjamanDto peminjamanDto = PeminjamanMapper.mapToPeminjamanDto(peminjaman);
                peminjamanDtos.add(peminjamanDto);
            }
        }
        return peminjamanDtos;
    }
    public int getJmlPeminjaman(){
        int count = 0;
        List<PeminjamanDto> peminjamanDtos = getPeminjamans();
        for (PeminjamanDto peminjamanDto : peminjamanDtos){
            if (peminjamanDto.isStatusPeminjaman())
                count++;
        }
        return count;
    }

    @Override
    public void approvePeminjaman(PeminjamanDto peminjamanDto){
        peminjamanDto.setStatusPeminjaman(true);
        updatePeminjaman(peminjamanDto);
    }

    @Override
    public PeminjamanDto getPeminjaman(Long peminjamanId) {
        Optional<Peminjaman> peminjaman = peminjamanRepository.findById(peminjamanId);
        if (peminjaman.isPresent()) {
            return PeminjamanMapper.mapToPeminjamanDto(peminjaman.get());
        } else {
            return null;
        }
    }

    @Override
    public void updatePeminjaman(PeminjamanDto peminjamanDto) {
        Peminjaman peminjaman = PeminjamanMapper.mapToPeminjaman(peminjamanDto);
        peminjamanRepository.save(peminjaman);
    }

    @Override
    public void deletePeminjaman(Long peminjamanId) {
        peminjamanRepository.deleteById(peminjamanId);
    }

    @Override
    public void savePeminjaman(PeminjamanDto peminjamanDto) {
        Peminjaman peminjaman = PeminjamanMapper.mapToPeminjaman(peminjamanDto);
        peminjamanRepository.save(peminjaman);
    }

    @Override
    public void savePeminjaman(PeminjamanDto peminjamanDto, User user, long book_id){
        peminjamanDto.setUser(mapToUserDto(user));
        peminjamanDto.setBook(bookService.getBook(book_id));
        bookService.pinjam(book_id);
        peminjamanRepository.save(PeminjamanMapper.mapToPeminjaman(peminjamanDto));
    }
}
