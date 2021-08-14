package domain.enumaration;

public enum StateOfActivity {


    OPEN(1),IN_PROGRESS(2),COMPLETED(3);

    private  int number;


    StateOfActivity(int number){

        this.number =number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {

        return this.name();

    }
}
