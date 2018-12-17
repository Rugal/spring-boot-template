package ga.rugal.upgrade.core.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import config.SystemDefaultProperty;

import lombok.Data;

@Data
@Entity
@Table(name = "registration", schema = SystemDefaultProperty.SCHEMA)
public class Registration {

  @Id
  @Basic(optional = false)
  @NotNull
  @Column(nullable = false)
  private Integer rid;

  @Column
  private Integer grade;

  @JoinColumn(name = "cid", referencedColumnName = "cid")
  @ManyToOne
  private Course course;

  @JoinColumn(name = "sid", referencedColumnName = "sid")
  @ManyToOne
  private Student student;
}
