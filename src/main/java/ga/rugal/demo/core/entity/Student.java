package ga.rugal.demo.core.entity;

import static config.SystemDefaultProperty.SCHEMA;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "student", schema = SCHEMA)
public class Student {

  private static final String SEQUENCE_NAME = "student_sid_seq";

  @Basic(optional = false)
  @Column(name = "sid")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
  @Id
  @SequenceGenerator(name = SEQUENCE_NAME, allocationSize = 1,
                     sequenceName = SCHEMA + "." + SEQUENCE_NAME)
  private Integer sid;

  @Size(max = 20)
  @Column(length = 20)
  private String name;

  @OneToMany(mappedBy = "student")
  private List<Registration> registrationList;
}
