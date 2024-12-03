/**
*Objectif des tests d'intégration
*Les tests d'intégration vérifient le fonctionnement de plusieurs méthodes 
*ou composants ensemble pour garantir une interaction correcte.
*/


package com.mycompany.account;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests d'intégration pour la classe Account.
 */
public class AccountIT {
    
    private Account account;

    @BeforeAll
    public static void setUpClass() {
        System.out.println("Début des tests d'intégration pour Account.");
    }

    @AfterAll
    public static void tearDownClass() {
        System.out.println("Fin des tests d'intégration pour Account.");
    }

    @BeforeEach
    public void setUp() {
        // Initialiser un compte avec un solde initial avant chaque test
        account = new Account(100.0);
    }

    @AfterEach
    public void tearDown() {
        // Réinitialiser l'instance après chaque test
        account = null;
    }

    /**
     * Test d'intégration : dépôt et vérification du solde.
     */
    @Test
    public void testDepositAndCheckBalance() {
        System.out.println("Test d'intégration : dépôt et consultation du solde.");
        
        // Dépôt de 50
        account.deposit(50.0);
        double balance = account.checkBalance();
        
        // Vérifie si le solde est correctement mis à jour
        assertEquals(150.0, balance, "Le solde devrait être de 150 après le dépôt.");
    }

    /**
     * Test d'intégration : retrait valide et vérification du solde.
     */
    @Test
    public void testWithdrawAndCheckBalance_Success() {
        System.out.println("Test d'intégration : retrait valide et consultation du solde.");
        
        // Retirer 40
        boolean
        result = account.withdraw(40.0);
        
        // Vérifie si le retrait a réussi
        assertTrue(result, "Le retrait devrait réussir.");
        
        // Vérifie si le solde est correctement mis à jour
        double balance = account.checkBalance();
        assertEquals(60.0, balance, "Le solde devrait être de 60 après un retrait de 40.");
    }

    /**
     * Test d'intégration : retrait invalide (fonds insuffisants) et vérification du solde.
     */
    @Test
    public void testWithdrawAndCheckBalance_Failure() {
        System.out.println("Test d'intégration : retrait invalide et consultation du solde.");
        
        // Essayer de retirer plus que le solde
        boolean result = account.withdraw(200.0);
        
        // Vérifie si le retrait a échoué
        assertFalse(result, "Le retrait ne devrait pas réussir avec un montant supérieur au solde.");
        
        // Vérifie si le solde reste inchangé
        double balance = account.checkBalance();
        assertEquals(100.0, balance, "Le solde devrait rester à 100 après un retrait échoué.");
    }

    /**
     * Test d'intégration : dépôt, retrait et vérification du solde.
     */
    @Test
    public void testDepositWithdrawAndCheckBalance() {
        System.out.println("Test d'intégration : dépôt, retrait et consultation du solde.");
        
        // Dépôt de 50
        account.deposit(50.0);
        
        // Retrait de 30
        boolean result = account.withdraw(30.0);
        
        // Vérifie si le retrait a réussi
        assertTrue(result, "Le retrait devrait réussir.");
        
        // Vérifie si le solde est correctement mis à jour
        double balance = account.checkBalance();
        assertEquals(120.0, balance, "Le solde devrait être de 120 après un dépôt de 50 et un retrait de 30.");
    }
}
