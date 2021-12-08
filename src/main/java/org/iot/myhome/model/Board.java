package org.iot.myhome.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Data
public class Board {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   //private Long userid;

   @NotNull
   @Size(min=2, max=30, message = "제목은 2자이상 30자 이하입니다. ")
   //@Column(nullable = true,name = "bo_subject")
   private String subject;

   //@Column(nullable = true,name = "bo_content")

   private String content;

   @ManyToOne
   @JoinColumn(name = "user_id")
   @JsonIgnore
   private User user;
}
