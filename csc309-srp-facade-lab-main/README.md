# srp-facade-lab

CSC 309 Lab on the Single Responsibility Principle and Facade Design Pattern

_Gather together those things that change for the same reason, and separate those things that change for different reasons._

_Create a Facade as a way to provide clients a unique central point of access to those different functionalities_

## Overview of this repo

Inside you will find the beginnings of a ground-breaking new RPG game. Unfortunately in the rush to get features completed the main
RpgPlayer class is starting to turn into a hair-ball of responsibilities.

You'll start by adding some more features to it to get it into a right mess, then we'll fix it up good.

## Getting started

1. Duplicate this repo so you can have this starter code. Don't clone it because you won't push to this repo and don't fork it because you won't be able to make your fork private.
   - There are different ways to do duplicate this repo, and perhaps the simplest one is to download this code using GH download ZIP button in this page.
   - Then, you unzip it locally in a new folder that will represent your new repo.
   - To make this new folder a git folder, run _git init_ in that folder.
   - Go to github.com and create a new repository at GitHub. Mark the repository as private.
   - Follow GH instructions to link your local git repo to this newly created GH repo.
     - git remote add origin https://github.com/<your_user>/<your_new_repo>.git
     - git branch -M main
     - git push -u origin main
   - Add me to your GH repo so I can access your solution later. My GH username: **cpzielke**
2. Start working on the code (move to next section).

## Getting familiar with the code, but making the situation worse...

### Feature: Super rare items look more awesome

- **As a** graphics designer
- **I want** rare and unique items to look more awesome
- **So that** the player gets a dopamine hit and keep buying more in-game currency

Add a new rule to `PickUpItem` that will show a `blue_swirly` effect if the `item`
is both `rare` and `unique`.

### Feature: Un-encumbered players can parry more successfully

- **As a** game designer
- **I want** the players damage taken to be reduced by 25% if they're carrying under 50% of my capacity
- **So that** they will win more fights because they're lighter and can dodge attacks more successfully.

Add a new rule that reduces all damage taken via `TakeDamage` by 25% if the player is carrying
less than half their `CarryingCapacity`.

## ...What's gone wrong?

Even though the public interface for the RpgPlayer looks nice and tidy, the nightmare exists behind the scenes.

The RpgPlayer has far too much knowledge about what the items are capable of, though initially this may have seemed like a good place to
put the logic as the items are _mostly_ acting on the player.

## How to fix it?

Apply the Single Responsibility Principle to provide a better modularity and improve class cohesion. The classes in the new design should have less responsibilities. For that purpose, feel free to create new classes and achieve better separation of concerns over your classes. _Refer to the lecture and reading assignment to recall this design principle or learn more about it._

Then, in a last step, implement a Facade class as a way to provide client code a unique central point of access to those different functionalities. Your facade should be the frontline for providing access to any functionality implemented in the 'game'. The facade should be able to receive calls and then delegate calls to the proper objects that implement the desired functionalities. _Again, refer to the lecture and reading assignment to recall this design parttern or learn more about it._

## Turning it in

Before turning it in, make sure you followed instructions in the Getting Started section above. Your repo should be private and not a fork neither a clone of this one. If you haven't turned your repo private, please do it before turning it in on Canvas.

Add me to your private repo so I can access your code for grading purposes. My GH username is: **cpzielke**

Finally, access the corresponding assignment on Canvas and submit your repo URL.
