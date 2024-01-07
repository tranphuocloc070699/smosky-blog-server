package com.smosky.blogserver.constant;

public final class PostsConstant {
  private PostsConstant() {
    // restrict instantiation
  }

  public static final String MESSAGE_FAIL = "Request fail. Error occur!";
  public static final String  STATUS_201 = "201";
  public static final String  MESSAGE_201 = "Post created successfully";
  public static final String  STATUS_200 = "200";
  public static final String  MESSAGE_200 = "Request processed successfully";

  public static final String  STATUS_409 = "409";
  public static final String  MESSAGE_409 = "Conflict ";

  public static final String  STATUS_417 = "417";
  public static final String  MESSAGE_417_UPDATE= "Update operation failed. Please try again or contact Dev team";
  public static final String  MESSAGE_417_DELETE= "Delete operation failed. Please try again or contact Dev team";
   public static final String  STATUS_500 = "500";
   public static final String  MESSAGE_500 = "An error occurred. Please try again or contact Dev team";
}
