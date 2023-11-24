
package Controller;


public class CountDownTimer {

    public static void timer() {

        Thread tr = new Thread();

        try {
            for (int i = 30; i != 0; i--) {
                tr.sleep(1000);
                System.out.println(i);
                if (i == 10) {
                    System.out.println("Only 10 seconds left");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
