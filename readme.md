# myTp plugin for spigot 1.14.x

  - Player can teleport to specific points, previously created by an OPerator.
  - Players can teleport to each others

All command are case insensitive.

## Available commands

### For OPs:

#### `/setgo [name]`

Set a new location based on the current OP coordinates.

example:
```/setGo village```
will set a new location called 'village' with the current OP's coordinates

#### `/delGo [name]`

Delete an existing location

example:
```/delGo village```
delete the locaton called 'village'

### For users:

#### `/goList`

List all available locations

example:
```/goList```
list of all available location, pipe separated

#### `/go [location]`

Teleport to a location

example:
```/go village```
teleport the player to the coordinates saved as 'village'

#### `/goTo [username]`

Teleports current player to another player

example:
```/goTo user123```
teleports the current user to the user123's coordinates