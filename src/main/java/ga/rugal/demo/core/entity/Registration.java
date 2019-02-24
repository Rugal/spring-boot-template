package ga.rugal.demo.core.entity;

import static config.SystemDefaultProperty.SCHEMA;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name = "registration", schema = SCHEMA)
public class Registration {

  private static final String SEQUENCE_NAME = "registration_rid_seq";

  @Basic(optional = false)
  @Column(name = "rid")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
  @Id
  @SequenceGenerator(name = SEQUENCE_NAME, allocationSize = 1,
                     sequenceName = SCHEMA + "." + SEQUENCE_NAME)
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
