package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(this::save);
    }

    private Integer userId;
    private final Map <Integer, Map <Integer, Meal>> mealsByUser= new ConcurrentHashMap<>(); //my map is empty... =(

    @Override
    // null if updated meal do not belong to userId
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            mealsByUser.get(userId).put(meal.getId(), meal);
            return meal;
        }
        else if (mealsByUser.get(userId).containsValue(meal))
        // handle case: update, but not present in storage
        return mealsByUser.get(userId).computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        else return null;
    }

    @Override
    public boolean delete(int id) {
        return mealsByUser.get(userId).remove(id) != null;
    }

    @Override
    // null if meal do not belong to userId
    public Meal get(int id) {
        return mealsByUser.get(userId).get(id) == null ? null : mealsByUser.get(userId).get(id);
    }

    @Override
    // ORDERED dateTime desc
    public Collection<Meal> getAll() {
        return mealsByUser.get(userId).values()
                .stream()
                .sorted((m1, m2) -> m2.getDateTime().compareTo(m1.getDateTime()))
                .collect(Collectors.toList());
    }
}

