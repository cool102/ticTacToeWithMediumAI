package tictactoe;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static String[][] field = new String[5][5];
    private static String coordinates = "3 4";
    private static boolean xWins = false;
    private static boolean oWins = false;
    private static boolean ai_queue = false;
    private static boolean human_queue = true;
    private static String inputCommand = "";
    private static boolean exitProgramm = false;
    private static String[] splitHuman = new String[]{"1", "1"};
    private static String[] splitAi = new String[]{"4", "4"};
    private static String autoPlay = "Z "; //первый ход ai когда комп играет против компа
    private static String uPlay; //за что играет user
    private static String aiPlay; //за что изграет ai


    private static boolean e_eGameMode = false;
    private static boolean u_uGameMode = false;
    private static boolean e_uGameMode = false;
    private static boolean u_eGameMode = false;
    private static boolean m_mGameMode = false;
    private static boolean u_mGameMode = false;
    private static boolean m_uGameMode = false;
    private static boolean m_eGameMode = false;
    ;
    private static boolean e_mGameMode = false;
    private static boolean xCanWin = false;
    private static boolean oCanWin = false;
    private static int winPositionLine;
    private static int winPositionColumn;
    ;

    public static void main(String[] args) {

        do {
            printWelcomeText();
            readInputCommand();
        } while (!isValidCommand());

        if (exitProgramm) {
            return;
        }

        initField(field);
        printField(field);

        gameTypeDefinition();

        whoIsFirstDedenition();

        //пока не выиграл X или пока не выиграл O размещай Хили O на поле
        while (!isXWins() && !isOWins()) {

            // ходим в звисимости от типа игры -locate
            playing();

            // printField(field);

            if (!isXWins() & !isOWins() & !isDraw()) {
                // System.out.println("Game not finished");
            }
            if (!isXWins() & !isOWins() & isDraw()) {
                System.out.println("Draw");
                break;
            }
        }
        if (xWins) {
            //printField(field);
            System.out.println("X wins");
        }
        if (oWins) {
            //printField(field);
            System.out.println("O wins");
        }


    }

    private static void whoIsFirstDedenition() {
        if (e_eGameMode) {
            autoPlay = "X ";
            return;
        }
        if (u_uGameMode) {
            uPlay = "X ";
            return;
        }
        if (e_uGameMode) {
            aiPlay = "X ";
            uPlay = "O ";
            return;
        }
        if (u_eGameMode) {
            uPlay = "X ";
            aiPlay = "O ";
            return;
        }

        if (m_mGameMode) {
            autoPlay = "X ";
            return;
        }

        if (m_uGameMode) {
            aiPlay = "X ";
            uPlay = "O ";
            return;
        }
        if (u_mGameMode) {
            uPlay = "X ";
            aiPlay = "O ";
            return;
        }

        //new
        if (e_mGameMode) {
            autoPlay = "X ";
            return;
        }

        if (m_eGameMode) {
            autoPlay = "X ";

        }


    }

    private static void gameTypeDefinition() {
        //определяем тип игры. e-e,h-h,e-h,h-e
        String[] splittedInput = inputCommand.split("\\s");
        if (splittedInput[1].equals("easy") && splittedInput[2].equals("easy")) {
            e_eGameMode = true;
            return;
        }

        if (splittedInput[1].equals("medium") && splittedInput[2].equals("medium")) {
            m_mGameMode = true;
            return;
        }

        if (splittedInput[1].equals("user") && splittedInput[2].equals("user")) {
            u_uGameMode = true;
            return;
        }

        if (splittedInput[1].equals("user") && splittedInput[2].equals("easy")) {
            u_eGameMode = true;
            return;
        }

        if (splittedInput[1].equals("user") && splittedInput[2].equals("medium")) {
            u_mGameMode = true;
            return;
        }

        if (splittedInput[1].equals("easy") && splittedInput[2].equals("user")) {
            e_uGameMode = true;
            return;
        }

        if (splittedInput[1].equals("medium") && splittedInput[2].equals("user")) {
            m_uGameMode = true;
            return;
        }

        if (splittedInput[1].equals("medium") && splittedInput[2].equals("easy")) {
            m_eGameMode = true;
            return;
        }
        if (splittedInput[1].equals("easy") && splittedInput[2].equals("medium")) {
            e_mGameMode = true;
            return;
        }
        if (splittedInput[1].equals("easy") && splittedInput[2].equals("medium")) {
            e_mGameMode = true;
            return;
        }
    }

    private static void readInputCommand() {
        inputCommand = scanner.nextLine();

    }

    private static void printWelcomeText() {
        System.out.print("Input command: >");
    }

    private static boolean isValidCommand() {
        String[] splittedInput = inputCommand.split("\\s");
        if (splittedInput.length != 3 && splittedInput[0].contains("exit")) {
            exitProgramm = true;
            return true;
        }

        if (splittedInput.length != 3 && splittedInput[0].contains("start")) {
            System.out.println("Bad parameters!");
            return false;
        }

        if (splittedInput[0].contains("start")) {
            if (splittedInput[1].equals("easy") && splittedInput[2].equals("user")) {
                return true;
            }

            if (splittedInput[1].equals("medium") && splittedInput[2].equals("user")) {
                return true;
            }

            if (splittedInput[1].equals("easy") && splittedInput[2].equals("easy")) {
                return true;
            }

            if (splittedInput[1].equals("medium") && splittedInput[2].equals("medium")) {
                return true;
            }

            if (splittedInput[1].equals("user") && splittedInput[2].equals("user")) {
                return true;
            }
            if (splittedInput[1].equals("user") && splittedInput[2].equals("easy")) {
                return true;
            }
            if (splittedInput[1].equals("user") && splittedInput[2].equals("medium")) {
                return true;
            }
            if (splittedInput[1].equals("easy") && splittedInput[2].equals("medium")) {
                return true;
            }
            if (splittedInput[1].equals("medium") && splittedInput[2].equals("easy")) {
                return true;
            }
        }
        System.out.println("Bad parameters!");
        return false;
    }

    private static boolean isDraw() {
        int emptyCellsCounter = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j].equals("  ")) {
                    emptyCellsCounter++;
                }
            }
        }
        return emptyCellsCounter == 0;

    }


    private static boolean isXCanWin() {
        //построчная
        if (field[1][1].equals("X ") & field[1][2].equals("X ") & field[1][3].equals("  ")) { //11_
            xCanWin = true;
            winPositionLine = 1;
            winPositionColumn = 3;
            return true;
        }
        if (field[1][1].equals("  ") & field[1][2].equals("X ") & field[1][3].equals("X ")) { //_11
            xCanWin = true;
            winPositionLine = 1;
            winPositionColumn = 1;
            return true;
        }

        if (field[1][1].equals("X ") & field[1][2].equals("  ") & field[1][3].equals("X ")) {//1_1
            xCanWin = true;
            winPositionLine = 1;
            winPositionColumn = 2;
            return true;
        }

        if (field[2][1].equals("X ") & field[2][2].equals("X ") & field[2][3].equals("  ")) {
            xCanWin = true;
            winPositionLine = 2;
            winPositionColumn = 3;
            return true;
        }

        if (field[2][1].equals("  ") & field[2][2].equals("X ") & field[2][3].equals("X ")) {
            xCanWin = true;
            winPositionLine = 2;
            winPositionColumn = 1;
            return true;
        }

        if (field[2][1].equals("X ") & field[2][2].equals("  ") & field[2][3].equals("X ")) {
            xCanWin = true;
            winPositionLine = 2;
            winPositionColumn = 2;
            return true;
        }


        if (field[3][1].equals("X ") & field[3][2].equals("X ") & field[3][3].equals("  ")) {
            xCanWin = true;
            winPositionLine = 3;
            winPositionColumn = 3;
            return true;
        }

        if (field[3][1].equals("  ") & field[3][2].equals("X ") & field[3][3].equals("X ")) {
            xCanWin = true;
            winPositionLine = 3;
            winPositionColumn = 1;
            return true;
        }
        if (field[3][1].equals("X ") & field[3][2].equals("  ") & field[3][3].equals("X ")) {
            xCanWin = true;
            winPositionLine = 3;
            winPositionColumn = 2;
            return true;
        }


        //по столбцам
        if (field[1][1].equals("X ") & field[2][1].equals("X ") & field[3][1].equals("  ")) {
            xCanWin = true;
            winPositionLine = 3;
            winPositionColumn = 1;
            return true;
        }

        if (field[1][1].equals("  ") & field[2][1].equals("X ") & field[3][1].equals("X ")) {
            xCanWin = true;
            winPositionLine = 1;
            winPositionColumn = 1;
            return true;
        }
        if (field[1][1].equals("X ") & field[2][1].equals("  ") & field[3][1].equals("X ")) {
            xCanWin = true;
            winPositionLine = 2;
            winPositionColumn = 1;
            return true;
        }


        if (field[1][2].equals("X ") & field[2][2].equals("X ") & field[3][2].equals("  ")) {
            xCanWin = true;
            winPositionLine = 3;
            winPositionColumn = 2;
            return true;
        }

        if (field[1][2].equals("  ") & field[2][2].equals("X ") & field[3][2].equals("X ")) {
            xCanWin = true;
            winPositionLine = 1;
            winPositionColumn = 2;
            return true;
        }
        if (field[1][2].equals("X ") & field[2][2].equals("  ") & field[3][2].equals("X ")) {
            xCanWin = true;
            winPositionLine = 2;
            winPositionColumn = 2;
            return true;
        }


        if (field[1][3].equals("X ") & field[2][3].equals("X ") & field[3][3].equals("  ")) {
            xCanWin = true;
            winPositionLine = 3;
            winPositionColumn = 3;
            return true;
        }

        if (field[1][3].equals("  ") & field[2][3].equals("X ") & field[3][3].equals("X ")) {
            xCanWin = true;
            winPositionLine = 1;
            winPositionColumn = 3;
            return true;
        }
        if (field[1][3].equals("X ") & field[2][3].equals("  ") & field[3][3].equals("X ")) {
            xCanWin = true;
            winPositionLine = 2;
            winPositionColumn = 3;
            return true;
        }
        // 1 диагональ
        if (field[1][1].equals("X ") & field[2][2].equals("X ") & field[3][3].equals("  ")) {
            xCanWin = true;
            winPositionLine = 3;
            winPositionColumn = 3;
            return true;
        }
        if (field[1][1].equals("  ") & field[2][2].equals("X ") & field[3][3].equals("X ")) {
            xCanWin = true;
            winPositionLine = 1;
            winPositionColumn = 1;
            return true;
        }
        if (field[1][1].equals("X ") & field[2][2].equals("  ") & field[3][3].equals("X ")) {
            xCanWin = true;
            winPositionLine = 2;
            winPositionColumn = 2;
            return true;
        }
        // 2 диагональ
        if (field[1][3].equals("X ") & field[2][2].equals("X ") & field[3][1].equals("  ")) {
            xCanWin = true;
            winPositionLine = 3;
            winPositionColumn = 1;
            return true;
        }
        if (field[1][3].equals("  ") & field[2][2].equals("X ") & field[3][1].equals("X ")) {
            xCanWin = true;
            winPositionLine = 1;
            winPositionColumn = 3;
            return true;
        }
        if (field[1][3].equals("X ") & field[2][2].equals("  ") & field[3][1].equals("X ")) {
            xCanWin = true;
            winPositionLine = 2;
            winPositionColumn = 2;
            return true;
        }
        return false;

    }

    private static boolean isOCanWin() {
        //построчная
        if (field[1][1].equals("O ") & field[1][2].equals("O ") & field[1][3].equals("  ")) { //11_
            oCanWin = true;
            winPositionLine = 1;
            winPositionColumn = 3;
            return true;
        }
        if (field[1][1].equals("  ") & field[1][2].equals("O ") & field[1][3].equals("O ")) { //_11
            oCanWin = true;
            winPositionLine = 1;
            winPositionColumn = 1;
            return true;
        }

        if (field[1][1].equals("O ") & field[1][2].equals("  ") & field[1][3].equals("O ")) {//1_1
            oCanWin = true;
            winPositionLine = 1;
            winPositionColumn = 2;
            return true;
        }

        if (field[2][1].equals("O ") & field[2][2].equals("O ") & field[2][3].equals("  ")) {
            oCanWin = true;
            winPositionLine = 2;
            winPositionColumn = 3;
            return true;
        }

        if (field[2][1].equals("  ") & field[2][2].equals("O ") & field[2][3].equals("O ")) {
            oCanWin = true;
            winPositionLine = 2;
            winPositionColumn = 1;
            return true;
        }

        if (field[2][1].equals("O ") & field[2][2].equals("  ") & field[2][3].equals("O ")) {
            oCanWin = true;
            winPositionLine = 2;
            winPositionColumn = 2;
            return true;
        }


        if (field[3][1].equals("O ") & field[3][2].equals("O ") & field[3][3].equals("  ")) {
            oCanWin = true;
            winPositionLine = 3;
            winPositionColumn = 3;
            return true;
        }

        if (field[3][1].equals("  ") & field[3][2].equals("O ") & field[3][3].equals("X ")) {
            oCanWin = true;
            winPositionLine = 3;
            winPositionColumn = 1;
            return true;
        }
        if (field[3][1].equals("O ") & field[3][2].equals("  ") & field[3][3].equals("O ")) {
            oCanWin = true;
            winPositionLine = 3;
            winPositionColumn = 2;
            return true;
        }


        //по столбцам
        if (field[1][1].equals("O ") & field[2][1].equals("O ") & field[3][1].equals("  ")) {
            oCanWin = true;
            winPositionLine = 3;
            winPositionColumn = 1;
            return true;
        }

        if (field[1][1].equals("  ") & field[2][1].equals("O ") & field[3][1].equals("O ")) {
            oCanWin = true;
            winPositionLine = 1;
            winPositionColumn = 1;
            return true;
        }
        if (field[1][1].equals("O ") & field[2][1].equals("  ") & field[3][1].equals("O ")) {
            oCanWin = true;
            winPositionLine = 2;
            winPositionColumn = 1;
            return true;
        }


        if (field[1][2].equals("O ") & field[2][2].equals("O ") & field[3][2].equals("  ")) {
            oCanWin = true;
            winPositionLine = 3;
            winPositionColumn = 2;
            return true;
        }

        if (field[1][2].equals("  ") & field[2][2].equals("O ") & field[3][2].equals("O ")) {
            oCanWin = true;
            winPositionLine = 1;
            winPositionColumn = 2;
            return true;
        }
        if (field[1][2].equals("O ") & field[2][2].equals("  ") & field[3][2].equals("O ")) {
            oCanWin = true;
            winPositionLine = 2;
            winPositionColumn = 2;
            return true;
        }


        if (field[1][3].equals("O ") & field[2][3].equals("O ") & field[3][3].equals("  ")) {
            oCanWin = true;
            winPositionLine = 3;
            winPositionColumn = 3;
            return true;
        }

        if (field[1][3].equals("  ") & field[2][3].equals("O ") & field[3][3].equals("O ")) {
            oCanWin = true;
            winPositionLine = 1;
            winPositionColumn = 3;
            return true;
        }
        if (field[1][3].equals("O ") & field[2][3].equals("  ") & field[3][3].equals("O ")) {
            oCanWin = true;
            winPositionLine = 2;
            winPositionColumn = 3;
            return true;
        }
        // 1 диагональ
        if (field[1][1].equals("O ") & field[2][2].equals("O ") & field[3][3].equals("  ")) {
            oCanWin = true;
            winPositionLine = 3;
            winPositionColumn = 3;
            return true;
        }
        if (field[1][1].equals("  ") & field[2][2].equals("O ") & field[3][3].equals("O ")) {
            oCanWin = true;
            winPositionLine = 1;
            winPositionColumn = 1;
            return true;
        }
        if (field[1][1].equals("O ") & field[2][2].equals("  ") & field[3][3].equals("O ")) {
            oCanWin = true;
            winPositionLine = 2;
            winPositionColumn = 2;
            return true;
        }
        // 2 диагональ
        if (field[1][3].equals("O ") & field[2][2].equals("O ") & field[3][1].equals("  ")) {
            oCanWin = true;
            winPositionLine = 3;
            winPositionColumn = 1;
            return true;
        }
        if (field[1][3].equals("  ") & field[2][2].equals("O ") & field[3][1].equals("O ")) {
            oCanWin = true;
            winPositionLine = 1;
            winPositionColumn = 3;
            return true;
        }
        if (field[1][3].equals("O ") & field[2][2].equals("  ") & field[3][1].equals("O ")) {
            oCanWin = true;
            winPositionLine = 2;
            winPositionColumn = 2;
            return true;
        }
        return false;

    }


    private static boolean isXWins() {
        //построчная
        if (field[1][1].equals("X ") & field[1][2].equals("X ") & field[1][3].equals("X ")) {
            xWins = true;
            oWins = false;
            return true;
        }
        if (field[2][1].equals("X ") & field[2][2].equals("X ") & field[2][3].equals("X ")) {
            xWins = true;
            return true;
        }
        if (field[3][1].equals("X ") & field[3][2].equals("X ") & field[3][3].equals("X ")) {
            xWins = true;
            return true;
        }
        //по столбцам
        if (field[1][1].equals("X ") & field[2][1].equals("X ") & field[3][1].equals("X ")) {
            xWins = true;
            return true;
        }
        if (field[1][2].equals("X ") & field[2][2].equals("X ") & field[3][2].equals("X ")) {
            xWins = true;
            return true;
        }
        if (field[1][3].equals("X ") & field[2][3].equals("X ") & field[3][3].equals("X ")) {
            xWins = true;
            return true;
        }
        // 1 диагональ
        if (field[1][1].equals("X ") & field[2][2].equals("X ") & field[3][3].equals("X ")) {
            xWins = true;
            return true;
        }
        // 2 диагональ
        if (field[1][3].equals("X ") & field[2][2].equals("X ") & field[3][1].equals("X ")) {
            xWins = true;
            return true;
        }

        return false;

    }

    private static boolean isOWins() {
//построчная
        if (field[1][1].equals("O ") & field[1][2].equals("O ") & field[1][3].equals("O ")) {
            oWins = true;
            return true;
        }
        if (field[2][1].equals("O ") & field[2][2].equals("O ") & field[2][3].equals("O ")) {
            oWins = true;
            return true;
        }
        if (field[3][1].equals("O ") & field[3][2].equals("O ") & field[3][3].equals("O ")) {
            oWins = true;
            return true;
        }
        //по столбцам
        if (field[1][1].equals("O ") & field[2][1].equals("O ") & field[3][1].equals("O ")) {
            oWins = true;
            return true;
        }
        if (field[1][2].equals("O ") & field[2][2].equals("O ") & field[3][2].equals("O ")) {
            oWins = true;
            return true;
        }
        if (field[1][3].equals("O ") & field[2][3].equals("O ") & field[3][3].equals("O ")) {
            oWins = true;
            return true;
        }
        // 1 диагональ
        if (field[1][1].equals("O ") & field[2][2].equals("O ") & field[3][3].equals("O ")) {
            oWins = true;
            return true;
        }
        // 2 диагональ
        if (field[1][3].equals("O ") & field[2][2].equals("O ") & field[3][1].equals("O ")) {
            oWins = true;
            return true;
        }

        return false;
    }

    //h->ai
    private static void playing() {
        if (e_eGameMode) {
            easy_ai_move();
            return;
        }
        if (u_uGameMode) {
            user_move();
            return;
        }

        if (e_uGameMode) {
            easy_ai_move();
            if (isOWins() || isXWins()) {
                return;
            }
            user_move();
            return;
        }

        if (u_eGameMode) {
            user_move();
            if (isOWins() || isXWins()) {
                return;
            }
            easy_ai_move();
            return;
        }
        //new
        if (u_mGameMode) {
            user_move();
            if (isOWins() || isXWins()) {
                return;
            }
            medium_ai_move();
            if (isOWins() || isXWins()) {
                return;
            }
            return;
        }
        if (m_mGameMode) {
            medium_ai_move();
            if (isOWins() || isXWins()) {
                return;
            }
            return;
        }
        if (m_uGameMode) {
            medium_ai_move();
            if (isOWins() || isXWins()) {
                return;
            }

            user_move();
            if (isOWins() || isXWins()) {
                return;
            }
            return;
        }

        //new
        if (e_mGameMode) {
            easy_ai_move();
            if (isOWins() || isXWins()) {
                return;
            }
            medium_ai_move();
            if (isOWins() || isXWins()) {
                return;
            }
            return;
        }
        if (m_eGameMode) {
            medium_ai_move();
            if (isOWins() || isXWins()) {
                return;
            }
            easy_ai_move();
            if (isOWins() || isXWins()) {
                return;
            }

        }
    }

    private static void medium_ai_move() {
        coordinates = "4 4";

        if (can_win()) {
            doWinMove();
            return;
        }
        if (!can_win() & can_lose()) {
            doBlockMove();
            return;
        }
        if (!can_win() & !can_lose()) {
            mediumAIRandomMove();
            return;
        }
    }

    private static void doBlockMove() {

        if (autoPlay.equals("X ")) {
            field[winPositionLine][winPositionColumn] = autoPlay;
            System.out.println("Making move level \"medium\"");
            printField(field);
            if (autoPlay.equals("X ")) {
                autoPlay = "O ";
                return;
            }
            if (autoPlay.equals("O ")) {
                autoPlay = "X ";
            }
            return;
        }

        if (autoPlay.equals("O ")) {
            field[winPositionLine][winPositionColumn] = autoPlay;
            System.out.println("Making move level \"medium\"");
            printField(field);
            if (autoPlay.equals("X ")) {
                autoPlay = "O ";
                return;
            }
            if (autoPlay.equals("O ")) {
                autoPlay = "X ";
            }
            return;
        }
        if (aiPlay.equals("X ")) {
            field[winPositionLine][winPositionColumn] = aiPlay;
            System.out.println("Making move level \"medium\"");
            printField(field);
            return;
        }
        if (aiPlay.equals("O ")) {
            field[winPositionLine][winPositionColumn] = aiPlay;
            System.out.println("Making move level \"medium\"");
            printField(field);
            return;
        }
    }

    private static void doWinMove() {
        if (autoPlay.equals("X ")) {
            field[winPositionLine][winPositionColumn] = autoPlay;
            System.out.println("Making move level \"medium\"");
            printField(field);
            return;
        }

        if (autoPlay.equals("O ")) {
            field[winPositionLine][winPositionColumn] = autoPlay;
            System.out.println("Making move level \"medium\"");
            printField(field);
            return;
        }
        if (aiPlay.equals("X ")) {
            field[winPositionLine][winPositionColumn] = aiPlay;
            System.out.println("Making move level \"medium\"");
            printField(field);
            return;

        }
        if (aiPlay.equals("O ")) {
            field[winPositionLine][winPositionColumn] = aiPlay;
            System.out.println("Making move level \"medium\"");
            printField(field);
            return;
        }
    }

    private static boolean can_lose() {

        if (autoPlay.equals("X ")) {
            return isOCanWin();
        }
        if (autoPlay.equals("O ")) {
            return isXCanWin();
        }
        if (aiPlay.equals("X ")) {
            return isOCanWin();
        }

        if (aiPlay.equals("O ")) {
            return isXCanWin();
        }
        return false;
    }

    private static boolean can_win() {

        if (autoPlay.equals("X ")) {
            return isXCanWin();
        }
        if (autoPlay.equals("O ")) {
            return isOCanWin();
        }

        if (aiPlay.equals("O ")) {
            return isOCanWin();
        }
        if (aiPlay.equals("X ")) {
            return isXCanWin();
        }
        return false;
    }

    private static void mediumAIRandomMove() {
        while (!isValidCoordinates()) {
            String[] randomCoords = new String[]{"1 1", "1 2", "1 3", "2 1", "2 2", "2 3", "3 1", "3 2", "3 3"};
            coordinates = randomCoords[new Random().nextInt(8) + 1];
            splitAi = coordinates.split("\\s");
        }
        int randomLine = Integer.parseInt(splitAi[0]);
        int randomColumn = Integer.parseInt(splitAi[1]);


        if (m_mGameMode) {
            System.out.println("Making move level \"medium\"");
            field[randomLine][randomColumn] = autoPlay;
            printField(field);
            //меняем Х на O если ai играет против ai
            if (autoPlay.equals("X ")) {
                autoPlay = "O ";
                return;
            }
            if (autoPlay.equals("O ")) {
                autoPlay = "X ";
            }

        }

        if (e_mGameMode) {
            System.out.println("Making move level \"medium\"");
            field[randomLine][randomColumn] = autoPlay;
            printField(field);
            //меняем Х на O если ai играет против ai
            if (autoPlay.equals("X ")) {
                autoPlay = "O ";
                return;
            }
            if (autoPlay.equals("O ")) {
                autoPlay = "X ";
            }

        }


        if (m_eGameMode) {
            System.out.println("Making move level \"medium\"");
            field[randomLine][randomColumn] = autoPlay;
            printField(field);
            //меняем Х на O если ai играет против ai
            if (autoPlay.equals("X ")) {
                autoPlay = "O ";
                return;
            }
            if (autoPlay.equals("O ")) {
                autoPlay = "X ";
            }

        }

        //TODO  дописать все варианты чел против комп
        if (m_uGameMode) {
            System.out.println("Making move level \"medium\"");
            field[randomLine][randomColumn] = aiPlay;
            printField(field);
        }

        if (u_mGameMode) {
            System.out.println("Making move level \"medium\"");
            field[randomLine][randomColumn] = aiPlay;
            printField(field);

        }
    }

    private static void easy_ai_move() {
        coordinates = "4 4";
        while (!isValidCoordinates()) {
            String[] randomCoords = new String[]{"1 1", "1 2", "1 3", "2 1", "2 2", "2 3", "3 1", "3 2", "3 3"};
            coordinates = randomCoords[new Random().nextInt(8) + 1];
            splitAi = coordinates.split("\\s");
        }
        int randomLine = Integer.parseInt(splitAi[0]);
        int randomColumn = Integer.parseInt(splitAi[1]);

        if (e_eGameMode) {
            System.out.println("Making move level \"easy\"");
            field[randomLine][randomColumn] = autoPlay;
            printField(field);
            //меняем Х на O если ai играет против ai
            if (autoPlay.equals("X ")) {
                autoPlay = "O ";
                return;
            }
            if (autoPlay.equals("O ")) {
                autoPlay = "X ";
            }
        }

        if (e_uGameMode) {
            System.out.println("Making move level \"easy\"");
            field[randomLine][randomColumn] = aiPlay;
            printField(field);
        }
        if (u_eGameMode) {
            System.out.println("Making move level \"easy\"");
            field[randomLine][randomColumn] = aiPlay;
            printField(field);
        }

        if (e_mGameMode) {
            System.out.println("Making move level \"easy\"");
            field[randomLine][randomColumn] = autoPlay;
            printField(field);
            //меняем Х на O если ai играет против ai
            if (autoPlay.equals("X ")) {
                autoPlay = "O ";
                return;
            }
            if (autoPlay.equals("O ")) {
                autoPlay = "X ";
            }
        }

        if (m_eGameMode) {
            System.out.println("Making move level \"easy\"");
            field[randomLine][randomColumn] = autoPlay;
            printField(field);
            //меняем Х на O если ai играет против ai
            if (autoPlay.equals("X ")) {
                autoPlay = "O ";
                return;
            }
            if (autoPlay.equals("O ")) {
                autoPlay = "X ";
            }
        }
    }

    private static void user_move() {
        readCoordinates();
        while (!isValidCoordinates()) {
            readCoordinates();
        }
        int line = Integer.parseInt(splitHuman[0]);
        int column = Integer.parseInt(splitHuman[1]);

        if (u_uGameMode) {
            field[line][column] = uPlay;

            if (uPlay.equals("X ")) {
                uPlay = "O ";
                return;
            }
            if (uPlay.equals("O ")) {
                uPlay = "X ";
                return;
            }
        }
        if (e_uGameMode) {
            field[line][column] = uPlay;
        }
        if (u_eGameMode) {
            field[line][column] = uPlay;
            printField(field);
        }

        //todo
        if (m_uGameMode) {
            field[line][column] = uPlay;
        }

        //todo
        if (u_mGameMode) {
            field[line][column] = uPlay;
            printField(field);
        }
    }

    private static boolean isValidCoordinates() {
        String[] split = coordinates.split("\\s");
        if ((split[0].matches("\\D+")) || (split[1].matches("\\D+"))) {
            System.out.println("You should enter numbers!");
            return false;
        }
        if ((split[0].matches("[4-9]")) || (split[0].matches("[O]")) || (split[1].matches("[O]")) || (split[1].matches("[4-9]"))) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }
        if (field[Integer.parseInt(split[0])][Integer.parseInt(split[1])].contains("O ") || field[Integer.parseInt(split[0])][Integer.parseInt(split[1])].contains("X ")) {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
        return true;

    }

    private static void readCoordinates() {
        System.out.print("Enter the coordinates: >");
        coordinates = scanner.nextLine();
        splitHuman = coordinates.split("\\s");

    }

    private static void printField(String[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j]);

            }
            System.out.println();
        }
    }

    private static void initField(String[][] f) {
        // System.out.print("Enter the cells: >");

        leftBorder(f);
        rightBorder(f);
        top(f);

        f[1][1] = "  ";
        f[1][2] = "  ";
        f[1][3] = "  ";
        f[2][1] = "  ";
        f[2][2] = "  ";
        f[2][3] = "  ";
        f[3][1] = "  ";
        f[3][2] = "  ";
        f[3][3] = "  ";

        bottom(f);
        //for (int i = 0; i < f.length; i++) {
        //    for (int j = 0; j < f[i].length; j++) {
        //        if (f[i][j].equals("_ ")) {
        //            f[i][j] = "  ";
        //        }
        //    }
        //}


        field = f;
    }

    private static void bottom(String[][] f) {
        for (int k = 0; k < f[4].length; k++) {
            f[4][k] = "--";
        }
        f[0][4] = "-";
        f[4][4] = "-";
    }

    private static void top(String[][] f) {
        for (int j = 0; j < f[0].length; j++) {
            f[0][j] = "--";
        }
    }

    private static void rightBorder(String[][] f) {
        for (int i = 0; i < f.length - 1; i++) {
            f[i][4] = "|";
        }
    }

    private static void leftBorder(String[][] f) {
        for (int i = 1; i < f.length; i++) {
            f[i][0] = "| ";
        }
    }


}
