package afekaton.afekatontests.models.members;

/**
 * Created by ps3to_000 on 04-Mar-18.
 */
public enum Department {
    SOFTWARE("תוכנה"),
    ELECTRIC("חשמל"),
    MEDICAL("רפואית"),
    MECHANICAL("מכונות"),
    INDUSTRIAL("תעשייה וניהול");

    Department(String displayName) {
        this.displayName = displayName;
    }

    private String displayName;
}
