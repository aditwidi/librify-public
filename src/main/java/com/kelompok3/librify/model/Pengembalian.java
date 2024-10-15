package com.kelompok3.librify.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "pengembalians")
public class Pengembalian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private long denda;

    @OneToOne
    @JoinColumn(name = "peminjaman_id", nullable = false)
    private Peminjaman peminjaman;

    @Column(nullable = false)
    private boolean statusPengembalian;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date tanggalPengembalian;

    @Column(nullable = false)
    @CreationTimestamp
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date updatedAt;
}
