package lld_elevator_v1.observer;

/**
 * Subject interface for the Observer Design Pattern
 * Defines methods to manage observers
 */
public interface Subject {
    /**
     * Add an observer to the subject
     * @param observer Observer to be added
     */
    void addObserver(Observer observer);
    
    /**
     * Remove an observer from the subject
     * @param observer Observer to be removed
     */
    void removeObserver(Observer observer);
    
    /**
     * Notify all observers about state changes
     */
    void notifyObservers();
} 