package com.example.medcardservice.controller;

import com.example.medcardservice.dto.PatientDTO;
import com.example.medcardservice.model.ErrorDetails;
import com.example.medcardservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Контроллер пациентов", description = "Операции, связанные с пациентами")
@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @Operation(summary = "Получить пациента по ID", description = "Извлечь данные пациента по его ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пациент найден"),
            @ApiResponse(responseCode = "404", description = "Пациент не найден",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorDetails.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable @Parameter(description = "Идентификатор пациента", required = true) int id) {
        PatientDTO patientDTO = patientService.getPatientById(id);
        return ResponseEntity.status(HttpStatus.OK).body(patientDTO);
    }

    @Operation(summary = "Создать нового пациента", description = "Добавить нового пациента в систему")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Пациент создан"),
            @ApiResponse(responseCode = "400", description = "Недопустимый ввод",
                            content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class),
                            examples = @ExampleObject(value = "{\"status\":400, \"message\":\"Ошибка валидации\", \"timestamp\":1234567890123456}")))
    })
    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(@Valid @RequestBody @Parameter(description = "Детали нового пациента, включая имя, фамилию, отчество, пол, дату рождения, адрес и номер ОМС", required = true) PatientDTO patientDTO) {
        patientService.createPatient(patientDTO);
        return new ResponseEntity<>(patientDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Обновить данные пациента", description = "Обновить информацию о существующем пациенте")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пациент обновлён"),
            @ApiResponse(responseCode = "404", description = "Пациент не найден",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))),
            @ApiResponse(responseCode = "400", description = "Недопустимый ввод",
                            content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class),
                            examples = @ExampleObject(value = "{\"status\":400, \"message\":\"Ошибка валидации\", \"timestamp\":1234567890123456}")))
    })
    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@Valid @PathVariable @Parameter(description = "Идентификатор пациента", required = true) int id,
                                                    @Parameter(description = "Детали нового пациента, включая имя, фамилию, отчество, пол, дату рождения, адрес и номер ОМС", required = true) @RequestBody PatientDTO patientDTO) {
        return ResponseEntity.ok(patientService.updatePatient(id, patientDTO));
    }

    @Operation(summary = "Удалить пациента", description = "Удалить пациента из системы")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Пациент удалён"),
            @ApiResponse(responseCode = "404", description = "Пациент не найден",  content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable @Parameter(description = "Идентификатор пациента", required = true) int id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
