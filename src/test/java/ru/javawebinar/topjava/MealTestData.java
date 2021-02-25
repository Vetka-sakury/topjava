package ru.javawebinar.topjava;

import org.assertj.core.api.Assertions;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final int MEAL_ID_USER = START_SEQ+2;
    public static final int MEAL_ID_ADMIN = START_SEQ + 3;

    public static final Meal mealUser = new Meal(MEAL_ID_USER, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
    public static final Meal mealAdmin = new Meal (MEAL_ID_ADMIN, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000);

    public static Meal getNewMeal(){
        return new Meal (null, LocalDateTime.of(2020, Month.JANUARY, 30, 23, 0), "newMeal", 500);
    }
   public static Meal getUpdated() {
        Meal updated = new Meal(mealUser);
        updated.setDateTime(LocalDateTime.of(2020, Month.FEBRUARY, 25, 23, 0));
        updated.setDescription("updatedDisc");
        updated.setCalories(100);
        return updated;
   }
   public static void assertMatch(Meal actual, Meal expected){
        assertThat(actual).isEqualToComparingFieldByField(expected);
   }
  // public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
    //   Assertions.assertThat(actual).isEqualTo(expected);   }

}
