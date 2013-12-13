package com.zimek.concurrent.blocks.lock;

public interface ConfirmingMicroBlogNode {
  void propagateUpdate(Update upd_, ConfirmingMicroBlogNode backup_);

  boolean tryConfirmUpdate(ConfirmingMicroBlogNode other_, Update update_);

  String getIdent();
}
