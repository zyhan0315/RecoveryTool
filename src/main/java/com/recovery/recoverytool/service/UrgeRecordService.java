package com.recovery.recoverytool.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recovery.recoverytool.entity.UrgeRecord;
import com.recovery.recoverytool.repository.UrgeRecordRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UrgeRecordService {

    @Autowired
    private UrgeRecordRepository repository;

    public void saveSummary(
            String summaryText,
            HttpSession session) {

        UrgeRecord record = new UrgeRecord();

        record.setIntensity(
                (Integer) session.getAttribute("intensity"));

        record.setEmotion(
                (String) session.getAttribute("emotion"));

        record.setTriggerReason(
                (String) session.getAttribute("triggerReason"));

        record.setNeedReason(
                (String) session.getAttribute("needReason"));

        record.setActionTaken(
                (String) session.getAttribute("actionTaken"));

        record.setResultText(
                (String) session.getAttribute("resultText"));

        record.setSummaryText(summaryText);

        record.setCreateTime(LocalDateTime.now());

        repository.save(record);

        session.removeAttribute("intensity");
        session.removeAttribute("emotion");
        session.removeAttribute("triggerReason");
        session.removeAttribute("needReason");
        session.removeAttribute("actionTaken");
        session.removeAttribute("resultText");
    }

    public UrgeRecord getRandomRecord() {

        List<UrgeRecord> records = repository.findAll();

        if (records.isEmpty()) {
            return null;
        }

        Random random = new Random();

        return records.get(
                random.nextInt(records.size()));
    }

    public List<UrgeRecord> getAllRecords() {

        return repository.findAllByOrderByCreateTimeDesc();
    }

    public UrgeRecord getRecordById(Long id) {

        return repository.findById(id)
                .orElse(null);
    }

    public Map<String, Long> getTriggerStats() {

        return repository.findAll()
                .stream()
                .filter(r -> r.getTriggerReason() != null)
                .collect(Collectors.groupingBy(
                        UrgeRecord::getTriggerReason,
                        Collectors.counting()
                ));
    }

    public Map<String, Long> getNeedStats() {

        return repository.findAll()
                .stream()
                .filter(r -> r.getNeedReason() != null)
                .collect(Collectors.groupingBy(
                        UrgeRecord::getNeedReason,
                        Collectors.counting()
                ));
    }
}