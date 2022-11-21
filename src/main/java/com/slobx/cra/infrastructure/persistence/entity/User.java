package com.slobx.cra.infrastructure.persistence.entity;

import com.slobx.cra.infrastructure.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@SequenceGenerator(name = User.SEQUENCE_NAME, sequenceName = User.SEQUENCE_NAME, allocationSize = 1)
public class User extends BaseEntity {

    public static final String SEQUENCE_NAME = "S_USERS";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    private Long id;

    @Column(name = "USER_NAME", unique = true, nullable = false)
    private String userName;

    @Column(name = "PHONE_NUMBER", unique = true, nullable = false)
    private String phoneNumber;
}
