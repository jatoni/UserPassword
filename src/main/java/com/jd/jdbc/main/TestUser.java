package com.jd.jdbc.main;

import java.util.Scanner;

import com.jd.jdbc.DAO.UsuarioDAO;
import com.jd.jdbc.entity.Usuario;
import com.jd.jdbc.option.Options;

public class TestUser {
	private static Options options;
	private static String user;
	private static String password;
	private static Usuario usuario;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int opc = 0;
		do {
			UsuarioDAO userDao = new UsuarioDAO();
			try {
				System.out.println();
				System.out.print(
						"Choose an option.\n1.- Create User.\n2.- See all users\n3.- Update an user\n4.- Delete an user\n5.- Exit\nWhat are you going to choose? ");
				opc = in.nextInt();
				switch (convertir(opc)) {
					case Create:
						System.out.println();
						try {
							System.out.print("Input user: ");
							user = in.next();
							System.out.print("Input password: ");
							password = in.next();
							usuario = new Usuario(user, password);
							System.out
									.println(userDao.CreateUser(usuario) > 0 ? "User has been created with successfully"
											: "User hasn't been created without successfully");
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case Update:
						System.out.println();
						System.out.print("What are you going to update? \n1.- Name\n2.- Password: ");
						int update = in.nextInt();
						try {
							if (update == 1) {
								System.out.print("Input your current name: ");
								String userCurrent = in.next();
								System.out.print("Input your new name: ");
								String newName = in.next();
								System.out.println(
										userDao.UpdateUserName(userCurrent, newName) > 0 ? "User has been update"
												: "User hasn't been update");
							} else if (update == 2) {
								System.out.print("Input your current password: ");
								String passwordCurrent = in.next();
								System.out.print("Input your new password: ");
								String newPassword = in.next();
								System.out.println(userDao.UpdateUserPassword(passwordCurrent, newPassword) > 0
										? "Password has been updated"
										: "Password hasn't been updated");
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case Read:
						System.out.println();
						try {
							if (userDao.getAllUsers().isEmpty()) {
								System.out.println("Databasae is empty");
							} else {
								for (Usuario userEach : userDao.getAllUsers()) {
									System.out.println(userEach);
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case Delete:
						try {
							System.out.print("What user do you going to remove? ");
							int id = in.nextInt();
							usuario = new Usuario(id);
							System.out
									.println(userDao.DeleteUser(usuario) > 0 ? "User has been removed with succesfully"
											: "User hasn't been removed without succesfully");
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case Exit:
						System.out.println("See you");
						break;
					default:
						System.out.println("That option isn't exist");
						break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} while (opc != 5);
	}

	public static Options convertir(int opc) {
		Options options = null;
		if (opc == 1) {
			options = Options.valueOf("Create");
		} else if (opc == 2) {
			options = Options.valueOf("Read");
		} else if (opc == 3) {
			options = Options.valueOf("Update");
		} else if (opc == 4) {
			options = Options.valueOf("Delete");
		} else if (opc == 5) {
			options = Options.valueOf("Exit");
		}
		return options;
	}
}
