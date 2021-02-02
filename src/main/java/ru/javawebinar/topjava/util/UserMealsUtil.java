package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

//        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    // TODO return filtered list with excess. Implement by cycles
    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // map for counting calories per a day
        Map <LocalDate, Integer> mapCaloriesPerDate = new HashMap<>();
        for (UserMeal um : meals){
            LocalDate date = um.getDateTime().toLocalDate();
            mapCaloriesPerDate.merge(date, um.getCalories(), (prev, one) -> prev + one);
        }
        // filling result list
        List <UserMealWithExcess> userMealWithExcess = new ArrayList<>();
        for (UserMeal um : meals){
            if (TimeUtil.isBetweenHalfOpen(um.getDateTime().toLocalTime(), startTime, endTime) ){
                boolean excess = (mapCaloriesPerDate.get(um.getDateTime().toLocalDate()) > caloriesPerDay) ? true : false;
                userMealWithExcess.add(createUserMealWithExcess(um, excess));
            }
        }
        return userMealWithExcess;
    }

    // simple create new UserMealWithExcess
    private static UserMealWithExcess createUserMealWithExcess(UserMeal um, boolean excess){
        return new UserMealWithExcess(um.getDateTime(), um.getDescription(), um.getCalories(), excess);
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO Implement by streams
        return null;
    }
}
