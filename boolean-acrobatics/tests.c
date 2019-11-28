#include "gtest/gtest.h"

#include "logic.h"




TEST (Test_is_valid_ship, one_ship_fits) {
  ship_t test_ship;
  test_ship.position.x = 3;
  test_ship.position.y = 2;
  test_ship.direction = horizontal;
  test_ship.bombed = {false, false};

  vector<ship_t> test_fleet = {};
  EXPECT_EQ (is_valid_ship(test_ship, test_fleet), true);
}

TEST (Test_is_valid_ship, one_ship_exceeds) {
  vector<ship_t> test_fleet = {};

  ship_t test_ship;
  test_ship.position.x = 9;
  test_ship.position.y = 9;
  test_ship.bombed = {false, false};

  test_ship.direction = horizontal;
  EXPECT_EQ (is_valid_ship(test_ship, test_fleet), false);

  test_ship.direction = vertical;
  EXPECT_EQ (is_valid_ship(test_ship, test_fleet), false);
}


TEST (Test_is_valid_ship, right_vs_down) {
  vector<ship_t> test_fleet = {};

  ship_t test_ship;
  test_ship.position.x = 9;
  test_ship.position.y = 0;
  test_ship.bombed = {false, false};

  test_ship.direction = horizontal;
  EXPECT_EQ (is_valid_ship(test_ship, test_fleet), false);

  test_ship.direction = vertical;
  EXPECT_EQ (is_valid_ship(test_ship, test_fleet), true);
}

TEST (Test_is_valid_ship, horizontal_overlap) {
  ship_t old_ship;
  old_ship.position.x = 4;
  old_ship.position.y = 4;
  old_ship.direction = horizontal;
  old_ship.bombed = {false, false};
  vector<ship_t> test_fleet = {old_ship};

  ship_t test_ship;
  test_ship.position.x = 3;
  test_ship.position.y = 4;
  test_ship.direction = horizontal;
  test_ship.bombed = {false, false};

  EXPECT_EQ (is_valid_ship(test_ship, test_fleet), false);

  test_ship.position.x = 2;
  test_ship.position.y = 4;
  test_ship.direction = horizontal;
  test_ship.bombed = {false, false};

  EXPECT_EQ (is_valid_ship(test_ship, test_fleet), true);
}
