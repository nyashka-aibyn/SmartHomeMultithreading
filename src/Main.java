class SmartDevice implements Runnable {

    private final String deviceName;

    public SmartDevice(String deviceName) {
        this.deviceName = deviceName;
    }

    @Override
    public void run() {

        System.out.println(deviceName + " іске қосылды!");

        String[] states;

        if (deviceName.equals("Шам")) {
            states = new String[]{
                "Қосылды",
                "Жарық деңгейі 50%",
                "Жарық деңгейі 100%",
                "Жарық деңгейі 30%",
                "Өшірілді"
            };

        } else if (deviceName.equals("Кондиционер")) {
            states = new String[]{
                "Қосылды",
                "Температура 24°C",
                "Температура 22°C",
                "Желдету режимі қосылды",
                "Өшірілді"
            };

        } else {
            states = new String[]{
                "Қозғалыс анықталмады",
                "Қозғалыс анықталды",
                "Температура 25°C",
                "Ылғалдылық 45%",
                "Жүйе қалыпты жұмыс істеп тұр"
            };
        }

        for (String state : states) {

            System.out.println(
                Thread.currentThread().getName()
                + " - " + deviceName + ": " + state
            );

            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                System.out.println(deviceName + " жұмысы тоқтатылды!");
                Thread.currentThread().interrupt();
                return;
            }
        }

        System.out.println(deviceName + " жұмысын аяқтады!");
    }
}

public class Main {

    public static void main(String[] args) {

        System.out.println("=== АҚЫЛДЫ ҮЙ ЖҮЙЕСІ ===");

        SmartDevice lamp = new SmartDevice("Шам");
        SmartDevice airConditioner = new SmartDevice("Кондиционер");
        SmartDevice sensor = new SmartDevice("Датчик");

        Thread thread1 = new Thread(lamp, "Lamp-Thread");
        Thread thread2 = new Thread(airConditioner, "AC-Thread");
        Thread thread3 = new Thread(sensor, "Sensor-Thread");

        thread1.start();
        thread2.start();
        thread3.start();

        try {

            thread1.join();
            thread2.join();
            thread3.join();

        } catch (InterruptedException e) {
            System.out.println("Негізгі ағын тоқтатылды!");
            Thread.currentThread().interrupt();
            return;
        }

        System.out.println("Барлық құрылғылардың жұмысы аяқталды!");
    }
}
