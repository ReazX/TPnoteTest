/**
*Objectif des tests systèmes
*Tester des scénarios globaux impliquant l’ensemble des fonctionnalités de la classe Account dans des situations réalistes.
*Simuler des workflows complets.
*/

package com.mycompany.account;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests systèmes pour la classe Account.
 */
public class AccountSystemTest {

    private Account account;

    @BeforeAll
    public static void setUpClass() {
        System.out.println("Début des tests systèmes pour Account.");
    }

    @AfterAll
    public static void tearDownClass() {
        System.out.println("Fin des tests systèmes pour Account.");
    }

    @BeforeEach
    public void setUp() {
        // Initialise un compte avant chaque test
        account = new Account(500.0);
    }

    @AfterEach
    public void tearDown() {
        // Réinitialisation après chaque test
        account = null;
    }

    /**
     * Test système : Dépôt, retrait et consultation du solde.
     */
    @Test
    public void testFullTransactionFlow() {
        System.out.println("Test système : Dépôt, retrait et consultation du solde.");
        
        // Dépôt initial
        account.deposit(200.0);
        assertEquals(700.0, account.checkBalance(), "Le solde devrait être de 700 après un dépôt de 200.");
        
        // Retrait valide
        boolean withdrawalResult = account.withdraw(300.0);
        assertTrue(withdrawalResult, "Le retrait devrait réussir.");
        assertEquals(400.0, account.checkBalance(), "Le solde devrait être de 400 après un retrait de 300.");
        
        // Tentative de retrait invalide (fonds insuffisants)
        boolean withdrawalFailure = account.withdraw(500.0);
        assertFalse(withdrawalFailure, "Le retrait ne devrait pas réussir.");
        assertEquals(400.0, account.checkBalance(), "Le solde devrait rester à 400 après un retrait échoué.");
    }

    /**
     * Test système : Retrait dépassant le solde initial.
     */
    @Test
    public void testOverdraftProtection() {
        System.out.println("Test système : Protection contre les découverts.");
        
        // Tentative de retirer plus que le solde disponible
        boolean result = account.withdraw(600.0);
        assertFalse(result, "Le retrait ne devrait pas être autorisé.");
        assertEquals(500.0, account.checkBalance(), "Le solde devrait rester inchangé après un retrait non autorisé.");
    }

    /**
     * Test système : Dépôts multiples et validation du solde.
     */
    @Test
    public void testMultipleDeposits() {
        System.out.println("Test système : Dépôts multiples.");
        
        // Dépôts successifs
        account.deposit(100.0);
        account.deposit(50.0);
        account.deposit(300.0);
        
        // Vérification finale du solde
        assertEquals(950.0, account.checkBalance(), "Le solde devrait être de 950 après trois dépôts.");
    }

    /**
     * Test système : Séquence complexe de transactions.
     */
    @Test
    public void testComplexTransactionSequence() {
        System.out.println("Test système : Séquence complexe de transactions.");
        
        // Dépôt initial
        account.deposit(100.0);
        
        // Retrait valide
        account.withdraw(200.0);
        
        // Dépôt supplémentaire
        account.deposit(300.0);
        
        // Retrait invalide
        boolean invalidWithdrawal = account.withdraw(1000.0);
        assertFalse(invalidWithdrawal, "Le retrait ne devrait pas réussir.");
        
        // Vérification finale du solde
        assertEquals(700.0, account.checkBalance(), "Le solde devrait être de 700 après toutes les transactions.");
    }
}
