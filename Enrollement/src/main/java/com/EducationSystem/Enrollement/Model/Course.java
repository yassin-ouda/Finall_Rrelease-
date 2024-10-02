package com.EducationSystem.Enrollement.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nimbusds.jose.shaded.json.annotate.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Course {

    @Id
    private String courseCode;

    private String courseTitle;
    private String category;



    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @JsonBackReference // Break the cycle by ignoring back reference to Course
    private List<Enrollment> enrollments = new ArrayList<>();
}

