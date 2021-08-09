public class Main {

    public static void main(String[] args) {

        MyLinkedList<String> myLinkedList=new MyLinkedList<>();

        myLinkedList.add("ali");
        myLinkedList.add("javad");
        myLinkedList.add("zahra");

        System.out.println(myLinkedList);
        System.out.println("remove first");
        myLinkedList.remove(0);

        System.out.println(myLinkedList);

        System.out.println("remove index 1");
        myLinkedList.remove(1);

        System.out.println(myLinkedList);

        myLinkedList.add("fatemeh");
        myLinkedList.add("masoome");

        myLinkedList.remove("fatemeh");

        System.out.println(myLinkedList);
        System.out.println(myLinkedList.remove("aaaaa"));

        System.out.println("size : "+ myLinkedList.size());

        System.out.println(myLinkedList.isEmpty());

        System.out.println("after setting");
        myLinkedList.set(0,"mahsa");
        System.out.println(myLinkedList);

        System.out.println("***************************");
        for(Object ob : myLinkedList.toArray())
            System.out.println(ob);

    }
}
