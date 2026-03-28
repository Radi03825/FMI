package bg.sofia.uni.fmi.mjt.newsfeed.exceptions.factory;

import bg.sofia.uni.fmi.mjt.newsfeed.dto.ErrorDTO;
import bg.sofia.uni.fmi.mjt.newsfeed.exceptions.ApiException;
import bg.sofia.uni.fmi.mjt.newsfeed.exceptions.BadRequestException;
import bg.sofia.uni.fmi.mjt.newsfeed.exceptions.ServiceErrorException;
import bg.sofia.uni.fmi.mjt.newsfeed.exceptions.TooManyRequestException;
import bg.sofia.uni.fmi.mjt.newsfeed.exceptions.UnauthorizedApiKeyException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExceptionFactoryTest {

    private final ErrorDTO errorDTO = new ErrorDTO("TEST_CODE", "Test error message");

    @Test
    public void testCreateBadRequestException() {
        ApiException ex = ExceptionFactory.createException(400, errorDTO);

        String expectedMessage = "BadRequestException: { ApiException: Test error message, statusCode: 400, code: TEST_CODE }";
        String string = ex.toString();

        assertEquals(expectedMessage, string, "Exception message should be as expected");
        assertTrue(ex instanceof BadRequestException, "Exception should be BadRequestException");
    }

    @Test
    public void testCreateUnauthorizedApiKeyException() {
        ApiException ex = ExceptionFactory.createException(401, errorDTO);

        String expectedMessage = "UnauthorizedApiKeyException: { ApiException: Test error message, statusCode: 401, code: TEST_CODE }";
        String string = ex.toString();

        assertEquals(expectedMessage, string, "Exception message should be as expected");
        assertTrue(ex instanceof UnauthorizedApiKeyException, "Exception should be UnauthorizedApiKeyException");
    }

    @Test
    public void testCreateTooManyRequestException() {
        ApiException ex = ExceptionFactory.createException(429, errorDTO);

        String expectedMessage = "TooManyRequestException: { ApiException: Test error message, statusCode: 429, code: TEST_CODE }";
        String string = ex.toString();

        assertEquals(expectedMessage, string, "Exception message should be as expected");
        assertTrue(ex instanceof TooManyRequestException, "Exception should be TooManyRequestException");
    }

    @Test
    public void testCreateServiceErrorException() {
        ApiException ex = ExceptionFactory.createException(500, errorDTO);

        String expectedMessage = "ServiceErrorException: { ApiException: Test error message, statusCode: 500, code: TEST_CODE }";
        String string = ex.toString();

        assertEquals(expectedMessage, string, "Exception message should be as expected");
        assertTrue(ex instanceof ServiceErrorException, "Exception should be ServiceErrorException");
    }

    @Test
    public void testCreateDefaultException() {
        ApiException ex = ExceptionFactory.createException(403, errorDTO);

        String expectedMessage = "ApiException: Test error message, statusCode: 403, code: TEST_CODE";
        String string = ex.toString();

        assertEquals(expectedMessage, string, "Exception message should be as expected");
        assertTrue(ex instanceof ApiException, "Exception should be ApiException");
        assertFalse(ex instanceof BadRequestException, "Exception should not be BadRequestException");
        assertFalse(ex instanceof UnauthorizedApiKeyException, "Exception should not be UnauthorizedApiKeyException");
        assertFalse(ex instanceof TooManyRequestException, "Exception should not be TooManyRequestException");
        assertFalse(ex instanceof ServiceErrorException, "Exception should not be ServiceErrorException");
    }
    
}
