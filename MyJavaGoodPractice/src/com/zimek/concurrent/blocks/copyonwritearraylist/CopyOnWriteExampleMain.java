package com.zimek.concurrent.blocks.copyonwritearraylist;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.zimek.concurrent.blocks.lock.Author;
import com.zimek.concurrent.blocks.lock.Update;

/**
 * CoutndownLatches used here for setting order of updates (first thread t2).
 *
 */
public class CopyOnWriteExampleMain {

  public static void main(String[] a) {
    final CountDownLatch firstLatch = new CountDownLatch(1);
    final CountDownLatch secondLatch = new CountDownLatch(1);
    final Update.Builder ub = new Update.Builder();

    final CopyOnWriteArrayList<Update> l = new CopyOnWriteArrayList<>();
    l.add(ub.author(new Author("Ben")).updateText("I like pie").build());
    l.add(ub.author(new Author("Charles")).updateText("I like ham on rye")
        .build());

    Lock lock = new ReentrantLock();
    final MicroBlogTimeline tl1 = new MicroBlogTimeline("TL1", l, lock);
    final MicroBlogTimeline tl2 = new MicroBlogTimeline("TL2", l, lock);

    Thread t1 = new Thread() {
      public void run() {
        l.add(ub.author(new Author("Jeffrey")).updateText("I like a lot of things").build());
        tl1.prep();
        firstLatch.countDown();
        try {
          secondLatch.await();
        } catch (InterruptedException e) {
        }
        tl1.printTimeline();
      }
    };

    Thread t2 = new Thread() {
      public void run() {
        try {
          firstLatch.await(); 	//at this point t1 added "Jeffrey" update to array and updated iterator
          						//by calling prep() and released a latch. t2 can continue, adds Gavin update, 
          						//updates iterator and prints timeline not waiting for t1 latch
          l.add(ub.author(new Author("Gavin")).updateText("I like otters").build());
          tl2.prep();
          secondLatch.countDown();
        } catch (InterruptedException e) {
        }
        tl2.printTimeline();
      }
    };
    t1.start();
    t2.start();
  }

}
