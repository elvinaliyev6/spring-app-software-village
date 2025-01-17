package az.softwarevillage.book.enums;

import lombok.Getter;

@Getter
public enum EnumAvailableStatus {

    ACTIVE(1),DEACTIVE(0);

    private int value;

    EnumAvailableStatus(int value) {
        this.value = value;
    }
}
