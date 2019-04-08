package com.ub.email.service;

/**
 * LoadDataService responsible for cleanup and populate data in db
 */
public interface LoadDataService {
    /**
     * This method will load fresh data into database
     */
    void loadData();

    /**
     * This method will populate new data for email-campaign application
     */
    void deleteData();
}
