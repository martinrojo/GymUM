package com.um.gym.application.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_movement")
public class UserMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private Date timeIn;

    @Basic
    private Date timeOut;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

}
