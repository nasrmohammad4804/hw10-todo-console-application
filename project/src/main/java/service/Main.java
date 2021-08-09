package service;

import util.HibernateUtil;

public class Main {

    public static void main(String[] args) {

        try {

            App app = new App();
            app.start();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        HibernateUtil.getSESSION().close();
    }
}
