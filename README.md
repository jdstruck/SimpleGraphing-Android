# Simple Graphing - Android

Visualize sine wave. Change frequency and amplitude by swiping horizontally and vertically.

## Project Goals
For this implementation, my goals were as follows:
- [x] Make the app as quick and simple as possible
- [x] Populate UI dynamically based based on spinner selection.  

### Simple UX
To provide instant feedback in the UI, instead of listening for a button click to trigger the conversion, a TextWatcher is used to identify change in the input EditText field, and triggers the conversion process each time a number is entered. The conversion process runs for _all_ output units at once, rather than requiring the user to choose only one.

### Dynamic UI
The second goal was to avoid statically defining UI elements that depend on user selection, and instead create (inflate) and populate them dynamically based on an array of Strings corresponding to the unit type category.

The app works, and can be expanded without great effort to include additional unit categories. At the moment, conversion only works for Temperature; selection of Length in the unit category spinner will correctly populate the input unit spinner and inflate the output fields, but I have not yet had time to enter the formulas to make the conversion itself happen.

## Daily Log

* Sept 9
  * Add initial extended SurfaceView class

* Sept 3
  * Make lines appear on SurfaceView
    
* Sept 4
  * configure basic auto-convert onKeyUp
  * change layout again...
    
* Sept 5
  * implement basic spinner populated by Unit enum; add Kelvin and additional formulas; manually add output fields, but the others don't work yet
  * moved onKeyX listeners to addTextChangedListener on num_in, now auto convert/update works for all keys, including backspace/forward delete
    
* Sept 6 - 7
  * Begin working on array-based unit containers
  * minus sign no longer crashes app
  * update layout
  * Fix convert to work within parentLinearLayout loop to set each unit type
  * loop through output layouts IN PROGRESS
  * inflate output fields in loop
    
* Sept 8
  * Refactor main computation to Unit class, fix odds and ends
  * Remove toolbar, replace with full sized unit category spinner; other minor layout updates
  * Refactor to fix spinner configuration, mechanics now seem to be working
  * notifyDataSetChanged() not working on inputUnitArrayAdapter
  * still fixing null ptr TextView bug when changing unit category
commit 8c0283ec9bada996545d542c62817930ea21d2ae
Author: Jesse Struck <jdstruck@gmail.com>
Date:   Sun Sep 22 11:12:09 2019 -0400

    add TextViews for x,y,freq,amp

 .../com/example/myapplication/MainActivity.java    | 27 +++++++++-
 .../java/com/example/myapplication/MySurface.java  | 25 ++++++---
 app/src/main/res/layout/activity_main.xml          | 60 +++++++++++++++++++++-
 3 files changed, 101 insertions(+), 11 deletions(-)

commit 514cb81ce5038f3fe46e3b465aa8d6b8b5e8fc26
Author: Jesse Struck <jdstruck@gmail.com>
Date:   Fri Sep 20 10:26:29 2019 -0400

    add grid, setWillNotDraw(false)

 .../com/example/myapplication/MainActivity.java    | 84 ++--------------------
 .../java/com/example/myapplication/MySurface.java  | 46 ++++++------
 2 files changed, 28 insertions(+), 102 deletions(-)

commit b0b0c2a2b93b328de39395cf9545729692f0416b
Author: Jesse Struck <jdstruck@gmail.com>
Date:   Thu Sep 19 21:54:03 2019 -0400

    minor refactor

 app/src/main/java/com/example/myapplication/MySurface.java | 8 ++++----
 1 file changed, 4 insertions(+), 4 deletions(-)

commit add1bb48bddb21bc5fcbd691241611683f85dd9d
Author: Jesse Struck <jdstruck@gmail.com>
Date:   Thu Sep 19 21:34:29 2019 -0400

    fix startY to stay in center (in portrait mode only)

 .../java/com/example/myapplication/MainActivity.java    |  4 ++--
 .../main/java/com/example/myapplication/MySurface.java  | 17 +++++++++++------
 2 files changed, 13 insertions(+), 8 deletions(-)

commit 1875ae47acb1a2c2f6e179fdb9a9cfa65fec3719
Author: Jesse Struck <jdstruck@gmail.com>
Date:   Thu Sep 19 21:08:10 2019 -0400

    adjust frequency with x, amplitude with y

 .../com/example/myapplication/MainActivity.java    | 51 +++++++++++++---------
 .../java/com/example/myapplication/MySurface.java  | 26 ++++++-----
 app/src/main/res/layout/coord_text_view.xml        | 11 +++++
 3 files changed, 57 insertions(+), 31 deletions(-)

commit 2a72a4a48306111081bfe62ac24bfb6693349c6a
Author: Jesse Struck <jdstruck@gmail.com>
Date:   Thu Sep 19 19:11:22 2019 -0400

    wave responds to touch and seek

 .../com/example/myapplication/MainActivity.java    | 53 +++++++++++++++-----
 .../java/com/example/myapplication/MySurface.java  | 56 ++++++++++------------
 2 files changed, 67 insertions(+), 42 deletions(-)

commit 5201edf08dc82b57f28c3119ca2ab704e4a12ed5
Author: Jesse Struck <jdstruck@gmail.com>
Date:   Wed Sep 18 17:03:23 2019 -0400

    more audio waveform experimentation

 .../com/example/myapplication/MainActivity.java    | 72 ++++++++++++++++++++--
 .../java/com/example/myapplication/MySurface.java  | 33 +++++-----
 .../com/example/myapplication/WaveFormView.java    | 42 +++++++++++++
 app/src/main/res/layout/activity_main.xml          | 15 ++---
 app/src/main/res/layout/seekbar.xml                | 10 +++
 5 files changed, 145 insertions(+), 27 deletions(-)

commit bc99e13deac41183903cb0c0b57d646714c79d49
Author: Jesse Struck <jdstruck@gmail.com>
Date:   Tue Sep 17 13:42:58 2019 -0400

    work on updating graph with sound playing information

 .idea/.name                                        |   1 +
 .idea/codeStyles/Project.xml                       | 116 +++++++++++++++++++
 .idea/gradle.xml                                   |  16 +++
 .idea/misc.xml                                     |   9 ++
 .idea/runConfigurations.xml                        |  12 ++
 .idea/vcs.xml                                      |   6 +
 app/build.gradle                                   |   2 +-
 .../com/example/myapplication/MainActivity.java    |   3 +
 .../java/com/example/myapplication/MySurface.java  |  56 +++++++---
 .../com/example/myapplication/WaveFormView.java    | 124 +++++++++++++++++++++
 10 files changed, 331 insertions(+), 14 deletions(-)

commit aa8f062ad33eb39cae5c5b2489ba61b90128444c
Author: Jesse Struck <jdstruck@gmail.com>
Date:   Tue Sep 17 10:55:16 2019 -0400

    test sine wave

 .../main/java/com/example/myapplication/MySurface.java   | 16 +++++++++++++---
 1 file changed, 13 insertions(+), 3 deletions(-)

commit 44e2852af2dde4b50e1428ec9f37dc060fd988db
Author: Jesse Struck <jdstruck@gmail.com>
Date:   Tue Sep 17 10:42:24 2019 -0400

    smooth out graph function

 app/src/main/java/com/example/myapplication/MySurface.java | 9 +++++----
 1 file changed, 5 insertions(+), 4 deletions(-)

commit a160de8d7fc03b3c63aca4117f80e9269d4e9e4e
Author: Jesse Struck <jdstruck@gmail.com>
Date:   Mon Sep 16 10:39:23 2019 -0400

    experimenting

 .../java/com/example/myapplication/MySurface.java  | 62 +++++++---------------
 1 file changed, 20 insertions(+), 42 deletions(-)

commit 74d54da64fd948d7c1d19b0f36f1e43f24942cbc
Author: Jesse Struck <jdstruck@gmail.com>
Date:   Mon Sep 16 09:44:22 2019 -0400

    graph loop

 .../java/com/example/myapplication/MySurface.java  | 33 ++++++++++++++--------
 1 file changed, 22 insertions(+), 11 deletions(-)

commit ab0319deacce224ff9c90e9240124c6e686822f4
Author: Jesse Struck <jdstruck@gmail.com>
Date:   Fri Sep 13 10:52:38 2019 -0400

    attempt drawing a graph

 app/src/main/java/com/example/myapplication/MainActivity.java | 3 +++
 app/src/main/java/com/example/myapplication/MySurface.java    | 4 ++++
 2 files changed, 7 insertions(+)

commit 202148b0de2071b357e3b81ce64645584f1698fa
Author: Jesse Struck <jdstruck@gmail.com>
Date:   Fri Sep 13 10:42:30 2019 -0400

    attempt drawing a graph

 .../java/com/example/myapplication/MySurface.java  | 38 +++++++++++++++++++---
 1 file changed, 34 insertions(+), 4 deletions(-)

commit 08594b5cc184fed7aa8ac23c418fa648a539a31f
Author: Jesse Struck <jdstruck@gmail.com>
Date:   Fri Sep 13 09:29:28 2019 -0400

    play around with paint properties

 app/src/main/java/com/example/myapplication/MainActivity.java | 10 ++++++++++
 app/src/main/java/com/example/myapplication/MySurface.java    | 10 ++++++----
 2 files changed, 16 insertions(+), 4 deletions(-)

commit a6ca8712de92f6be86ae28d692d4dd5b8106326f
Author: Jesse Struck <jdstruck@gmail.com>
Date:   Wed Sep 11 10:26:30 2019 -0400

    add circle

 app/src/main/java/com/example/myapplication/MySurface.java | 3 ++-
 1 file changed, 2 insertions(+), 1 deletion(-)

commit ae6a393a7846577f644d16f6a856140b6eb851b4
Author: Jesse Struck <jdstruck@gmail.com>
Date:   Wed Sep 11 10:23:13 2019 -0400

    Make lines appear on SurfaceView


