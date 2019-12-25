package org.cplier.codegen.entity;

import lombok.Getter;
import lombok.Setter;
import org.cplier.codegen.annotation.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "t_micro_service")
public class MicroService {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @NotBlank(message = "服务名必填", groups = {OnCreate.class, OnUpdate.class})
  private String name;

  private String description;

  @NotBlank(message = "标识必填", groups = {OnCreate.class, OnUpdate.class})
  @UniqueIdentificationValidate(message = "标识已经被其它服务所使用", groups = {OnCreate.class})
  private String identification;

  @NotBlank(message = "表名不能为空，多个用逗号分隔", groups = {OnCreate.class, OnUpdate.class})
  private String tableNames;

  @NotBlank(message = "包名不能为空", groups = {OnCreate.class, OnUpdate.class})
  @PackagePatternValidate(message = "包名格式不不对", groups = {OnCreate.class, OnUpdate.class})
  private String packet;

  @NotNull(message = "端口号唯一，默认随机4位数", groups = {OnCreate.class, OnUpdate.class})
  @Min(value = 1, message = "必须大于0", groups = {OnCreate.class, OnUpdate.class})
  @Max(value = 9999, message = "限定4位数", groups = {OnCreate.class, OnUpdate.class})
  @UniquePortValidate(message = "端口已经被其它服务所使用", groups = {OnCreate.class})
  private int port;

  @Temporal(TemporalType.TIMESTAMP)
  @CreatedDate
  private Date createdAt;

  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  private Date updateAt;
}
