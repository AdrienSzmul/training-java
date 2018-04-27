/**
 *
 */
package com.excilys.formation.computerdatabase.service;

/**
 * @author excilys
 */
public class MissingCompanyException extends ValidationException {
    /**
     * @param message
     */
    public MissingCompanyException(final String message) {
        super(message);
    }
}
