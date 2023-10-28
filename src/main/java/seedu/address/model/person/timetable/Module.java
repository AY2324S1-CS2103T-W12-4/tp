package seedu.address.model.person.timetable;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;

/**
 * Represents a Module in the application.
 * Contains information about the module name and its timings.
 */
public class Module extends TimeBlock {
    private static final String MESSAGE_CONSTRAINTS = "Module Name should be in the format 'AANNNN', \n"
            + "where 'AA' are any two alphabetic characters (e.g., CS, cS, Cs), \n"
            + "and 'NNNN' represents a four-digit number (e.g., 2100, 1001, 0001). \n"
            + "The alphabetic characters are case-insensitive.";
    private static final String VALIDATION_REGEX = "^[a-zA-Z]{2}\\d{4}$";
    private final String moduleName;

    public Module(String name, String timeBlockString) { //name = name, timeBlockString = DAY HHMM HHMM
        super(timeBlockString);
        requireNonNull(name);
        checkArgument(isValidModuleName(name), MESSAGE_CONSTRAINTS);

        this.moduleName = name;
    }

    public static Module newModule(String unparsedInput) {
        requireNonNull(unparsedInput);

        // Split the unparsed input string by whitespace
        String[] parts = unparsedInput.split("\\s+");

        // Check for valid number of parts
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid module input format. Expected: NAME DAY HHMM HHMM");
        }

        String name = parts[0];
        String day = parts[1];
        String startTime = parts[2];
        String endTime = parts[3];
        String timeBlockString = day + " " + startTime + " " + endTime;

        // Check for valid module name format
        if (!isValidModuleName(name)) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }

        return new Module(name, timeBlockString);
    }

    public Module editName(String newName) {
        requireNonNull(newName);
        checkArgument(isValidModuleName(newName), MESSAGE_CONSTRAINTS);
        return new Module(newName, timeBlockString);
    }

    public String getName() {
        return moduleName;
    }

    /**
     * Checks if the given module name is valid.
     *
     * @param test The module name to check.
     * @return true if the module name is valid, false otherwise.
     */
    private static boolean isValidModuleName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Checks if the current event is a module.
     *
     * @return True since it is a module.
     */
    @Override
    public boolean isModule() {
        return true;
    }


    @Override
    public String toString() {
        return moduleName + ": " + super.toString();
    }
}
