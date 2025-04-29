package Multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PasswordGuess {
    private static final int MAX_PASSWORD_RANGE = 9999;
    public static void main(String[] args) {
        final int randomPassword = new Random().nextInt(MAX_PASSWORD_RANGE);
        Vault vault = new Vault(randomPassword);
        
        List<Thread> threads = new ArrayList<>();
        threads.add(new AscendingHacker(vault));
        threads.add(new DescendingHacker(vault));
        threads.add(new PoliceThread());

        System.out.println("Password to guess: " + randomPassword);
        threads.forEach(Thread::start);
    }

    private static class PoliceThread extends Thread {
        @Override
        public void run() {
            for(int i = 10; i > 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
                System.out.println(i);
            }

            System.out.println("Game over - hacker caught.");
            System.exit(0);
        }
    }

    private static class AscendingHacker extends HackerThread {
        public AscendingHacker(Vault vault) {
            super(vault);
        }
        
        @Override
        public void run() {
            for (int guess = 0; guess < MAX_PASSWORD_RANGE; guess++) {
                if (vault.isCorrectPassword(guess)) {
                    System.out.println(this.getName() + " guessed the password: " + guess);
                    System.exit(0);
                }
            }
        }
    }

    private static class DescendingHacker extends HackerThread {
        public DescendingHacker(Vault vault) {
            super(vault);
        }
        
        @Override
        public void run() {
            for (int guess = MAX_PASSWORD_RANGE; guess > 0 ; guess--) {
                if (vault.isCorrectPassword(guess)) {
                    System.out.println(this.getName() + " guessed the password: " + guess);
                    System.exit(0);
                }
            }
        }
    }

    private static abstract class HackerThread extends Thread {
        protected Vault vault;

        public HackerThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getName());
            this.setPriority(MAX_PRIORITY);
        }

        @Override
        public void run() {
            System.out.println("Starting thread " + this.getName());
            super.start();
        }
    }

    private record Vault(int password) {
        public boolean isCorrectPassword(int guessPassword) {
                try {
                    Thread.sleep(2);
                } catch (InterruptedException ie) {
                    ie.getMessage();
                }

                return this.password == guessPassword;
            }
        }
}