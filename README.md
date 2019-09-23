# Simple Graphing - Android

Visualize sine wave. Change frequency and amplitude by swiping horizontally and vertically.

## Project Goals
For this implementation, my goals were as follows:
- [x] Visualize sine wave, manipulate parameters with onTouch
- [] Play audio sample stored in buffer

Originally I'd intended to not only enable dynamic manipulation of the visual representation of the sine wave, but play the same sample using an AudioTrack object.

I was able to initially play the audio, but not in sync with the visuals, so I dropped the audio in an effort to make the visual representation smooth and reactive.

As a continued project, I would like to incorporate the audio back into the app.

## Daily Log

* Sept 9
  * Add initial extended SurfaceView class

* Sept 11
  * Make lines appear on SurfaceView
    
* Sept 13
  * Continue playing around with SurfaceView and prof's android-surface example
    
* Sept 16
  * Try graphing more than one point by putting canvas.drawLine in loop over array points
    
* Sept 17
  * Test out sine function, play around with drawing on SurfaceView
  * Successful attempt to play sine wave as audio
    
* Sept 18
  * More experimentation with audio, but eventually scrap that to focus on sine visualization

* Sept 19
  * Work on using onTouch to adjust sine parameters with finger drag
  * x axis adjusts frequency, y axis adjusts amplitude

* Sept 20
  * Fix initial view not displaying by activating onDraw with setWillNotDraw(false) when initializing object in MainActivity

* Sept 22
  * Add text boxes to display x, y coords, plus frequency and amplitude