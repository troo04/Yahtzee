import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

class Game implements ActionListener {
  //variables to make gui display
  JFrame frame;
  JPanel game;
  JPanel cats;
  JPanel cats_2;
  JPanel scores_1;
  JPanel scores_2;
  JLabel label;
  JLabel drop;
  JLabel drop_2;
  // buttons to roll dices
  JButton rollButton;
  JButton dice1;
  JButton dice2;
  JButton dice3;
  JButton dice4;
  JButton dice5;
  //dropdown to allow user to select which place they want their score in
  JComboBox cat;
  JComboBox cat_2;
  // individual panels_1 for score labels_1 and values
  JPanel[] panels_1 = new JPanel[14];
  // label array for score labels_1
  JLabel[] labels_1 = new JLabel[14];
  // textField array for score values
  JTextField[] fields_1 = new JTextField[14];
  // individual panels_1 for score labels_1 and values
  JPanel[] panels_2 = new JPanel[14];
  // label array for score labels_1
  JLabel[] labels_2 = new JLabel[14];
  // textField array for score values
  JTextField[] fields_2 = new JTextField[14];
  // final dice
  int[] numsRolled = { 1, 1, 1, 1, 1 };
  // scoring categories
  String[] categories = { "Aces (Total 1's)", "Twos (Total 2's)", "Threes (Total 3's)", "Four (Total 4's)",
      "Five (Total 5's)", "Six (Total 6's)", "Three of a kind (Total Dice)", "Four of a kind (Total Dice)",
      "Full house (Score: 25)", "Small Straight (Score: 30)", "Large Straight (Score: 40)", "Yahtzee (Score: 50)",
      "Chance (Score: Sum of All Dice)" };
  // player turn
  int player = 1;
  // dice images
  Icon diceImage1 = new ImageIcon("dice_images/one.png");
  Icon diceImage2 = new ImageIcon("dice_images/two.png");
  Icon diceImage3 = new ImageIcon("dice_images/three.png");
  Icon diceImage4 = new ImageIcon("dice_images/four.png");
  Icon diceImage5 = new ImageIcon("dice_images/five.png");
  Icon diceImage6 = new ImageIcon("dice_images/six.png");
  // boolean values to indicate whether or not the button was clicked
  boolean dice1_isClicked = false;
  boolean dice2_isClicked = false;
  boolean dice3_isClicked = false;
  boolean dice4_isClicked = false;
  boolean dice5_isClicked = false;
  // int to check how many the user has clicked the roll button
  int counter = 0;
  JTabbedPane tabbed = new JTabbedPane();
  JTabbedPane dropD = new JTabbedPane();

  JFrame pf;
  JPanel pp;
  JButton yes;
  JButton no;

  boolean cond = false;
  
  //constructor for the Game class
  public Game() {

    //creates the general layout of the GUI, with 2 panels
    frame = new JFrame("Yahtzee");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //top half of the right panel on both of the players' screens
    cats = new JPanel();
    cats.setPreferredSize(new Dimension(295, 75));
    cats.setLayout(new BoxLayout(cats, BoxLayout.PAGE_AXIS));
    cats_2 = new JPanel();
    cats_2.setPreferredSize(new Dimension(295, 75));
    cats_2.setLayout(new BoxLayout(cats_2, BoxLayout.PAGE_AXIS));
    
    //bottom half of the right panel on both of the players' screens
    scores_1 = new JPanel();
    scores_1.setPreferredSize(new Dimension(295, 325));
    scores_2 = new JPanel();
    scores_2.setPreferredSize(new Dimension(295, 325));

    //code for making the dices roll. Here we are just making the panel, and some buttons. Also makes the grid layout and color changes.
    game = new JPanel();
    game.setPreferredSize(new Dimension(300, 400));
    rollButton = new JButton("Roll dice");
    rollButton.setActionCommand("Roll");
    rollButton.addActionListener(this);

    game.setLayout(new BoxLayout(game, BoxLayout.PAGE_AXIS));
    game.setLayout(new GridLayout(3, 5));

    dice1 = new JButton(diceImage1);
    dice1.setBorder(BorderFactory.createLineBorder(Color.GREEN, 4));
    dice2 = new JButton(diceImage1);
    dice2.setBorder(BorderFactory.createLineBorder(Color.GREEN, 4));
    dice3 = new JButton(diceImage1);
    dice3.setBorder(BorderFactory.createLineBorder(Color.GREEN, 4));
    dice4 = new JButton(diceImage1);
    dice4.setBorder(BorderFactory.createLineBorder(Color.GREEN, 4));
    dice5 = new JButton(diceImage1);
    dice5.setBorder(BorderFactory.createLineBorder(Color.GREEN, 4));
    
    //creates the buttons and adds them to the panel.
    rollButton = new JButton("Roll Dice");
    dice1.addActionListener(this);
    dice2.addActionListener(this);
    dice3.addActionListener(this);
    dice4.addActionListener(this);
    dice5.addActionListener(this);
    rollButton.addActionListener(this);

    game.add(dice1);
    game.add(dice2);
    game.add(dice3);
    game.add(dice4);
    game.add(dice5);
    game.add(rollButton);

    // the following code creates the dropdown with the scoring options, for each player
    drop = new JLabel(
        "<html>Upper & Lower Section Scoring <br/>(Choose how you would like your game be scored):</html>",
        SwingConstants.CENTER);
    drop_2 = new JLabel(
        "<html>Upper & Lower Section Scoring <br/>(Choose how you would like your game be scored):</html>",
        SwingConstants.CENTER);
    cats.add(drop);
    cats_2.add(drop_2);
    cat = new JComboBox(categories);
    cat.setAlignmentX(JComboBox.LEFT_ALIGNMENT);
    cat.setSelectedIndex(0);
    cat.addActionListener(this);
    cats.add(cat);
    cat.setEnabled(false);
    cat_2 = new JComboBox(categories);
    cat_2.setAlignmentX(JComboBox.LEFT_ALIGNMENT);
    cat_2.setSelectedIndex(0);
    cat_2.addActionListener(this);
    cats_2.add(cat_2);
    cat_2.setEnabled(false);
    dropD.add("Player 1: ", cats);
    dropD.add("Player 2: ", cats_2);

    //helps to switch between player screens, so that the user doesn't have to manual switch between screens
    dropD.setSelectedIndex(0);
    dropD.setEnabledAt(1, false);
    
    // the following codes adds all of the labels in both player screens
    scores_1.setLayout(new GridLayout(4, 3));
    scores_2.setLayout(new GridLayout(4, 3));
    for (int i = 0; i < labels_1.length; i++) {
      switch (i) {
        case 0:
          labels_1[i] = new JLabel("Aces:");
          labels_2[i] = new JLabel("Aces:");
          break;
        case 1:
          labels_1[i] = new JLabel("Twos:");
          labels_2[i] = new JLabel("Twos:");
          break;
        case 2:
          labels_1[i] = new JLabel("Threes:");
          labels_2[i] = new JLabel("Threes:");
          break;
        case 3:
          labels_1[i] = new JLabel("Fours:");
          labels_2[i] = new JLabel("Fours:");
          break;
        case 4:
          labels_1[i] = new JLabel("Fives:");
          labels_2[i] = new JLabel("Fives:");
          break;
        case 5:
          labels_1[i] = new JLabel("Sixes:");
          labels_2[i] = new JLabel("Sixes:");
          break;
        case 6:
          labels_1[i] = new JLabel("Three oK:");
          labels_2[i] = new JLabel("Three oK:");
          break;
        case 7:
          labels_1[i] = new JLabel("Four oK:");
          labels_2[i] = new JLabel("Four oK:");
          break;
        case 8:
          labels_1[i] = new JLabel("FullHouse:");
          labels_2[i] = new JLabel("FullHouse:");
          break;
        case 9:
          labels_1[i] = new JLabel("Small Str:");
          labels_2[i] = new JLabel("Small Str:");
          break;
        case 10:
          labels_1[i] = new JLabel("Large Str:");
          labels_2[i] = new JLabel("Large Str:");
          break;
        case 11:
          labels_1[i] = new JLabel("Yahtzee:");
          labels_2[i] = new JLabel("Yahtzee:");
          break;
        case 12:
          labels_1[i] = new JLabel("Chance:");
          labels_2[i] = new JLabel("Chance:");
          break;
        case 13:
          labels_1[i] = new JLabel("Total:");
          labels_2[i] = new JLabel("Total:");
          break;
      }
      //instantiates 14 new panels for each player screen and adding the 14 new textfields for each player screen
      panels_1[i] = new JPanel();
      panels_1[i].setLayout(new BoxLayout(panels_1[i], BoxLayout.PAGE_AXIS));
      fields_1[i] = new JTextField();
      fields_1[i].setText("");

      fields_1[i].setEditable(false);
      labels_1[i].setHorizontalAlignment(JLabel.CENTER);

      panels_1[i].add(labels_1[i]);
      panels_1[i].add(fields_1[i]);

      scores_1.add(panels_1[i]);

      panels_2[i] = new JPanel();
      panels_2[i].setLayout(new BoxLayout(panels_2[i], BoxLayout.PAGE_AXIS));
      fields_2[i] = new JTextField();
      fields_2[i].setText("");

      fields_2[i].setEditable(false);
      labels_2[i].setHorizontalAlignment(JLabel.CENTER);

      panels_2[i].add(labels_2[i]);
      panels_2[i].add(fields_2[i]);

      scores_2.add(panels_2[i]);
    }
    //this creates a two different tabs for each player
    tabbed.addTab("Player 1:", scores_1);
    tabbed.addTab("Player 2:", scores_2);
    
    //creates mulitples panels within the frame
    JSplitPane rS = new JSplitPane(SwingConstants.HORIZONTAL, dropD, tabbed);

    JSplitPane sl = new JSplitPane(SwingConstants.VERTICAL, game, rS);

    // set Orientation for slider
    sl.setOrientation(SwingConstants.VERTICAL);
    // packages the frame and sets it visible to the user
    frame.setContentPane(sl);
    frame.pack();
    frame.setVisible(true);
    fields_1[13].setText("0");
    fields_2[13].setText("0");
    
  }
//events that will take place based on which button is clicked, or dropdown is selected.
  public void actionPerformed(ActionEvent event) {
    try {
      // dice rolling, and toggles whether or not the dice can be rolled(Hold dice won't roll this way) again.
      JButton die = (JButton) event.getSource();
      if (die.equals(rollButton)) {
        if (counter < 3) {
          for (int i = 0; i < 5; i++) {
            if (i == 0 && dice1_isClicked) {
              continue;
            } else if (i == 1 && dice2_isClicked) {
              continue;
            } else if (i == 2 && dice3_isClicked) {
              continue;
            } else if (i == 3 && dice4_isClicked) {
              continue;
            } else if (i == 4 && dice5_isClicked) {
              continue;
            }
          //assigns random values to the dice when the rollDice button is clicked.
            int a = (int) (Math.random() * 6) + 1;
            JButton temp = new JButton();
            switch (i) {
              case 0:
                temp = dice1;
                break;
              case 1:
                temp = dice2;
                break;
              case 2:
                temp = dice3;
                break;
              case 3:
                temp = dice4;
                break;
              case 4:
                temp = dice5;
                break;
            }

            changeDice(temp, a);
          }
          counter++;
          if (counter == 3) {
            //changes the color of dice based off of whether or not they are held.
                      dice1.setBorder(BorderFactory.createLineBorder(Color.RED, 4));
            dice2.setBorder(BorderFactory.createLineBorder(Color.RED, 4));
            dice3.setBorder(BorderFactory.createLineBorder(Color.RED, 4));
            dice4.setBorder(BorderFactory.createLineBorder(Color.RED, 4));
            dice5.setBorder(BorderFactory.createLineBorder(Color.RED, 4));
            assign();
          }
        }
        //changes if the dice is held or not with a boolean variable by calling the assign() method and changing the border color of dies to red
      } else {
        if (die.equals(dice1) && counter != 0) {
          if (dice1_isClicked == false) {
            dice1_isClicked = true;
            dice1.setBorder(BorderFactory.createLineBorder(Color.RED, 4));
            if (dice1_isClicked != false && dice2_isClicked != false && dice3_isClicked != false
                && dice4_isClicked != false && dice5_isClicked != false && counter != 0) {
              double_check();
              if (cond)
                assign();
              cond = true;
            }
          } else {
            dice1_isClicked = false;
            dice1.setBorder(BorderFactory.createLineBorder(Color.GREEN, 4));
          }
        } else if (die.equals(dice2) && counter != 0) {
          if (dice2_isClicked == false) {
            dice2_isClicked = true;
            dice2.setBorder(BorderFactory.createLineBorder(Color.RED, 4));
            if (dice1_isClicked != false && dice2_isClicked != false && dice3_isClicked != false
                && dice4_isClicked != false && dice5_isClicked != false && counter != 0) {
              double_check();
              if (cond)
                assign();
              cond = true;
            }
          } else {
            dice2_isClicked = false;
            dice2.setBorder(BorderFactory.createLineBorder(Color.GREEN, 4));
          }
        } else if (die.equals(dice3) && counter != 0) {
          if (dice3_isClicked == false) {
            dice3_isClicked = true;
            dice3.setBorder(BorderFactory.createLineBorder(Color.RED, 4));
            if (dice1_isClicked != false && dice2_isClicked != false && dice3_isClicked != false
                && dice4_isClicked != false && dice5_isClicked != false && counter != 0) {
              double_check();
              if (cond)
                assign();
              cond = true;
            }
          } else {
            dice3_isClicked = false;
            dice3.setBorder(BorderFactory.createLineBorder(Color.GREEN, 4));
          }
        } else if (die.equals(dice4) && counter != 0) {
          if (dice4_isClicked == false) {
            dice4_isClicked = true;
            dice4.setBorder(BorderFactory.createLineBorder(Color.RED, 4));
            if (dice1_isClicked != false && dice2_isClicked != false && dice3_isClicked != false
                && dice4_isClicked != false && dice5_isClicked != false && counter != 0) {
              double_check();
              if (cond)
                assign();
              cond = true;
            }
          } else {
            dice4_isClicked = false;
            dice4.setBorder(BorderFactory.createLineBorder(Color.GREEN, 4));
          }
        } else if (die.equals(dice5) && counter != 0) {
          if (dice5_isClicked == false) {
            dice5_isClicked = true;
            dice5.setBorder(BorderFactory.createLineBorder(Color.RED, 4));
            if (dice1_isClicked != false && dice2_isClicked != false && dice3_isClicked != false
                && dice4_isClicked != false && dice5_isClicked != false && counter != 0) {
              double_check();
              if (cond)
                assign();
              cond = true;
            }
          } else {
            dice5_isClicked = false;
            dice5.setBorder(BorderFactory.createLineBorder(Color.GREEN, 4));
          }
        } else {
          //handles the yes and no button in the popup
          JButton temp = (JButton) event.getSource();

          if (temp.equals(yes)) {
            assign();
          } else {
            cond = false;
            dice1.setBorder(BorderFactory.createLineBorder(Color.GREEN, 4));
            dice1_isClicked = false;
            dice2.setBorder(BorderFactory.createLineBorder(Color.GREEN, 4));
            dice2_isClicked = false;
            dice3.setBorder(BorderFactory.createLineBorder(Color.GREEN, 4));
            dice3_isClicked = false;
            dice4.setBorder(BorderFactory.createLineBorder(Color.GREEN, 4));
            dice4_isClicked = false;
            dice5.setBorder(BorderFactory.createLineBorder(Color.GREEN, 4));
            dice5_isClicked = false;
          }
          pf.setVisible(false);
        }
      }
    } catch (Exception e) {
      //the following code will be executed if the dropdown is used
      counter = 0;
      JComboBox comboBox = (JComboBox) event.getSource();
      if (comboBox.equals(cat)) {
        helper(comboBox);
      } else {
        helper_2(comboBox);
      }
      fields_1[13].setText(total());
      fields_2[13].setText(total_2());

      // reset values
      for (int i = 0; i < numsRolled.length; i++) {
        numsRolled[i] = 0;
      }
      changeDice(dice1, 1);
      changeDice(dice2, 1);
      changeDice(dice3, 1);
      changeDice(dice4, 1);
      changeDice(dice5, 1);
      ableDice(true);
      dice1.setBorder(BorderFactory.createLineBorder(Color.GREEN, 4));
      dice1_isClicked = false;
      dice2.setBorder(BorderFactory.createLineBorder(Color.GREEN, 4));
      dice2_isClicked = false;
      dice3.setBorder(BorderFactory.createLineBorder(Color.GREEN, 4));
      dice3_isClicked = false;
      dice4.setBorder(BorderFactory.createLineBorder(Color.GREEN, 4));
      dice4_isClicked = false;
      dice5.setBorder(BorderFactory.createLineBorder(Color.GREEN, 4));
      dice5_isClicked = false;
    }

  }
//Determines if the catergory selected by user is valid, if valid, gives proper score, else will set score to zero(player 1)
  public void helper(JComboBox comboBox) {
    String in = (String) comboBox.getSelectedItem();
    comboBox.removeItem(in);
    comboBox.setEnabled(false);

    if (in.equals(categories[0])) {
      fields_1[0].setText(String.valueOf(count(1)));
    } else if (in.equals(categories[1])) {
      fields_1[1].setText(String.valueOf(count(2) * 2));
    } else if (in.equals(categories[2])) {
      fields_1[2].setText(String.valueOf(count(3) * 3));
    } else if (in.equals(categories[3])) {
      fields_1[3].setText(String.valueOf(count(4) * 4));
    } else if (in.equals(categories[4])) {
      fields_1[4].setText(String.valueOf(count(5) * 5));
    } else if (in.equals(categories[5])) {
      fields_1[5].setText(String.valueOf(count(6) * 6));
    } else if (in.equals(categories[6])) {
      if (oK(3)) {
        fields_1[6].setText(String.valueOf(chance()));
      } else {
        fields_1[6].setText("0");
        comboBox.setEnabled(true);
      }
    } else if (in.equals(categories[7])) {
      if (oK(4)) {
        fields_1[7].setText(String.valueOf(chance()));
      } else {
        fields_1[7].setText("0");
        comboBox.setEnabled(true);
      }
    } else if (in.equals(categories[8])) {
      if (fullHouse()) {
        fields_1[8].setText("25");
      } else {
        fields_1[8].setText("0");
        comboBox.setEnabled(true);
      }
    } else if (in.equals(categories[9])) {
      if (smallStr()) {
        fields_1[9].setText("30");
      } else {
        fields_1[9].setText("0");
        comboBox.setEnabled(true);
      }
    } else if (in.equals(categories[10])) {
      if (largeStr()) {
        fields_1[10].setText("40");
      } else {
        fields_1[10].setText("0");
        comboBox.setEnabled(true);
      }
    } else if (in.equals(categories[11])) {
      if (yahtzee()) {
        fields_1[11].setText("50");
      } else {
        fields_1[11].setText("0");
        comboBox.setEnabled(true);
      }
    } else if (in.equals(categories[12])) {
      fields_1[12].setText(String.valueOf(chance()));
    }
    player = 2;
    dropD.setSelectedIndex(1);
    dropD.setEnabledAt(1, true);
    dropD.setEnabledAt(0, false);
    tabbed.setSelectedIndex(1);
  }
  //Determines if the catergory selected by user is valid, if valid, gives proper score, else will set score to zero(player 2)
  public void helper_2(JComboBox comboBox) {
    String in = (String) comboBox.getSelectedItem();
    comboBox.removeItem(in);
    comboBox.setEnabled(false);

    if (in.equals(categories[0])) {
      fields_2[0].setText(String.valueOf(count(1)));
    } else if (in.equals(categories[1])) {
      fields_2[1].setText(String.valueOf(count(2) * 2));
    } else if (in.equals(categories[2])) {
      fields_2[2].setText(String.valueOf(count(3) * 3));
    } else if (in.equals(categories[3])) {
      fields_2[3].setText(String.valueOf(count(4) * 4));
    } else if (in.equals(categories[4])) {
      fields_2[4].setText(String.valueOf(count(5) * 5));
    } else if (in.equals(categories[5])) {
      fields_2[5].setText(String.valueOf(count(6) * 6));
    } else if (in.equals(categories[6])) {
      if (oK(3)) {
        fields_2[6].setText(String.valueOf(chance()));
      } else {
        fields_2[6].setText("0");
        comboBox.setEnabled(true);
      }
    } else if (in.equals(categories[7])) {
      if (oK(4)) {
        fields_2[7].setText(String.valueOf(chance()));
      } else {
        fields_2[7].setText("0");
        comboBox.setEnabled(true);
      }
    } else if (in.equals(categories[8])) {
      if (fullHouse()) {
        fields_2[8].setText("25");
      } else {
        fields_2[8].setText("0");
        comboBox.setEnabled(true);
      }
    } else if (in.equals(categories[9])) {
      if (smallStr()) {
        fields_2[9].setText("30");
      } else {
        fields_2[9].setText("0");        
        comboBox.setEnabled(true);
      }
    } else if (in.equals(categories[10])) {
      if (largeStr()) {
        fields_2[10].setText("40");
      } else {
        fields_2[10].setText("0");
        comboBox.setEnabled(true);
      }
    } else if (in.equals(categories[11])) {
      if (yahtzee()) {
        fields_2[11].setText("50");
      } else {
        fields_2[11].setText("0");
        comboBox.setEnabled(true);
      }
    } else if (in.equals(categories[12])) {
      fields_2[12].setText(String.valueOf(chance()));
    }
    player = 1;
    dropD.setSelectedIndex(0);
    dropD.setEnabledAt(0, true);
    dropD.setEnabledAt(1, false);
    tabbed.setSelectedIndex(0);
  }

  //counts how many times a certain element occurs in the array rolled by the user
  public int count(int num) {
    int counter = 0;
    for (int ele : numsRolled) {
      if (ele == num) {
        counter++;
      }
    }
    return counter;
  }

  //returns true is the user has a three of a kind or a four of a kind, returns false if they don't
  public boolean oK(int type) {
    int counter = 1;
    if (type == 3) {
      for (int i = 0; i < numsRolled.length; i++) {
        for (int j = 0; j < numsRolled.length; j++) {
          if (i != j && numsRolled[i] == numsRolled[j]) {
            counter++;
          }
        }
        if (counter >= 3) {
          return true;
        }
        counter = 1;
      }
    } else {
      for (int i = 0; i < numsRolled.length; i++) {
        for (int j = 0; j < numsRolled.length; j++) {
          if (i != j && numsRolled[i] == numsRolled[j]) {
            counter++;
          }
        }
        if (counter >= 4) {
          return true;
        }
        counter = 1;
      }
    }
    return false;
  }
//determines if the user has a fullhouse, returns true if they have a full house, returns false if they don't
  public boolean fullHouse() {
    int counter = 1;
    int threeNum = 0;
    int pos = 0;
    int[] leftOver = new int[2];
    for (int i = 0; i < numsRolled.length; i++) {
      for (int j = 0; j < numsRolled.length; j++) {
        if (i != j && numsRolled[i] == numsRolled[j]) {
          threeNum = numsRolled[i];
          counter++;
        }
      }
      if (counter == 3) {
        break;
      }
      counter = 1;
    }
    if (counter == 3) {
      for (int i = 0; i < numsRolled.length; i++) {
        if (numsRolled[i] != threeNum) {
          leftOver[pos] = numsRolled[i];
          pos++;
        }
      }
    }
    return (counter == 3 && leftOver[0] == leftOver[1] && leftOver[0] != threeNum);
  }

  // returns whether or not the user possesses a small straight
  public boolean smallStr() {
    return ((count(1) >= 1 && count(2) >= 1 && count(3) >= 1) && count(4) >= 1)
        || (count(2) >= 1 && count(3) >= 1 && count(4) >= 1 && count(5) >= 1) || (count(6) >= 1 && count(3) >= 1 && count(4) >= 1 && count(5) >= 1);
  }

  // returns whether or not the user possesses a large straight
  public boolean largeStr() {
    return (count(1) == 1 && count(2) == 1 && count(3) == 1 && count(4) == 1 && count(5) == 1) || (count(6) == 1 && count(2) == 1 && count(3) == 1 && count(4) == 1 && count(5) == 1);
  }
  //determines if user has gotten a yatzhee(5 of the same dice) Returns true if they have, false if they havent.
  public boolean yahtzee() {
    return count(numsRolled[0]) == 5;
  }

  //returns the sum of all the dies
  public int chance() {
    int counter = 0;
    for (int ele : numsRolled) {
      counter += ele;
    }
    return counter;
  }

  //updates the score within the total box within the scorecard (player 1)
  public String total() {
    String t;
    int sum = 0;
    for (JTextField f : fields_1) {
      if (!f.getText().equals("") && !f.equals(fields_1[13])) {
        sum += Integer.parseInt(f.getText());
      }
    }
    int check = 0;
    for (int i = 0; i < 6; i++) {
      if (!fields_1[i].getText().equals("")) {
        check += Integer.parseInt(fields_1[i].getText());
      }
    }
    if (check >= 63) {
      sum += 35;
      JOptionPane.showMessageDialog(null,
        "Player 1, you got 35 extra points!!!");
    }
    t = String.valueOf(sum);
    if (isFilled(fields_1) && isFilled(fields_2)) {
      winner();
    }
    return t;
  }
  //updates the score within the total box within the scorecard (player 2)
  public String total_2() {
    String t;
    int sum = 0;
    for (JTextField f : fields_2) {
      if (!f.getText().equals("") && !f.equals(fields_2[13])) {
        sum += Integer.parseInt(f.getText());
      }
    }
    int check = 0;
    for (int i = 0; i < 6; i++) {
      if (!fields_2[i].getText().equals("")) {
        check += Integer.parseInt(fields_2[i].getText());
      }
    }
    if (check >= 63) {
      sum += 35;
      JOptionPane.showMessageDialog(null,
        "Player 2, you got 35 extra points!!!");
    }
    t = String.valueOf(sum);
    if (isFilled(fields_1) && isFilled(fields_2)) {
      winner();
    }
    return t;
  }

  //changes the image of the dice based on which number they rolled.
  public void changeDice(JButton c, int a) {
    switch (a) {
      case 1:
        c.setIcon(diceImage1);
        break;
      case 2:
        c.setIcon(diceImage2);
        break;
      case 3:
        c.setIcon(diceImage3);
        break;
      case 4:
        c.setIcon(diceImage4);
        break;
      case 5:
        c.setIcon(diceImage5);
        break;
      case 6:
        c.setIcon(diceImage6);
        break;
    }
  }

  //fills in the array within the dies that was chosen
  public void assign() {
    numsRolled[0] = figure(dice1);
    numsRolled[1] = figure(dice2);
    numsRolled[2] = figure(dice3);
    numsRolled[3] = figure(dice4);
    numsRolled[4] = figure(dice5);
    ableDice(false);
    if (player == 1) {
      cat.setEnabled(true);
    } else {
      cat_2.setEnabled(true);
    }
  }

  //links each die to the number on that die
  public int figure(JButton button) {
    Icon src = button.getIcon();

    if (src.equals(diceImage1)) {
      return 1;
    } else if (src.equals(diceImage2)) {
      return 2;
    } else if (src.equals(diceImage3)) {
      return 3;
    } else if (src.equals(diceImage4)) {
      return 4;
    } else if (src.equals(diceImage5)) {
      return 5;
    } else if (src.equals(diceImage6)) {
      return 6;
    }
    return 0;
  
    }
//sets all dice to true or false, allowing them to be clicked or not.
  public void ableDice(boolean s) {
    dice1.setEnabled(s);
    dice2.setEnabled(s);
    dice3.setEnabled(s);
    dice4.setEnabled(s);
    dice5.setEnabled(s);
  }
// Determines the winner by comparing the total scores. Displays a message saying who is the winner.
  public void winner() {
    if (Integer.parseInt(fields_1[13].getText()) > Integer.parseInt(fields_2[13].getText())) {
      JOptionPane.showMessageDialog(null,
        "<html>Player 1 wins!!! <br/>Player one's score: " + fields_1[13].getText() + "<br/> Player two's score: " + fields_2[13].getText() + "</html>");
    } else if (Integer.parseInt(fields_1[13].getText()) < Integer.parseInt(fields_2[13].getText())){
      JOptionPane.showMessageDialog(null,
        "<html>Player 2 wins!!! <br/>Player one's score: " + fields_1[13].getText() + "<br/> Player two's score: " + fields_2[13].getText() + "</html>");
    } else {
      JOptionPane.showMessageDialog(null,
        "<html>Tie!!! <br/>Player one's score: " + fields_1[13].getText() + "<br/> Player two's score: " + fields_2[13].getText() + "</html>");
    }
    System.exit(0);
  }
  //determines if the textfield is empty. True if filled, false if empty.
  public boolean isFilled(JTextField[] fields) {
    for (JTextField f : fields) {
      if (f.getText().equals("")) {
        return false;
      }
    }
    return true;
  }
  //makes sure that the selected dice by the user is correct
  public void double_check() {
    pf = new JFrame();
    pp = new JPanel();

    pf.setSize(new Dimension(100, 50));
    
    JLabel pl = new JLabel("Is this your final dice?");
    yes = new JButton();
    yes.setText("Yes");
    yes.addActionListener(this);
    no = new JButton();
    no.setText("No");
    no.addActionListener(this);

    pp.add(pl);
    pp.add(yes);
    pp.add(no);
    
    pf.setContentPane(pp);
    pf.pack();
    pf.setVisible(true);
  }
}
