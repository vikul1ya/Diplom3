package ru.practicum.api;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;


public class UserApi {

    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site";

    @Step("Создаем пользователя")
    public static ValidatableResponse createUser(User user) {
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/api/auth/register")
                .then();
    }

    @Step("Удаляем пользователя")
    public static ValidatableResponse deleteUser(String accessToken) {
        return given()
                .baseUri(BASE_URL)
                .header("Authorization", accessToken)
                .when()
                .delete("/api/auth/user")
                .then();
    }

    @Step("Авторизация пользователя")
    public static ValidatableResponse login(User user) {
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/api/auth/login")
                .then();
    }

    @Step("Удаление пользователя после успешной авторизации")
    public static void deleteUserRequest(User user) {
        try {
            // Сначала логинимся, чтобы получить токен
            String accessToken = login(user)
                    .extract()
                    .path("accessToken");

            if(accessToken != null) {
                // Удаляем пользователя
                given()
                        .baseUri(BASE_URL)
                        .header("Authorization", accessToken)
                        .when()
                        .delete("/api/auth/user")
                        .then();
            }
        } catch (Exception e) {
            System.out.println("Не удалось удалить пользователя " + user.getEmail() + ": " + e.getMessage());
        }
    }
}