#include "logic.h"

bool is_valid_ship(ship_t new_ship, vector<ship_t> existing_fleet) {
  bool result;
  int new_length = new_ship.bombed.size();

  bool fits_horizontal = (new_ship.direction != horizontal) ||
                         ((new_ship.position.x + new_length < grid_size) &&
                          (new_ship.position.y < grid_size));
  bool fits_vertical = (new_ship.direction != vertical) ||
                       ((new_ship.position.x < grid_size) &&
                        (new_ship.position.y + new_length < grid_size));

  result = fits_horizontal && fits_vertical;

  bool overlap = false;
  for (ship_t ship: existing_fleet) {
    overlap = overlap || ((new_ship.direction == horizontal && ship.direction == horizontal) &&
                          ((new_ship.position.y == ship.position.y) &&
                          ((new_ship.position.x + new_length > ship.position.x) ||
                           (new_ship.position.x < ship.position.x + ship.bombed.size()))));
    overlap = overlap || ((new_ship.direction == vertical && ship.direction == vertical) &&
                          ((new_ship.position.x == ship.position.x) &&
                          ((new_ship.position.y + new_length > ship.position.y) ||
                           (new_ship.position.y < ship.position.y + ship.bombed.size()))));
  }
  result = result && (!overlap);
  return result;
}
