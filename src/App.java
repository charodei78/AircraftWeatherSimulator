import config.Config;

public class App {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            throw new IllegalArgumentException();
          }
          
          try {
            Config config = new Config(args[0]);
            System.out.println(config);
          } catch (Exception e) {
            System.out.println("Файл " + args[0] + " не найден");
            return;
          }
    }
}
