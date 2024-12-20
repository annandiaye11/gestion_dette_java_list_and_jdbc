package com.baf.views.impl;

import java.util.List;
import com.baf.core.PasswordHashing;
// import com.baf.core.PasswordHashing;
import com.baf.core.enums.Role;
import com.baf.data.entities.Client;
import com.baf.data.entities.User;
import com.baf.services.ClientService;
import com.baf.services.UserServ;

public class UserView extends ViewImpl<User> {
    private UserServ userServ;
    private ClientService clientService;

    public UserView( UserServ userServ, ClientService clientService) {
        this.userServ = userServ;
        this.clientService = clientService;
    }

    public User createUserForClient(String surnameClient) {
        System.out.println("Creer un compte user a un client sans compte");

        // Afficher les clients sans compte
        // String surnameClient = clientView.showClientWhitoutAccount();
        Client client = clientService.selectBySurname(surnameClient);
        if (client == null) {
            System.out.println("Ce client n'est pas valide");
            return null;
        }

        System.out.println("Entrer le login : ");
        String login = scanner.nextLine();

        while (isLoginTaken(login)) {
            System.out.println("Ce login est déjà pris. Veuillez en choisir un autre.");
            login = scanner.nextLine();
        }

        System.out.println("Entrer le mail : ");
        String email = scanner.nextLine();

        while (isMailTaken(email)) {
            System.out.println("Ce mail est déjà pris. Veuillez en choisir un autre ");
            login = scanner.nextLine();
        }

        System.out.println("Entrer le mot de passe");
        String password = scanner.nextLine();

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(Role.CLIENT);
        user.setEmail(email);

        System.out.println("Utilisateur créé avec succès !");
        return user;

    }

    public User createUser() {
        System.out.println("Creer un compte user Admin ou Boutiquier");

        System.out.println("Entrer le login");
        String login = scanner.nextLine();

        while (isLoginTaken(login)) {
            System.out.println("Ce login est déjà pris. Veuillez en choisir un autre.");
            login = scanner.nextLine();
        }

        System.out.println("Entrer le mail");
        String email = scanner.nextLine();

        while (isMailTaken(email)) {
            System.out.println("Ce mail est déjà pris. Veuillez en choisir un autre ");
            email = scanner.nextLine();
        }

        System.out.println("Entrer le mot de passe");
        String password = scanner.nextLine();

        System.out.println("Donner le role. Les roles disponibles: ADMIN, BOUTIQUIER");
        String roleInput = scanner.nextLine().toUpperCase();
        Role role;
        while (true) {
            switch (roleInput) {
                case "ADMIN":
                    role = Role.ADMIN;
                    break;
                case "BOUTIQUIER":
                    role = Role.BOUTIQUIER;
                    break;
                default:
                    System.out.println("Role inconnu. Veuillez entrer un role valide.");
                    roleInput = scanner.nextLine().toUpperCase();
                    continue;
            }
            break;

        }

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        user.setEmail(email);
        user.setIsActive(true);

        System.out.println("Utilisateur créé avec succès !");
        return user;

    }

    private boolean isLoginTaken(String login) {
        List<User> allUsers = userServ.selectAll();
        if (allUsers.isEmpty()) {
            return false;
        }
        for (User user : allUsers) {
            if (user != null) {
                if (user.getLogin().equalsIgnoreCase(login)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isMailTaken(String mail) {
        List<User> allUsers = userServ.selectAll();
        if (allUsers.isEmpty()) {
            return false;
        }
        for (User user : allUsers) {
            if (user != null) {
                if (user.getEmail().equalsIgnoreCase(mail)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void showAllUsers() {
        List<User> allClients = userServ.selectAll();
        for (int i = 0; i < allClients.size(); i++) {
            System.out.println(i + " - " + allClients.get(i).toString());
        }
    }

    public void showAllActifUsers() {
        List<User> allClients = userServ.selectAll();
        int countActifClient = 0;
        if (allClients.isEmpty()) {
            System.out.println("Aucun utilisateur trouvé");
            return;
        }
        for (int i = 0; i < allClients.size(); i++) {
            if (allClients.get(i).getIsActive()) {
                ++countActifClient;
                System.out.println(i + " - " + allClients.get(i).toString());
            }
        }
        if (countActifClient == 0) {
            System.out.println("Aucun utilisateur actif trouvé");
        }
    }

    public void showAllUsersByRole() {
        scanner.nextLine();
        System.out.println("Donner le role. Les roles disponibles: ADMIN, CLIENT, BOUTIQUIER");
        String roleInput = scanner.nextLine().toUpperCase();
        Role role;
        switch (roleInput) {
            case "ADMIN":
                role = Role.ADMIN;
                break;
            case "BOUTIQUIER":
                role = Role.BOUTIQUIER;
                break;
            case "CLIENT":
                role = Role.CLIENT;
                break;
            default:
                System.out.println("Role inconnu.");
                return;
        }

        List<User> allClients = userServ.selectAll();
        for (int i = 0; i < allClients.size(); i++) {
            if (allClients.get(i).getRole().equals(role)) {
                System.out.println(i + " - " + allClients.get(i).toString());
            }
        }
    }

    public void toggleUserAccount() {
        showAllUsers();
        System.out.print("Entrer l'id du user : ");
        int userId = scanner.nextInt();
        if (userServ.getUserById(userId) == null) {
            System.out.println("User introuvable");
            return;
        }
        userServ.toggleUser(userId);
        System.out.println("Action bien effectuee");
    }

    public int subMenuFilter() {
        System.out.println("Filtrer les comptes users par :");
        System.out.println("1 - Actif");
        System.out.println("2 - Role");
        return scanner.nextInt();
    }

    @Override
    public User saisie() {
        User user = new User();
        boolean userExist = true;
        do {
            System.out.println("Veuillez saisir votre email ");
            user.setEmail(scanner.nextLine());
            userExist = userServ.selectByMail(user.getEmail()) != null;
            if (userExist) {
                System.out.println("Ooups !! Ce mail exist deja");
                System.out.println("veuillez réessayer");
            }
        } while (userExist);

        userExist = true;
        do {
            System.out.println("Veuillez saisir le login ");
            user.setLogin(scanner.nextLine());
            userExist = userServ.selectByLogin(user.getLogin()) != null;
            if (userExist) {
                System.out.println("Ooups !! Ce login exist deja");
                System.out.println("veuillez réessayer");
            }
        } while (userExist);
        user.setRole(Role.CLIENT);
        do {
            System.out.println("Veuillez saisir le password");
            user.setPassword(PasswordHashing.hashPassword(scanner.nextLine()));
        } while (user.getPassword().trim() == "");
        user.setIsActive(true);
        return user;
    }

}
