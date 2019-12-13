package org.cplier.codegen.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Setter
@Getter
@Entity(name = "t_micro_service")
public class MicroService {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @NotBlank(message = "服务名必填")
  private String name;

  private String description;

  @NotBlank(message = "标识必填")
  private String identification;

  @NotNull(message = "端口号唯一，默认随机4位数")
  @Min(value = 1, message = "必须大于0")
  @Max(value = 9999, message = "限定4位数")
  private int port;

  @Temporal(TemporalType.TIMESTAMP)
  @CreatedDate
  private Date createdAt;

  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  private Date updateAt;
}
