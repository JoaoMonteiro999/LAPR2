package pt.ipp.isep.dei.esoft.project.ui.console;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */
public class DevTeamUI implements Runnable {

    public DevTeamUI() {

    }

    public void run() {
        System.out.println("\n");
        System.out.print("Development Team:\n");
        System.out.print("\t João Monteiro - 1221023@isep.ipp.pt \n");
        System.out.print("\t Hugo Medeiros - 1221265@isep.ipp.pt \n");
        System.out.print("\t Martim Penedones - 1211369@isep.ipp.pt \n");
        System.out.print("\t Samuel Leite - 1191647@isep.ipp.pt \n");
        System.out.println("\n");
    }
}
