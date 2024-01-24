package g60131.atlg.turingmachine;

import g60131.atlg.turingmachine.controller.Controller;

import java.util.List;

public class ConsoleApp {
    public static final String RESET = "\033[0m";
    public static final String BLUE = "\033[0;34m";
    public static void main(String[] args) {
        Controller controller = new Controller();
        System.out.println(BLUE + "Welcome to Turing Machine Game !" + RESET);
        String yesOrNo = controller.askYesOrNo("Do you want to choose a problem (y or n) (if it's 'n', a random problem is choose)");
        int problemNb;
        List<Integer> problem = null;
        while (problem == null) {
            if (yesOrNo.equals("y")) {
                controller.displayProblem();
                problemNb = controller.askInt("Enter the problem number");
            } else {
                problemNb = (int) (Math.random() * 16) + 1;
            }
            problem = controller.getProblem(problemNb);
        }
        controller.start(problem);
    }
}