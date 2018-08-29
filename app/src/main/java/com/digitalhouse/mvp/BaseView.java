package com.digitalhouse.mvp;

public interface BaseView<T> {
    // Esto es necesario por los fragments, para separar la vista de la activity.
    void setPresenter(T presenter);
    boolean isActive();
}
