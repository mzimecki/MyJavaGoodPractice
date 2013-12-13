package com.zimek.concurrent.blocks.concurrenthashmap;

import com.zimek.concurrent.blocks.lock.Update;

public interface SimpleMicroBlogNode {
  void propagateUpdate(Update upd_, SimpleMicroBlogNode backup_);

  void confirmUpdate(SimpleMicroBlogNode other_, Update update_);

  String getIdent();
}
