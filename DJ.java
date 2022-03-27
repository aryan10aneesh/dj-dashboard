public class DJ {

    public static void main(String[] args) 
    {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://soundcloud4.p.rapidapi.com/search?query=almost%20lover&type=all"))
            .header("X-RapidAPI-Host", "soundcloud4.p.rapidapi.com")
            .header("X-RapidAPI-Key", "bbc179cde2mshe4d8449e9e67328p1a22ccjsn8f02e50e781d")
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        
    }

}
