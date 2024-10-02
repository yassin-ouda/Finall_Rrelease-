package com.EducationSystem.Enrollement.Services;

import com.EducationSystem.Enrollement.DTO.CourseDTO;
import com.EducationSystem.Enrollement.Model.Course;
import com.EducationSystem.Enrollement.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course createCourse(CourseDTO courseDTO) {
        Course course = new Course();
        course.setCourseCode(courseDTO.getCourseCode());
        course.setCourseTitle(courseDTO.getCourseTitle());
        course.setCategory(courseDTO.getCategory());

        return courseRepository.save(course);
    }

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(course -> new CourseDTO(course.getCourseCode(), course.getCourseTitle(),course.getCategory()))
                .collect(Collectors.toList());
    }

    public CourseDTO getCourseByCode(String courseCode) {
        Course course = courseRepository.findById(courseCode)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        return new CourseDTO(course.getCourseCode(), course.getCourseTitle(), course.getCategory());
    }

    public void deleteCourse(String code){
         courseRepository.deleteById(code);

    }
    public void updateCourse(CourseDTO courseDTO) {
        Course existingCourse = courseRepository.findById(courseDTO.getCourseCode())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        existingCourse.setCourseTitle(courseDTO.getCourseTitle());
        existingCourse.setCategory(courseDTO.getCategory());

        courseRepository.save(existingCourse);
    }

}
