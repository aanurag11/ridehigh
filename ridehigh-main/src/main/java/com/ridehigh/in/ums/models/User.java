package com.ridehigh.in.ums.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
  private long userId;
  private String firstName;
  private String lastName;
  private int age;
  private UserAddress address;
  private String gender;

}
