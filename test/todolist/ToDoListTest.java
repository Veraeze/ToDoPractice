package todolist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import todolist.exceptions.CannotBeFound;
import todolist.exceptions.LoginError;
import todolist.exceptions.WrongPassword;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListTest {

        ToDoList toDo;

        @BeforeEach
        void setToDo() {
            toDo = new ToDoList("username", "password");
        }

        @Test
        public void userCanLogInWithCorrectDetails() {
            assertFalse(toDo.isLoggedin());

            toDo.login("password");
            assertTrue(toDo.isLoggedin());
        }

        @Test
        public void throwsException_userLogInWithWrongPassword() {
            assertThrows(WrongPassword.class, () -> toDo.login("maybe"));
        }

        @Test
        public void userCanLogOut() {
            toDo.login("password");
            assertTrue(toDo.isLoggedin());

            toDo.logout();
            assertFalse(toDo.isLoggedin());
        }

        @Test
        public void testThatUserCanCreateATask() {
            toDo.login("password");
            assertTrue(toDo.isLoggedin());

            toDo.writeTask("Title", "Body");
            assertEquals(1, toDo.getNumberOfTasks());
            assertEquals("TDL1", toDo.generateID());

        }

        @Test
        public void testThatUserCanCreateMoreThanOneTask() {
            toDo.login("password");
            assertTrue(toDo.isLoggedin());

            toDo.writeTask("Title", "Body");
            assertEquals(1, toDo.getNumberOfTasks());
            assertEquals("TDL1", toDo.generateID());

            toDo.writeTask("Title", "Body");
            assertEquals(2, toDo.getNumberOfTasks());
            assertEquals("TDL2", toDo.generateID());

        }

        @Test
        public void userIsLoggedOut_ThrowExceptionWhenTryingToCreateTask(){

            assertThrows(LoginError.class, ()-> toDo.writeTask("title", "body"));

        }

        @Test
        public void testThatUserCanDeleteTask() {
            toDo.login("password");
            assertTrue(toDo.isLoggedin());

            toDo.writeTask("Title", "Body");
            assertEquals(1, toDo.getNumberOfTasks());
            assertEquals("TDL1", toDo.generateID());

            toDo.deleteTask("TDL1");
            assertEquals(0, toDo.getNumberOfTasks());

        }

        @Test
        public void testThatUserCanDeleteMoreThanOneTask() {
            toDo.login("password");
            assertTrue(toDo.isLoggedin());

            toDo.writeTask("Title1", "Body1");
            assertEquals("TDL1", toDo.generateID());

            toDo.writeTask("Title2", "Body2");
            assertEquals("TDL2", toDo.generateID());

            toDo.writeTask("Title3", "Body3");
            assertEquals("TDL3", toDo.generateID());

            assertEquals(3, toDo.getNumberOfTasks());

            toDo.deleteTask("TDL1");
            toDo.deleteTask("TDL3");

            assertEquals(1, toDo.getNumberOfTasks());

        }

        @Test
        public void userIsLoggedOut_ThrowExceptionWhenTryingToDeleteEntry(){

            assertThrows(LoginError.class, ()-> toDo.deleteTask("TDL1"));

        }

        @Test
        public void testThatAnExceptionIsThrownWhenTryingToDeleteADeletedTask() {
            toDo.login("password");
            assertTrue(toDo.isLoggedin());

            toDo.writeTask("Title", "Body");
            assertEquals(1, toDo.getNumberOfTasks());
            assertEquals("TDL1", toDo.generateID());

            toDo.deleteTask("TDL1");
            assertEquals(0, toDo.getNumberOfTasks());

            assertThrows(CannotBeFound.class, () -> toDo.deleteTask("TDL1"));

        }

        @Test
        public void testThatUserCanFindTask() {
            toDo.login("password");
            assertTrue(toDo.isLoggedin());

            toDo.writeTask("Title1", "Body1");
            assertEquals("TDL1", toDo.generateID());

            toDo.writeTask("Title2", "Body2");
            assertEquals("TDL2", toDo.generateID());

            assertEquals(2, toDo.getNumberOfTasks());

            Task task2 = toDo.findTask("TDL2");
            Task task1 = toDo.findTask("TDL1");
            assertEquals("TDL2", task2.getID());
            assertEquals("TDL1", task1.getID());

        }

        @Test
        public void testThatAnExceptionIsThrownWhenTryingToFindATaskThatDoesNotExist() {
            toDo.login("password");
            assertTrue(toDo.isLoggedin());

            toDo.writeTask("Title1", "Body1");
            assertEquals("TDL1", toDo.generateID());

            assertThrows(CannotBeFound.class, () -> toDo.findTask("TDL109"));

        }

        @Test
        public void testThatUserCanUpdatePreviousTask() {
            toDo.login("password");
            assertTrue(toDo.isLoggedin());

            toDo.writeTask("Title1", "Body1");
            assertEquals("TDL1", toDo.generateID());

            toDo.writeTask("Title2", "Body2");
            assertEquals("TDL2", toDo.generateID());

            assertEquals(2, toDo.getNumberOfTasks());

            toDo.updateTask("TDL1","Title1","Body1");
            assertEquals(2, toDo.getNumberOfTasks());
            assertEquals(0, toDo.findIndexOf("TDL1"));

            toDo.updateTask("TDL2","Title2","Body2");
            assertEquals(2, toDo.getNumberOfTasks());
            assertEquals(1, toDo.findIndexOf("TDL2"));
        }

    @Test
    public void userIsLoggedOut_ThrowExceptionWhenTryingToUpdateEntry(){

        assertThrows(LoginError.class, ()-> toDo.updateTask("TDL1","Title1","Body1"));

    }

    }

