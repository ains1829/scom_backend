package com.ains.myspring.services.function;

import java.security.SecureRandom;

public class Fonction {
  private static final SecureRandom random = new SecureRandom();
  private static final int BOUND = 10;

  public int generateCode() {
    int numberOfCode = 6;
    StringBuilder sb = new StringBuilder(numberOfCode);
    for (int i = 0; i < numberOfCode; i++) {
      sb.append(random.nextInt(BOUND));
    }
    return Integer.valueOf(sb.toString());
  }
}
