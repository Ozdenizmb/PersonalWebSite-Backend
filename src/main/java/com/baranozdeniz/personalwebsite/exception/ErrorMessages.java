package com.baranozdeniz.personalwebsite.exception;

public class ErrorMessages {

    private ErrorMessages(){}

    public static final String DEFAULT_ERROR_MESSAGE = "An unexpected error occurred! Please contact the api support!";

    public static final String USER_NOT_FOUND = "User Not Found!";

    public static final String ADMIN_NOT_FOUND = "Admin Not Found!";

    public static final String WRONG_ADMIN_KEY = "Wrong Admin Key. You Are Not Admin. Fuck Off!";

    public static final String PROJECT_NOT_FOUND = "Project Not Found!";

    public static final String COMMENT_NOT_FOUND = "Comment Not Found!";

    public static final String LIKE_NOT_FOUND = "Like Not Found!";

    public static final String FILE_NOT_FOUND = "File Not Found!";

    public static final String FILE_CANNOT_DELETE = "An error occurred while deleting the file!";

    public static final String FILE_CANNOT_WRITE = "An error occurred while uploading the file!";

    public static final String UNSUPPORTED_FILE_TYPE = "Unsupported file. Only PNG, JPEG, JPG, MP3, MP4, PDF and CSV supported";

}
