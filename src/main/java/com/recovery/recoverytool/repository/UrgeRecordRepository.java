package com.recovery.recoverytool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recovery.recoverytool.entity.UrgeRecord;

public interface UrgeRecordRepository
        extends JpaRepository<UrgeRecord, Long> {

    List<UrgeRecord> findAllByOrderByCreateTimeDesc();

}