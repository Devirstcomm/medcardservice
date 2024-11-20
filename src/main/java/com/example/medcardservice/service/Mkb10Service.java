package com.example.medcardservice.service;

import com.example.medcardservice.model.Mkb10Entry;
import com.example.medcardservice.repository.Mkb10Repository;
import com.example.medcardservice.util.CsvUtils;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class Mkb10Service {

    private final Mkb10Repository mkb10Repository;
    private static final Logger logger = LoggerFactory.getLogger(Mkb10Service.class);

    @Autowired
    public Mkb10Service(Mkb10Repository mkb10Repository) {
        this.mkb10Repository = mkb10Repository;
    }

    @Cacheable("mkb10Entries") // Аннотация для кэширования
    public Set<Mkb10Entry> getAllMkb10Entries() {
        return new HashSet<>(mkb10Repository.findAll()); // Обращение к базе данных и преобразование в HashSet
    }


    @Scheduled(cron = "0 0 0 * * ?") // Ежедневно в полночь
    @CacheEvict(value = "mkb10Entries", allEntries = true)
    @Transactional
    public void updateMkb10EntriesFromCsv() throws IOException {
        logger.info("Запуск обновления данных из CSV");

        logger.info("Запуск ежедневного обновления справочника МКБ-10");

        try {
            Set<Mkb10Entry> entries = CsvUtils.parseMkb10Entry();
            logger.info("Загружено {} записей из CSV", entries.size());

            // Сохраняет или обновляет записи в базе данных
            mkb10Repository.saveAll(entries);

            logger.info("Справочник МКБ-10 успешно обновлен в базе данных");
        }
        catch (IOException | CsvValidationException e) {
            logger.error("Ошибка при обновлении справочника МКБ-10", e);
        }
    }
}

