import java.util.List;

public class Lexer {
  public void lex(String source) {
    Scanner scanner = new Scanner(source);
    List<String> tokens = scanner.scanTokens();
    for (String token : tokens) {
      System.out.printf("token: %s\n", token);
    }
  }
}
