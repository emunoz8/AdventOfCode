import java.net.http.*;
import java.net.URI;
import java.nio.file.*;
import java.io.IOException;

public class WebSaver {

    public static void saveWebContent(String url, String filePath) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Files.writeString(Paths.get(filePath), response.body());
        System.out.println("Content saved to: " + filePath);
    }

    public static void createTxt(String url, String filePath) {
        try {
            WebSaver.saveWebContent(url, filePath);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
