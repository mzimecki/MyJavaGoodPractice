package com.zimek.concurrent.immutable;

import com.zimek.concurrent.blocks.lock.Author;
import com.zimek.concurrent.blocks.lock.Update;

public class Main {
	public static void main(String [] args) {
		Update.Builder b = new Update.Builder(); 
		Update u = b.author(new Author("Zenek")).updateText("some text").build();
	}
}
