package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.concurrent.atomic.AtomicInteger;

public class DaoCrud implements Crud{

    AtomicInteger id;

    @Override
    public Meal update() {
        return null;
    }

    @Override
    public void delete() {

    }

    @Override
    public Meal add() {
        return null;
    }
}
