package com.ridehigh.in.ums.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAddress {
  private long lat;
  private String address;


}
