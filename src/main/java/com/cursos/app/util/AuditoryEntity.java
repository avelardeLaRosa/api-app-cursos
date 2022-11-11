package com.cursos.app.util;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class AuditoryEntity {

    @Column(name = "status")
    private String status;


    @Column(name = "create_user")
    private String createUser;
    @Column(name = "create_user_name")
    private String createUsername;
    @Column(name = "create_user_email")
    private String createUserMail;
    @Column(name = "create_date")
    private LocalDateTime createDate;


    @Column(name = "update_user")
    private String updateUser;
    @Column(name = "update_user_name")
    private String updateUserName;
    @Column(name = "update_user_email")
    private String updateUserMail;
    @Column(name = "update_date")
    private LocalDateTime updateDate;


    @Column(name = "delete_user")
    private String deleteUser;
    @Column(name = "delete_user_name")
    private String deleteUserName;
    @Column(name = "delete_user_email")
    private String deleteUserMail;
    @Column(name = "delete_date")
    private LocalDateTime deleteDate;


}
