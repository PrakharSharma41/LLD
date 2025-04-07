package rateLimiterType;

public interface AttributeExtractorStrategy {
    String extract(Request request,GroupBy groupBy);
}
