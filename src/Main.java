import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        boolean exit = false;

        SubstitutionPolicy substitutionPolicy = selectSubstitutionPolicy();
        BufferManager bufferManager = new BufferManager(substitutionPolicy);

        while (!exit){
            int option = getMenuOption();

            switch (option){
                case 1: bufferManager.fecth(typeKey()); break;
                case 2: bufferManager.displayCache(); break;
                case 3: bufferManager.displayStats(); break;
                case 4: exit = true; break;
            }
        }

    }

    private static SubstitutionPolicy selectSubstitutionPolicy(){
        int option = 0;

        do{
            System.out.println("Selecione uma política de substituição: ");
            System.out.println("[1] - LRU");
            System.out.println("[2] - CLOCK");
            System.out.println("[3] - MRU");
            System.out.println("[4] - FIFO");

            Scanner scanner = new Scanner(System.in);
            option = scanner.nextInt();
        }while (option < 0 && option > 4);

        SubstitutionPolicy substitutionPolicy = SubstitutionPolicy.LRU;

        switch (option){
            case 1: substitutionPolicy = SubstitutionPolicy.LRU; break;
            case 2: substitutionPolicy = SubstitutionPolicy.CLOCK; break;
            case 3: substitutionPolicy = SubstitutionPolicy.MRU; break;
            case 4: substitutionPolicy = SubstitutionPolicy.FIFO; break;
        }

        return substitutionPolicy;
    }

    private static int getMenuOption(){
        int option = 0;

        do{
            System.out.println();
            System.out.println("Selecione uma opção: ");
            System.out.println("[1] - Buscar página");
            System.out.println("[2] - Ver cache");
            System.out.println("[3] - Ver informações");
            System.out.println("[4] - Sair");

            Scanner scanner = new Scanner(System.in);
            option = scanner.nextInt();
        }while (option < 0 && option > 4);

        return option;
    }

    private static int typeKey(){
        System.out.println("Digite a chave da página");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
