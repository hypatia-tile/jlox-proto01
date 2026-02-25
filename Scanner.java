import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ourselves scanner
 */
class Scanner {
  private final String source;
  private final List<String> tokens = new ArrayList<>();

  Scanner(String source) {
    this.source = source;
  }

  public List<String> scanTokens() {
    // for simplicity, we just split the source by whitespace
    String[] parts = source.split("\\s+");
    for (String part : parts) {
      if (!part.isEmpty()) {
        tokens.add(part);
      }
    }
    return tokens;
  }
}
