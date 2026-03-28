package bg.sofia.uni.fmi.mjt.newsfeed.exceptions.factory;

import bg.sofia.uni.fmi.mjt.newsfeed.dto.ErrorDTO;
import bg.sofia.uni.fmi.mjt.newsfeed.exceptions.ApiException;
import bg.sofia.uni.fmi.mjt.newsfeed.exceptions.BadRequestException;
import bg.sofia.uni.fmi.mjt.newsfeed.exceptions.ServiceErrorException;
import bg.sofia.uni.fmi.mjt.newsfeed.exceptions.TooManyRequestException;
import bg.sofia.uni.fmi.mjt.newsfeed.exceptions.UnauthorizedApiKeyException;

public class ExceptionFactory {

    private static final int BAD_REQUEST_STATUS_CODE = 400;
    private static final int UNAUTHORIZED_STATUS_CODE = 401;
    private static final int TOO_MANY_REQUESTS_STATUS_CODE = 429;
    private static final int SERVICE_ERROR_STATUS_CODE = 500;

    public static ApiException createException(int statusCode, ErrorDTO errorDTO) {
        String code = errorDTO.code();
        String message = errorDTO.message();

        return switch (statusCode) {
            case BAD_REQUEST_STATUS_CODE -> new BadRequestException(statusCode, code, message);
            case UNAUTHORIZED_STATUS_CODE -> new UnauthorizedApiKeyException(statusCode, code, message);
            case TOO_MANY_REQUESTS_STATUS_CODE -> new TooManyRequestException(statusCode, code, message);
            case SERVICE_ERROR_STATUS_CODE -> new ServiceErrorException(statusCode, code, message);
            default -> new ApiException(statusCode, code, message);
        };
    }

}
