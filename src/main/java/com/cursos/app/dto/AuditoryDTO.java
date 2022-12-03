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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getCreateUserMail() {
        return createUserMail;
    }

    public void setCreateUserMail(String createUserMail) {
        this.createUserMail = createUserMail;
    }


    public LocalDateTime getCreateDate() {
        return createDate;
    }

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
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public String getUpdateUserMail() {
        return updateUserMail;
    }

    public void setUpdateUserMail(String updateUserMail) {
        this.updateUserMail = updateUserMail;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

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
    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public String getDeleteUser() {
        return deleteUser;
    }

    public void setDeleteUser(String deleteUser) {
        this.deleteUser = deleteUser;
    }

    public String getDeleteUserName() {
        return deleteUserName;
    }

    public void setDeleteUserName(String deleteUserName) {
        this.deleteUserName = deleteUserName;
    }

    public String getDeleteUserMail() {
        return deleteUserMail;
    }

    public void setDeleteUserMail(String deleteUserMail) {
        this.deleteUserMail = deleteUserMail;
    }


    public LocalDateTime getDeleteDate() {
        return deleteDate;
    }

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
    public void setDeleteDate(LocalDateTime deleteDate) {
        this.deleteDate = deleteDate;
    }
}
