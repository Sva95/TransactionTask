public class App {

    public static void main(String[] args) {
        if (args != null && args.length > 0) {
            for (String filePath : args) {
                Solution.solution(filePath);
            }
        } else {
            System.out.println(Const.EMPTY_PATH);
        }
    }
}