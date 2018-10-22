package uy.edu.ude.practicows.entity;

public class Response {
  private final int id;
  private final String type;
  private final String quote;

  public Response(int id, String type, String quote) {
    this.id = id;
    this.type = type;
    this.quote = quote;
  }

  public int getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  public String getQuote() {
    return quote;
  }
}
