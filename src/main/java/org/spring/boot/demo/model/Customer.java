package org.spring.boot.demo.model;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Customer implements Serializable {

    private static final long serialVersionUID = -1417367370185359001L;

	@Id
    @GeneratedValue
    private int id;

    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private boolean isActive;

}