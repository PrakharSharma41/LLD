package rateLimiterType;

public class AttributeExtractorStrategyImpl implements AttributeExtractorStrategy{

    @Override
    public String extract(Request request,GroupBy groupBy) {
        return switch (groupBy) {
            case IP -> request.getIpAddress();
            case BROWSER -> (request instanceof ModifiedRequest mr) ? mr.getBrowser() : null;
        };
    }
    
}
