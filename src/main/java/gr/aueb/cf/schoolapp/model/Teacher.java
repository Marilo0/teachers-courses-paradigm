package gr.aueb.cf.schoolapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private Boolean active;

    @JoinColumn(name = "region_id")
    @ManyToOne
    private Region region;

    @ManyToMany(mappedBy = "teachers")
    @Getter(AccessLevel.PROTECTED)
    private Set<Course> courses = new HashSet<>();

    @JoinColumn(name = "teachers_more_info_id")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private TeachersMoreInfo teachersMoreInfo;

    public void addCourse(Course course){
        if (courses == null) courses = new HashSet<>();
        courses.add(course);
        course.getTeachers().add(this);
    }

    public void removeCourse(Course course){
        if (courses == null) return;
        courses.remove(course);
        course.getTeachers().remove(this);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", active=" + active +
                '}';
    }

}
