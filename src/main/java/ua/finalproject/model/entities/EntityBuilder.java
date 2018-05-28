package ua.finalproject.model.entities;

public interface EntityBuilder<T> {

    /**
     * Builds entity
     *
     * @return entity
     */
    T build();
}
