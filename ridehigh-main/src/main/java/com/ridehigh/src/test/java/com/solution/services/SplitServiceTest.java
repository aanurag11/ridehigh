/*
package com.solution.services;

import static com.solution.Constants.EQUAL_SPLIT;
import static org.junit.jupiter.api.Assertions.*;

import com.solution.models.ExpenseInfo;
import com.solution.models.Group;
import com.solution.models.User;
import com.solution.storage.GroupDAOInMemory;
import com.solution.storage.SplitSystemInMemory;
import com.solution.storage.UserDAOInMemory;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class SplitServiceTest {

  @Autowired
  private static UserDAOInMemory userDAOInMemory;
  @Autowired
  private static GroupDAOInMemory groupDAOInMemory;
  @Autowired
  private static SplitSystemInMemory splitSystemInMemory;

  @Autowired
  private static SplitService splitService;

  @Test
  public  void simulateApplication() {
    String groupId1 = "group1";
    String groupId2 = "group2";
    long userId1 = 1234;
    long userId2 = 9876;
    long userId3 = 9999;
    long userId4 = 8888;
    User user1 = User.builder().userId(userId1).groupId(groupId1).build();
    User user2 = User.builder().userId(userId2).groupId(groupId1).build();
    User user3 = User.builder().userId(userId3).groupId(groupId2).build();
    User user4 = User.builder().userId(userId4).groupId(groupId2).build();

    userDAOInMemory.addUser(user1);
    userDAOInMemory.addUser(user2);
    userDAOInMemory.addUser(user3);
    userDAOInMemory.addUser(user4);

    Set<User> group1Users = new HashSet<>();
    group1Users.add(user1);
    group1Users.add(user2);
    Group group1 = Group.builder().groupId(groupId1).users(group1Users).build();

    Set<User> group2Users = new HashSet<>();
    group2Users.add(user3);
    group2Users.add(user4);
    Group group2 = Group.builder().groupId(groupId2).users(group2Users).build();

    groupDAOInMemory.createGroup(group1);
    groupDAOInMemory.createGroup(group2);

    long expenseId1 = 4444;
    Set<User> expense1 = new HashSet<>();
    expense1.add(user1);
    expense1.add(user2);
    ExpenseInfo expenseInfo = ExpenseInfo.builder().expenseId(expenseId1).amount(100).spender(userId1).
        splitters(expense1).groupId(groupId1).build();
    splitSystemInMemory.addExpense(expenseInfo);

    splitService.splitAmount(EQUAL_SPLIT, expenseInfo);

    System.out.println("Transaction"+ splitSystemInMemory.fetchTransactions());

  }
}
*/
