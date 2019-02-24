package ga.rugal.demo.core.entity;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import config.SystemDefaultProperty;

import lombok.Data;

@Data
@Entity
@Table(name = "student", schema = SystemDefaultProperty.SCHEMA)
public class Student {

  @Id
  @Basic(optional = false)
  @NotNull
  @Column(nullable = false)
  private Integer sid;

  @Size(max = 20)
  @Column(length = 20)
  private String name;

  @OneToMany(mappedBy = "student")
  private List<Registration> registrationList;
}