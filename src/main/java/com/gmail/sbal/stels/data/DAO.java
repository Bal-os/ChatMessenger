package com.gmail.sbal.stels.data;

/**
 * @author Alex Balashov
 */
public interface DAO < Model, ID > {
    Model get(ID id);
    void add(Model model);
    void update(Model model);
    void delete(Model model);
}
