package com.cursos.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuditoryDTO {

    private String status;
    private String createUser;
    private String createUserName;
    private String createUserMail;
    @JsonFormat(
            shape = Shape.STRING,
            pattern = "dd/MM/yyyy HH:mm:ss"
    )
    @JsonDeserialize(
            using = LocalDateDeserializer.class
    )
    @JsonSerialize(
            using = LocalDateSerializer.class
    )
    private LocalDateTime createDate;

    private String updateUser;
    private String updateUserName;
    private String updateUserMail;
    @JsonFormat(
            shape = Shape.STRING,
            pattern = "dd/MM/yyyy HH:mm:ss"
    )
    @JsonDeserialize(
            using = LocalDateDeserializer.class
    )
    @JsonSerialize(
            using = LocalDateSerializer.class
    )
    private LocalDateTime updateDate;


    private String deleteUser;
    private String deleteUserName;
    private String deleteUserMail;
    @JsonFormat(
            shape = Shape.STRING,
            pattern = "dd/MM/yyyy HH:mm:ss"
    )
    @JsonDeserialize(
            using = LocalDateDeserializer.class
    )
    @JsonSerialize(
            using = LocalDateSerializer.class
    )
    private LocalDateTime deleteDate;
}
