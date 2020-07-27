package BankServer;

import java.util.Random;

public class Token {
    private String token;
    private long startingTime;

    public Token() {
        this.token = createToken();
        this.startingTime = System.currentTimeMillis();
    }

    private String createToken() {
        StringBuilder token;
        do {
            token = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < 16; i++) {
                token.append((char) (random.nextInt(80) + 33));
            }
        } while (isTokenExists(token.toString()));
        return token.toString();
    }

    public static boolean isTokenExists(String token) {
        for (Token value : BankServer.accountsWithTokens.values()) {
            if (value != null) {
                if (value.getToken().equals(token))
                    return true;
            }
        }
        return false;
    }

    public static Token getTokenByTokenString(String token) {
        for (Token value : BankServer.accountsWithTokens.values()) {
            if (value.getToken().equals(token)) {
                return value;
            }
        }
        return null;
    }

    public String getToken() {
        return token;
    }

    public long getStartingTime() {
        return startingTime;
    }

    public boolean checkExpiration() {
        return System.currentTimeMillis() - startingTime > 3600000;
    }
}
