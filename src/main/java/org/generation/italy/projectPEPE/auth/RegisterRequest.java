package org.generation.italy.projectPEPE.auth;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.generation.italy.projectPEPE.model.entities.enums.Diet;
import org.generation.italy.projectPEPE.model.entities.enums.PhysicalActivity;
import org.generation.italy.projectPEPE.model.entities.enums.Sex;
import org.generation.italy.projectPEPE.model.entities.enums.Work;
import org.hibernate.annotations.Type;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String mail;
    private String password;
    private String firstname;
    private String lastname;
    private LocalDate dob;
    private int weight;
    private int height;
    private Sex sex;
    private Work work;
    private Diet diet;
    private PhysicalActivity physicalActivity;
    private double idealWeight;
    private int calorieReq;
}
