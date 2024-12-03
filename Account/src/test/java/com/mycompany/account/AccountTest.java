/**
*Objectif des tests Unitaires
*S’assurer que chaque composant fonctionne correctement indépendamment des autres.
*Identifier les bugs le plus tôt possible dans le cycle de développement.
*/

package com.mycompany.account;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la classe Account.
 */
public class AccountTest {
    
    private Account account; // Instance partagée pour les tests.
    
    /*
    *Méthode exécutée une seule fois avant tous les tests de la classe.
    *Utilisée pour initialiser des ressources partagées entre les tests.
    */
    @BeforeAll
    public static void setUpClass() {
        System.out.println("Début des tests de la classe Account.");
    }
    
    /*
    *Méthode exécutée une seule fois après tous les tests de la classe.
    *Utilisée pour nettoyer ou libérer des ressources partagées.
    */
    @AfterAll
    public static void tearDownClass() {
        System.out.println("Fin des tests de la classe Account.");
    }
    
    /*
    *Méthode exécutée avant chaque test.
    *Utilisée pour préparer un état propre ou initialiser des objets nécessaires à un test.
    */
    @BeforeEach
    public void setUp() {
        // Initialise un compte avec un solde initial de 100 pour chaque test.
        account = new Account(100.0);
    }
    
    /*
    *Méthode exécutée après chaque test.
    *Utilisée pour nettoyer ou réinitialiser des ressources spécifiques à un test.
    */
    @AfterEach
    public void tearDown() {
        // Nettoyage ou réinitialisation si nécessaire.
        account = null;
    }

    /**
     * Test de la méthode deposit.
     */
    /*
    *Annotation qui indique qu’une méthode est un test.
    */
    @Test
    public void testDeposit() {
        System.out.println("Test du dépôt.");
        double amount = 50.0;
        account.deposit(amount);
        /*
        *Vérifie que deux valeurs (attendue et réelle) sont égales.
        *Si elles ne le sont pas, le test échoue.
        */
        assertEquals(150.0, account.checkBalance(), "Le solde devrait être de 150 après le dépôt.");
    }

    /**
     * Test de la méthode withdraw (succès).
     */
    @Test
    public void testWithdraw_Success() {
        System.out.println("Test du retrait (succès).");
        double amount = 40.0;
        boolean result = account.withdraw(amount);
        /*
        *Vérifie qu’une condition est vraie.
        *Si la condition est fausse, le test échoue.
        */
        assertTrue(result, "Le retrait devrait réussir.");
        assertEquals(60.0, account.checkBalance(), "Le solde devrait être de 60 après un retrait de 40.");
    }

    /**
     * Test de la méthode withdraw (échec, fonds insuffisants).
     */
    @Test
    public void testWithdraw_Failure() {
        System.out.println("Test du retrait (échec).");
        double amount = 200.0;
        boolean result = account.withdraw(amount);
        /*
        *Vérifie qu’une condition est fausse.
        *Si la condition est vraie, le test échoue.
        */
        assertFalse(result, "Le retrait ne devrait pas réussir avec un montant supérieur au solde.");
        assertEquals(100.0, account.checkBalance(), "Le solde ne devrait pas changer après un retrait échoué.");
    }

    /**
     * Test de la méthode checkBalance.
     */
    @Test
    public void testCheckBalance() {
        System.out.println("Test de la consultation du solde.");
        double expResult = 100.0;
        double result = account.checkBalance();
        assertEquals(expResult, result, "Le solde initial devrait être de 100.");
    }
}

