package com.tiago.util;

import org.springframework.batch.item.file.separator.SimpleRecordSeparatorPolicy;

public class BlankLineRecordSeparatorPolicy extends SimpleRecordSeparatorPolicy {

  @Override
  public boolean isEndOfRecord(String line) {
    return line.trim().length() != 0 && super.isEndOfRecord(line);
  }

  @Override
  public String postProcess(String line) {
    if (line == null || line.trim().length() == 0) {
      return null;
    }
    return super.postProcess(line);
  }

  @Override
  public String preProcess(String line) {
    if (line == null || line.trim().length() == 0) {
      return null;
    }
    return super.preProcess(line);
  }
}
