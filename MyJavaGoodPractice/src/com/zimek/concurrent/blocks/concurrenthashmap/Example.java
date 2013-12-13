package com.zimek.concurrent.blocks.concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;

import com.zimek.concurrent.blocks.lock.Update;

public class Example implements SimpleMicroBlogNode {

  private final String identifier;

  private final ConcurrentHashMap<Update, Long> arrivalTime = new ConcurrentHashMap<>();

  public Example(String identifier_) {
    identifier = identifier_;
  }

  @Override
  public void propagateUpdate(Update upd_, SimpleMicroBlogNode backup_) {
    long currentTime = System.currentTimeMillis();
    arrivalTime.putIfAbsent(upd_, currentTime);
  }

  @Override
  public void confirmUpdate(SimpleMicroBlogNode other_, Update update_) {
    Long timeRecvd = arrivalTime.get(update_);
    System.out.println("Recvd confirm: " + update_.getUpdateText() + " from "
        + other_.getIdent());
  }

  @Override
  public String getIdent() {
    return identifier;
  }

}