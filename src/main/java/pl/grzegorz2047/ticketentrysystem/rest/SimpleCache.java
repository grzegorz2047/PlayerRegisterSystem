package pl.grzegorz2047.ticketentrysystem.rest;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class SimpleCache {
    private final int MAX_ATTEMPT = 4;
    private LoadingCache<String, Integer> attemptsCache;
    private final int durationInHours = 4;

    SimpleCache() {
        attemptsCache = CacheBuilder.newBuilder()
            .expireAfterWrite(durationInHours, TimeUnit.HOURS)
            .build(
                    new CacheLoader<>() {
                        @Override
                        public Integer load(String key) {
                            return 0;
                        }
                    });

    }

    public boolean canAttempt(String ip)  {
        return attemptsCache.getUnchecked(ip) < MAX_ATTEMPT;
    }
    public void attempt(String ip) {
        int attempts = attemptsCache.getUnchecked(ip);
        attempts++;
        attemptsCache.put(ip, attempts);
    }
}
