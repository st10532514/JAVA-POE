/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package javaapplication;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Student
 */
public class LoginTest {

    public LoginTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of checkUserName method, of class Login - correctly formatted username.
     */
    @Test
    public void testCheckUserName_CorrectlyFormatted() {
        System.out.println("checkUserName");
        String username = "kyl_1";
        Login instance = new Login();
        boolean expResult = true;
        boolean result = instance.checkUserName(username);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkUserName method, of class Login - incorrectly formatted username.
     */
    @Test
    public void testCheckUserName_IncorrectlyFormatted() {
        System.out.println("checkUserName");
        String username = "kyle!!!!!!!";
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.checkUserName(username);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkPasswordComplexity method, of class Login - meets complexity rules.
     */
    @Test
    public void testCheckPasswordComplexity_MeetsRequirements() {
        System.out.println("checkPasswordComplexity");
        String password = "Ch&&sec@ke99!";
        Login instance = new Login();
        boolean expResult = true;
        boolean result = instance.checkPasswordComplexity(password);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkPasswordComplexity method, of class Login - does not meet complexity rules.
     */
    @Test
    public void testCheckPasswordComplexity_DoesNotMeetRequirements() {
        System.out.println("checkPasswordComplexity");
        String password = "password";
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.checkPasswordComplexity(password);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkCellPhoneNumber method, of class Login - correctly formatted number.
     */
    @Test
    public void testCheckCellPhoneNumber_CorrectlyFormatted() {
        System.out.println("checkCellPhoneNumber");
        String cellPhoneNumber = "+27838968976";
        Login instance = new Login();
        boolean expResult = true;
        boolean result = instance.checkCellPhoneNumber(cellPhoneNumber);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkCellPhoneNumber method, of class Login - incorrectly formatted number.
     */
    @Test
    public void testCheckCellPhoneNumber_IncorrectlyFormatted() {
        System.out.println("checkCellPhoneNumber");
        String cellPhoneNumber = "08966553";
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.checkCellPhoneNumber(cellPhoneNumber);
        assertEquals(expResult, result);
    }

    /**
     * Test of registerUser method, of class Login - successful registration.
     */
    @Test
    public void testRegisterUser_Successful() {
        System.out.println("registerUser");
        String username = "kyl_1";
        String password = "Ch&&sec@ke99!";
        String cellPhoneNumber = "+27838968976";
        String firstName = "Kyle";
        String lastName = "Smith";
        Login instance = new Login();
        String expResult = "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.\nYou have registered successfully.";
        String result = instance.registerUser(username, password, cellPhoneNumber, firstName, lastName);
        assertEquals(expResult, result);
    }

    /**
     * Test of registerUser method, of class Login - unsuccessful registration (bad username).
     */
    @Test
    public void testRegisterUser_Unsuccessful() {
        System.out.println("registerUser");
        String username = "kyle!!!!!!!";
        String password = "Ch&&sec@ke99!";
        String cellPhoneNumber = "+27838968976";
        String firstName = "Kyle";
        String lastName = "Smith";
        Login instance = new Login();
        String expResult = "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        String result = instance.registerUser(username, password, cellPhoneNumber, firstName, lastName);
        assertEquals(expResult, result);
    }

    /**
     * Test of loginUser method, of class Login - successful login.
     */
    @Test
    public void testLoginUser_Successful() {
        System.out.println("loginUser");
        String username = "kyl_1";
        String password = "Ch&&sec@ke99!";
        Login instance = new Login();
        instance.registerUser(username, password, "+27838968976", "Kyle", "Smith");
        boolean expResult = true;
        boolean result = instance.loginUser(username, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of loginUser method, of class Login - failed login.
     */
    @Test
    public void testLoginUser_Failed() {
        System.out.println("loginUser");
        String username = "kyl_1";
        String password = "Ch&&sec@ke99!";
        Login instance = new Login();
        instance.registerUser(username, password, "+27838968976", "Kyle", "Smith");
        boolean expResult = false;
        boolean result = instance.loginUser("wrongUser", "wrongPassword");
        assertEquals(expResult, result);
    }

    /**
     * Test of returnLoginStatus method, of class Login - successful login status.
     */
    @Test
    public void testReturnLoginStatus_Successful() {
        System.out.println("returnLoginStatus");
        boolean loginSuccess = true;
        String firstName = "Kyle";
        String lastName = "Smith";
        Login instance = new Login();
        String expResult = "Welcome Kyle, Smith it is great to see you again.";
        String result = instance.returnLoginStatus(loginSuccess, firstName, lastName);
        assertEquals(expResult, result);
    }

    /**
     * Test of returnLoginStatus method, of class Login - failed login status.
     */
    @Test
    public void testReturnLoginStatus_Failed() {
        System.out.println("returnLoginStatus");
        boolean loginSuccess = false;
        String firstName = "Kyle";
        String lastName = "Smith";
        Login instance = new Login();
        String expResult = "Username or password incorrect, please try again.";
        String result = instance.returnLoginStatus(loginSuccess, firstName, lastName);
        assertEquals(expResult, result);
    }

}