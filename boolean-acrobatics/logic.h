#include <vector>

using namespace std;

typedef struct {
  int x;
  int y;
} position_t;

typedef enum {horizontal, vertical} direction_t;

typedef struct {
  position_t position;
  direction_t direction;
  vector<bool> bombed;
} ship_t;

bool is_valid_ship(ship_t new_ship, vector<ship_t> existing_fleet);

const int grid_size = 10;
