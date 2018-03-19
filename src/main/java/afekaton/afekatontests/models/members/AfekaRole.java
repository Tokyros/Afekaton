package afekaton.afekatontests.models.members;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AfekaRole {
    STUDENT("תלמיד"),
    TEACHER("מרצה"),
    TA("מתרגל"),
    ADMINISTRATION("מנהלה");


    @JsonValue
    private final String displayValue;

    AfekaRole(String displayValue) {
        this.displayValue = displayValue;
    }

    @JsonCreator
    public static AfekaRole getRole(String displayValue){
        for (AfekaRole afekaRole : values()) {
            if (afekaRole.displayValue.equals(displayValue)) return afekaRole;
            if (afekaRole.name().equals(displayValue)) return afekaRole;
        }
        return null;
    }
}
