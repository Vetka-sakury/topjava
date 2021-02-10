package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

public interface Crud {
    Meal update();
    void delete();
    Meal add();
}
