package domain.enumaration;

public enum StateOfActivity {


    OPEN,IN_PROGRESS,COMPLETED;

    @Override
    public String toString() {

        return this.name();

    }
}
