package Multithreading;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

public class ResourceAwareRateLimitExample {

    private static final int MAX_CONCURRENT_REQUESTS = 10;
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final String API_URL = "https://api.chucknorris.io/jokes/random";

    public static void main(String[] args) {
        try {
            // Fetch jokes, respecting rate limit of API
            List<String> jokes = fetchJokesWithRateLimit(100);
            jokes.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

    private static List<String> fetchJokesWithRateLimit(final int numOfJokes) throws Exception {
        Semaphore semaphore = new Semaphore(MAX_CONCURRENT_REQUESTS);
        List<String> jokes = new ArrayList<>();

        try (ExecutorService service = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<String>> futures = new ArrayList<>();
            for (int i = 0; i < numOfJokes; i++) {
                futures.add(service.submit(() -> {
                    // Acquire a permit before making an API request
                    semaphore.acquire();
                    try {
                        return getJokeFromAPI();
                    } finally {
                        // Release the permit regardless of success/failure
                        semaphore.release();
                    }
                }));
            }

            // Get results from futures
            for (Future<String> future: futures) {
                jokes.add(future.get());
            }
        }

        return jokes;
    }

    private static String getJokeFromAPI() throws Exception {
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(API_URL))
                .build();
        HttpResponse<String> response = CLIENT
                .send(request, HttpResponse.BodyHandlers.ofString());
        return response.body(); // Assuming the API returns the joke in the body
    }
}
