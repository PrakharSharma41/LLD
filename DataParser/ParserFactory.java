public class ParserFactory {
    public static Parser getParser(String fileType) {
        return switch (fileType.toLowerCase()) {
            case "csv" -> new CSVParser();
            case "json" -> new JSONParser();
            default -> throw new IllegalArgumentException("Unsupported file type");
        };
    }
}