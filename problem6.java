import java.util.*;

class TokenBucket {
    int tokens;
    long lastRefill;
    int maxTokens;

    TokenBucket(int maxTokens) {
        this.maxTokens = maxTokens;
        this.tokens = maxTokens;
        this.lastRefill = System.currentTimeMillis();
    }
}

public class Problem6 {

    private HashMap<String, TokenBucket> clients = new HashMap<>();
    private static final int LIMIT = 1000;
    private static final long REFILL_TIME = 3600000;

    public synchronized void checkRateLimit(String clientId) {

        clients.putIfAbsent(clientId, new TokenBucket(LIMIT));
        TokenBucket bucket = clients.get(clientId);

        long now = System.currentTimeMillis();

        if (now - bucket.lastRefill > REFILL_TIME) {
            bucket.tokens = LIMIT;
            bucket.lastRefill = now;
        }

        if (bucket.tokens > 0) {
            bucket.tokens--;
            System.out.println("Allowed (" + bucket.tokens + " remaining)");
        } else {
            System.out.println("Denied - limit exceeded");
        }
    }

    public static void main(String[] args) {

        Problem6 limiter = new Problem6();

        limiter.checkRateLimit("abc123");
        limiter.checkRateLimit("abc123");
        limiter.checkRateLimit("abc123");
    }
}