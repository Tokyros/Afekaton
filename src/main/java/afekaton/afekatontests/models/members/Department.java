package afekaton.afekatontests.models.members;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by ps3to_000 on 04-Mar-18.
 */
public enum Department {
    SCIENCE("מדעי היסוד"),
    SOFTWARE("תוכנה"),
    ELECTRIC("חשמל"),
    MEDICAL("רפואית"),
    MECHANICAL("מכונות"),
    INDUSTRIAL("תעשייה וניהול");

    Department(String displayName) {
        this.displayValue = displayName;
    }

    @JsonValue
    private String displayValue;

    @JsonCreator
    public static Department getDepartment(String displayValue){
        for (Department department : values()) {
            if (department.displayValue.equals(displayValue)) return department;
            if (department.name().equals(displayValue)) return department;
        }
        return null;
    }
}
